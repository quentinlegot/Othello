# Jeu d’infection - Othello 

## Contributeurs

Antonin Boyon

[Quentin Legot](https://github.com/SexiestCHiba)

[Arthur Page](https://github.com/Arthur7770)

## Comment lancer le jeu 

Après avoir compilé et placé les fichiers dans une archive .jar:

`java -jar othello.jar profondeurJ1 profondeurJ2 alphabeta`

`profondeurJ1` et `profondeurJ2` correspondent à un entier indiquant à l'algorithme de décision à quelle profondeur il doit calculer et
`alphabeta` correspond à un booléen indiquant au programme si vous souhaitez utiliser l'algorithme alphabeta (True ou true) ou l'algorithme Negamax (toutes autres valeurs que true)

Si profondeurJ1 ou profondeurJ2 ne sont pas corrects, l'algorithme utilisera des paramètres par défaut, soit  (4,4,true)

Si vous ne souhaitez pas utiliser le logger, indiquer dans le fichier META_INF que vous souhaitez qu'il se lance sur le fichier Main et non sur MainStats
