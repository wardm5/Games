public class Player {
    String name;
    int fingerCountLeft;
    int fingerCountRight;
    public Player() {
        this.name = "";
        this.fingerCountLeft = 1;
        this.fingerCountRight = 1;
    }
    public Player(String name) {
        this.name = name;
        this.fingerCountLeft = 1;
        this.fingerCountRight = 1;
    }
    public void showFingerCounts() {
        System.out.println("RIGHT hand has " + fingerCountRight + " fingers up. ");
        System.out.println("LEFT hand has " + fingerCountLeft + " fingers up. ");
        // System.out.print("LEFT HAND:   ");
        // for (int i = 0; i < fingerCountLeft; i++) {
        //     System.out.print("|");
        // }
        // System.out.print("     RIGHT HAND:   ");
        // for (int i = 0; i < fingerCountRight; i++) {
        //     System.out.print("|");
        // }
        System.out.println();
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
        this.fingerCountRight /= 2;
        this.fingerCountLeft = this.fingerCountRight;
    }

}
