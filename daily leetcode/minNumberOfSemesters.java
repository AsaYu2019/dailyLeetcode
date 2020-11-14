class Solution {
    /*look at the range of n, think about the status compress dp
    if n < 10 ==> O(n!)
    if 10 < n < 20 ==> O(2^n)
    think about using status to represent which courses are selected.( 10001, 10111)
    means:  status,  total courses have been selected, means status < 2^n
            preStatus, total courses have been select until last semester.
        So there are several conditions for the relation of status and preStatus.
            1, preStatus are a subset of status
            2, countOne(status) - countOne(preStatus) <= k
            3, preStatus must contains prerequisite of status
    
    so dp[status] = min(dp[status], dp[preStatus] + 1); 1 means 1 semester
    return dp[(1<<n) - 1]
    
    Optim: for 1: How to get the subset of status effeciency?
                  subset = (subset - 1) & status 
                  then if subset == 0, break
           for 3: How could we know preStatus contains prerequite?
                 if   preStatus == preStatus | prerequiste.
                        10010        10010        10000
    How could we get prerequisite?
        for every x in dependencies: prerequisite[x[1]] |= 1<<(x[0]);
    */
    public int minNumberOfSemesters(int n, int[][] dependencies, int k) {
        int[] dp = new int[1 << n];
        Arrays.fill(dp, Integer.MAX_VALUE/2);
        int[] preCourse = new int[n];
        int[] prereq = new int[1 << n];
        preCourse[0] = 0;
        //get prerequisite courses for every course
        for(int[] x : dependencies){
            //preCourse[i] represents i's prerequisite course
            //find every equals x[1], shift 1 left x[0] bits to represent ith course is its prere
            //+ or | them up, remember: preCourse[] are all 0s in the beginning.
            //when we check preCourse[i], we get i's prereq, which represent by 1s of preCourse[i]'s value
            //why x[1] - 1? why [x0] - 1?
            //because this means the ith course, if ith x[0] == 1, we don't need to shift it
            //so 1<<x[0] should be wrong, so we use x[0] - 1; same with x[1], it's ith course
            preCourse[x[1] - 1] = preCourse[x[1] - 1] | (1 << (x[0] - 1));
        }
        
        //for every status, determin its prerequisite course
        for(int status = 0; status < (1<<n);status++){
            //we got a kind of status, we need to know all of its prerequisites, how?
            //select all the courses one by one in status then add their preCourse up!
            prereq[status] = 0;
            for(int i = 0; i < n; i++){
                if(((status>>i) & 1) != 0){
                    prereq[status] = prereq[status] | preCourse[i];
                }
            }
        }
        //prereq[status] done!
        //function of countOnes, done!
        //
        dp[0] = 0;
        for(int status = 0; status < (1 << n); status++){
            //for every subset contains by status
            for(int subset = status; subset >= 0; subset = status & (subset - 1)){
                //if new selecet course less than k and this subset are contains by prereq[status], which meash all the new courses' prereq has been selected.
                if(countOnes(status) - countOnes(subset) <= k 
                   && (subset & prereq[status]) == prereq[status]){
                    dp[status] = Math.min(dp[status], dp[subset] + 1);
                }
                if(subset == 0) break;
            }
        }
        return dp[(1<<n) - 1];
        
        
    }
    
    private int countOnes(int n){
        int count = 0;
        while(n != 0){
            n = n & (n - 1);
            count++;
        }
        return count;
    }
}
