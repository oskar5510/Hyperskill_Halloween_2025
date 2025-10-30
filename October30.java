/*
 * Task: You Can (not) Escape
 *
 * Description:
 * You are given a 2D map of a mansion represented as characters:
 *   - '#' → a wall (impassable)
 *   - '.' → a walkable floor
 *   - 'P' → your starting point
 *   - 'G' → the garage (destination)
 *
 * Your goal is to find the **shortest path** from 'P' to 'G'.
 * The path should be represented as a string of movement directions:
 *   U (Up), D (Down), L (Left), R (Right)
 * Example: "UDLRRLDU"
 *
 * Multiple shortest paths may exist — any valid shortest path is acceptable.
 *
 * Approach:
 * 1. Read the input map into a 2D character array.
 * 2. Locate the starting point 'P' on the map.
 * 3. Perform a **Breadth-First Search (BFS)** to explore the mansion grid:
 *    - Each node stores its position (row, col) and the path taken to reach it.
 *    - BFS ensures the first time we reach 'G' corresponds to the shortest path.
 * 4. When 'G' is found, return the path string.
 * 5. If no path exists, return "No path found".
 *
 * Details:
 * - `visited[][]` prevents revisiting the same cells.
 * - Movement directions are handled via arrays:
 *     dr = {-1, 1, 0, 0}   → Up, Down, Right, Left (rows)
 *     dc = {0, 0, 1, -1}   → Up, Down, Right, Left (cols)
 * - Each valid move adds a new `Point` to the BFS queue, updating its path.
 *
 * Example Input:
 * #######
 * #P...G#
 * #.#.#.#
 * #######
 *
 * Output (one possible result):
 * RRRR
 *
 * Key Points:
 * - Uses BFS for optimal shortest path discovery in grid-based maps.
 * - Maintains direction tracking by building a string as traversal progresses.
 * - Works efficiently even for large grids due to queue-based exploration.
 *
 * Output:
 * Prints the sequence of moves (e.g., "UDLRRDLU") representing the shortest path from P to G.
 */


void main() throws IOException {
    File dataset = new File("October30_dataset.txt");

    List<String> lines = Files.readAllLines(dataset.toPath());
    char[][] map = new char[lines.size()][];
    for (int i = 0; i < lines.size(); i++) {
        map[i] = lines.get(i).toCharArray();
    }

    int startRow = -1, startCol = -1;
    for (int i = 0; i < map.length; i++) {
        for (int j = 0; j < map[i].length; j++) {
            if (map[i][j] == 'P') {
                startRow = i;
                startCol = j;
                break;
            }
        }
    }

    String path = findShortestPath(map, startRow, startCol);
    System.out.println(path);
}

String findShortestPath(char[][] map, int startRow, int startCol) {
    int rows = map.length;
    int cols = map[0].length;
    boolean[][] visited = new boolean[rows][cols];
    Queue<Point> queue = new LinkedList<>();

    visited[startRow][startCol] = true;
    queue.add(new Point(startRow, startCol, ""));

    int[] dr = {-1, 1, 0, 0}; // Up, Down, Right, Left
    int[] dc = {0, 0, 1, -1};
    char[] move = {'U', 'D', 'R', 'L'};

    while (!queue.isEmpty()) {
        Point current = queue.poll();

        if (map[current.row()][current.col()] == 'G') {
            return current.path();
        }

        for (int i = 0; i < 4; i++) {
            int newRow = current.row() + dr[i];
            int newCol = current.col() + dc[i];

            if (isValid(newRow, newCol, rows, cols, map, visited)) {
                visited[newRow][newCol] = true;
                queue.add(new Point(newRow, newCol, current.path() + move[i]));
            }
        }
    }

    return "No path found";
}

boolean isValid(int row, int col, int rows, int cols, char[][] map, boolean[][] visited) {
    return row >= 0 && row < rows && col >= 0 && col < cols &&
            map[row][col] != '#' && !visited[row][col];
}

record Point(int row, int col, String path) {
}