class Solution {
    /*
    //minmax 问题，详解图见Tree部分笔记
    //method1，递归未剪枝
    public boolean PredictTheWinner(int[] nums) {
        return getScore(nums, 0, nums.length - 1) >= 0;
    }
    private int getScore(int[] nums, int left, int right){
        if(left == right) return nums[left]; //若待选时只剩下一个数了
        //nums[left] - getScore(nums, left + 1, right)意义为本层选择者选了最左边的元素，即nums[left],则nums中待选元素范围为[left+1， right]
        //nums[left] - getScore(nums, left + 1, right)意义为本层选择者选择了最右边的元素,即nums[right],则nums中待选元素范围为[left,right - 1];
        //而每次的最优选择是，选择了left或者right后手上的分值是最大的。
        return Math.max(nums[left] - getScore(nums, left + 1, right), nums[right] - getScore(nums, left, right - 1));
        
    }*/
    /*
    //method 2， 由笔记中决策树可知，递归方法时，有很多重复子问题，故考虑用memo或dp
    
    public boolean PredictTheWinner(int[] nums) {
    //二维数组做memo保存已经重复过的子问题，即数组nums左右两边分别为left、right 时，做出选择后得到的最优解的分数
        //tips：用Integer而非int的原因是，在helper中做判断时可直接与null对比，不需要先赋值为integer_minvalue
        Integer[][] memo = new Integer[nums.length][nums.length];
        return getScore(nums, 0, nums.length - 1, memo) >= 0;
    }
    private int getScore(int[] nums, int left, int right, Integer[][] memo){
        if(left == right) return nums[left];
        //若nums的left、right的这种结果在上一次遍历的时候已经做过了，即对于剩下范围的数组已经有了最优解，故直接用
        if(memo[left][right] != null) return memo[left][right];
        int a = nums[left] - getScore(nums, left + 1, right, memo);
        int b = nums[right] - getScore(nums, left, right - 1, memo);
        //若memo中还没有，则将本次计算得到的最优解放到memo中
        memo[left][right] = Math.max(a, b);
        return memo[left][right];
    }*/
    //method3，递归+memo+降维
    
    public boolean PredictTheWinner(int[] nums) {
        Integer[] memo = new Integer[nums.length * nums.length];
        return getScore(nums, 0, nums.length - 1, memo) >= 0;
    }
    private int getScore(int[] nums, int left, int right, Integer[] memo){
        if(left == right) return nums[left];
        int index = left * nums.length + right;
        if(memo[index] != null) return memo[index];
        int a = nums[left] - getScore(nums, left + 1, right, memo);
        int b = nums[right] - getScore(nums, left, right - 1, memo);
        memo[index] = Math.max(a, b);
        return memo[index];
    }
}
