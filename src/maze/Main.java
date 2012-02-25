package maze;

import java.io.File;
import java.io.FileReader;

public class Main {
    public static void main(String... args) {
        if (args.length == 0) {
            System.err.println("Usage Main <path to maze file>");
        }
        else {
            try {
                final Maze maze = new MazeLoader().loadMaze(args[0]);
                System.out.println("Maze loaded");
                System.out.println(maze.toString());
                final MazeTraverser traverser = new MazeTraverser();
                final Maze solvedMaze = traverser.traverse(maze);
                
                if (solvedMaze == null) {
                    System.out.println("No solution");
                }
                else {
                    System.out.print(solvedMaze.toString());
                }
            }
            catch (Exception e) {
                e.printStackTrace(System.err);
            }
        }
    }
}
