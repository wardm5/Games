public class Person {
    private String name;
    private int position;
    private boolean computer;
    public Person() {
        this.name = "";
        this.position = 0;
        this.computer = false;
    }
    public Person(String name) {
        this.name = name;
        this.position = 0;
        this.computer = false;
    }
    public String getName() {
        return this.name;
    }
    public int getPosition() {
        return this.position;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPosition(int position) {
        this.position = position;
    }
}
