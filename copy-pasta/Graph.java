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
    private static final int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}}; // 左右上下

    private int dfs(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != 1) {
            return 0; // 越界返回 0 而不是 size
        }
        grid[i][j] = 0;
        int size = 1;

        for (int[] d : dirs) {
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
    private static final int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}}; // 左右上下

    public int bfs(char[][] maze, int[] entrance) {
        int m = maze.length;
        int n = maze[0].length;
        boolean[][] visited = new boolean[m][n];
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{entrance[0], entrance[1]});
        visited[entrance[0]][entrance[1]] = true;
        int ans = 0;
        while (!q.isEmpty()) {
            ans++;
            Deque<int[]> curs = q;
            q = new ArrayDeque<>();
            for (int[] cur : curs) {
                for (int[] dir : dirs) {
                    int x = cur[0] + dir[0];
                    int y = cur[1] + dir[1];
                    if (x >= 0 && x < m && y >= 0 && y < n && maze[x][y] == '.' && !visited[x][y]) {
                        if (x == 0 || y == 0 || x == m - 1 || y == n - 1) {
                            return ans;
                        }
                        visited[x][y] = true;
                        q.offer(new int[]{x, y});
                    }
                }
            }
        }
        return -1;
    }

    /**
     * 网格图 Dijkstra 算法（朴素版）
     * 1. 选距离源点最近且未访问的点
     * 2. 标记该点访问过
     * 3. 更新所有未访问节点到源点的距离
     */
    public int networkDelayTime(int[][] times, int n, int k) {
        final int inf = Integer.MAX_VALUE / 2;
        int[][] g = new int[n][n]; // 存储从 i 到 j 的边权
        boolean[] visited = new boolean[n];
        for (int[] row : g) {
            Arrays.fill(row, inf);
        }
        for (int[] t : times) {
            g[t[0] - 1][t[1] - 1] = t[2];
        }

        int[] dis = new int[n]; // dis[i] 表示 i 到源点的最短路径
        Arrays.fill(dis, inf);
        dis[k - 1] = 0;
        int minDis = inf;
        for (int i = 0; i < n; i++) {
            minDis = inf;
            int cur = 0;
            for (int j = 0; j < n; j++) {
                if (!visited[j] && dis[j] < minDis) {
                    minDis = dis[j];
                    cur = j;
                }
            }
            if (minDis == inf) {
                return -1;
            }

            visited[cur] = true;
            for (int j = 0; j < n; j++) {
                if (!visited[j]) {
                    dis[j] = Math.min(minDis + g[cur][j], dis[j]);
                }
            }
        }

        return minDis;
    }

    /**
     * 网格图 Dijkstra 算法（堆优化版）
     * 1. 选距离源点最近且未访问的点
     * 2. 标记该点访问过
     * 3. 更新所有未访问节点到源点的距离
     */
    public int networkDelayTime(int[][] times, int n, int k) {
        List<int[]>[] g = new ArrayList[n]; // 邻接表存储图
        Arrays.setAll(g, i -> new ArrayList<>());
        int[] dis = new int[n];
        Arrays.fill(dis, Integer.MAX_VALUE);
        for (int[] t : times) {
            g[t[0] - 1].add(new int[]{t[1] - 1, t[2]});
        }

        dis[k - 1] = 0;
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> (a[1] - b[1]));
        int left = n;
        q.offer(new int[]{k - 1, 0});
        int minDis = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int d = cur[1];
            if (d > dis[x]) {
                continue;
            }
            minDis = d;
            left--;
            for (int[] e : g[x]) {
                int y = e[0];
                if (minDis + e[1] < dis[y]) {
                    dis[y] = minDis + e[1];
                    q.offer(new int[]{y, dis[y]});
                }
            }
        }
        return left == 0 ? minDis : -1;
    }

    /**
     * 网格图 0-1 BFS
     * 0-1 BFS 本质是对 Dijkstra 算法的优化。
     * 因为边权只有 0 和 1，我们可以把最小堆换成双端队列.
     * 遇到 0 边权就加入队首，遇到 1 边权就加入队尾，这样可以保证队首总是最小的，就不需要最小堆了
     */
    private static final int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    private static final int inf = 0x3f3f3f3f;

    public int minimumObstacles(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dis = new int[m][n];
        for (int[] row : dis) {
            Arrays.fill(row, inf);
        }
        Deque<int[]> q = new ArrayDeque<>();
        dis[0][0] = 0;
        q.addFirst(new int[]{0, 0});
        while (!q.isEmpty()) {
            int[] cur = q.pollFirst();
            int i = cur[0], j = cur[1];
            int d = dis[i][j];

            for (int[] dir : dirs) {
                int x = i + dir[0];
                int y = j + dir[1];
                if (x >= 0 && x < m && y >= 0 && y < n) {
                    int cost = grid[x][y];
                    int newDis = d + cost;
                    if (newDis < dis[x][y]) {
                        dis[x][y] = newDis;
                        if (cost == 0) {
                            q.addFirst(new int[]{x, y});
                        } else {
                            q.addLast(new int[]{x, y});
                        }
                    }
                }
            }
        }
        return dis[m - 1][n - 1];
    }
}