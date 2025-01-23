Problem:    
Rows:       12
Columns:    18 (6 integer, 6 binary)
Non-zeros:  30
Status:     INTEGER OPTIMAL
Objective:  CoutTotal = 210.5 (MINimum)

   No.   Row name        Activity     Lower bound   Upper bound
------ ------------    ------------- ------------- -------------
     1 RespectDEffectueEnM(D1,F1)
                                   2             2             = 
     2 RespectDEffectueEnM(D1,F2)
                                   0            -0             = 
     3 RespectDEffectueEnM(D2,F1)
                                   1             1             = 
     4 RespectDEffectueEnM(D2,F2)
                                   3             3             = 
     5 RespectStockage(M1,F1)
                                 2.5                         2.5 
     6 RespectStockage(M1,F2)
                                   1                           1 
     7 RespectStockage(M2,F1)
                                 0.5                           1 
     8 RespectStockage(M2,F2)
                                   1                           2 
     9 RespectStockage(M3,F1)
                                   0                           2 
    10 RespectStockage(M3,F2)
                                   1                           1 
    11 RespectLivraisonVersClient(D1)
                                   1             1             = 
    12 RespectLivraisonVersClient(D2)
                                   1             1             = 

   No. Column name       Activity     Lower bound   Upper bound
------ ------------    ------------- ------------- -------------
     1 QUANTITE(D1,M1,F1)
                                   2             0               
     2 QUANTITE(D1,M2,F1)
                                   0             0               
     3 QUANTITE(D1,M3,F1)
                                   0             0               
     4 QUANTITE(D1,M1,F2)
                                   0             0               
     5 QUANTITE(D1,M2,F2)
                                   0             0               
     6 QUANTITE(D1,M3,F2)
                                   0             0               
     7 QUANTITE(D2,M1,F1)
                                 0.5             0               
     8 QUANTITE(D2,M2,F1)
                                 0.5             0               
     9 QUANTITE(D2,M3,F1)
                                   0             0               
    10 QUANTITE(D2,M1,F2)
                                   1             0               
    11 QUANTITE(D2,M2,F2)
                                   1             0               
    12 QUANTITE(D2,M3,F2)
                                   1             0               
    13 EXPEDITIONS(D1,M1)
                    *              0             0             1 
    14 EXPEDITIONS(D1,M2)
                    *              1             0             1 
    15 EXPEDITIONS(D1,M3)
                    *              0             0             1 
    16 EXPEDITIONS(D2,M1)
                    *              0             0             1 
    17 EXPEDITIONS(D2,M2)
                    *              0             0             1 
    18 EXPEDITIONS(D2,M3)
                    *              1             0             1 

Integer feasibility conditions:

KKT.PE: max.abs.err = 0.00e+00 on row 0
        max.rel.err = 0.00e+00 on row 0
        High quality

KKT.PB: max.abs.err = 0.00e+00 on row 0
        max.rel.err = 0.00e+00 on row 0
        High quality

End of output
