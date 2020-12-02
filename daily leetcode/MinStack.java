    //method 1: implement minstack without Stack
    List<Integer> ls;
    List<Integer> min;
    /** initialize your data structure here. */
    public MinStack() {
        ls = new ArrayList<>();
        min = new ArrayList<>();
    }
    
    public void push(int x) {
        ls.add(x);
        if( min.size() == 0) {
            min.add(x);
        }else if(min.get(min.size() - 1) >= x) min.add(x);
    }
    
    public void pop() {
        int n = ls.size() - 1;
        int m = min.size() - 1;
        if(ls.get(n).equals(min.get(m))) {
            min.remove(m);   
        }
        ls.remove(n);
    }
    
    public int top() {
        return ls.get(ls.size() - 1);
    }
    
    public int getMin() {
        System.out.println(min.size());
        return min.get(min.size() - 1);
    }
}
