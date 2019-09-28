import java.util.*;
public class main {
    private static Player[] players = new Player[2];
    public static void main(String[] args) {
        int numOfPlayers = start();
        setup(numOfPlayers);
        playGame();
    }
    public static int start() {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Welcome to the finger game! ");
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
            System.out.println("Player 2 has won!! ");
        else
            System.out.println("Player 1 has won!!");
        // end game announcement
    }
    public static void turn(int p1, int p2) {
        System.out.println(players[p1].getName() + ", it is your turn!!");
        System.out.println("Your finger counts are below: ");
        players[p1].showFingerCounts();
        System.out.println("Your opponent's finger count is: ");
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
            System.out.println("You have the option to split! Do you want to do this? (enter 'yes' or 'no')");
            //user input
            Scanner temp = new Scanner(System.in); // need to finish...
            String str = temp.nextLine();
            while (!str.toLowerCase().equals("yes") && !str.toLowerCase().equals("no")) {
                System.out.println("Incorrect entry, enter 'yes' or 'no')");
                str = temp.nextLine(); // need to finish...
            }
            if (str.equals("yes")) {
                p1.split();
                return true;
            }
            return false;
        }
    }
    public static int pickYourHand(Player p1) {
        System.out.print("Which of your hands do you want to use to attack?? (enter 'right' or 'left')  ");
        Scanner scan = new Scanner(System.in);
        String temp = scan.nextLine();
        int attack = 0;
        while ((!temp.toLowerCase().equals("left") && !temp.toLowerCase().equals("right")) || attack <= 0) {
            if (!temp.toLowerCase().equals("left") && !temp.toLowerCase().equals("right")) {
                System.out.print("Incorrect entry, enter 'right' or 'left'.  ");
                temp = scan.nextLine();
                continue;
            }
            if (temp.toLowerCase().equals("left"))
                attack = p1.attack(false);
            else
                attack = p1.attack(true);
            System.out.println(attack);
            if (attack == -1) {
                System.out.println("The hand you chose has zero fingers left, please choose the other hand. ");
                temp = scan.nextLine();
            }
            // if (temp.toLowerCase().equals("left"))
            //     attack = p1.attack(false);
            // else
            //     attack = p1.attack(true);
        }
        // if (temp.toLowerCase().equals("left"))
        //     attack = players[p1].attack(false);
        // else
        //     attack = players[p1].attack(true);
        // return attack;
        return attack;
    }
    public static void pickOppHand(Player p2, int attack) {
        System.out.print("Which of your opponent's hands do you want to attack?");
        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();
        int opponentHand = 1;
        while ((!str.toLowerCase().equals("left") && !str.toLowerCase().equals("right")) || (opponentHand <= 0)) {
            if (opponentHand <= 0)
                System.out.println("The hand you chose had 0 fingers, please choose the other hand.");
            else
                System.out.print("Incorrect entry, enter 'right' or 'left'.  ");
            str = scan.nextLine();
            if (!str.toLowerCase().equals("left") && !str.toLowerCase().equals("right"))
                break;
            if (str.toLowerCase().equals("right"))
                opponentHand = p2.getHand(true);
            else
                opponentHand = p2.getHand(false);
        }
        System.out.println("This is a test to see what happens to p2: " + p2.getName() + "   attack = " + attack);
        if (str.toLowerCase().equals("left"))
            p2.updateFingers(attack, false);
        else
            p2.updateFingers(attack, true);
    }
}
