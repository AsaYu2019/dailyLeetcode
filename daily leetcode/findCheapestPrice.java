class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[][] matrix = new int[n][n];
        for(int i = 0; i < flights.length; i++){
            matrix[flights[i][0]][flights[i][1]] = flights[i][2];
        }
        Map<Pair<Integer,Integer>, Long> distance = new HashMap<>();
        distance.put(new Pair<Integer,Integer>(src,0), 0L);
        int stops = 0;
        long res = Long.MAX_VALUE;
        Queue<Integer> q = new LinkedList<>();
        q.add(src);
        
        while(!q.isEmpty() && stops < K + 1){
            int length = q.size();
            for(int i = 0; i < length; i++){
                int node = q.poll();
                for(int nei = 0; nei < n; nei++){
                    if(matrix[node][nei] > 0){
                        long dU = distance.getOrDefault(new Pair<Integer, Integer>(node, stops), Long.MAX_VALUE);
                        long dV = distance.getOrDefault(new Pair<Integer,Integer>(nei,stops + 1), Long.MAX_VALUE);
                        long wUV = matrix[node][nei];
                        if(stops == K && nei != dst){
                            continue;
                        }
                        if(dU + wUV < dV){
                            distance.put(new Pair<Integer, Integer>(nei, stops + 1), dU + wUV);
                            q.add(nei);
                            if(nei == dst){
                                res = Math.min(res, dU + wUV);
                            }
                        }
                    }
                }
            }
            stops++;
        }
        return res == Long.MAX_VALUE ? -1 : (int) res;
    }
}
