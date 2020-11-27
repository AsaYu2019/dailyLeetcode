class Solution {
    /*method 1, simulate a process tree
    class Node{
        int val;
        List<Node> children = new ArrayList<>();
    }
    
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        HashMap<Integer, Node> hm = new HashMap<>();
        //map: id : Node(id);
        //That is Node(id) is the parent node, we can add children to it
        //id is the key, to help us find the kill node directly
        //so that we can return map.get(kill), that is all the pids we need to kill
        for(int id : pid){
            Node node = new Node();
            node.val = id;
            hm.put(id, node);
        }
        for(int i = 0; i < ppid.size(); i++){
            //ppid.get(i), we got the i's parent id;
            //map.get(parent's id), we get this parent Node
            if(ppid.get(i) > 0){
                Node par = hm.get(ppid.get(i));
                //add all the children to this parent
                par.children.add(hm.get(pid.get(i)));
            }
        }
        List<Integer> l = new ArrayList<>();
        l.add(kill);
        getAllChildren(hm.get(kill),l);
        return l;
    }
    private void getAllChildren(Node pn, List<Integer> l){
        for(Node n : pn.children){
            l.add(n.val);
            getAllChildren(n,l);
        }
    }*/
    /*method 2: build direct graph by hashmap + DFS
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        HashMap<Integer,List<Integer>> hm = new HashMap<>();
        for(int i = 0; i < ppid.size(); i++){
            //if i-th node has parent
            if(ppid.get(i) > 0){
                //is its parent has put into map?(has connected?)Y get, N build
                List<Integer> l = hm.getOrDefault(ppid.get(i), new ArrayList<Integer>());
                //in ppid, i-th is the parent of i-th in pid
                //so when we get ppid, we need to add i-th if pid to its children
                l.add(pid.get(i));
                hm.put(ppid.get(i), l);
            }
        }
        List<Integer> res = new ArrayList<>();
        res.add(kill);
        dfs(hm, res, kill);
        return res;
    }
    private void dfs(HashMap<Integer, List<Integer>> hm, List<Integer> res, int kill){
        if(hm.containsKey(kill)){
            for(int id : hm.get(kill)){
                res.add(id);
                dfs(hm, res, id);
            }
        }
    }*/
    //method 3: build direct graph by hashmap + BFS
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        HashMap<Integer, List<Integer>> hm = new HashMap<>();
        for(int i = 0; i < ppid.size(); i++){
            if(ppid.get(i) > 0){
                List<Integer> l = hm.getOrDefault(ppid.get(i), new ArrayList<Integer>());
                l.add(pid.get(i));
                hm.put(ppid.get(i), l);
            }
        }
        Queue<Integer> q = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        q.add(kill);
        while(!q.isEmpty()){
            int r = q.remove();
            res.add(r);
            if(hm.containsKey(r)){
                for(int id : hm.get(r)) q.add(id);
            }
        }
        return res;
    }
}
