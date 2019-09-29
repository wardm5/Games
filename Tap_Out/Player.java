public class Player {
    String name;
    int fingerCountLeft;
    int fingerCountRight;
    boolean isComputer;
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
    public Player(boolean isComputer) {
        this.name = name;
        this.fingerCountLeft = 1;
        this.fingerCountRight = 1;
        this.isComputer = isComputer;
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
        return (this.fingerCountLeft == 0 && this.fingerCountRight == 0);
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

}
