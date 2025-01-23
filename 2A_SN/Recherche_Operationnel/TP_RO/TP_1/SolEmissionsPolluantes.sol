Problem:    
Rows:       40
Columns:    21 (9 integer, 9 binary)
Non-zeros:  102
Status:     INTEGER OPTIMAL
Objective:  TotalEmission = 0 (MINimum)

   No.   Row name        Activity     Lower bound   Upper bound
------ ------------    ------------- ------------- -------------
     1 RespectDemandes(D1,P1)
                                   0                           2 
     2 RespectDemandes(D1,P2)
                                   0                           4 
     3 RespectDemandes(D2,P1)
                                   0                           7 
     4 RespectDemandes(D2,P2)
                                   0                           6 
     5 RespectStock(D1,P1,M1)
                                   0                          -0 
     6 RespectStock(D1,P1,M2)
                                   0                          -0 
     7 RespectStock(D1,P1,M3)
                                   0                          -0 
     8 RespectStock(D1,P2,M1)
                                   0                          -0 
     9 RespectStock(D1,P2,M2)
                                   0                          -0 
    10 RespectStock(D1,P2,M3)
                                   0                          -0 
    11 RespectStock(D2,P1,M1)
                                   0                          -0 
    12 RespectStock(D2,P1,M2)
                                   0                          -0 
    13 RespectStock(D2,P1,M3)
                                   0                          -0 
    14 RespectStock(D2,P2,M1)
                                   0                          -0 
    15 RespectStock(D2,P2,M2)
                                   0                          -0 
    16 RespectStock(D2,P2,M3)
                                   0                          -0 
    17 RespectPreTour(M1)
                                   0            -0             = 
    18 RespectPreTour(M2)
                                   0            -0             = 
    19 RespectPreTour(M3)
                                   0            -0             = 
    20 RespectLivreurMagasin(M1,P1)
                                   0            -0             = 
    21 RespectLivreurMagasin(M1,P2)
                                   0            -0             = 
    22 RespectLivreurMagasin(M2,P1)
                                   0            -0             = 
    23 RespectLivreurMagasin(M2,P2)
                                   0            -0             = 
    24 RespectLivreurMagasin(M3,P1)
                                   0            -0             = 
    25 RespectLivreurMagasin(M3,P2)
                                   0            -0             = 
    26 RespectSatifaitDemandes(D1,P1,M1)
                                   0                          -0 
    27 RespectSatifaitDemandes(D1,P1,M2)
                                   0                          -0 
    28 RespectSatifaitDemandes(D1,P1,M3)
                                   0                          -0 
    29 RespectSatifaitDemandes(D1,P2,M1)
                                   0                          -0 
    30 RespectSatifaitDemandes(D1,P2,M2)
                                   0                          -0 
    31 RespectSatifaitDemandes(D1,P2,M3)
                                   0                          -0 
    32 RespectSatifaitDemandes(D2,P1,M1)
                                   0                          -0 
    33 RespectSatifaitDemandes(D2,P1,M2)
                                   0                          -0 
    34 RespectSatifaitDemandes(D2,P1,M3)
                                   0                          -0 
    35 RespectSatifaitDemandes(D2,P2,M1)
                                   0                          -0 
    36 RespectSatifaitDemandes(D2,P2,M2)
                                   0                          -0 
    37 RespectSatifaitDemandes(D2,P2,M3)
                                   0                          -0 
    38 RespectExpDuMagasin(M1)
                                   0                          -0 
    39 RespectExpDuMagasin(M2)
                                   0                          -0 
    40 RespectExpDuMagasin(M3)
                                   0                          -0 

   No. Column name       Activity     Lower bound   Upper bound
------ ------------    ------------- ------------- -------------
     1 qt(M1,D1,P1)                0             0               
     2 qt(M2,D1,P1)                0             0               
     3 qt(M3,D1,P1)                0             0               
     4 qt(M1,D1,P2)                0             0               
     5 qt(M2,D1,P2)                0             0               
     6 qt(M3,D1,P2)                0             0               
     7 qt(M1,D2,P1)                0             0               
     8 qt(M2,D2,P1)                0             0               
     9 qt(M3,D2,P1)                0             0               
    10 qt(M1,D2,P2)                0             0               
    11 qt(M2,D2,P2)                0             0               
    12 qt(M3,D2,P2)                0             0               
    13 livraison(M1,D1)
                    *              0             0             1 
    14 livraison(M2,D1)
                    *              0             0             1 
    15 livraison(M3,D1)
                    *              0             0             1 
    16 livraison(M1,D2)
                    *              0             0             1 
    17 livraison(M2,D2)
                    *              0             0             1 
    18 livraison(M3,D2)
                    *              0             0             1 
    19 expedition(M1)
                    *              0             0             1 
    20 expedition(M2)
                    *              0             0             1 
    21 expedition(M3)
                    *              0             0             1 

Integer feasibility conditions:

KKT.PE: max.abs.err = 0.00e+00 on row 0
        max.rel.err = 0.00e+00 on row 0
        High quality

KKT.PB: max.abs.err = 0.00e+00 on row 0
        max.rel.err = 0.00e+00 on row 0
        High quality

End of output
