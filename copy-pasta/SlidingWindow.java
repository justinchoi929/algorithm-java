class SlidingWindow {
    /**
     * 定长滑窗套路
     * 窗口右端点在 i 时，由于窗口长度为 k，所以窗口左端点为 i−k+1。
     * <p>
     * 我总结成三步：入-更新-出。
     * <p>
     * 入：下标为 i 的元素进入窗口，更新相关统计量。如果窗口左端点 i−k+1<0，即 i<k−1，则尚未形成第一个窗口，重复第一步。
     * 更新：更新答案。一般是更新最大值/最小值。
     * 出：下标为 i−k+1 的元素离开窗口，更新相关统计量，为下一个循环做准备。
     * 以上三步适用于所有定长滑窗题目。
     * <p>
     * 作者：灵茶山艾府
     * 链接：https://leetcode.cn/problems/maximum-number-of-vowels-in-a-substring-of-given-length/solutions/2809359/tao-lu-jiao-ni-jie-jue-ding-chang-hua-ch-fzfo/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int fixedSizeSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int ans = 0;
        int tmp;
        for (int i = 0; i < s.length; i++) {
            // 1. 进入窗口
            if (/*条件*/) {
                // 进入窗口操作
                tmp++;
            }
            if (i < k - 1) { // 窗口大小不足 k
                continue;
            }
            // 2. 更新答案
            ans = Math.max(ans, vowel);
            // 3. 离开窗口，为下一个循环做准备
            char out = s[i - k + 1];
            if (/*条件*/) {
                // 出窗口操作
                vowel--;
            }
        }
        return ans;
    }

    /**
     * 不定长滑动窗口套路
     * 不定长滑动窗口主要分为三类：求最长子数组，求最短子数组，求子数组个数。
     *
     * 1.维护一个有条件的滑动窗口；
     * 2.右端点右移，导致窗口扩大，是不满足条件的罪魁祸首；
     * 3.左端点右移目的是为了缩小窗口，重新满足条件
     */
    public int variableSizeSlidingWindow(int[] nums, int k) {
        int ans = 0;
        int left = 0;
        for (int right = 0; right < nums.length; right++) { // 右指针向右移动
            // 进入窗口

            while (/*不满足条件了*/) {
                // 左指针出窗口
            }
            // 更新答案
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }
}