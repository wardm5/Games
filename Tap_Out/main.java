import java.util.Scanner;

/********************************************************************************
*                                                                               *
*                                                                               *
*                                                                               *
*
*                                                                               *
*                                                                               *
*                                                                               *
/*******************************************************************************/

public class main {
    private static Player[] players = new Player[2];
    public static void main(String[] args) {
        int numOfPlayers = start();
        setup(numOfPlayers);
        playGame();
        // playAgainQuestion();
    }
    public static int start() {
        System.out.println("\n\n\nWelcome to the finger game! ");
        System.out.print("How many players do you want to name? (min of 0, max of 2) ");
        Scanner scan = new Scanner(System.in);
        int numOfPlayers = scan.nextInt();
        while (numOfPlayers > 2 || numOfPlayers < 0) {
            System.out.print("How many players do you want to name? (min of 0, max of 2) ");
            numOfPlayers = scan.nextInt();
        }
        return numOfPlayers;
    }
    public static void setup(int numOfPlayers) {
        for (int i = 0; i < 2; i++) {
            if (numOfPlayers > 0) {
                System.out.print("What would you like to name Player " + (i+1) + "? ");
                Scanner test = new Scanner(System.in);
                String tempName = test.nextLine();
                Player player1 = new Player(tempName);

                numOfPlayers--;
                players[i] = player1;

            } else {
                Player player1 = new Player("Player " + (i+1));
                players[i] = player1;
            }
        }
        System.out.println();
        System.out.println();
    }
    public static void playGame() {
        int turn = 0;
        while (!players[0].hasLost() && !players[1].hasLost()) {
            if (turn % 2 == 0) {
                turn(0, 1);
            } else {
                turn(1, 0);
            }
            turn++;
        }
        if (players[0].hasLost())
            System.out.println(players[1].getName() + " has won!! ");
        else
            System.out.println(players[0].getName() + " has won!!");
        System.out.println("Thank you for playing! ");
    }
    // public static void playAgainQuestion() {
    //
    // }
    public static void turn(int p1, int p2) {
        System.out.println("\n" + players[p1].getName() + ", it is your turn!!   ");
        System.out.println("Your finger counts are below: ");
        players[p1].showFingerCounts();
        System.out.println("Your opponent's finger count is:   ");
        players[p2].showFingerCounts();

        //  SPLIT SECTION, asks user if they want to split due to having greater than 1 finger in each hand that are also even.
        if (split(players[p1]))
            return;

        // PICK YOUR HAND TO ATTACK WITH
        int attack = pickYourHand(players[p1]);
        pickOppHand(players[p2] ,attack);
    }
    public static boolean split(Player p1) {
        if ( (p1.getLeftFingerCount() > 0 && p1.getLeftFingerCount() % 2 == 0 && p1.getRightFingerCount() == 0)
            || (p1.getRightFingerCount() > 0 && p1.getRightFingerCount() % 2 == 0 && p1.getLeftFingerCount() == 0)) {
            System.out.println("You have the option to split! Do you want to do this? (enter 'yes', 'no', 'y', or 'n')   ");
            Scanner temp = new Scanner(System.in);
            String str = temp.nextLine();
            str = standardizeUserInput(str);
            while (!str.equals("yes") && !str.equals("no") && !str.equals("y") && !str.equals("n")) {
                System.out.println("Incorrect entry, enter 'yes' or 'no')   ");
                str = temp.nextLine();
                str = standardizeUserInput(str);
            }
            if (str.equals("yes") && str.equals("y")) {
                p1.split();
                return true;
            }
        }
        return false;
    }
    public static int pickYourHand(Player p1) {
        System.out.print("Which of your hands do you want to use to attack?? (enter 'right', 'r', 'left', or 'l')  ");
        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();
        str = standardizeUserInput(str);
        int attack = 0;
        while (checkUserInput(str) || attack <= 0) {
            if (checkUserInput(str)) {
                System.out.print("Incorrect entry, enter 'right','r', 'left', or 'l'.  ");
                str = scan.nextLine();
                str = standardizeUserInput(str);
                continue;
            }
            if (str.toLowerCase().equals("left"))
                attack = p1.attack(false);
            else
                attack = p1.attack(true);
            if (attack == -1) {
                System.out.print("The hand you chose has zero fingers left, please choose the other hand.   ");
                str = scan.nextLine();
                str = standardizeUserInput(str);
                continue;
            }
        }
        return attack;
    }
    public static void pickOppHand(Player p2, int attack) {
        System.out.print("Which of your opponent's hands do you want to attack?   ");
        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();
        str = standardizeUserInput(str);
        int opponentHand = 0;
        while (checkUserInput(str) || (opponentHand <= 0)) {
            if (checkUserInput(str)) {
                System.out.print("Incorrect entry, enter 'right', 'r', 'left', or 'l'.  ");
                str = scan.nextLine();
                str = standardizeUserInput(str);
                continue;
            }
            if (str.toLowerCase().trim().equals("right"))
                opponentHand = p2.getHand(true);
            else
                opponentHand = p2.getHand(false);
            if (opponentHand <= 0) {
                System.out.print("The hand you chose had 0 fingers, please choose the other hand.   ");
                str = scan.nextLine();
                continue;
            }
        }
        if (str.toLowerCase().equals("left"))
            p2.updateFingers(attack, false);
        else
            p2.updateFingers(attack, true);
    }
    public static String standardizeUserInput(String s) {
        return s.trim().toLowerCase();
    }
    public static boolean checkUserInput(String str) {
        return (!str.equals("left") && !str.equals("right") && !str.equals("r") && !str.equals("l"));
    }
}
