Problem:    
Rows:       10
Columns:    12
Non-zeros:  24
Status:     OPTIMAL
Objective:  CoutTotal = 9.5 (MINimum)

   No.   Row name   St   Activity     Lower bound   Upper bound    Marginal
------ ------------ -- ------------- ------------- ------------- -------------
     1 RespectEffectueEnM(D1,F1)
                    NS             2             2             =             2 
     2 RespectEffectueEnM(D1,F2)
                    B              0            -0             = 
     3 RespectEffectueEnM(D2,F1)
                    NS             1             1             =             2 
     4 RespectEffectueEnM(D2,F2)
                    NS             3             3             =             3 
     5 RespectStockage(M1,F1)
                    NU           2.5                         2.5            -1 
     6 RespectStockage(M1,F2)
                    NU             1                           1            -2 
     7 RespectStockage(M2,F1)
                    B            0.5                           1 
     8 RespectStockage(M2,F2)
                    B              1                           2 
     9 RespectStockage(M3,F1)
                    B              0                           2 
    10 RespectStockage(M3,F2)
                    NU             1                           1            -1 

   No. Column name  St   Activity     Lower bound   Upper bound    Marginal
------ ------------ -- ------------- ------------- ------------- -------------
     1 QUANTITE(D1,M1,F1)
                    B              2             0               
     2 QUANTITE(D1,M2,F1)
                    NL             0             0                       < eps
     3 QUANTITE(D1,M3,F1)
                    NL             0             0                           1 
     4 QUANTITE(D1,M1,F2)
                    NL             0             0                           3 
     5 QUANTITE(D1,M2,F2)
                    NL             0             0                           3 
     6 QUANTITE(D1,M3,F2)
                    NL             0             0                           3 
     7 QUANTITE(D2,M1,F1)
                    B            0.5             0               
     8 QUANTITE(D2,M2,F1)
                    B            0.5             0               
     9 QUANTITE(D2,M3,F1)
                    NL             0             0                           1 
    10 QUANTITE(D2,M1,F2)
                    B              1             0               
    11 QUANTITE(D2,M2,F2)
                    B              1             0               
    12 QUANTITE(D2,M3,F2)
                    B              1             0               

Karush-Kuhn-Tucker optimality conditions:

KKT.PE: max.abs.err = 0.00e+00 on row 0
        max.rel.err = 0.00e+00 on row 0
        High quality

KKT.PB: max.abs.err = 0.00e+00 on row 0
        max.rel.err = 0.00e+00 on row 0
        High quality

KKT.DE: max.abs.err = 0.00e+00 on column 0
        max.rel.err = 0.00e+00 on column 0
        High quality

KKT.DB: max.abs.err = 0.00e+00 on row 0
        max.rel.err = 0.00e+00 on row 0
        High quality

End of output
