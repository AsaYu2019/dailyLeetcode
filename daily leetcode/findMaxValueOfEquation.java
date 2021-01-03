class Solution {
    //because xj > xi ==> yi + yj + |xi - xj| == yi - xi + yj + xj;
    //based on this equation, we use the mono queue to maintain the largest yi-xi for every yj+xj;
    public int findMaxValueOfEquation(int[][] points, int k) {
        Deque<Pair<Integer,Integer>> ms = new ArrayDeque<>();
        int res = Integer.MIN_VALUE;
        for(int[] point : points){
            while(!ms.isEmpty() && point[0] - ms.peekFirst().getValue() > k){
                ms.pollFirst();
            }
            //check every points in the que, update the res
            if(!ms.isEmpty()){
                res = Math.max(res, ms.peekFirst().getKey() + point[0] + point[1]);
            }
            //from here we know this is a increase queue
            while(!ms.isEmpty() && point[1] - point[0] > ms.peekLast().getKey()){
                ms.pollLast();
            }
            ms.offerLast(new Pair<>(point[1] - point[0], point[0]));
        }
        return res;
    }
}
