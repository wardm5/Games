public class Options {
    // DEFAULT VALUES
    private int AI_level;  // easy
    private boolean displayText;
    public Options() {
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
}
