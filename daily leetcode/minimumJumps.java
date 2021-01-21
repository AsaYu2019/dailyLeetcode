class Solution {

    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        int res = 0;
        if(x == 0) return res;
        Set<Integer> set = new HashSet<>();//restore forbidden or position after right move
        for(int i : forbidden)
            set.add(i);
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        Queue<Boolean> status = new LinkedList<>();
        status.add(true); // if move to left, add false
        while(!q.isEmpty()){
            res++;
            int len = q.size();
            for(int i = 0; i < len; i++){
                int poll = q.poll();
                boolean flag = status.poll();//detect could move to left or not
                int nextPosition = poll + a;
                if(nextPosition == x) return res;
                if(nextPosition < 6000 && !set.contains(nextPosition)){ //if can reach here
                    q.add(nextPosition);
                    status.add(true);
                    set.add(nextPosition);
                }
                if(flag){// if flag is true, means prev step is moving to right, we can move to left now
                    nextPosition = poll - b;
                    if(nextPosition == x) return res;
                    if(nextPosition > 0 && !set.contains(nextPosition)){
                        q.add(nextPosition);
                        status.add(false);
                        //set.add(nextPosition); 如果这里将其放入set中，则可能出现此种情况：先：左移，加入set；后: 某一处左移之后为起始点经过若干次右移恰好位于此点，则此时原本应该继续右移的则变为不可右移，中断了第21行；
                    }
                }
            }
        }
        return -1;
    }
}
