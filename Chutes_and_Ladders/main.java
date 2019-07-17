import java.util.*;
import java.io.File;
public class main {
    private static int numOfPlayers;
    private static int[] board;
    private static ArrayList<Person> players;
    public static void main(String[] args) {
        startGame();
    }
    public static void startGame() {
        intro();
        setupGame();
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
        setupBoard();
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
    public static void setupBoard() {
        board = new int[101];
        try {
            // URL url = getClass().getResource("chutes_ladders.txt");
            // File file = new File(url.getPath());
            File file = new File("chutes_ladders.txt");
            // File file = new File(".//Games//Chutes_and_Ladders//chutes_ladders.txt");
            Scanner fileScan = new Scanner(file);
            while(fileScan.hasNextLine()) {
                int index = fileScan.nextInt();
                int position = fileScan.nextInt();
                board[index] = position;
                if (!fileScan.hasNextInt())
                    break;
            }
        } catch (Exception e){
            System.out.println(e);
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
    public static int playGame() {
        int maxPosition = 0;
        while (maxPosition < 100) {
            for (int i = 0; i < numOfPlayers; i++) {
                Person temp = players.get(i);
                String name = temp.getName();
                int roll = diceRoll(name);
                int position = temp.getPosition() + roll;
                position = checkPosition(position, name) + position;
                temp.setPosition(position);
                if (maxPosition < position)
                    maxPosition = position;
                else if (position == 100)
                    return i;  // return winner index
            }
        }
        return -1; // error code
    }
    public static int diceRoll(String name) {
        int roll = (int)(6.0 * Math.random()) + 1;
        System.out.println(name + " rolled a " + roll + "!");
        return roll;
    }
    public static int checkPosition(int position, String name) {
        if (board[position] > 0) {
            System.out.println("Lucky you " + name + ", you landed on a slide, you will advance " + board[position] + " spaces!");
            System.out.println("Your new position is:    " + (board[position] + position));
        } else if (board[position] < 0) {
            System.out.println("Oh nooo " + name + "! You landed on a slide, you go backwards " + (-board[position]) + " spaces!");
            System.out.println("Your new position is:    " + (board[position] + position));
        } else {
            System.out.println(name + ", you did not land on a shoot or ladder this turn...");
            System.out.println("Your new position is:    " + (board[position] + position));
        }
        newLine();
        if (position + board[position] > 100)
            return position;
        return board[position];
    }
}
