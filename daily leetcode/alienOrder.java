class Solution {
    public String alienOrder(String[] words) {
        //prepare to build a graph, character is the nodes
        Map<Character, List<Character>> Graph = new HashMap<>();
        Map<Character, Integer> degree = new HashMap<>();
        
        for(String word : words){
            for(char c : word.toCharArray()){
                degree.put(c, 0);
                Graph.put(c, new ArrayList<>());
            }
        }
        //according to the words, build the edges and indegree
        for(int i = 0; i < words.length - 1; i++){
            String word1 = words[i];
            String word2 = words[i + 1];
            //        abcd           abc        word2 is the prefix of word1
            if(word1.length() > word2.length() && word1.startsWith(word2)){
                return "";
            }
            for(int j = 0; j < Math.min(word1.length(), word2.length()); j++){
                char c1 = word1.charAt(j);
                char c2 = word2.charAt(j);
                if(c1 != c2){
                    Graph.get(c1).add(c2);
                    degree.put(c2, degree.get(c2) + 1);
                    break;
                }
            }
        }
        //bfs to find the res
        StringBuilder sb = new StringBuilder();
        Queue<Character> q = new LinkedList<>();
        for(Character c : degree.keySet()){
            if(degree.get(c).equals(0)){
                q.add(c);
            }
        }
        while(!q.isEmpty()){
            Character c = q.poll();
            sb.append(c);
            for(Character next : Graph.get(c)){
                degree.put(next, degree.get(next) - 1);
                if(degree.get(next).equals(0)){
                    q.add(next);
                }
            }
        }
        if(sb.length() < degree.size()) return "";
        return sb.toString();
    }
}
