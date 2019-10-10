// import HelperMethod.*;
import java.util.ArrayList;

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
        ArrayList<GameStat> stats = new ArrayList<>();
        Game game = new Game(stats, true);             // creates game, creates stats for records, and displays text automatically
        boolean playGame = true;            // boolean for when to play the game
        while (playGame) {
            playGame = game.homeScreen();   // playgame is updated based on the homeScreen method
        }
        goodbye();
        showGameStatistics(stats);
    }
    private static void goodbye() {
        System.out.println("Goodbye, please play again soon!  ");
    }
    private static void showGameStatistics(ArrayList<GameStat> stats) {
        System.out.println("GAME STATISTICS:   ");
        for (int i = 0; i < stats.size(); i++) {
            System.out.println("Game " + (i+1) + " Winner:  " + stats.get(i).getWinnerName() + ", turns:  " + stats.get(i).getTurns());
        }
    }
}
