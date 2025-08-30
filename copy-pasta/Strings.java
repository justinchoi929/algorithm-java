class Strings {
    /**
     * KMP 算法
     * 不懂的时候看左程云的讲解
     * https://www.bilibili.com/video/BV19Q4y1c7ko/?spm_id_from=333.1007.top_right_bar_window_default_collection.content.click&vd_source=059bb19e5eb419a7c6aee4e07b53199d
     */
    public int kmp(char[] s1, char[] s2) {
        int n = s1.length, m = s2.length, x = 0, y = 0;
        int[] next = nextArray(s2);
        while (x < n && y < m) {
            if (s1[x] == s2[y]) {
                x++;
                y++;
            } else if (y == 0) {
                x++;
            } else {
                y = next[y];
            }
        }
        return y == m ? x - y : -1;
    }

    /**
     * 求 next 数组
     */
    public int[] nextArray(char[] s) {
        int len = s.length;
        if (len == 1) {
            return new int[]{-1};
        }
        int[] next = new int[len];
        next[0] = -1;
        next[1] = 0;
        int i = 2, cn = 0;
        while (i < len) {
            if (s[i - 1] == s[cn]) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }
}