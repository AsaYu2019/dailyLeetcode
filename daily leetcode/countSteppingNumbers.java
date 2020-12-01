class Solution {
    //Think about: 10,12,21,22,32,34.....101, 121,123, 132,134...
    //what's their common point?
    //that is: 101 = 10 * 10 + 0 + 1
    //          121 = 12 * 10 + 2 -1, 121 = 12 * 10 + 2 + 1;
    //the res is relevent to the tens digit, we can do + 1 and - 1 of tens to get the res
   public List<Integer> countSteppingNumbers(int low, int high) {
        List<Integer> res = new ArrayList<>();
        if(low > high) return res;
       //prepare for BFS
        Queue<Integer> q = new LinkedList<>();
       //the start points of BFS
       //Tips: why 0 shouldn't be added?
        for(int i = 1; i < 10; i++) q.add(i);
       //put 0 first
        if(low == 0) res.add(0);
        while(!q.isEmpty()){
            int num = q.poll();
            //think about: why we can use num compare to high/10?
            //because we will use num * 10 next step, and if we compare num to hgih directly, num * 10 may overflow. On the other hand, if num is the same digits with high, we also don't need to caculate num * 10, because we don't need the numbers of high * 10;
            if(num <= high/10){
                int last = num % 10;
                if(last > 0) q.add(num * 10 + last - 1);
                if(last < 9) q.add(num * 10 + last + 1);
            }
            if(num >= low && num <= high) res.add(num);
        }
        return res;
    }
}
