import java.util.*;
public class main {
    private static int numOfPlayers;
    private static ArrayList<Person> players;
    public static void main(String[] args) {
        startGame();

    }
    public static void startGame() {
        intro();
        setupGame();
        newLine();
        // test();
        playGame();
        // End();
    }
    public static void intro() {
        System.out.println("Welcome to Chutes and Ladders! ");
        newLine();
    }
    public static void setupGame() {
        System.out.println("Please enter how many players you want. (minimum number is 2, max is 6)");
        setupGameHelper();
        newLine();
        setupPlayers();
    }
    public static void newLine() {
        System.out.println();
    }
    public static void setupGameHelper() {
        Scanner scan = new Scanner(System.in);
        numOfPlayers = scan.nextInt();
        while(numOfPlayers < 2 || numOfPlayers > 6) {
            System.out.print("Please re-enter the number of players:    ");
            numOfPlayers = scan.nextInt();
        }
    }
    public static void setupPlayers() {
        players = new ArrayList<>();
        for (int i = 0; i < numOfPlayers; i++) {
            setupPlayersHelper(i);
        }
    }
    public static  void setupPlayersHelper(int i) {
        System.out.print("Please enter player " + (i+1) + "'s name:    ");
        Scanner strScan = new Scanner(System.in);
        String name = strScan.next();
        Person temp = new Person(name);
        players.add(temp);
    }
    public static void playGame() {
        int maxPosition = 0;
        int currentPlayer = 0;
        while (maxPosition < 100) {
            for (int i = 0; i < numOfPlayers; i++) {
                int roll = diceRoll();
                Person temp = players.get(i);
                int position = temp.getPosition() + roll;
                temp.setPosition(position);
                if (maxPosition < position)
                    maxPosition = position;
                // positionCheck();
                // System.out.println(maxPosition + "    " + temp.getName());
            }
        }
    }
    public static int diceRoll() {
        int roll = (int)(6.0 * Math.random()) + 1;
        System.out.println(roll);
        return roll;
        // return (int)(5.0 * Math.random()) + 1;
    }

    // public static void test() {
    //     newLine();
    //     newLine();
    //     for (int i = 0; i < players.size(); i++) {
    //         System.out.println(players.get(i).getName());
    //     }
    // }
}
