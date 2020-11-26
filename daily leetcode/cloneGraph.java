/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    //undirected graph, how could we know every nodes in its right place?
    //use a map to store the one - one reflection
    Map<Node, Node> hm = new HashMap<>();
    public Node cloneGraph(Node node) {
        if(node == null) return null;
        if(hm.containsKey(node)) return hm.get(node);
        Node cur = new Node(node.val);
        hm.put(node, cur);
        for(Node neb : node.neighbors){
            cur.neighbors.add(cloneGraph(neb));
        }
        return cur;
    }
}
