class Solution {
    public boolean canReach(int[] arr, int start) {
        int len = arr.length;
        if(len == 1){
            if(arr[0] == 0) return true;
            return false;
        }
        return dfs(arr, start);
    }
    private boolean dfs(int[] arr, int start){
        if(start > arr.length - 1 || start < 0 || arr[start] == -1) return false;
        if(arr[start] == 0) return true;
        int temp = arr[start];
        arr[start] = -1;
        return dfs(arr, start - temp) || dfs(arr, start + temp);
    }
}
