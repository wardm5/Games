import java.util.*;
// import HelperMethod.*;

// fix input mismatch issue
// finish AI settings
// get import working for HelperMethod class
// get API working
// finish settings class

public class Game {
    public Settings settings = new Settings();
    List<GameStat> stats;
    public Game(List<GameStat> stats, boolean displayText) {
        this.stats = stats;
        settings.updateDisplayText(false);
    }
    private List<Player> playersList = new ArrayList<>();

    public boolean homeScreen() {
        boolean playGame = true;
        while (playGame) {
            print("\n\n\nWelcome to the Tap Out game! ");
            reviewOptions();
            int input = userIntInput();
            while (input > 3 || input < 0) {
                printNoNewLine("Which option do you want to pick? (enter 1, 2, 3, or 4 to review options) ");
                input = userIntInput();
                if (input == 4)
                    reviewOptions();
            }
            if (input == 1)
                playGame = beginGame();
            else if (input == 2)
                settings.viewSettings();
            else
                playGame = false;
        }
        return playGame;
    }
    private void reviewOptions() {
        print("Please choose an option (input number):  ");
        print("  1 - Play Game! ");
        print("  2 - Game Options ");
        print("  3 - Exit Game ");
    }
    private boolean beginGame() {
        int numOfPlayers = determinePlayers();
        int numOfHumans = determineHumans(numOfPlayers);
        setup(numOfPlayers, numOfHumans);
        playGame();
        if (playAgainQuestion())
            return true;
        print("Thanks for playing, hope you want to play again soon!  ");
        return false;
    }
    private int determinePlayers() {
        printNoNewLine("How many players do you want? (min of 2, max of 8) ");
        int numOfPlayers = userIntInput();
        while (numOfPlayers > 8 || numOfPlayers < 2) {
            printNoNewLine("Input error: How many players do you want? (min of 2, max of 8) ");
            numOfPlayers = userIntInput();
        }
        return numOfPlayers;
    }
    private int determineHumans(int players) {
        printNoNewLine("How many human players do you want? (min of 0, max of " + players + ") ");
        int numOfHumans = userIntInput();
        while (numOfHumans > players || numOfHumans < 0) {
            printNoNewLine("Input error: How many players do you want to name? (min of 0, max of " + players + ") ");
            numOfHumans = userIntInput();
        }
        return numOfHumans;
    }
    private void setup(int numOfPlayers, int numOfHumans) {
        for (int i = 0; i < numOfPlayers; i++) {
            if (numOfHumans > 0) {
                printNoNewLine("What would you like to name Player " + (i+1) + "? ");
                String tempName = userStringInput();
                Player player1 = new Player(tempName);
                playersList.add(player1);
                numOfHumans--;
            } else {
                Player player1 = new Player("Computer " + (i+1), true);
                playersList.add(player1);
            }
        }
        print();
        print();
    }
    private void playGame() {
        int totalTurns = 0;
        int turn = 0;
        int currPlayers = playersList.size();
        while (playersList.size() > 1) {
            int attackedPlayer = playTurn(turn % playersList.size(), turn);
            if (playersList.get(attackedPlayer).hasLost()) {
                playersList.remove(attackedPlayer);
                turn--;
            }
            totalTurns++;
            turn++;
        }
        GameStat stat = new GameStat(playersList.get(0).getName(), totalTurns);
        stats.add(stat);
        print(playersList.get(0).getName() + " has won!! ");
        playersList.remove(0);
        print("Thank you for playing! ");
    }
    private boolean playAgainQuestion() {
        print("Do you want to play again?  (enter 'yes', 'y', 'no', 'n')   ");
        String str = userStringInput();
        while (!str.equals("yes") && !str.equals("no") && !str.equals("y") && !str.equals("n")) {
            print("Incorrect entry, enter 'yes', 'y', 'no', or 'n')   ");
            str = userStringInput();
        }
        return (str.equals("yes") || str.equals("y"));
    }
    private int playTurn(int p1, int turn) {
        print("\n" + playersList.get(p1).getName() + ", it is your turn!!   ");
        print(playersList.get(p1).getName() + "'s finger count below:  ");
        playersList.get(p1).showFingerCounts();
        print("Your opponent's finger count is:   ");
        /*
            ex output:
                            01234567890123456789012345678
                                                | L | R |
                            *-------------------*---*---*
             Your Turn ---> | 1. Player_name    | L | R |
                            *-------------------*---*---*
                            | 2. Player_name    | L | R |
                            *-------------------*---*---*
        */
        top();
        for (int i = 0; i < playersList.size(); i++) {
            if (p1 == i)
                printNoNewLine("Your Turn ---> ");
            else
                printNoNewLine("               ");
            String modifiedName = getModifiedName(playersList.get(i).getName());
            print("| " + (i+1) + ". " + modifiedName + "  | " + playersList.get(i).getLeftFingerCount() + " | " + playersList.get(i).getRightFingerCount() + " |");
            bot();
        }

        //  SPLIT SECTION, asks user if they want to split due to having greater than 1 finger in each hand that are also even.
        if (split(playersList.get(p1)))
            return p1;
        // PICK YOUR HAND TO ATTACK WITH
        int attack = pickYourHand(playersList.get(p1));
        // SELECT OPPONET TO ATTACK
        int selectedPlayer;
        if (turn % 2 == 0)
            selectedPlayer = 1;
        else
            selectedPlayer = 0;
        if (playersList.size() > 2) {
            printNoNewLine("Which player do you want to attack?  (enter a number from the above list exluding yourself) ");
            selectedPlayer = userIntInput();
            while ((selectedPlayer > playersList.size() || selectedPlayer < 1) || selectedPlayer == (p1 + 1)) {
                printNoNewLine("Which player do you want to attack?  (enter a number from the above list exluding yourself) ");
                selectedPlayer = userIntInput();
            }
            selectedPlayer--;
        }
        pickOppHand(playersList.get(selectedPlayer), attack);
        return (selectedPlayer);   // return selected player to check if they lost
    }
    private boolean split(Player p1) {
        if ( (p1.getLeftFingerCount() > 0 && p1.getLeftFingerCount() % 2 == 0 && p1.getRightFingerCount() == 0)
            || (p1.getRightFingerCount() > 0 && p1.getRightFingerCount() % 2 == 0 && p1.getLeftFingerCount() == 0)) {
            printNoNewLine("You have the option to split! Do you want to do this? (enter 'yes', 'no', 'y', or 'n')   ");
            String str = userStringInput();
            while (!str.equals("yes") && !str.equals("no") && !str.equals("y") && !str.equals("n")) {
                print("Incorrect entry, enter 'yes' or 'no')   ");
                str = userStringInput();
            }
            if (str.equals("yes") || str.equals("y")) {
                p1.split();
                return true;
            }
        }
        return false;
    }
    private int pickYourHand(Player p1) {
        printNoNewLine("Which of your hands do you want to use to attack?? (enter 'right', 'r', 'left', or 'l')  ");
        String str = userStringInput();
        int attack = 0;
        while (checkUserInput(str) || attack <= 0) {
            if (checkUserInput(str)) {
                print("Incorrect entry, enter 'right','r', 'left', or 'l'.  ");
                str = userStringInput();
                continue;
            }
            if (str.equals("left") || str.equals("l"))
                attack = p1.attack(false);
            else
                attack = p1.attack(true);
            if (attack == -1) {
                print("The hand you chose has zero fingers left, please choose the other hand.   ");
                str = userStringInput();
                continue;
            }
        }
        return attack;
    }
    private void pickOppHand(Player p2, int attack) {
        printNoNewLine("Which of your opponent's hands do you want to attack?   ");
        String str = userStringInput();
        int opponentHand = 0;
        while (checkUserInput(str) || (opponentHand <= 0)) {
            if (checkUserInput(str)) {
                print("Incorrect entry, enter 'right', 'r', 'left', or 'l'.  ");
                str = userStringInput();
                continue;
            }
            if (str.equals("right") || str.equals("r"))
                opponentHand = p2.getHand(true);
            else
                opponentHand = p2.getHand(false);
            if (opponentHand <= 0) {
                print("The hand you chose had 0 fingers, please choose the other hand.   ");
                str = userStringInput();
                continue;
            }
        }
        if (str.equals("left") || str.equals("l"))
            p2.updateFingers(attack, false);
        else
            p2.updateFingers(attack, true);
    }

    // HELPER METHODS
    private String standardizeUserInput(String s) {
        return s.trim().toLowerCase();
    }
    private boolean checkUserInput(String str) {
        return (!str.equals("left") && !str.equals("right") && !str.equals("r") && !str.equals("l"));
    }
    private void print() {
        System.out.println();
    }
    private void print(String str) {
        System.out.println(str);
    }
    private void printNoNewLine(String str) {
        System.out.print(str);
    }
    private String userStringInput() {
        Scanner scan = new Scanner(System.in);
        return standardizeUserInput(scan.nextLine());
    }
    private int userIntInput() {
        Scanner scan = new Scanner(System.in);
        return scan.nextInt();
    }
    private void top() {
        System.out.println("                                   | L | R |");
        System.out.println("               *-------------------*---*---*");
    }
    private String getModifiedName(String name) {
        if (name.length() > 13)
            return name.substring(0,13);
        else
            return String.format("%-13s", name);
    }
    private void bot() {
        System.out.println("               *-------------------*---*---*");
    }
}
