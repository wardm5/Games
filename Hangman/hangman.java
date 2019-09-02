import java.util.*;
public class hangman {
    public static void main(String[] args) {
        System.out.println("\nPlease enter your word:   ");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        char[] arr = input.toCharArray();
        hangman(arr);

        System.out.println(input);
    }
    public static void hangman(char[] arr) {
        Scanner scan = new Scanner(System.in);
        LinkedHashMap<Character, Boolean> map = new LinkedHashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], false);
        }
        int count = 0;
        while (count < arr.length) {
            System.out.println("\nPlease enter a guess: ");
            String tempChar = scan.next();
            if (map.containsKey(tempChar.charAt(0))) {
                map.put(tempChar.charAt(0), true);
                count++;
                print(map);
            } else {
                System.out.print("\nNo value found\n");
                print(map);
            }
        }
        System.out.println("You found the word!");
    }
    public static void print(LinkedHashMap<Character, Boolean> map) {
        for (Character key: map.keySet()){
            if (map.get(key) == false) {
                System.out.print("-");
            } else {
                System.out.print(key);
            }

        }
        System.out.println();
    }
}
