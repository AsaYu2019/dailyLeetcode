class Solution {
    public int openLock(String[] deadends, String target) {
        Set<String> dead = new HashSet<>();
        for(String s : deadends){
            dead.add(s);
        }
        Queue<String> q = new LinkedList<>();
        q.offer("0000");
        q.offer(null);
        
        Set<String> seen = new HashSet<>();
        seen.add("0000");
        
        int depth = 0;
        while(!q.isEmpty()){
            String node = q.poll();
            if(node == null){
                depth++;
                if(q.peek() != null){
                    q.offer(null);
                }
            }else if(node.equals(target)){
                return depth;
            }else if(!dead.contains(node)){
                for(int i = 0; i < 4; i++){
                    for(int d = -1; d <= 1; d +=2){
                        int y = ((node.charAt(i) - '0') + d + 10) % 10;
                        String nei = node.substring(0,i) + ("" + y) + node.substring(i+1);
                        if(!seen.contains(nei)){
                            seen.add(nei);
                            q.offer(nei);
                        }
                    }
                }
            }
        }
        return -1;
    }
}
