class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        //build graph: point1 -->(point2 --> weightEdge), so that we got the directed graph
        //                       (point3 --> weightEdge)
        HashMap<String, HashMap<String, Double>> graph = new HashMap<>();
        for(int i = 0; i < equations.size(); i++){
            List<String> equation = equations.get(i);
            String dividend = equation.get(0), divisor = equation.get(1);
            double quotient = values[i];
            
            if(!graph.containsKey(dividend)){
                graph.put(dividend, new HashMap<String, Double>());
            }
            if(!graph.containsKey(divisor)){
                graph.put(divisor, new HashMap<String, Double>());
            }
            
            graph.get(dividend).put(divisor, quotient);
            graph.get(divisor).put(dividend, 1 / quotient);
        }
        
        //DFS to find if there is a path between points
        double[] res = new double[queries.size()];
        for(int i = 0; i < queries.size(); i++){
            List<String> query = queries.get(i);
            String dividend = query.get(0), divisor = query.get(1);
            
            if(!graph.containsKey(dividend) || !graph.containsKey(divisor)) res[i] = -1.0;
            else if(dividend == divisor) res[i] = 1.0;
            else{
                //hashset to represent visited or not
                HashSet<String> visited = new HashSet<>();
                res[i] = backtrack(graph, dividend, divisor, 1, visited);
            }
        }
        return res;
    }
    private double backtrack(HashMap<String, HashMap<String, Double>> graph, String dividend, String divisor, double accProduct, Set<String> visited){
        visited.add(dividend);
        double ret = -1.0;
        //get all neighbors
        Map<String, Double> neighbors = graph.get(dividend);
        //if we find our target in neigbors, we get the result
        if(neighbors.containsKey(divisor)) ret = accProduct * neighbors.get(divisor);
        //if not, backtrack
        else{
            //backtrack every points connected to currNode
            for(Map.Entry<String,Double> pair : neighbors.entrySet()){
                String nextNode = pair.getKey();
                if(visited.contains(nextNode)) continue;
                ret = backtrack(graph, nextNode, divisor, accProduct*pair.getValue(), visited);
                if(ret != -1.0) break;
            }
        }
        visited.remove(dividend);
        return ret;
    }
}
