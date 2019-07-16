import java.util.*;
public class main {
    private int numOfPlayers;
    private ArrayList<Person> players;
    public static void main(String[] args) {
        StartGame();

    }
    public static StartGame() {
        Intro();
        SetupGame();
        PlayGame();
        End();
    }
    public static void Intro() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to Chutes and Ladders! ");
        newLine();
        System.out.println("Please enter how many players you want. (minimum number is 2, max is 6)");
        // while loop here
        numOfPlayers = scan.nextInt();
        newLine();
    }
    public static void SetupGame() {
        players = new ArrayList<>();

    }
    public static void newLine() {
        System.out.println();
    }
}
