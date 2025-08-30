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
}