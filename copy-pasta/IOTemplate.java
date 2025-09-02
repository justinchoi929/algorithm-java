class IOTemplate {
    public static void main(String[] args) {
        /**
         * 包含规模：
         * 3 3
         * -90 48 78
         * 64 -40 64
         * -81 -7 66
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

        // 读取n和m
        in.nextToken();
        int n = (int) in.nval;
        in.nextToken();
        int m = (int) in.nval;

        int[][] nums = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                in.nextToken();
                nums[i][j] = (int) in.nval;
            }
        }
        out.println(Arrays.deepToString(nums));

        out.flush();
        br.close();
        out.close();

        /**
         * 按行读取
         */
        String line;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

        while ((line = in.readLine()) != null) {
            if (line.trim().isEmpty()) { // 输入空行结束，实际题目中不需要，自测时使用
                break;
            }
            String[] tokens = line.split(" ");
            int[] nums = new int[tokens.length];
            for (int i = 0; i < tokens.length; i++) {
                nums[i] = Integer.parseInt(tokens[i]);
            }
            out.println(Arrays.toString(nums));
        }
        out.flush();
        in.close();
        out.close();
    }
}
