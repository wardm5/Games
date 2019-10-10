import java.util.Scanner;
// import HelperMethod;
public class Settings {
    // DEFAULT VALUES
    private int AI_level;  // easy
    private boolean displayText;
    public Settings() {
        this.AI_level = 0;
        this.displayText = true;
    }
    public void updateAI(int level) {
        this.AI_level = level;
    }
    public void updateDisplayText(boolean val) {
        this.displayText = val;
    }
    public int getAI() {
        return this.AI_level;
    }
    public boolean getDisplayTest() {
        return this.displayText;
    }
    public String printDisplayTextSetting() {
        if (this.displayText)
            return "Show in game text";
        else
            return "Do not show in game text";
    }
    public String printAI_LevelSetting() {
        switch (this.AI_level) {
            case 0:
                return "Easy";
            case 2:
                return "Medium";
            case 3:
                return "Hard";
        }
        return "Error occurred";
    }
    public void viewSettings() {
        print("\nThe game settings are below:  ");
        print("AI:                  " + printAI_LevelSetting());
        print("Update Game Text:    " + printDisplayTextSetting());
        printNoNewLine("\nDo you want to change these game settings?  (enter 'yes', 'y', 'no', 'n')  ");
        // yes/no
        String str = userStringInput();
        while (!str.equals("yes") && !str.equals("no") && !str.equals("y") && !str.equals("n")) {
            print("Incorrect entry, enter 'yes', 'y', 'no', or 'n')   ");
            str = userStringInput();
        }

        if (str.equals("yes") || str.equals("y"))
            changeSettings();
            // ask which option the user wants to update
                // update option
        // repeat
        else
            return;

        // ask if done editing Options

        // if yes, go to homeScreen
        // else repeat
    }
    private void changeSettings() {
        // ask which option the user wants to update
            // update option
        System.out.println("testing 123");
        viewSettings();
    }

    // HELPER METHODS
    // HELPER METHODS
    private String standardizeUserInput(String s) {
        return s.trim().toLowerCase();
    }
    private boolean checkUserInput(String str) {
        return (!str.equals("left") && !str.equals("right") && !str.equals("r") && !str.equals("l"));
    }
    private void print() {
        System.out.println();
    }
    private void print(String str) {
        System.out.println(str);
    }
    private void printNoNewLine(String str) {
        System.out.print(str);
    }
    private String userStringInput() {
        Scanner scan = new Scanner(System.in);
        return standardizeUserInput(scan.nextLine());
    }
    private int userIntInput() {
        Scanner scan = new Scanner(System.in);
        return scan.nextInt();
    }
    private void top() {
        System.out.println("                                   | L | R |");
        System.out.println("               *-------------------*---*---*");
    }
    private String getModifiedName(String name) {
        if (name.length() > 13)
            return name.substring(0,13);
        else
            return String.format("%-13s", name);
    }
    private void bot() {
        System.out.println("               *-------------------*---*---*");
    }
}
