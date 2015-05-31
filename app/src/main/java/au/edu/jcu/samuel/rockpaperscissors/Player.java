package au.edu.jcu.samuel.rockpaperscissors;


public class Player {
    String name;
    int moves;
    public Player(String name, int moves) {
        this.name = name;
        this.moves = moves;
    }

    @Override
    public String toString() {
        return String.format("%s : Moves %d", name, moves);
    }

}
