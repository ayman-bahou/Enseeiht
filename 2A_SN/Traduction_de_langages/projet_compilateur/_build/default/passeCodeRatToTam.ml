(*(* Module de la passe de génération de code *)
(* doit être conforme à l'interface Passe *)

open Tds
open Ast
open Type
open Tam
open Code

type t1 = Ast.AstPlacement.programme
type t2 = string


(* analyse_code_affectable : AstType.affectable -> bool -> string *)
(* Paramètre aff : l'affectable à analyser *)
(* Paramètre modifiable : est-que l'affectable est modifiable *)
(* Analyser le code RAT d'un affectable et le transformer en code TAM *)
(* Erreur si mauvaise utilisation des identifiants *)

let  analyse_code_affectable a modifiable =
 match a with
 | AstType.Ident ia ->
 begin
 match info_ast_to_info ia with
 | InfoVar (_, t, dep, reg) -> if modifiable
 then store (getTaille t) dep reg 
 else load (getTaille t) dep reg
 | InfoConst (_,i) -> loadl_int i
 | _ -> failwith "erreur"
 end


(* analyse_code_expression : AstPlacement.expression -> string *)
(* Paramètre e : l'expression à analyser *)
(* Analyser le code RAT d'une expression et le transforme en code TAM *)
(*Erreur si mauvais code (mauvais matching)*)

let rec analyse_code_expression e= 
begin
match e with
| AstType.AppelFonction (info,le) -> 
 let analyse_param=List.fold_left(fun acc x->acc^(analyse_code_expression x)) "" le in 
 begin 
 match info_ast_to_info info with
 |InfoFun(n,_,_)->analyse_param^(call ("SB") n)
 |_->failwith "erreur"
 end

| Booleen b-> if b then loadl_int 1 else loadl_int 0
| Entier n-> loadl_int n
| Unaire (op,e) ->let ne=analyse_code_expression e in 
begin
 match op with
 |Numerateur -> ne^pop (0) 1
 |Denominateur ->ne^pop (1) 1
end
| Binaire (op, e1, e2)->let analyse_e1=analyse_code_expression e1 in let analyse_e2=analyse_code_expression e2 in 
begin
 analyse_e1^analyse_e2^
 match op with 
 |Fraction->call ("SB") "norm"
 |PlusInt->subr "IAdd"
 |PlusRat->call ("SB") "RAdd"
 |MultInt->subr "IMul"
 |MultRat->call ("SB") "RMul"
 |EquInt->subr "IEq"
 |EquBool->subr "IEq"
 |Inf->subr "ILss"

end
| AstType.Affectable a -> analyse_code_affectable a false


(* analyse_code_instruction : AstPlacement.instruction -> string *)
(* Paramètre i : l'instruction à analyser *)
(* Analyser le code RAT d'une instruction et le transforme en code TAM *)
(*Erreur si mauvais code (mauvais matching)*)

end
let rec analyse_code_instruction i = 
 begin
 match i with
 |AstPlacement.Declaration(info,e)->begin 
 match info_ast_to_info info with
 |InfoVar(_,t,depl,reg)->let p=(push (getTaille t) ) in let ne=analyse_code_expression e in p^ne^(store (getTaille t) depl reg)
 |_->failwith "error"
 end
 | AstPlacement.Affectation(info,e)-> (analyse_code_expression e)^(analyse_code_affectable info true)

 | AffichageInt(e)->let ne=analyse_code_expression e in ne^subr "IOut"
 | AffichageRat(e)->let ne=analyse_code_expression e in ne ^call ("SB") "ROut"
 | AffichageBool(e)-> let ne=analyse_code_expression e in ne^(subr "BOut")
 | Conditionnelle(e,b1,b2)->let ne=analyse_code_expression e in let sinon=getEtiquette() in let finsi=getEtiquette() in ne^(jumpif 0 sinon) ^(analyse_code_bloc b1)^(jump finsi)^sinon^"\n"^(analyse_code_bloc b2)^finsi^"\n"
 | TantQue(e,b)-> let ne=analyse_code_expression e in let tq=getEtiquette() in let fintq=getEtiquette() in tq^"\n"^ne^jumpif (0) fintq^(analyse_code_bloc b)^jump tq^fintq^"\n"
 | Retour(e,tr,tp)->let ne=analyse_code_expression e in ne^(return (tr) tp)
 | AstPlacement.Empty->""
 end

 and analyse_code_bloc (li,taille_b)= let analyse_instr=List.fold_left(fun acc x -> acc^(analyse_code_instruction x)) "" li in analyse_instr^(pop (0) taille_b)

 let analyse_code_fonction (AstPlacement.Fonction(info,_,(li,_)))=
 begin
 match info_ast_to_info info with
 |InfoFun(n,_,_)->n^"\n"^(List.fold_left(fun acc x->acc^(analyse_code_instruction x)) "" li)^halt
 |_->failwith ""
 end



 let analyser (AstPlacement.Programme(fonctions,(li,tb)))=
 getEntete()
 ^ List.fold_left(fun acc x -> acc^(analyse_code_fonction x)) "" fonctions
 ^ "main\n"
 ^ analyse_code_bloc (li,tb)
 ^ halt*)