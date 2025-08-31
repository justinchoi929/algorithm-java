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
        int[] cnt1 = new int[10]; // 题目保证只有前 10 个小写字母
        int[] cnt2 = new int[10];
        for (String s : cards) {
            // 统计形如 x? 的每个 ? 的出现次数
            char c0 = s.charAt(0);
            char c1 = s.charAt(1);
            if (c0 == x) {
                cnt1[c1 - 'a']++;
            }
            // 统计形如 ?x 的每个 ? 的出现次数
            if (c1 == x) {
                cnt2[c0 - 'a']++;
            }
        }

        int[] res1 = getSumAndMax(cnt1, x);
        int[] res2 = getSumAndMax(cnt2, x);
        int sum1 = res1[0], max1 = res1[1];
        int sum2 = res2[0], max2 = res2[1];

        int cntXX = cnt1[x - 'a'];
        int ans = 0;
        // 枚举分配 k 个 xx 给第一组，其余的 xx 给第二组
        for (int k = 0; k <= cntXX; k++) {
            int score1 = calcScore(sum1, max1, k);
            int score2 = calcScore(sum2, max2, cntXX - k);
            ans = Math.max(ans, score1 + score2);
        }
        return ans;
    }

    // 计算除了 x 以外的出现次数之和 sum，出现次数最大值 mx
    private int[] getSumAndMax(int[] cnt, char x) {
        int sum = 0, mx = 0;
        for (int i = 0; i < cnt.length; i++) {
            if (i != x - 'a') {
                sum += cnt[i];
                mx = Math.max(mx, cnt[i]);
            }
        }
        return new int[]{sum, mx};
    }

    // 计算这一组在得到 k 个 xx 后的得分
    private int calcScore(int sum, int mx, int k) {
        sum += k;
        mx = Math.max(mx, k);
        return Math.min(sum / 2, sum - mx);
    }

    /**
     * 3665. 统计镜子反射路径数目
     */
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

    /**
     * 3666. 使二进制字符串全为 1
     */

}