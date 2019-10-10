// This class is designed to store game infomration that will be used for a future API/analysis

public class GameStat {
    /*
        String winner - name
        int turns: totalTurns
    */
    private String winnerName;
    private int turns;

    public GameStat() {
        this.turns = 0;
        this.winnerName = "";
    }
    public GameStat(int turns) {
        this.turns = turns;
        this.winnerName = "";
    }
    public GameStat(String name, int turns) {
        this.turns = turns;
        this.winnerName = name;
    }
    public int getTurns() {
        return this.turns;
    }
    public String getWinnerName() {
        return this.winnerName;
    }
}
