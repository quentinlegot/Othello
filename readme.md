# Infection game - Othello 

## Contributors

[Antonin Boyon](https://github.com/Detextra)

[Quentin Legot](https://github.com/SexiestCHiba)

[Arthur Page](https://github.com/Arthur7770)

## How to launch the game 

After you compiled and placed files in an jar archives (using jar command or ide):

`java -jar othello.jar depthP1 depthP2 alphabeta`

`depthP1` and `depthP2` correspond to an integer indicating to the decision algorithim how deep to calculate and
`alphabeta` correspond to a boolean indicating to the program if you wish to use alphabeta algorithm (True or true values) or negamax algorithm ( all others values than true).

If `depthP1` or `depthP2` aren't correct, the program will use default settings (4, 4, true).

If you doesn't want to use the logger, change the value `MainStats` to `Main` in META_INF file.
