import java.util.*;
import java.io.File;
import com.google.gson.Gson;
public class main {
    private static int numOfPlayers;
    private static int[] board;
    private static ArrayList<Person> players;
    private static API quiz;
    private static int turn;
    public static void main(String[] args) {
        /*  things to do:
                Get Quiz questions and answers from API: https://opentdb.com/api_config.php
                incorprate this in to the game, if player, then they need to answer the question for ladders (use slide) and slides (avoid slide)
                ensure that users have to roll dice (press enter on their turn)
                various other tweeks if needed.
         */

        // intro();    // intro to game, will only show up once even if the player wants to play again.
        // startGame();
        quiz = new API();
        System.out.println(quiz.getResponse());
        // Gson gson = new Gson(); // Or use new GsonBuilder().create();
        MyType target2 = gson.fromJson(json, MyType.class); // deserializes json into target2
    }
    public static void startGame() {
        setupGame();
        int winner = playGame();
        end(winner);
    }
    public static void intro() {
        newLine();
        newLine();
        System.out.println("***************************************************************************************************************\n"
                         + "*                                                                                                             *\n"
                         + "*                                   Welcome to Chutes and Ladders!                                            *\n"
                         + "*                                                                                                             *\n"
                         + "*  Shoots and Ladders, otherwise known as Snakes and Ladders, is a game of chance for which you roll a dice   *\n"
                         + "*  and move accross a board of 100 spaces. If you land on a 'ladder', you advance up the board. If you hit a  *\n"
                         + "*  'slide' on the other hand, you go backwards. The first person to get to 100 wins. Good luck!               *\n"
                         + "*                                                                                                             *\n"
                         + "***************************************************************************************************************"
                        );
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
        System.out.print("Do you want to use quiz questions to use ladders and avoid shoots?");
        // Scanner scan = new Scanner(System.in);
        String response = scan.next();
        while (!response.toLowerCase().equals("yes") && !response.toLowerCase().equals("no")) {
            System.out.println("Please enter a valid response of either 'yes' or 'no'.");
            response = scan.next();
        }
        if (response.toLowerCase().equals("yes"))
            setupAPI();
    }
    public static void setupAPI() {

    }
    public static void setupBoard() {
        board = new int[101];
        try {
            File file = new File("chutes_ladders.txt");
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
            System.out.print("Please enter player " + (i+1) + "'s name:    ");
            Scanner strScan = new Scanner(System.in);
            String name = strScan.next();
            Person temp = new Person(name);
            System.out.println("Do you want this player to be a human? Enter 'yes' or 'no'.");
            Scanner scan = new Scanner(System.in);
            String response = scan.next();
            while (!response.toLowerCase().equals("yes") && !response.toLowerCase().equals("no")) {
                System.out.println("Please enter a valid response of either 'yes' or 'no'.");
                response = scan.next();
            }
            if (response.toLowerCase().equals("no")) {
                temp.setComputer(true);
            }
            players.add(temp);
        }
    }
    public static int playGame() {
        newLine();
        int turn = 1;
        int maxPosition = 0;
        while (maxPosition <= 100) {
            newLine();
            System.out.println("************* TURN " + turn + " *************");
            for (int i = 0; i < numOfPlayers; i++) {
                Person temp = players.get(i);
                String name = temp.getName();
                boolean comp = temp.isComputer();
                if (!comp)
                    notifyPlayer(name);
                int roll = diceRoll(name);
                int position = temp.getPosition();
                position = checkPosition(position, roll, name);
                temp.setPosition(position);
                if (maxPosition < position)
                    maxPosition = position;
                if (position == 100)
                    return i;  // return winner index
            }
            turn++;
        }
        return -1; // error code
    }
    public static void notifyPlayer(String name) {
        System.out.println(name + ", it is your turn to play! Press enter to roll the dice!");
        Scanner scan = new Scanner(System.in);
        scan.nextLine();
    }
    public static int diceRoll(String name) {
        int roll = (int)(6.0 * Math.random()) + 1;
        System.out.println(name + " rolled a " + roll + "!");
        return roll;
    }
    public static int checkPosition(int position, int roll, String name) {
        if (roll + position > 100) {
            System.out.println(name + ", you did not get to 100 this turn...");
            System.out.println("Your current position is:    " + position);
            newLine();
            return position;
        }
        position = roll + position;
        int newPosition = board[position] + position;
        if (board[position] > 0) {
            System.out.println("Lucky you " + name + ", you landed on a ladder, you will advance " + board[position] + " spaces!");
            System.out.println("Your new position is:    " + newPosition);
        } else if (board[position] < 0) {
            System.out.println("Oh nooo " + name + "! You landed on a slide, you go backwards " + (-board[position]) + " spaces!");
            System.out.println("Your new position is:    " + newPosition);
        } else {
            System.out.println(name + ", you did not land on a shoot or ladder this turn...");
            System.out.println("Your new position is:    " + newPosition);
        }
        newLine();
        return newPosition;
    }
    public static void end(int i) {
        System.out.println("Winner winner, lasagna dinner!!\n"
                         + "Congratulations " + players.get(i).getName() + ", you reached 100 and won the game!");
        newLine();
        System.out.println("Do you want to play again? Enter 'yes' or 'no'.");
        Scanner scan = new Scanner(System.in);
        String response = scan.next();
        while (!response.toLowerCase().equals("yes") && !response.toLowerCase().equals("no")) {
            System.out.println("Please enter a valid response of either 'yes' or 'no'.");
            response = scan.next();
        }
        if (response.toLowerCase().equals("yes")) {
            startGame();
        } else {
            System.out.println("Goodbye! Hope you had fun! ");
            // quiz.disconnect();   // disconnect from website when done with the game.
            return;
        }
    }
}
