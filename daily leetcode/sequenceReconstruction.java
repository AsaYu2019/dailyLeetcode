class Solution {
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        Map<Integer, Integer> degree = new HashMap<>();
        //prepare nodes, and empty indegree map: node -> indegree
        for(List<Integer> list : seqs){
            for(int i = 0; i < list.size(); i++){
                degree.putIfAbsent(list.get(i), 0);
                map.putIfAbsent(list.get(i), new ArrayList<>());
            }
        }
        
        if(degree.size() != org.length) return false;
        //prepare edges
        for(List<Integer> list : seqs){
            //[[5,2,6,3],[4,1,5,2]], segregate them to nodes and use edgs to connect them
            // 5->2, 2->6, 6->3;    4->1, 1-> 5, 5->2
            //and update there indegree: 1-1, 2-2, 3-1,4-0,5-1, 6-1
            for(int i = 0; i < list.size() - 1; i++){
                if(!map.get(list.get(i)).contains(i + 1)){
                    int temp = list.get(i + 1);
                    map.get(list.get(i)).add(temp);
                    degree.put(temp, degree.get(temp) + 1);
                }
            }
        }
        
        Queue<Integer> q = new LinkedList<>();
        int[] res = new int[org.length];
        
        for(int i : degree.keySet()){
            if(degree.get(i) == 0){
                q.offer(i);
            }
        }
        int index = 0;
        while(!q.isEmpty()){
            if(q.size() > 1) return false;
            int num = q.poll();
            res[index++] = num;
            for(int i : map.get(num)){
                degree.put(i, degree.get(i) - 1);
                if(degree.get(i) == 0) q.offer(i);
            }
        }
        
        for(int i = 0; i < org.length; i++){
            if(res[i] != org[i]) return false;
        }
        return true;
    }
}
