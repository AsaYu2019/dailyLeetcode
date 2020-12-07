class Solution {
    //method 1: memorize dupulicate in a new array count[], if count[i] == k, delete
  /*  public String removeDuplicates(String s, int k) {
        StringBuilder sb = new StringBuilder(s);
        int count[] = new int[sb.length()];
        for(int i = 0; i < sb.length(); i++){
            if(i == 0 || sb.charAt(i) != sb.charAt(i - 1)) count[i] = 1;
            else{
                count[i] = count[i - 1] + 1;
                if(count[i] == k){
                    sb.delete(i - k + 1, i + 1);
                    i = i - k;
                }
            }
        }
        return sb.toString();
    }*/
    //method 2: use stack to instead of count[]
    /*
    public String removeDuplicates(String s, int k) {
        StringBuilder sb = new StringBuilder(s);
        Stack<Integer> countSt = new Stack<>();
        for(int i = 0; i < sb.length(); i++){
            if(i == 0 || sb.charAt(i) != sb.charAt(i - 1)) countSt.push(1);
            else{
                int incremented = countSt.pop() + 1;
                if(incremented == k){
                    sb.delete(i - k + 1, i + 1);
                    i = i - k;
                }else{
                    countSt.push(incremented);
                }
            }
        }
        return sb.toString();
    }*/
    // method 3: when every time there is k duplicates, remove, then move i back to i - k + 1 positon. (without extra space)
    public String removeDuplicates(String s, int k) {
        StringBuilder sb = new StringBuilder(s);
        int count = 0;
        for(int i = 0; i < sb.length(); i++){
            if(i == 0 || sb.charAt(i) != sb.charAt(i - 1)) count = 1;
            else{
                count++;
                if(count == k){
                    sb.delete(i - k + 1, i + 1);
                    i = i - k;
                    if(i - k + 1 < 0) i = -1;
                    else i = i - k + 1;
                }
            }
        }
        return sb.toString();
    }
}
