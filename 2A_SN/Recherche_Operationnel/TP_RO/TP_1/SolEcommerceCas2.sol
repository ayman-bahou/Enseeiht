Problem:    
Rows:       40
Columns:    48 (42 integer, 36 binary)
Non-zeros:  151
Status:     INTEGER OPTIMAL
Objective:  DistanceTotal = 22 (MINimum)

   No.   Row name        Activity     Lower bound   Upper bound
------ ------------    ------------- ------------- -------------
     1 RespectSeulVisite(ALPHA)
                                   1             1             = 
     2 RespectSeulVisite(C1)
                                   1             1             = 
     3 RespectSeulVisite(C2)
                                   1             1             = 
     4 RespectSeulVisite(C3)
                                   1             1             = 
     5 RespectSeulVisite(C4)
                                   1             1             = 
     6 RespectSeulVisite(C5)
                                   1             1             = 
     7 RespectSeulQuitte(ALPHA)
                                   1             1             = 
     8 RespectSeulQuitte(C1)
                                   1             1             = 
     9 RespectSeulQuitte(C2)
                                   1             1             = 
    10 RespectSeulQuitte(C3)
                                   1             1             = 
    11 RespectSeulQuitte(C4)
                                   1             1             = 
    12 RespectSeulQuitte(C5)
                                   1             1             = 
    13 RespectDeparArr
                                   0            -0             = 
    14 RespectSousTournee(C1,C2)
                                  -4                           5 
    15 RespectSousTournee(C1,C3)
                                   5                           5 
    16 RespectSousTournee(C1,C4)
                                  -2                           5 
    17 RespectSousTournee(C1,C5)
                                  -3                           5 
    18 RespectSousTournee(C2,C1)
                                   4                           5 
    19 RespectSousTournee(C2,C3)
                                   3                           5 
    20 RespectSousTournee(C2,C4)
                                   2                           5 
    21 RespectSousTournee(C2,C5)
                                   1                           5 
    22 RespectSousTournee(C3,C1)
                                   1                           5 
    23 RespectSousTournee(C3,C2)
                                  -3                           5 
    24 RespectSousTournee(C3,C4)
                                   5                           5 
    25 RespectSousTournee(C3,C5)
                                  -2                           5 
    26 RespectSousTournee(C4,C1)
                                   2                           5 
    27 RespectSousTournee(C4,C2)
                                  -2                           5 
    28 RespectSousTournee(C4,C3)
                                   1                           5 
    29 RespectSousTournee(C4,C5)
                                   5                           5 
    30 RespectSousTournee(C5,C1)
                                   3                           5 
    31 RespectSousTournee(C5,C2)
                                   5                           5 
    32 RespectSousTournee(C5,C3)
                                   2                           5 
    33 RespectSousTournee(C5,C4)
                                   1                           5 
    34 RespectPremierTour(ALPHA)
                                   1             1             = 
    35 RespectTour(ALPHA)
                                   1             1             = 
    36 RespectTour(C1)
                                   1             1             = 
    37 RespectTour(C2)
                                   1             1             = 
    38 RespectTour(C3)
                                   1             1             = 
    39 RespectTour(C4)
                                   1             1             = 
    40 RespectTour(C5)
                                   1             1             = 

   No. Column name       Activity     Lower bound   Upper bound
------ ------------    ------------- ------------- -------------
     1 X1(ALPHA,C1) *              1             0             1 
     2 X1(ALPHA,C2) *              0             0             1 
     3 X1(ALPHA,C3) *              0             0             1 
     4 X1(ALPHA,C4) *              0             0             1 
     5 X1(ALPHA,C5) *              0             0             1 
     6 X1(C1,ALPHA) *              0             0             1 
     7 X1(C1,C2)    *              0             0             1 
     8 X1(C1,C3)    *              1             0             1 
     9 X1(C1,C4)    *              0             0             1 
    10 X1(C1,C5)    *              0             0             1 
    11 X1(C2,ALPHA) *              1             0             1 
    12 X1(C2,C1)    *              0             0             1 
    13 X1(C2,C3)    *              0             0             1 
    14 X1(C2,C4)    *              0             0             1 
    15 X1(C2,C5)    *              0             0             1 
    16 X1(C3,ALPHA) *              0             0             1 
    17 X1(C3,C1)    *              0             0             1 
    18 X1(C3,C2)    *              0             0             1 
    19 X1(C3,C4)    *              1             0             1 
    20 X1(C3,C5)    *              0             0             1 
    21 X1(C4,ALPHA) *              0             0             1 
    22 X1(C4,C1)    *              0             0             1 
    23 X1(C4,C2)    *              0             0             1 
    24 X1(C4,C3)    *              0             0             1 
    25 X1(C4,C5)    *              1             0             1 
    26 X1(C5,ALPHA) *              0             0             1 
    27 X1(C5,C1)    *              0             0             1 
    28 X1(C5,C2)    *              1             0             1 
    29 X1(C5,C3)    *              0             0             1 
    30 X1(C5,C4)    *              0             0             1 
    31 X1(ALPHA,ALPHA)
                    *              0             0             1 
    32 X1(C1,C1)    *              0             0             1 
    33 X1(C2,C2)    *              0             0             1 
    34 X1(C3,C3)    *              0             0             1 
    35 X1(C4,C4)    *              0             0             1 
    36 X1(C5,C5)    *              0             0             1 
    37 X2(C2)       *              5             0               
    38 X2(C1)       *              1             0               
    39 X2(C3)       *              2             0               
    40 X2(C4)       *              3             0               
    41 X2(C5)       *              4             0               
    42 X2(ALPHA)    *              1             0               
    43 ~r_35                       0             0             5 
    44 ~r_36                       0             0             5 
    45 ~r_37                       4             0             5 
    46 ~r_38                       1             0             5 
    47 ~r_39                       2             0             5 
    48 ~r_40                       3             0             5 

Integer feasibility conditions:

KKT.PE: max.abs.err = 0.00e+00 on row 0
        max.rel.err = 0.00e+00 on row 0
        High quality

KKT.PB: max.abs.err = 0.00e+00 on row 0
        max.rel.err = 0.00e+00 on row 0
        High quality

End of output
