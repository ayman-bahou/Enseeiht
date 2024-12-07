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
          |_->failwith "erreur"
        end
  | AstTds.Binaire (op,e1,e2) ->
      (* Vérification de le bon typage dans les expressions *)
      (* et obtention des types et des expressions transformées *)
      let (ne1,t1) = analyse_type_expression e1 in
      let (ne2,t2) = analyse_type_expression e2 in
      begin
        match (op,t1,t2) with
        |(Plus,Int,Int)->(AstType.Binaire(PlusInt,ne1,ne2),Int)
        |(Plus,Rat,Rat)->(AstType.Binaire(PlusRat,ne1,ne2),Rat)
        |(Fraction,Int,Int)->(AstType.Binaire(Fraction,ne1,ne2),Rat)
        |(Mult,Int,Int)->(AstType.Binaire(MultInt,ne1,ne2),Int)
        |(Mult,Rat,Rat)->(AstType.Binaire(MultRat,ne1,ne2),Rat)
        |(Equ,Int,Int)->(AstType.Binaire(EquInt,ne1,ne2),Bool)
        |(Equ,Bool,Bool)->(AstType.Binaire(EquBool,ne1,ne2),Bool)
        |(Inf,Int,Int)->(AstType.Binaire(Inf,ne1,ne2),Bool)
        |_->raise (TypeBinaireInattendu(op,t1,t2))
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
      |_->raise (TypeInattendu(t1,Rat))
      end
  | AstTds.Booleen b -> (AstType.Booleen b,Bool)
  | AstTds.Entier i -> (AstType.Entier i,Int)
  | AstTds.AppelFonction (info,le) ->
    
      let liste_analyse_arg=List.map(analyse_type_expression) le in let liste_type_arg=List.map(snd) liste_analyse_arg in
      begin
      match info_ast_to_info info with
      |InfoFun(_,tr,lt)->if est_compatible_list lt liste_type_arg then let nle=List.map(fst) liste_analyse_arg in (AstType.AppelFonction(info,nle),tr)
      else raise (TypesParametresInattendus(liste_type_arg,lt))
      |_->failwith "erreur"
      
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
  | AstTds.Declaration (t, info, e) ->
      let (ne,te)=analyse_type_expression e in
      (modifier_type_variable t info);
    if est_compatible t te then 
      
    AstType.Declaration(info,ne)
      
    else raise (TypeInattendu(te,t))
  
  | AstTds.Affectation (info,e) ->
      let (ne,te)=analyse_type_expression e in 
      begin
        match (info_ast_to_info) info with
        |InfoVar(_,t,_,_)->if est_compatible t te then AstType.Affectation(info,ne) else raise  (TypeInattendu(te,t))
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
      let (nc,t1)=analyse_type_expression c in
      if est_compatible t1 Bool then 
      (* Renvoie la nouvelle structure de la conditionnelle *)
      AstType.Conditionnelle (nc, tast, east)
      else raise (TypeInattendu(t1, Bool))
  | AstTds.TantQue (c,b) ->
      (* Analyse de la condition *)
      let (nc,t)=analyse_type_expression c in
      if est_compatible t Bool then 
      (* Analyse du bloc *)
      let bl = analyse_type_bloc b in
      (* Renvoie la nouvelle structure de la boucle *)
      AstType.TantQue (nc, bl) else raise (TypeInattendu(t, Bool))
  | AstTds.Retour (e,info) ->let (ne,te)=analyse_type_expression e in
    begin 
      match info_ast_to_info info with
      |InfoFun(_,tr,_)->if est_compatible tr te then AstType.Retour(ne,info) else raise (TypeInattendu(te,tr))
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
and analyse_type_bloc li =
  List.map(analyse_type_instruction) li




(* analyse_tds_fonction : tds -> AstSyntax.fonction -> AstTds.fonction *)
(* Paramètre tds : la table des symboles courante *)
(* Paramètre : la fonction à analyser *)
(* Vérifie la bonne utilisation des identifiants et tranforme la fonction
en une fonction de type AstTds.fonction *)
(* Erreur si mauvaise utilisation des identifiants *)
let analyse_type_fonction (AstTds.Fonction(t,info,lp,li))  = 

  let tp=List.map(fst) lp in
  modifier_type_fonction t tp info;
  let lst=List.map(fun (t,v)-> modifier_type_variable t v; v) lp in
  let nli=analyse_type_bloc li in AstType.Fonction(info,lst,nli)


(* analyser : AstSyntax.programme -> AstTds.programme *)
(* Paramètre : le programme à analyser *)
(* Vérifie la bonne utilisation des identifiants et tranforme le programme
en un programme de type AstTds.programme *)
(* Erreur si mauvaise utilisation des identifiants *)
let analyser (AstTds.Programme (fonctions,prog)) =
  let nf = List.map (analyse_type_fonction) fonctions in
  let nb = analyse_type_bloc prog in
  AstType.Programme (nf,nb)
