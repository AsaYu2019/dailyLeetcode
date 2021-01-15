class Solution {
    /*method 1: di-graph + bfs, TLE
    public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
        List<Integer>[] graph = new LinkedList[n];
        List<Boolean> res = new LinkedList<>();
        for(int i = 0; i < n; i++){
            graph[i] = new LinkedList<>();
        }
        for(int[] pre : prerequisites){
            //from pre[0], we can learn pre[1]
            graph[pre[0]].add(pre[1]);
        }
        for(int[] query : queries){
            //bfs from query[0] to query[1]
            res.add(bfs(query[0], query[1], graph));
        }
        return res;
    }
    
    private boolean bfs(int pre, int dest, List<Integer>[] graph){
        if(graph[pre].contains(dest)) return true;
        if(graph[pre].equals(null)) return false;
        Queue<Integer> q = new LinkedList<>();
        q.add(pre);
        while(!q.isEmpty()){
            int temp = q.poll();
            if(graph[temp].contains(dest)) return true;
            else{
                for(int i : graph[temp]){
                    q.offer(i);
                }
            }
        }
        return false;
    }*/
   /* //method 2 Floyd–Warshall Algorithm, construct connected matrix, iterate every point,if point i connected k, k connected j, we can get i,j connected
    public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
        boolean[][] connected = new boolean[n][n];
        for(int[] pre : prerequisites){
            connected[pre[0]][pre[1]] = true;
        }
        for(int k = 0; k < n; k++)
            for(int j = 0; j < n; j++)
                for(int i = 0; i < n; i++)
                    connected[i][j] = connected[i][j] || connected[i][k] && connected[k][j];
        List<Boolean> res = new LinkedList<>();
        for(int[] query : queries){
            res.add(connected[query[0]][query[1]]);
        }
        return res;
    }*/
    //method 3, Topologic sort
    public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries){
        List<Boolean> res = new LinkedList<>();
        List<Integer>[] graph = new List[n];
        int[] indegree = new int[n];
        boolean[][] connected = new boolean[n][n];
        
        for(int i = 0; i < n; i++){
            graph[i] = new LinkedList<>();
        }
        for(int[] pre : prerequisites){
            graph[pre[0]].add(pre[1]);
            indegree[pre[1]]++;
        }
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < n; i++){
            if(indegree[i] == 0) q.add(i);
        }
        while(!q.isEmpty()){
            int t = q.poll();
            for(int prereq : graph[t]){
                connected[prereq][t] = true;
                for(int i = 0; i < n; i++){
                    if(connected[t][i]){
                        connected[prereq][i] = true;
                    }
                }
                indegree[prereq]--;
                if(indegree[prereq] == 0) q.add(prereq);
            }
        }
        for(int[] query : queries){
            res.add(connected[query[1]][query[0]]);
        }
        return res;
    }
}
