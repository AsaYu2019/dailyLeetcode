class Solution {
    /*
    //method 1:DFS
    //For two string: s1,s2, there are three cases, 
    //          case1: abcd  cdef, the last substring of s1 is overlaped by the first substring of s2;
    //          case2: cdef abcd, the first substring of s1 is overlaped by the last  substring of s2;
    //          case3: cd   ef, no verlap;
    // So we need a method to find the longest overlap for two of the all the strings each time, 1, merge them, 2, delete them from the list, 3, add merged string into the list.
    public String shortestSuperstring(String[] A) {
        List<String> list = new ArrayList<>(Arrays.asList(A));;
        while(true){
            int n = list.size();
            if(n==1)break;
            
            int maxLength = -1;
            int index1 = 0, index2 = 0;
            String newString = "";
            for(int i = 0; i < n -1; i++){
                for(int j = i + 1; j < n; j++){
                    String s1 = list.get(i);
                    String s2 = list.get(j);
                    String merged = merge(s1,s2);
                    int saved = s1.length() + s2.length() - merged.length();
                    
                    //finde the longest overlap
                    if(saved > maxLength){
                        maxLength = saved;
                        index1 = i;
                        index2 = j;
                        newString = merged;
                    }
                }
            }
            String str1 = list.get(index1);
            String str2 = list.get(index2);
            list.remove(str1);
            list.remove(str2);
            list.add(newString);
        }
        return list.get(0);
    }
    private String merge(String s1, String s2){
        int len1 = s1.length();
        int len2 = s2.length();
        int overlap1 = 0, overlap2 = 0;
        //len is the overlap part, so len < len1
        for(int len = 1; len < len1 && len < len2; len++){
            //case1, use the rear of s1
            if(s1.substring(len1 - len).equals(s2.substring(0,len))){
                overlap1 = len;
            }
            //case2, use the rear of s2
            if(s2.substring(len2 - len).equals(s1.substring(0,len))){
                overlap2 = len;
            }
        }
        if(overlap1 >= overlap2){
            return s1 + s2.substring(overlap1);
        }else{
            return s2 + s1.substring(overlap2);
        }
    } */
    //method 2: DP
    // For three strings  A: abc    B:bcd    C:cde
    //      we have some cases: A->B: 1 (only need to add 1 char after A)
    //      A->C:2    B->A: 3    B->C: 1    C->A: 3     C->B: 3
    //      we got the shortest path A->B->C:abcde = 2;(like direct graph)
    //      Therefore, those cases equal to : for every point, we need to transit it one and only one time, and try to find out the shortest path(NP question)
    // DP + status compression
    //status: bitmask: for three points:
    //        for set = 0000 to 1111, chose 0 to chose all points(ABCD)
    //        dp[set][P]: the shortest path of we traved points in the set, and the last point we passed is P
    //        for dep[0111][B], what kind of status could be transform to it ?
    //          that is: dp[0011][C] + distance(B,C) OR dp[0011][D] + distance(B,D) 
    //              means:       DCB                            CDB
    //          and we need to chose the smaller one
    
    public String shortestSuperstring(String[] A) {
        int N = A.length;
        
        //populate overlaps
        int[][] overlaps = new int[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(i != j){
                    int m = Math.min(A[i].length(), A[j].length());
                    for(int k = m; k >= 0; k--){
                        //find overlaps from big to small
                        if(A[i].endsWith(A[j].substring(0,k))){
                            overlaps[i][j] = k;
                            break;
                        }
                    }
                }
            }
        }
        //dp[bitMask][i] = which string has been visited(0101101),ending with ith element.
        int[][] dp = new int[1 << N][N];
        int[][] parent = new int[1 << N][N];
        for(int mask = 0; mask < (1 << N); mask++){
            //initialize every position's val to -1
            Arrays.fill(parent[mask], -1);
            
            for(int bit = 0; bit < N; bit++){
                    //&1? to make sure this bit or this string is in the set.
                if(((mask >> bit) & 1) > 0) {
                    //try to find dp[mask][bit].
                    //we had a collection of items represent by pmask
                    int pmask = mask^(1 << bit); //which one is parent's status
                    if(pmask == 0) continue; //you are the first string,no parent
                    for(int i = 0; i < N; i++){
                        if(((pmask >> i) & 1) > 0){
                            //for each bit i in pmask, calculate the value
                            //if we ended with string i, then added string 'bit';
                            int val = dp[pmask][i] + overlaps[i][bit];
                            if(val > dp[mask][bit]){
                                dp[mask][bit] = val;
                                parent[mask][bit] = i;
                            }
                        }
                    }
                }
            }
        }
        int[] perm = new int[N];
        boolean[] seen = new boolean[N];
        int t = 0;
        int mask = (1 << N) - 1;
        
        int p = 0;
        for(int j = 0; j < N; j++){
            if(dp[(1 << N) - 1][j] > dp[(1 << N) - 1][p]){
                p = j;
            }
        }
        while(p != -1){
            perm[t++] = p;
            seen[p] = true;
            int p2 = parent[mask][p];
            mask ^= 1 << p;
            p = p2;
        }
        for(int i = 0; i < t/2; i++){
            int v = perm[i];
            perm[i] = perm[t - 1 - i];
            perm[t - 1 -i] = v;
        }
        
        for(int i = 0; i < N; i++){
            if(!seen[i]){
                perm[t++] = i;
            }
        }
        StringBuilder res = new StringBuilder(A[perm[0]]);
        for(int i = 1; i < N; i++){
            int overlap = overlaps[perm[i - 1]][perm[i]];
            res.append(A[perm[i]].substring(overlap));
        }
        return res.toString();
    }
}
