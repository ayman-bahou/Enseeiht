(* Module de la passe de gestion de placement *)
(* doit être conforme à l'interface Passe *)

open Tds
open Ast
open Type

type t1 = Ast.AstType.programme
type t2 = Ast.AstPlacement.programme

(* analyse_type_expression : AstTds.expression -> AstType.expression *)
(* Paramètre e : l'expression à analyser *)
(* Vérifie le bon typage et tranforme l'expression
en une expression de type AstType.expression *)
(* Erreur si mauvaise utilisation des identifiants *)
let rec analyse_placement_instruction i depl reg = 
  begin 
    match i with
    |AstType.Declaration(info,e)->modifier_adresse_variable depl reg info;
    begin
    match info_ast_to_info info with
    |InfoVar(_,t,_,_)->(AstPlacement.Declaration(info,e),getTaille t)
    |_->failwith "erreur Declaration"
    end
    |AstType.Affectation(info,e)->(AstPlacement.Affectation(info,e),0)
    |AstType.Empty->(AstPlacement.Empty,0)
    |AstType.AffichageInt(e)->(AstPlacement.AffichageInt(e),0)
    |AstType.AffichageBool(e)->(AstPlacement.AffichageBool(e),0)
    |AstType.AffichageRat(e)->(AstPlacement.AffichageRat(e),0)
    |AstType.Conditionnelle(e,b1,b2)->(AstPlacement.Conditionnelle(e,(analyse_placement_bloc b1 depl reg),(analyse_placement_bloc b2 depl reg)),0)
    |AstType.TantQue(e,b)->(AstPlacement.TantQue(e,(analyse_placement_bloc b depl reg)),0)
    |AstType.Retour(e,info)->
      begin
      match info_ast_to_info info with
      |InfoFun(_,tr,tl)->let taille_param=List.fold_right(fun t sq->(getTaille t)+sq ) tl 0 in   (AstPlacement.Retour(e,getTaille tr,taille_param),0)
      |_->failwith "erreur Retour"
      end
  end

and analyse_placement_bloc b depl reg = match b with 
|[]->([],0)
|i::q->let (ni,ti)=analyse_placement_instruction i depl reg in let (nq,tq)=analyse_placement_bloc q (depl+ti) reg in (ni::nq,ti+tq)

let analyse_placement_fonction (AstType.Fonction(info,lp,li))=let auxi i d=
begin
match info_ast_to_info i with
|InfoVar(_,t,_,_)->modifier_adresse_variable (d-getTaille t) "LB" i ; (d-getTaille t)
|_->failwith "erreur info"
end
in let _=List.fold_right(auxi) lp 0 in let nb=analyse_placement_bloc li 3 "LB" in AstPlacement.Fonction(info,lp,nb)


let analyser (AstType.Programme (fonctions,prog)) =
  let nf = List.map (analyse_placement_fonction) fonctions in
  let nb = analyse_placement_bloc prog 0 "SB" in
  AstPlacement.Programme (nf,nb)