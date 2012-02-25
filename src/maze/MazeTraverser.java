package maze;

public class MazeTraverser {
    final static int[][] positionsToCheck = new int[][] {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };

    /**
     * Traverse a {@link Maze} and return a solved {@link Maze}
     *
     * @param toTraverse The maze to traverse
     * @return A solved Maze. null is returned if no solution exists.
     */
    public Maze traverse(final Maze toTraverse) {
        final Maze maze;
        try {
            // it's not a good idea to modify your arguments.
            maze = toTraverse.clone();

            while (!maze.get(0, 0).equals(Maze.END)) {
                boolean moved = false;

                // try moving
                for (final int[] positionToCheck : positionsToCheck) {
                    final Character c = maze.get(positionToCheck);
                    if (c.equals(Maze.TRAVERSABLE) || c.equals(Maze.END)) {
                        maze.move(positionToCheck);
                        moved = true;
                        break; // we made our move
                    }
                }

                // there was nowhere to go
                if (!moved) {
                    // if we cannot un move, it means that we have no moves left, and there is no solution
                    if (!maze.unMove()) {
                        return null;
                    }
                }
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

        return maze;
    }
}
