class Solution {
    //think: this problem is a little different to course scheduel because it has 'time' as its weight, so what should we do?
    /*method 1: Floyd–Warshall algorithm, build a graph matrix, store the time as the weight
    public int networkDelayTime(int[][] times, int n, int k) {
        double[][] connected = new double[n][n];
        for(int i = 0; i < n; i++){
            Arrays.fill(connected[i], Double.POSITIVE_INFINITY);
            connected[i][i] = 0;
        }
        for(int[] t : times){
            connected[t[0]-1][t[1]-1] = t[2];
        }
        for(int m = 0; m < n; m++)
            for(int j = 0; j < n; j++)
                for(int i = 0; i <n; i++)
                    if(connected[j][i] > connected[j][m] + connected[m][i])
                        connected[j][i] = connected[j][m] + connected[m][i];
        double res = Double.MIN_VALUE;
        for(int i = 0; i < n; i++){
            if(i == k-1) continue;
            if(connected[k-1][i] == Double.POSITIVE_INFINITY) return -1;
            res = Math.max(res, connected[k-1][i]);
        }
        return (int)res;
    }*/
    //method 2: Bellman-Ford algorithm
    /*public int networkDelayTime(int[][] times, int n, int k) {
        double[] disTo = new double[n];
        Arrays.fill(disTo, Double.POSITIVE_INFINITY);
        disTo[k - 1] = 0;
        for(int i = 1; i < n; i++){
            for(int[] t : times){
                int u = t[0] - 1;
                int v = t[1] - 1;
                int edge = t[2];
                disTo[v] = Math.min(disTo[v], disTo[u] + edge);
            }
        }
        double res = Double.MIN_VALUE;
        for(double i : disTo){
            res = Math.max(res, i);
        }
        return res == Double.POSITIVE_INFINITY ? -1 : (int)res;
    }*/
    //build graph matrix, find closest point from the rest, update its distance to closest;
    public int networkDelayTime(int[][] times, int n, int k) {
        //build direct graph matrix, stored are their weight
        int[][] G = new int[n+1][n+1];
        for(int[] row : G){
            Arrays.fill(row, -1);
        }
        //connected points
        for(int[] edge : times){
            G[edge[0]][edge[1]] = edge[2];
        }
        //prepare distacne
        int[] distance = new int[n+1];
        Arrays.fill(distance, -1);
        //start from k point, store distance of all points connect k directly
        for(int i = 1; i <= n ; i++){
            distance[i] = G[k][i];
        }
        //k as the start piont, its distance is 0
        distance[k] = 0;
        boolean[] visited = new boolean[n + 1];
        //mark k is visited
        visited[k] = true;
        //iterat all points, update the min time to K point
        for(int i = 1; i <= n - 1; i++){
            int min = Integer.MAX_VALUE;
            int minIndex = 1;
            
            //find the closest point of k from the non-visited points
            for(int j = 1; j <= n; j++){
                //if j is not visited and distance of k~j is exist and its distance smaller than min, update
                if(!visited[j] && distance[j] != -1 && distance[j] < min){
                    min = distance[j];
                    minIndex = j;
                }
            }
            //mark this point as the closest point of the non-visited points
            visited[minIndex] = true;
            //update the distance between k and minIndex
            for(int j = 1; j <= n; j++){
                if(G[minIndex][j] != -1){
                    if(distance[j] == -1) distance[j] = distance[minIndex] + G[minIndex][j];
                    else distance[j] = Math.min(distance[j], distance[minIndex] + G[minIndex][j]);
                }
            }
        }
        int res = -1;
        for(int i = 1; i <= n ; i++){
            if(distance[i] == -1) return -1;
            res = Math.max(res,distance[i]);
        }
        return res; 
    }
}
