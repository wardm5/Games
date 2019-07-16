import java.util.*;
public class main {
    private static int numOfPlayers;
    private static ArrayList<Person> players;
    public static void main(String[] args) {
        StartGame();

    }
    public static void StartGame() {
        Intro();
        SetupGame();
        // PlayGame();
        // End();
    }
    public static void Intro() {
        System.out.println("Welcome to Chutes and Ladders! ");
        newLine();
    }
    public static void SetupGame() {
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
        System.out.println("Please enter player " + (i+1) + "'s name:   ");
    }
}
