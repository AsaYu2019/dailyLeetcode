class Solution {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n];
        Arrays.fill(color, -1);
        
        for(int start = 0; start < n;start++){
            //for every nonvisited point, DFS
            if(color[start] == -1){
                Stack<Integer> st = new Stack<>();
                st.push(start);
                color[start] = 0;//color it
                
                while(!st.isEmpty()){
                    Integer node = st.pop();
                    for(int nei : graph[node]){
                        //if the neihbor hasn't been visited
                        if(color[nei] == -1){
                            st.push(nei);
                            //dye its neihbors to the other color
                            color[nei] = color[node] ^ 1;
                        //if the neihbor has been visited and the color is the same
                        }else if(color[nei] == color[node]){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
