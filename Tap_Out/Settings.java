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
}
