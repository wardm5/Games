import java.util.*;
public class main {
    private static Player[] players = new Player[2];
    private static int numOfPlayers;
    public static void main(String[] args) {
        start();
        setup();
        playGame();
    }
    public static void start() {
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
    }
    public static void setup() {
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

        if (   players[p1].getLeftFingerCount() > 0 && players[p1].getLeftFingerCount() % 2 == 0
            && players[p1].getRightFingerCount() > 0 && players[p1].getRightFingerCount() % 2 == 0) {
            System.out.println("You have the option to split! Do you want to do this? (enter 'yes' or 'no')");
            //user input
            Scanner temp = new Scanner(System.in); // need to finish...

        }

        System.out.print("Which of your hands do you want to use to attack?? (enter 'right' or 'left')  ");
        Scanner scan = new Scanner(System.in);
        String temp = scan.nextLine();
        int attack = 0;
        while (!temp.toLowerCase().equals("left") && !temp.toLowerCase().equals("right")) {
            System.out.print("Incorrect entry, enter 'right' or 'left'.  ");
            temp = scan.nextLine();
        }

        if (temp.toLowerCase().equals("left"))
            attack = players[p1].attack(false);
        else
            attack = players[p1].attack(true);
        // System.out.print("Which of your opponent's hands do you want to attack?");



        if (temp.toLowerCase().equals("left"))
            players[p2].updateFingers(attack, false);
        else
            players[p2].updateFingers(attack, true);
    }
}
