class Graph {
    /**
     * 网格图 DFS
     */
    private void dfs(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != '1') {
            return;
        }
        grid[i][j] = '2';
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
    }

    /**
     * 需要更新参数的网格图 DFS
     */
    private int dfs(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != 1) {
            return 0;
        }
        grid[i][j] = 0;
        int size = 1;
        size += dfs(grid, i, j - 1);
        size += dfs(grid, i, j + 1);
        size += dfs(grid, i - 1, j);
        size += dfs(grid, i + 1, j);
        return size;
    }
    // 也可写成如下形式
    private static final int[][] DIRS = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}}; // 左右上下
    private int dfs(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != 1) {
            return 0; // 越界返回 0 而不是 size
        }
        grid[i][j] = 0;
        int size = 1;

        for (int[] d : DIRS) {
            int x = i + d[0];
            int y = j + d[1];
            size += dfs(grid, x, y);
        }
        return size;
    }

    /**
     * 网格图 BFS
     * 适用于需要计算最短距离（最短路）的题目
     */
    public int bfs(char[][] maze, int[] entrance) {
        int m = maze.length;
        int n = maze[0].length;
        boolean[][] visited = new boolean[m][n];
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] { entrance[0], entrance[1] });
        visited[entrance[0]][entrance[1]] = true;
        int ans = 0;
        while (!queue.isEmpty()) {
            ans++;
            Deque<int[]> curs = queue;
            queue = new ArrayDeque<>();
            for (int[] cur : curs) {
                int x = cur[0];
                int y = cur[1];
                for (int[] dir : DIRS) {
                    int nx = x + dir[0];
                    int ny = y + dir[1];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n && maze[nx][ny] == '.' && !visited[nx][ny]) {
                        if (nx == 0 || ny == 0 || nx == m - 1 || ny == n - 1) {
                            return ans;
                        }
                        visited[nx][ny] = true;
                        queue.offer(new int[] { nx, ny });
                    }
                }
            }

        }
        return -1;
    }
}