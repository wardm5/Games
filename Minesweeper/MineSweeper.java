import java.util.*;
public class MineSweeper {
    private int smallBombs = 10;
    private int mediumBoms = 40;
    private int largeBombs = 99;
    public static void main(String[] args) {
        System.out.println("YES");
        char[][] test = setup();
        System.out.println("hello");
        System.out.println("Setup complete, board size is " + test.length + " by " + test[0].length + ". ");
        for (int i = 0; i < test[0].length; i++) {
            for (int j = 0; j < test.length; j++) {
                System.out.print(test[j][i] + " ");
            }
            System.out.println();
        }
        printBoard(test);

    }
    public static char[][] setup() {
        System.out.println("Hello and welcome to MineSweeper! \n");
        System.out.print("Would you like to play on a small (8X8) with 10 mines, medium (16X16) with 40 mines, or large (16X30) with 99 mines?");
        Scanner scan = new Scanner(System.in);
        int result = boardInput(scan);
        return boardSetup(result);
    }
    public static int boardInput(Scanner scan) {
        System.out.print("\n\nPlease select by typing in either 'beginner', 'intermediate', or 'expert'. ");
        String input = scan.nextLine();
        System.out.println(input);
        input = input.toLowerCase();
        while (!input.equals("beginner") && !input.equals("intermediate") && !input.equals("expert")) {
            System.out.print("Incorrect value, please select by typing in either 'beginner', 'intermediate', or 'expert'. ");
            input = scan.nextLine();
            input = input.toLowerCase();
            System.out.println();
        }
        if (input.equals("beginner")) {
            return 0;
        } else if (input.equals("intermediate")) {
            return 1;
        } else {
            return 2;
        }
    }
    public static char[][] boardSetup(int num) {
        System.out.println("Yolo");
        char[][] arr = null;
        if (num == 0) {
            arr = new char[8][8];
            int x1 = 7;
            int y1 = 0;
            int rand1 = (int)(Math.random() * x1) + y1;
            int rand2 = (int)(Math.random() * x1) + y1;
            for (int i = 0; i < 10; i++) {
                while (arr[rand1][rand2] != 'x') {
                    rand1 = (int)(Math.random() * x1) + y1;
                    rand2 = (int)(Math.random() * x1) + y1;
                }
                arr[x1][y1] = 'x';  // bomb
            }
        }
        if (num == 1) {
            arr = new char[16][16];
            int x1 = 15;
            int y1 = 0;
            int rand1 = (int)(Math.random() * x1) + y1;
            int rand2 = (int)(Math.random() * x1) + y1;
            for (int i = 0; i < 40; i++) {
                while (arr[x1][y1] != 'x') {
                    rand1 = (int)(Math.random() * x1) + y1;
                    rand2 = (int)(Math.random() * x1) + y1;
                }
                arr[x1][y1] = 'x';  // bomb
            }
        }
        if (num == 2) {
            System.out.println("hi there");
            arr = new char[30][16];
            int x1 = 29;
            int x0 = 0;
            int y1 = 15;
            int y0 = 0;
            int x = (int)(Math.random() * x1) + x0;
            int y = (int)(Math.random() * y1) + y0;
            for (int i = 0; i < 99; i++) {
                while (arr[x][y] == 'x') {
                    x = (int)(Math.random() * x1) + x0;
                    y = (int)(Math.random() * y1) + y0;
                }
                arr[x][y] = 'x';  // bomb
            }
        }
        return arr;
    }
    public static void printBoard(char[][] arr) {
        System.out.println("   | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 | ");
        System.out.println("   +----------------------------------------+ ");
        System.out.println(" 1 |   |   |   |   |   |   |   |   |   |    | ");
        System.out.println("   +----------------------------------------+ ");
        System.out.println(" 2 |   |   |   |   |   |   |   |   |   |    | ");
        System.out.println("   +----------------------------------------+ ");
        System.out.println(" 3 |   |   |   |   |   |   |   |   |   |    | ");
        System.out.println("   +----------------------------------------+ ");
        System.out.println(" 4 |   |   |   |   |   |   |   |   |   |    | ");
        System.out.println("   +----------------------------------------+ ");
        System.out.println(" 5 |   |   |   |   |   |   |   |   |   |    | ");
        System.out.println("   +----------------------------------------+ ");
        System.out.println(" 6 |   |   |   |   |   |   |   |   |   |    | ");
        System.out.println();
        System.out.print("    |");
        for (int i = 1; i <= arr.length; i++) {
            if (i < 10) {
                System.out.print("  " + i + " |");
            } else {
                System.out.print(" " + i + " |");
            }
        }
        System.out.println();
        for (int i = 0; i < arr[0].length; i++) {
            if (i < 10) {
                System.out.print("     +");
            } else {
                System.out.print("    +");
            }
            for (int j = 1; j < arr.length; j++) {
                System.out.print("----");
            }
            System.out.println("+ ");
            System.out.print("  " + i + " |");
            for (int j = 0; j < arr.length; j++) {
                System.out.print(" " + arr[j][i] + "  |");
            }
            System.out.println();
        }

        // for (int i = 0; i < test.length; i++) {
        //     for (int j = 0; j < test[i].length; j++) {
        //         System.out.print(test[i][j] + " ");
        //     }
        //     System.out.println();
        // }
    }

}
