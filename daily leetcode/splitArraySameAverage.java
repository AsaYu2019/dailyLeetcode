class Solution {
    /* 普通DFS，超时
    int sum;
    int n;
    public boolean splitArraySameAverage(int[] A) {
        n = A.length;
        if(n == 2) return A[0] == A[1];
        sum = 0;
        Arrays.sort(A); //排序一次，方便实施剪枝
        for(int num : A){
            sum += num;
        }
        
        return DFS(A, 0, 0, 0);
    }
    
    private boolean DFS(int[] A, int curSum, int curNum, int index){
        // curSum/curNum = sum/n时，即能找到
        if(curNum > 0 && curNum < n && curSum * n == sum * curNum) return true; 
        //找到最后一个了也没成功
        if(index == n) return false;
        
        //剪枝2：如果现有的平均值已经超过了目标平均值，之后的元素又越来越大，故之后的就不用考虑了
        if(curSum * n > sum * curNum) return false;
        
        //对于index处元素，两种情况：
        //case1: 将A【index】选入B，递归下去能true则return true
        if(DFS(A, curSum + A[index], curNum + 1, index + 1)) return true;
        
        //case2: 将A[index] 不选入B，递归下去能true，则return true;
        //剪枝1：不选的时候，如果有重复数字，直接跳过该重复数字,下次进入dfs时直接在i处进入
        int i = index + 1;
        while(i < n && A[i] == A[index]) i++;
        
        if(DFS(A, curSum, curNum, i )) return true;
        //case3: 将A[index] 选不选入都是false，return false;
        return false;
    } */
    
    //观察： sum/n = curSum/curNum ==> sum * curNum == curSum * n
    //即：找出curNum个数，使其与sum的乘机等于其和curSum与总个数n的乘积
    int sum;
    int n;
    public boolean splitArraySameAverage(int[] A) {
        n = A.length;
        if(n == 2) return A[0] == A[1];
        sum = 0;
        Arrays.sort(A); //排序一次，方便实施剪枝
        for(int num : A){
            sum += num;
        }
        for(int curNum = 1; curNum < n; curNum++){
            if(sum * curNum % n != 0) continue; // 不能被n整除，则排除在外
            int curSum = sum * curNum / n;
            if(DFS(A, curNum, curSum, 0)) return true;
        }
        
        return false;
    }
    
    private boolean DFS(int[] A, int curNum, int curSum, int index){
        if(curNum == 0 && curSum == 0) return true;
        if(curNum == 0 || curSum == 0) return false;
        if(index == n) return false;
        
        if(DFS(A, curNum -1, curSum - A[index], index + 1)) return true;
        //剪枝1：不选的时候，如果有重复数字，直接跳过该重复数字,下次进入dfs时直接在i处进入
        int i = index + 1;
        while(i < n && A[i] == A[index]) i++;
        if(DFS(A, curNum, curSum, i)) return true;
        return false;
    }
}
