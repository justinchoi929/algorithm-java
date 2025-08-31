class No465 {
    /**
     * 第 465 场周赛
     * https://leetcode.cn/contest/weekly-contest-465/
     */

    /**
     * 3668. 重排完成顺序
     */
    public int[] recoverOrder(int[] order, int[] friends) {
        int[] ans = new int[friends.length];
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < friends.length; i++) {
            set.add(friends[i]);
        }
        int index = 0;
        for (int i = 0; i < order.length; i++) {
            if (set.contains(order[i])) {
                ans[index++] = order[i];
            }
        }
        return ans;
    }

    /**
     * 3669. k因数分解
     */
    static int bestDiff;
    static List<Integer> bestAns;
    public int[] minDifference(int n, int k) {
        bestDiff = Integer.MAX_VALUE;
        bestAns = new ArrayList<>();
        dfs(n, k, 1, new ArrayList<>());

        // 转换成数组并排序
        int[] ans = new int[bestAns.size()];
        for (int i = 0; i < bestAns.size(); i++) ans[i] = bestAns.get(i);
        Arrays.sort(ans);
        return ans;
    }

    private static void dfs(int n, int k, int start, List<Integer> path) {
        if (k == 1) {
            // 最后一个数必须能整除
            if (n >= start) {
                path.add(n);

                int minVal = Collections.min(path);
                int maxVal = Collections.max(path);
                int diff = maxVal - minVal;
                if (diff < bestDiff) {
                    bestDiff = diff;
                    bestAns = new ArrayList<>(path);
                }

                path.remove(path.size() - 1);
            }
            return;
        }

        // 尝试所有因子（包括1）
        for (int i = start; i <= n; i++) {
            if (n % i == 0) {
                path.add(i);
                dfs(n / i, k - 1, i, path);
                path.remove(path.size() - 1);
            }
        }
    }

    /**
     * 3670. 没有公共位的整数最大乘积
     */


    /**
     * 3671. 子序列美丽值求和
     */
}
