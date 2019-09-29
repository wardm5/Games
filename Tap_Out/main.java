import java.util.Scanner;

/********************************************************************************
*                                                                               *
*                                                                               *
*                                                                               *
*                                                                               *
*                                                                               *
*                                                                               *
*                                                                               *
/*******************************************************************************/

public class main {
    public static void main(String[] args) {
        Game game = new Game();
        boolean playGame = true;
        while (playGame) {
            playGame = game.beginGame();
        }
    }
}
