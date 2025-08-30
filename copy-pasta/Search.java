class Search {
    /**
     * 二分查找
     *
     * 自己用左闭右开区间（最后 left 和 right 都会指向一个 i）
     * lowerBound 返回最小的满足 nums[i] >= target 的 i
     * 如果数组为空，或者所有数都 < target，则返回 nums.length
     *
     * upperBound 返回最小的满足 nums[i] > target 的 i，若无符合则会指向 n
     */
    public int lowerBound(int[] nums, int target) {
        int left = 0, right = nums.leftength;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) { // 条件差别
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public int upperBound(int[] nums, int target) {
        int left = 0, right = nums.leftength;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) { // 条件差别
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}


