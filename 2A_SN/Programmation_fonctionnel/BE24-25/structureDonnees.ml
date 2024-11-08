(* Pour les tests *)
(* [eq_perm l l'] retourne true ssi [l] et [l']
   sont égales à à permutation près (pour (=)).
   [l'] ne doit pas contenir de doublon. *)
let eq_perm l l' =
  List.length l = List.length l' && List.for_all (fun x -> List.mem x l) l'


module type StructureDonnees =
sig

  (* Type permettant de stocker le dictionnaire *)
  type dico

  (* Dictionnaire vide *)
  val empty : dico

  (* Ajoute un mot et son encodage au dictionnaire *)
  (* premier parametre : l'encodage du mot *)
  (* deuxième paramètre : le mot *)
  (* troisième paramètre : le dictionnaire *)
  val ajouter : int list -> string -> dico -> dico

  (* Cherche tous les mots associés à un encodage dans un dictionnaire *)
  (* premier parametre : l'encodage du mot *)
  (* second paramètre : le dictionnaire *)
  val chercher : int list -> dico -> string list


  (* Calcule le nombre maximum de mots ayant le même encodage dans un
     dictionnaire *)
  (* paramètre : le dictionnaire *)
  val max_mots_code_identique : dico -> int

  (* Liste tous les mots d'un dictionnaire dont un prefixe de l'encodage est donné en paramètre *)
  (* premier paramètre : le prefixe de l'encodage *)
  (* second paramètre : le dictionnaire *)
  val prefixe : int list -> dico -> string list

end

module ListAssoc:StructureDonnees = struct
  type dico=( (int list) * (string list) ) list
  let empty=[]
  let rec ajouter lst str dic=match dic with 
                          |[]->[lst,[str]]
                          |(c,v)::q->if c=lst then ((c,v@[str])::q) else (c,v)::(ajouter lst str q)
  let rec chercher lst dic=match dic with 
                      |[]-> []
                      |(c,v)::q->if c=lst then v else (chercher lst q)
  let max_mots_code_identique dic=List.fold_right(fun (lst,lstr) rq->if (List.length lstr)>rq then (List.length lstr) else rq ) dic 0

  (*fonction auxiliaire pour déterminer si pour deux listes l'une est préfixe de l'autre*)
  (*Signature : 'a list -> 'a list -> bool*)
  (* Param : -liste1  -liste2*)
  (*Result : l'une est préfixe de l'autre*)
  let rec est_pref l1 l2=match l1,l2 with
  |[],_->true
  |_,[]->true
  |t1::q1,t2::q2->if t1=t2 then (est_pref q1 q2) else false

  let prefixe lst dic=List.fold_right(fun (c,v) rq-> if est_pref c lst then v@rq else rq) dic []

  let%test _ = eq_perm (chercher [2;2] [([2;2],["bb";"aa";"cc"]); ([2;7;3;3],["bref"]);([2;6;6],["bon"])]) ["bb";"aa";"cc"]
  let%test _ = eq_perm (chercher [3;3] [([2;2],["bb";"aa";"cc"]); ([2;7;3;3],["bref"]);([2;6;6],["bon"])]) []
  let%test _ = eq_perm (chercher [2;7;3;3] [([2;2],["bb";"aa";"cc"]); ([2;7;3;3],["bref"]);([2;6;6],["bon"])]) ["bref"]
  let%test _ = eq_perm (chercher [2;6;6] [([2;2],["bb";"aa";"cc"]); ([2;7;3;3],["bref"]);([2;6;6],["bon"])]) ["bon"]
  let%test _ = eq_perm (chercher [2;6;6] []) []


  let%test _ = max_mots_code_identique [([2;2],["bb";"aa";"cc"]); ([2;7;3;3],["bref"]);([2;6;6],["bon"])] = 3
  let%test _ = max_mots_code_identique [([2;7;3;3],["bref"]);([2;2],["bb";"aa";"cc"]); ([2;6;6],["bon"])] = 3
  let%test _ = max_mots_code_identique [] = 0
  let%test _ = max_mots_code_identique [([2;7;3;3],["bref"]);([2;2],["bb"]); ([2;6;6],["bon"])] = 1

  let%test _ = eq_perm (prefixe [] [([2;2],["bb";"aa";"cc"]); ([2;7;3;3],["bref"]);([2;6;6],["bon"])]) ["bb";"aa";"cc";"bref";"bon"]
  let%test _ = eq_perm (prefixe [] [([2;7;3;3],["bref"]);([2;2],["bb";"aa";"cc"]); ([2;6;6],["bon"])]) ["bref";"bb";"aa";"cc";"bon"]
  let%test _ = eq_perm (prefixe [] []) []
  let%test _ = eq_perm (prefixe [] [([2;7;3;3],["bref"]);([2;2],["bb"]); ([2;6;6],["bon"])]) ["bref";"bb";"bon"]
  let%test _ = eq_perm (prefixe [2] [([2;2],["bb";"aa";"cc"]); ([2;7;3;3],["bref"]);([2;6;6],["bon"])]) ["bb";"aa";"cc";"bref";"bon"]
  let%test _ = eq_perm (prefixe [2;2] [([2;2],["bb";"aa";"cc"]); ([2;7;3;3],["bref"]);([2;6;6],["bon"])]) ["bb";"aa";"cc"]
  let%test _ = eq_perm (prefixe [2;2] [([2;2],["bb";"aa";"cc"]); ([2;7;3;3],["bref"]);([2;2;2],["bac";"bab"]);([2;6;6],["bon"])]) ["bb";"aa";"cc";"bac";"bab"]

  let%test _ =  (ajouter [2;2] "test1" [([2;2],["bb";"aa";"cc"]); ([2;7;3;3],["bref"]);([2;6;6],["bon"])])= [([2;2],["bb";"aa";"cc";"test1"]); ([2;7;3;3],["bref"]);([2;6;6],["bon"])]
  let%test _ = eq_perm (ajouter [2;1] "test2" [([2;2],["bb";"aa";"cc"]); ([2;7;3;3],["bref"]);([2;6;6],["bon"])]) [([2;1],["test2"]);([2;2],["bb";"aa";"cc"]); ([2;7;3;3],["bref"]);([2;6;6],["bon"])]
  let%test _ = ajouter [2;1] "test3" []= [([2;1],["test3"])]
  let%test _ = eq_perm (ajouter [] "test4" [([2;2],["bb";"aa";"cc"]); ([2;7;3;3],["bref"]);([2;6;6],["bon"])]) [([],["test4"]);([2;2],["bb";"aa";"cc"]); ([2;7;3;3],["bref"]);([2;6;6],["bon"])]
end

module Arbre:StructureDonnees=struct
  type dico = Noeud of ( (string list) * (( int * dico) list) )
  let empty=Noeud([],[])

  (*fonction auxiliaire de recherche dans une liste associative sur la valeur associé à une clé*)
  let rec recherche t liste=match liste with
  |[]->None
  |(c,v)::q->if c=t then Some(v) else (recherche t q)

  (*fonction pour modifier une valeur dans une liste associative *)
  let rec set t liste nv=match liste with 
  |[]->[]
  |(c,v)::q->if t=c then (c,nv)::q else (c,v)::(set t q nv)

  let rec ajouter lstint str (Noeud(lstr,lst))=match lstint with
        |[]->Noeud(str::lstr,lst)
        |t::q->match (recherche t lst) with 
                  |None->Noeud(lstr,(t,(ajouter q str empty))::lst)
                  |Some(Noeud(ls,lsi))->Noeud(lstr,set t lst (ajouter q str (Noeud(ls,lsi)) ))
  let rec chercher lstint (Noeud(lstr,lst))=match lstint with 
  |[]->lstr
  |t::q->match (recherche t lst) with
            |None->[]
            |Some(Noeud(ls,lsi))->chercher q (Noeud(ls,lsi))

  let rec max_mots_code_identique (Noeud(lstr,lst)) = match lst with
  |[]->List.length lstr
  |_->max (List.length lstr) (List.fold_right (fun t rq-> max t rq) (List.map (fun (c,v)->max_mots_code_identique v) lst) (List.length lstr))

  let rec prefixe lstint (Noeud(lstr,lst))=match lstint with
  |[]->lstr@(List.fold_right(fun (i,v) rq->((prefixe lstint v)@rq)) lst []) 
  |t::q->match (recherche t lst) with
      |None->[]
      |Some(a)->prefixe q a
  (* TESTS - ATTENTION les tests peuvent échouer car l'ordre des branches n'est pas fixé *)  

  let a1 = Noeud
      ([],
       [(2,
         Noeud
           ([],
            [(6,
              Noeud
                ([],
                 [(6,
                   Noeud
                     ([],
                      [(5,
                        Noeud
                          ([],
                           [(6,
                             Noeud
                               ([], [(8, Noeud ([], [(7, Noeud (["bonjour"], []))]))]))]))]))]))]))])
  let%test _ = a1 = ajouter [2;6;6;5;6;8;7] "bonjour" empty

  let a2 = Noeud
      ([],
       [(6,
         Noeud
           ([],
            [(2,
              Noeud
                ([],
                 [(2, Noeud ([], [(6, Noeud ([], [(5, Noeud (["ocaml"], []))]))]))]))]))])

  let%test _ = a2 = ajouter [6;2;2;6;5] "ocaml" empty

  let a3 =   Noeud
      ([],
       [(2, Noeud (["a"], []));
        (6,
         Noeud
           ([],
            [(2,
              Noeud
                ([],
                 [(2, Noeud ([], [(6, Noeud ([], [(5, Noeud (["ocaml"], []))]))]))]))]))])

  let%test _ = a3 = ajouter [2] "a" a2

  let a4 = Noeud ([], [(2, Noeud ([], [(8, Noeud (["au"], []))]))])

  let%test _ = a4 = ajouter [2;8] "au" empty

  let a5 = Noeud
      ([], [(2, Noeud ([], [(6, Noeud (["an"], [])); (8, Noeud (["au"], []))]))])

  let%test _ = a5 = ajouter [2;6] "an" a4

  let a6 = Noeud
      ([],
       [(2,
         Noeud
           ([],
            [(6, Noeud (["an"], [(3, Noeud (["ane"], []))]));
             (8, Noeud (["au"], []))]))])

  let%test _ = a6 = ajouter [2;6;3] "ane" a5

  let a7 = Noeud
      ([],
       [(2,
         Noeud
           ([],
            [(6, Noeud (["an"], [(3, Noeud (["ame";"ane"], []))]));
             (8, Noeud (["au"], []))]))])


  let%test _ = a7 = ajouter [2;6;3] "ame" a6

  let a8 = Noeud
      ([],
       [(2,
         Noeud
           ([],
            [(6, Noeud (["an"], [(3, Noeud (["bof";"ame";"ane"], []))]));
             (8, Noeud (["au"], []))]))])


  let%test _ = a8 = ajouter [2;6;3] "bof" a7

  let a9_1 = Noeud
      ([],
       [(2,
         Noeud
           ([],
            [(6, Noeud (["an"], [(3, Noeud (["bof";"ame";"ane"], []))]));
             (8, Noeud (["bu";"au"], []))]))])
  let a9_2 = Noeud
      ([],
       [(2,
         Noeud
           ([],
            [(8, Noeud (["bu"; "au"], []));
             (6, Noeud (["an"], [(3, Noeud (["bof"; "ame"; "ane"], []))]))]))])


  let%test _ = (a9_1 = ajouter [2;8] "bu" a8 || a9_2 = ajouter [2;8] "bu" a8)
  let%test _ = eq_perm (chercher [2;8] a9_1) ["bu"; "au"]
  let%test _ = eq_perm (chercher [2;6] a9_1) ["an"]
  let%test _ = eq_perm (chercher [2;6;3] a9_1) ["bof"; "ame"; "ane"]
  let%test _ = eq_perm (chercher [1;4;5] a9_1) []
  let%test _ = eq_perm (chercher [2;8] a9_2) ["bu"; "au"]
  let%test _ = eq_perm (chercher [2;6] a9_2) ["an"]
  let%test _ = eq_perm (chercher [2;6;3] a9_2) ["bof"; "ame"; "ane"]
  let%test _ = eq_perm (chercher [1;4;5] a9_2) []
  let%test _ = max_mots_code_identique a9_1 = 3
  let%test _ = max_mots_code_identique a9_2 = 3
  let%test _ = max_mots_code_identique a8 = 3
  let%test _ = max_mots_code_identique a7 = 2
  let%test _ = max_mots_code_identique a6 = 1
  let%test _ = max_mots_code_identique a5 = 1
  let%test _ = max_mots_code_identique a4 = 1
  let%test _ = max_mots_code_identique a3 = 1
  let%test _ = max_mots_code_identique a2 = 1
  let%test _ = max_mots_code_identique a1 = 1

  let%test _ = eq_perm (prefixe [2] a9_1) ["ame";"an";"ane";"au";"bof";"bu"]
  let%test _ = eq_perm (prefixe [2;6] a9_1) ["ame";"an";"ane";"bof"]
  let%test _ = eq_perm (prefixe [2;8] a9_1) ["au";"bu"]
  let%test _ = eq_perm (prefixe [3;8] a9_1) []
  let%test _ = eq_perm (prefixe [] a9_1) ["ame";"an";"ane";"au";"bof";"bu"]
  let%test _ = eq_perm (prefixe [] a9_2) ["ame";"an";"ane";"au";"bof";"bu"]
  let%test _ = eq_perm (prefixe [] a6) ["an";"ane";"au"]

end