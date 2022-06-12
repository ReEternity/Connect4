# Connect4

This project was built upon a third party library, Grid World Case Study.

Connect 4 is a board game that is played generally by 2 players, with each of them taking turns to place a piece. The goal is to connect 4 pieces of your own color to win.

I customized the Connect 4 so that the board size can change through editing the main Connect4 class, and can also change how many pieces were necessary to win the game.

The only part that was difficult about creating this program was that I had to check how many pieces connected to a certain amount. By putting the pieces that were on the board into a Linked List, I had a history of all the pieces that are currently placed on the board. Then I checked each angle, starting from 0 up until 315, to check all 8 spaces surrounding the main piece. If the next piece was the same color, then I continued to do the same procedure of checking until I hit connect4, or there were no more pieces on the board.

