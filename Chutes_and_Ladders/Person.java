public class Person {
    private String name;
    private int position;
    private boolean computer;
    //  Constructor
    public Person() {
        this.name = "";
        this.position = 0;
        this.computer = false;
    }
    //  Constructor with parameter for player's name
    public Person(String name) {
        this.name = name;
        this.position = 0;
        this.computer = false;
    }
    // getter for player's name
    public String getName() {
        return this.name;
    }
    // getter for player's position on the board
    public int getPosition() {
        return this.position;
    }
    // setter for player's name
    public void setName(String name) {
        this.name = name;
    }
    // setter for player's position
    public void setPosition(int position) {
        this.position = position;
    }
    // method to set the player as a computer 
    public void setComputer(boolean val) {
        computer = val;
    }
    // method to determine if the player is set as a computer
    public boolean isComputer() {
        return computer;
    }
}
