package maze;

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class MazeLoader {
    public Maze loadMaze(String fileName) throws Exception {
        final Maze result = new Maze();
        final FileReader mazeFile = new FileReader(fileName);
        try {
            final Scanner scanner = new Scanner(mazeFile);

            while (scanner.hasNextLine()) {
                result.nextRow();
                final String line = scanner.nextLine();
                result.push(line);
            }
            scanner.close();
        }
        finally {
            mazeFile.close();
        }
        return result;
    }
}
