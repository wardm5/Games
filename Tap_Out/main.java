
/********************************************************************************
*                                                                               *
*                                Place Holder                                   *
*                                                                               *
*                                                                               *
*                                                                               *
*                                                                               *
*                                                                               *
/*******************************************************************************/

public class main {
    public static void main(String[] args) {
        Game game = new Game();             // creates game
        boolean playGame = true;            // boolean for when to play the game
        while (playGame) {
            playGame = game.homeScreen();   // playgame is updated based on the homeScreen method
        }
        goodbye();
    }
    private static void goodbye() {
        System.out.println("Goodbye, please play again soon!  ");
    }
}
