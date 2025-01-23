Problem:    
Rows:       8
Columns:    16 (16 integer, 16 binary)
Non-zeros:  32
Status:     INTEGER OPTIMAL
Objective:  BeneficeTotal = 26 (MAXimum)

   No.   Row name        Activity     Lower bound   Upper bound
------ ------------    ------------- ------------- -------------
     1 RespectUneTacheParPersonne(t1)
                                   1             1             = 
     2 RespectUneTacheParPersonne(t2)
                                   1             1             = 
     3 RespectUneTacheParPersonne(t3)
                                   1             1             = 
     4 RespectUneTacheParPersonne(t4)
                                   1             1             = 
     5 RespectUnePersonneParTache(p1)
                                   1             1             = 
     6 RespectUnePersonneParTache(p2)
                                   1             1             = 
     7 RespectUnePersonneParTache(p3)
                                   1             1             = 
     8 RespectUnePersonneParTache(p4)
                                   1             1             = 

   No. Column name       Activity     Lower bound   Upper bound
------ ------------    ------------- ------------- -------------
     1 Q(p1,t1)     *              0             0             1 
     2 Q(p2,t1)     *              1             0             1 
     3 Q(p3,t1)     *              0             0             1 
     4 Q(p4,t1)     *              0             0             1 
     5 Q(p1,t2)     *              0             0             1 
     6 Q(p2,t2)     *              0             0             1 
     7 Q(p3,t2)     *              1             0             1 
     8 Q(p4,t2)     *              0             0             1 
     9 Q(p1,t3)     *              1             0             1 
    10 Q(p2,t3)     *              0             0             1 
    11 Q(p3,t3)     *              0             0             1 
    12 Q(p4,t3)     *              0             0             1 
    13 Q(p1,t4)     *              0             0             1 
    14 Q(p2,t4)     *              0             0             1 
    15 Q(p3,t4)     *              0             0             1 
    16 Q(p4,t4)     *              1             0             1 

Integer feasibility conditions:

KKT.PE: max.abs.err = 0.00e+00 on row 0
        max.rel.err = 0.00e+00 on row 0
        High quality

KKT.PB: max.abs.err = 0.00e+00 on row 0
        max.rel.err = 0.00e+00 on row 0
        High quality

End of output
