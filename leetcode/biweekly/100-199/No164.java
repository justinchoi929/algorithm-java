class No164 {
    /**
     * 第 164 场双周赛
     * https://leetcode.cn/contest/biweekly-contest-164/
     */

    /**
     * 3663. 出现频率最低的数字
     */
    public int getLeastFrequentDigit(int n) {
        // 统计每个数字的出现次数
        int[] cnt = new int[10];
        while (n > 0) {
            cnt[n % 10]++;
            n /= 10;
        }

        // 找出现次数最小的数字
        int min = Integer.MAX_VALUE;
        int ans = 0;
        for (int i = 0; i < 10; i++) {
            int c = cnt[i];
            if (c > 0 && c < min) {
                min = c;
                ans = i;
            }
        }
        return ans;
    }

    /**
     * 3664. 两个字母卡片游戏
     */
    public int score(String[] cards, char x) {
        int[] cnt1 = new int[10]; // 存储 x* 型数据
        int[] cnt2 = new int[10]; // 存储 *x 型数据
        for (String card : cards) {
            char a = card.charAt(0);
            char b = card.charAt(1);
            if (a == x) {
                cnt1[b - 'a']++;
            }
            if (b == x) {
                cnt2[a - 'a']++;
            }
        }
        int[] res1 = getSumAndMax(cnt1, x);
        int[] res2 = getSumAndMax(cnt2, x);
        int sum1 = res1[0], max1 = res1[1];
        int sum2 = res2[0], max2 = res2[1];

        int xx = cnt1[x - 'a']; // cnt1[x - 'a'] 和 cnt2[x - 'a'] 结果是一样的
        int ans = 0;

        for (int k = 0; k <= xx; k++) {
            int score1 = getScore(sum1, max1, k);
            int score2 = getScore(sum2, max2, xx - k);
            ans = Math.max(ans, score1 + score2);
        }
        return ans;

    }

    private int[] getSumAndMax(int[] arr, char x) {
        int sum = 0, max = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i != x - 'a') { // xx 的情况单独讨论
                sum += arr[i];
                max = Math.max(max, arr[i]);
            }

        }
        return new int[]{sum, max};
    }

    private int getScore(int sum, int max, int k) {
        sum += k;
        max = Math.max(max, k);
        // return Math.min(sum/2, sum-max);
        // 如果 max 比其余元素个数 sum−max 还多，那么操作次数为其余元素个数 sum−max。否则操作次数为 sum / 2
        if (max > (sum - max)) {
            return sum - max;
        } else {
            return sum / 2;
        }
    }

    /**
     * 3665. 统计镜子反射路径数目
     */

    // 递归写法
    private static final int MOD = 1_000_000_007;

    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][][] memo = new int[m][n][2];
        for (int[][] mat : memo) {
            for (int[] row : mat) {
                Arrays.fill(row, -1); // -1 表示没有计算过
            }
        }
        return dfs(m - 1, n - 1, 0, memo, grid); // 从终点出发
    }

    private int dfs(int i, int j, int k, int[][][] memo, int[][] grid) {
        if (i < 0 || j < 0) { // 出界
            return 0;
        }
        if (i == 0 && j == 0) { // 到达起点
            return 1;
        }
        if (memo[i][j][k] != -1) { // 之前计算过
            return memo[i][j][k];
        }
        int res;
        if (grid[i][j] == 0) { // 没有镜子，随便走
            res = (dfs(i, j - 1, 0, memo, grid) + dfs(i - 1, j, 1, memo, grid)) % MOD;
        } else if (k == 0) { // 从下边过来
            res = dfs(i - 1, j, 1, memo, grid); // 反射到左边
        } else { // 从右边过来
            res = dfs(i, j - 1, 0, memo, grid); // 反射到上边
        }
        return memo[i][j][k] = res; // 记忆化
    }

    // 递推写法
    private static final int mod = (int) (1e9 + 7);

    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][][] dp = new int[m + 1][n + 1][2];
        dp[0][1][0] = dp[0][1][1] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    dp[i + 1][j + 1][0] = (dp[i][j + 1][1] + dp[i + 1][j][0]) % mod;
                    dp[i + 1][j + 1][1] = (dp[i][j + 1][1] + dp[i + 1][j][0]) % mod;
                } else {
                    dp[i + 1][j + 1][0] = dp[i][j + 1][1];
                    dp[i + 1][j + 1][1] = dp[i + 1][j][0];
                }
            }
        }
        return dp[m][n][0];
    }

    /**
     * 3666. 使二进制字符串全为 1
     */

}