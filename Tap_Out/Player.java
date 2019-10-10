public class Player {
    final String name;
    int fingerCountLeft;
    int fingerCountRight;
    final boolean isComputer;
    AI ai;
    public Player() {  // never really use this...
        this.name = "";
        this.fingerCountLeft = 1;
        this.fingerCountRight = 1;
        this.isComputer = true;
    }
    public Player(String name) {
        this.name = name;
        this.fingerCountLeft = 1;
        this.fingerCountRight = 1;
        this.isComputer = false;
    }
    public Player(String name, boolean isComputer) {
        this.name = name;
        this.fingerCountLeft = 1;
        this.fingerCountRight = 1;
        if (isComputer) {
            this.isComputer = isComputer;
            ai = new AI();
        }
    }
    public Player(boolean isComputer) {
        this.name = name;
        this.fingerCountLeft = 1;
        this.fingerCountRight = 1;
        this.isComputer = isComputer;
    }
    public void setComputer(boolean isComputer) {
        if (isComputer) {
            this.isComputer = isComputer;
            ai = new AI();
        }
    }
    public void showFingerCounts() {
        System.out.println("RIGHT:  " + fingerCountRight + "  ");
        System.out.println("LEFT:   " + fingerCountLeft + "  ");
        System.out.println();
    }
    public void updatePlayerName(String name) {
        this.name = name;
    }
    public void updateFingers(int n, boolean leftOrRight) {  // left = false, right = true
        if (leftOrRight) {
            fingerCountRight += n;
            if (fingerCountRight >= 5)
                fingerCountRight = 0;
        } else {
            fingerCountLeft += n;
            if (fingerCountLeft >= 5)
                fingerCountLeft = 0;
        }
    }
    public int attack(boolean leftOrRight) {
        if (leftOrRight && fingerCountRight > 0)
            return fingerCountRight;
        else if (!leftOrRight && fingerCountLeft > 0)
            return fingerCountLeft;
        else return -1;
    }
    public String getName() {
        return this.name;
    }
    public boolean hasLost() {
        return (this.fingerCountLeft <= 0 && this.fingerCountRight <= 0);
    }
    public int getLeftFingerCount() {
        return fingerCountLeft;
    }
    public int getRightFingerCount() {
        return fingerCountRight;
    }
    public void split() {
        this.fingerCountRight = (this.fingerCountRight + this.fingerCountLeft)/2;
        this.fingerCountLeft = this.fingerCountRight;
    }
    public int getHand(boolean check) {
        if (check)
            return fingerCountRight;
        return fingerCountLeft;
    }
    public boolean getIsComputerSetting() {
        return this.isComputer;
    }

}
