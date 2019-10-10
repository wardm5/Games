import java.util.Scanner;
public class HelperMethod {
    // HELPER METHODS
    public static String standardizeUserInput(String s) {
        return s.trim().toLowerCase();
    }
    public static boolean checkUserInput(String str) {
        return (!str.equals("left") && !str.equals("right") && !str.equals("r") && !str.equals("l"));
    }
    public static void print() {
        System.out.println();
    }
    public static void print(String str) {
        System.out.println(str);
    }
    public static void printNoNewLine(String str) {
        System.out.print(str);
    }
    public static String userStringInput() {
        Scanner scan = new Scanner(System.in);
        return standardizeUserInput(scan.nextLine());
    }
    public static int userIntInput() {
        Scanner scan = new Scanner(System.in);
        return scan.nextInt();
    }
    public static void top() {
        System.out.println("                                   | L | R |");
        System.out.println("               *-------------------*---*---*");
    }
    public static String getModifiedName(String name) {
        if (name.length() > 13)
            return name.substring(0,13);
        else
            return String.format("%-13s", name);
    }
    public static void bot() {
        System.out.println("               *-------------------*---*---*");
    }
}
