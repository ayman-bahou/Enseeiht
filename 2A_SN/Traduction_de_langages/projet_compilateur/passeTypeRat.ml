(* Module de la passe de gestion des types *)
(* doit être conforme à l'interface Passe *)
open Tds
open Exceptions
open Ast
open Type
type t1 = Ast.AstTds.programme
type t2 = Ast.AstType.programme

(* analyse_type_expression : AstTds.expression -> AstType.expression *)
(* Paramètre e : l'expression à analyser *)
(* Vérifie le bon typage et tranforme l'expression
en une expression de type AstType.expression *)
(* Erreur si mauvaise utilisation des identifiants *)
let rec analyse_type_expression e = match e with 
  | AstTds.Ident info->
        begin
          match info_ast_to_info info with
          |InfoVar(_,t,_,_)->(AstType.Ident info,t)
          |InfoFun(_,t,_)->(AstType.Ident info,t)
          |InfoConst(_,_)->(AstType.Ident info,Int)
        end
  | AstTds.Binaire (op,e1,e2) ->
      (* Vérification de le bon typage dans les expressions *)
      (* et obtention des types et des expressions transformées *)
      let (ne1,t1) = analyse_type_expression e1 in
      let (ne2,t2) = analyse_type_expression e2 in
      begin
      (* Vérification de la cohérence entre les types et l'op binaire et retour du nouveau Binaire avec le type de l'expression *)
      match (op,t1,t2) with
      |(Fraction,Int,Int)->(AstType.Binaire (Fraction, ne1, ne2),Rat)
      |(Plus,Int,Int)->(AstType.Binaire (PlusInt, ne1, ne2),Int)
      |(Plus,Rat,Rat)->(AstType.Binaire (PlusRat, ne1, ne2),Rat)
      |(Mult,Int,Int)->(AstType.Binaire (MultInt, ne1, ne2),Int)
      |(Mult,Rat,Rat)->(AstType.Binaire (MultRat, ne1, ne2),Rat)
      |(Equ,Int,Int) ->(AstType.Binaire (EquInt, ne1, ne2),Bool)
      |(Equ,Bool,Bool)->(AstType.Binaire (EquBool, ne1, ne2),Bool)
      |(Inf,Int,Int)->(AstType.Binaire (Inf, ne1, ne2),Bool)
      |_->raise (TypeBinaireInattendu (op, t1, t2))
      end
  | AstTds.Unaire (op,e1) ->
      (* Vérification du bon typage dans l'expression *)
      (* et obtention de l'expression transformée et son type*)
      let (ne1,t1) = analyse_type_expression e1 in
      (* Renvoie du nouveau unaire et son type où l'expression a été remplacée par l'expression issue de l'analyse *)
      begin
      match (op,t1) with
      |(Numerateur,Rat)->(AstType.Unaire(Numerateur,ne1),Int)
      |(Denominateur,Rat)->(AstType.Unaire(Denominateur,ne1),Int)
      |_->raise (TypeInattendu(Rat,t1))
      end
  | AstTds.Booleen b -> (AstType.Booleen b,Bool)
  | AstTds.Entier i -> (AstType.Entier i,Int)
  | AstTds.AppelFonction (n,le) ->
      begin
        match chercherGlobalement tds n with
        | None ->
          (* L'identifiant n'est pas trouvé dans la tds globale. *)
          raise (IdentifiantNonDeclare n)
        | Some info ->
          (* L'identifiant est trouvé dans la tds globale,
          il a donc déjà été déclaré. L'information associée est récupérée. *)
          begin
            match info_ast_to_info info with
            | InfoFun _ ->
              (* Vérification de la bonne utilisation des identifiants dans les expressions *)
              (* et obtention des expressions transformées *)
              let nle = List.map (analyse_tds_expression tds) le in
              (* Renvoie du nouvel appel de fonction où le nom a été remplacé par l'information
              et les expressions remplacées par les expressions issues de l'analyse *)
              AstTds.AppelFonction (info, nle)
            | _ ->
              (* Appel d'une variable ou d'une constante *)
              raise (MauvaiseUtilisationIdentifiant n)
          end
      end
  

(* analyse_tds_instruction :info_ast option -> AstSyntax.instruction -> AstTds.instruction *)
(* Paramètre oia : None si l'instruction i est dans le bloc principal,
                   Some ia où ia est l'information associée à la fonction dans laquelle est l'instruction i sinon *)
(* Paramètre i : l'instruction à analyser *)
(* Vérifie le bon typage et tranforme l'instruction AstTds.instruction
en une instruction de type AstType.instruction *)
(* Erreur si mauvaise utilisation des identifiants *)
let rec analyse_type_instruction i =
  match i with
  | AstTds.Declaration (t, info, e) ->begin
      let (ne,te)=analyse_type_expression e in
    if est_compatible t te then 
      begin
    (modifier_type_variable t info);
    AstType.Declaration(info,ne)
      end
    else raise (TypeInattendu(t,te))
  end
  | AstTds.Affectation (info,e) ->
      let (ne,te)=analyse_type_expression e in 
      begin
        match (info_ast_to_info) info with
        |InfoVar(_,t,_,_)->if est_compatible t te then AstType.Affectation(info,ne) else raise  (TypeInattendu(t,te))
        |InfoConst(n,_)->raise (MauvaiseUtilisationIdentifiant n)
        |InfoFun(n,_,_)->raise (MauvaiseUtilisationIdentifiant n)
      end
  | Empty->
      Empty
  | AstTds.Affichage e ->
      (* Vérification de la bonne utilisation des identifiants dans l'expression *)
      (* et obtention de l'expression transformée *)
      let (ne,te) = analyse_type_expression e in
      begin
        match te with
        |Int->AstType.AffichageInt(ne)
        |Bool->AstType.AffichageBool(ne)
        |Rat->AstType.AffichageRat(ne)
        |_->failwith "type inattendu"
      end
      (* Renvoie du nouvel affichage où l'expression remplacée par l'expression issue de l'analyse *)
      
  | AstTds.Conditionnelle (c,t,e) -> 
      (* Analyse du bloc then *)
      let tast = analyse_type_bloc t in
      (* Analyse du bloc else *)
      let east = analyse_type_bloc e in
       (* Analyse de la condition *)
      let (nc,t)=analyse_type_expression c in
      if est_compatible t Bool then 
      (* Renvoie la nouvelle structure de la conditionnelle *)
      AstType.Conditionnelle (nc, tast, east)
      else raise TypeInattendu(t, Bool) 
  | AstTds.TantQue (c,b) ->
      (* Analyse de la condition *)
      let (nc,t)=analyse_type_expression c in
      if est_compatible t Bool then 
      (* Analyse du bloc *)
      let bl = analyse_tds_bloc b in
      (* Renvoie la nouvelle structure de la boucle *)
      AstType.TantQue (nc, bl)
  | AstTds.Retour (e,info) ->let (ne,te)=analyse_type_expression e in
    begin 
      match info_ast_to_info info with
      |InfoFun(_,tr,_)->if est_compatible tr te then AstType.Retour(ne,info) else raise (TypeInattendu(tr,te))
      |InfoVar(n,_,_,_)->raise (MauvaiseUtilisationIdentifiant n)
      |InfoConst(n,_)->raise (MauvaiseUtilisationIdentifiant n)
      end
     

(* analyse_tds_bloc : tds -> info_ast option -> AstSyntax.bloc -> AstTds.bloc *)
(* Paramètre tds : la table des symboles courante *)
(* Paramètre oia : None si le bloc li est dans le programme principal,
                   Some ia où ia est l'information associée à la fonction dans laquelle est le bloc li sinon *)
(* Paramètre li : liste d'instructions à analyser *)
(* Vérifie la bonne utilisation des identifiants et tranforme le bloc en un bloc de type AstTds.bloc *)
(* Erreur si mauvaise utilisation des identifiants *)
and analyse_tds_bloc tds oia li =
  (* Entrée dans un nouveau bloc, donc création d'une nouvelle tds locale
  pointant sur la table du bloc parent *)
  let tdsbloc = creerTDSFille tds in
  (* Analyse des instructions du bloc avec la tds du nouveau bloc.
     Cette tds est modifiée par effet de bord *)
   let nli = List.map (analyse_tds_instruction tdsbloc oia) li in
   (* afficher_locale tdsbloc ; *) (* décommenter pour afficher la table locale *)
   nli


(*traiter_param_fonction : tds->string->unit*)
(*param tds : table de symboles locale aux variables*)
(*param v : variable à traiter*)
(*traiter chaque paramètre de la fonction en le cherchant dans la tds locale et on l'ajoute s'il n'est pas déclaré*)
let traiter_param_fonction tds v=
match chercherLocalement tds v with
|None-> ajouter tds v (info_to_info_ast (InfoVar (v,Undefined, 0, "")))
|Some _->raise (DoubleDeclaration v)
(*traite_fonction : tds->AstSyntax.fonction->AstTds.fonction*)
(*param maintds : table de symbole courante*)
(* Paramètre : la fonction à analyser *)
(*tranforme la fonction en une fonction de type AstTds.fonction*)
let traite_fonction maintds (AstSyntax.Fonction(t,n,lp,li)) =
let ltype=List.map(fst) lp in let lvariable=List.map(snd) lp in
let infor=info_to_info_ast (InfoFun(n,t,ltype)) in
(*ajout de la fonction à la tds*)
ajouter maintds n  infor;
(*création de tds fille pour ajouter les paramètres dedans *)
let tdsbloc = creerTDSFille maintds in
(*traitement des paramètres*)
let l=List.map(fun v-> traiter_param_fonction tdsbloc v )(lvariable) in
(*analyse de la liste d'instruction(bloc)*)
let nli=List.map (analyse_tds_instruction tdsbloc (Some infor) ) li in
(*creation de la nouvelle liste de paramètres en remplacant les variables par des pointeurs*)
let nlp=List.map(fun (t,v)->(t,info_to_info_ast (InfoVar(v,Undefined, 0, "")))) lp in 
AstTds.Fonction(t,infor,nlp,nli)


(* analyse_tds_fonction : tds -> AstSyntax.fonction -> AstTds.fonction *)
(* Paramètre tds : la table des symboles courante *)
(* Paramètre : la fonction à analyser *)
(* Vérifie la bonne utilisation des identifiants et tranforme la fonction
en une fonction de type AstTds.fonction *)
(* Erreur si mauvaise utilisation des identifiants *)
let analyse_tds_fonction maintds (AstSyntax.Fonction(t,n,lp,li))  = 
begin
match chercherLocalement maintds n with
|None-> traite_fonction maintds (AstSyntax.Fonction(t,n,lp,li))
|Some info-> 
  begin
  match info_ast_to_info info with
  |InfoFun(_,_,ltyp)->if (est_compatible_list ltyp (List.map(fst) lp)) then raise (DoubleDeclaration n) 
  else traite_fonction maintds (AstSyntax.Fonction(t,n,lp,li))
  |_->raise (MauvaiseUtilisationIdentifiant n)
end
end
(* analyser : AstSyntax.programme -> AstTds.programme *)
(* Paramètre : le programme à analyser *)
(* Vérifie la bonne utilisation des identifiants et tranforme le programme
en un programme de type AstTds.programme *)
(* Erreur si mauvaise utilisation des identifiants *)
let analyser (AstSyntax.Programme (fonctions,prog)) =
  let tds = creerTDSMere () in
  let nf = List.map (analyse_tds_fonction tds) fonctions in
  let nb = analyse_tds_bloc tds None prog in
  AstTds.Programme (nf,nb)
