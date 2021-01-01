class Solution {
    public int subarraysWithKDistinct(int[] A, int K) {
        return atMost(A, K) - atMost(A, K - 1);
    }
    
    private int atMost(int[] arr, int N){
        int low = 0, hi = 0, res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        while(hi < arr.length){
            if(map.getOrDefault(arr[hi], 0) == 0) N--;
            map.put(arr[hi], map.getOrDefault(arr[hi],0) + 1);
            while(N < 0){
                map.put(arr[low], map.get(arr[low]) - 1);
                if(map.get(arr[low]) == 0) N++;
                low++;
            }
            hi++;
            res += hi - low;
        }
        return res;
    }
}
