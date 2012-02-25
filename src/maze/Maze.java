package maze;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Maze implements Cloneable {
    public static final Character WALL = '#';
    public static final Character START = 's';
    public static final Character END = 'f';
    public static final Character TRAVERSABLE = ' ';
    public static final Character PATH = '.';
    public static final Character BAD = 'x';

    protected List<List<Character>> maze = new ArrayList<List<Character>>();
    protected Stack<int[]> lastPositions = new Stack<int[]>();
    protected int[] position = {0, 0};

    public void push(Character c) {
        int currentRow = maze.size() - 1;

        maze.get(currentRow).add(c);

        if (c.equals(START)) {
            position = new int[]{currentRow, maze.get(currentRow).size() - 1};
        }
    }

    public void push(String str) {
        for (int i = 0; i < str.length(); i++) {
            push(str.charAt(i));
        }
    }
    
    public void nextRow() {
        maze.add(new ArrayList<Character>());
    }

    public void set(int x, int y, char c) {
        maze.get(x).set(y, c);
    }

    protected Character getAbsolute(int x, int y) {
        // anything out-of-bounds is considered a WALL. So we can't traverse it.
        if (x < 0 || y < 0 || x > maze.size() - 1 || y > maze.get(x).size() - 1) return WALL;
        return maze.get(x).get(y);
    }

    public Character get(int[] pair) {
        return get(pair[0], pair[1]);
    }
    
    // get an offset from the current position
    public Character get(int offsetX, int offsetY) {
        return getAbsolute(position[0] + offsetX, position[1] + offsetY);
    }

    public boolean move(int[] pair) {
        return move(pair[0], pair[1]);
    }
    
    // move to the given offset of the current position
    public boolean move(int offsetX, int offsetY) {
        lastPositions.push(position);
        position = new int[] {position[0] + offsetX, position[1] + offsetY};
        checkState();
        if (get(0, 0).equals(END)) {
            return true;
        }
        else {
            set(position[0], position[1], PATH);
            return false;
        }
    }

    /**
     * Undo the last move.
     * @return +true+ if the move was undone. +false+ if no undo is possible
     */
    public boolean unMove() {
        set(position[0], position[1], BAD);
        if (lastPositions.empty()) {
            return false;
        }
        else {
            position = lastPositions.pop();
            return true;
        }
    }

    // check that we moved to a legal spot. Otherwise throw an exception (debugging)
    protected void checkState() {
        Character currentChar = get(0, 0);
        if (!(currentChar.equals(START) || currentChar.equals(END) || currentChar.equals(TRAVERSABLE))) {
            throw new RuntimeException("Position " + position[0] + "," + position[1] + " is occupied");
        }
    }

    @Override
    public String toString() {
        final StringBuilder out = new StringBuilder(maze.size() * maze.size());
        
        for (final List<Character> line : maze) {
            for (final Character c : line) {
                out.append(c.equals(BAD) ? TRAVERSABLE : c);
            }
            out.append('\n');
        }
        
        return out.toString();
    }

    @Override
    protected Maze clone() throws CloneNotSupportedException {
        return (Maze) super.clone();    //To change body of overridden methods use File | Settings | File Templates.
    }
}
