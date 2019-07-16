public class Person {
    private String name;
    private int position;
    public Person() {
        this.name = "";
        this.position = 0;
    }
    public Person(String name) {
        this.name = name;
        this.position = 0;
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
