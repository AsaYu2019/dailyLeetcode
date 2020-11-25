class Solution {
    /*
    //Method 1, basic slide window
    public String minWindow(String s, String t) {
        if(s.length() == 0 || t.length() == 0){
            return "";
        }
        Map<Character, Integer> map = new HashMap<>();
        //store every character and its frequence.
        for(int i = 0; i < t.length(); i++){
            int count = map.getOrDefault(t.charAt(i), 0);
            map.put(t.charAt(i), count + 1);
        }
        int required = map.size();
        int l = 0, r = 0;
        int formed = 0;
        Map<Character, Integer> window = new HashMap<>();
        int[] res = {-1, 0, 0};
        while(r < s.length()){
            char c = s.charAt(r);
            int count = window.getOrDefault(c, 0);
            window.put(c, count + 1);
            //if c is counted and its frequence is matched, we got one character done
            //on other words, if c is counted but the frequence is bigger or smaller than t, we couldn't say this character has been included in to our window.
            if(map.containsKey(c) && window.get(c).intValue() == map.get(c).intValue()){
                formed++;
            }
            //if formed == required, means we got all the characters in t into our window,
            //now we need to move left pointer to minimize the window so that to find the proper window that contain all the characters in t.
            while(l <= r && formed == required){
                c = s.charAt(l);
                if(res[0] == -1 || r - l + 1 < res[0]){
                    //update the total size of the window
                    res[0] = r - l + 1;
                    res[1] = l;
                    res[2] = r;
                }
                window.put(c, window.get(c) - 1);
                //after move left to left one step, we get formed--, which means this step makes our window couldnot contain all the characters in t, and then break the inner loop's condition(formed == required). 
                //so that it will jump out and into the outer loop, which moves the right pointer to right.
                if(map.containsKey(c) && window.get(c).intValue() < map.get(c).intValue()) formed--;
                l++;
            }
            r++;
        }
        return res[0] == -1 ? "" : s.substring(res[1], res[2] + 1);
    } */
    
    
    //method 2: fliter S and slide window
    public String minWindow(String s, String t) {
        if(s.length() == 0 || t.length() == 0){
            return "";
        }
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < t.length(); i++){
            int count = map.getOrDefault(t.charAt(i), 0);
            map.put(t.charAt(i), count + 1);
        }
        int required = map.size();
        
        List<Pair<Integer,Character>> fliteredS = new ArrayList<>();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(map.containsKey(c)){
                fliteredS.add(new Pair<Integer, Character>(i,c));
            }
        }
        
        int l = 0, r = 0, formed = 0;
        Map<Character, Integer> window = new HashMap<>();
        int[] res = {-1, 0, 0};
        
        while(r <= fliteredS.size()){
            char c = fliteredS.get(r).getValue();
            int count = window.getOrDefault(c, 0);
            window.put(c, count + 1);
            
            if(map.containsKey(c) && window.get(c).intValue() == map.get(c).intValue()) formed++;
            
            while(l < r && formed == required){
                c = fliteredS.get(l).getValue();
                int end = fliteredS.get(r).getKey();
                int start = fliteredS.get(l).getKey();
                if(res[0] == -1 || end - start + 1 < res[0]){
                    res[0] = end - start + 1;
                    res[1] = start;
                    res[2] = end;
                }
                window.put(c, window.get(c) - 1);
                if(map.containsKey(c) && window.get(c).intValue() < map.get(c).intValue()) formed--;
                l++;
            }
            r++;
        }
        return res[0] == -1 ? "" : s.substring(res[1], res[2] + 1);
    }
}
