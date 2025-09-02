class MonotoneStack {
    /**
     * 单调栈
     *
     * 如何理解：
     * 把数组想象成一列山峰，站在 a[i] 的山顶，仰望两侧比 a[i] 更高的山峰，是看不到高山背后的矮山的，只能看到一座座更高的山峰。
     * 此外，如果一座山无法看到，那么在后续的遍历中，就永远无法看到这座山了。
     * 比如从右到左遍历，现在右边无法看到的山，继续向左也无法看到。（注意只看比 a[i] 更高的山峰）
     * 这启发我们引入一个底大顶小（远大近小）的单调栈，入栈时不断弹出栈顶元素，直到栈顶比当前元素大。弹出的元素就是被 a[i] 挡住的，永远无法看到的山。
     *
     * 时间复杂度 O(n)
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] ans = new int[n];
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!q.isEmpty() && temperatures[i] > temperatures[q.peek()]) {
                int j = q.pop();
                ans[j] = i - j;
            }
            q.push(i);
        }
        return ans;
    }
}