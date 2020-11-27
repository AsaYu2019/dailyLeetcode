class Solution {
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        List<Integer>[] graph = new List[n];
        for(int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for(int i = 0; i < n; i++){
            if(manager[i] != -1)
                graph[manager[i]].add(i);
        }
        return dfs(graph, headID, informTime);
    }
    private int dfs(List<Integer>[] graph, int headID, int[] informTime){
        int max = 0;
        for(int v : graph[headID]){
            max = Math.max(max, dfs(graph,v,informTime));
        }
        return max + informTime[headID];
    }
}
