class Solution {
    public String simplifyPath(String path) {
        if(path.isEmpty()) return path;
        Stack<String> st = new Stack<>();
        String[] componets = path.split("/");
        
        for(String directory : componets){
            if(directory.equals(".") || directory.isEmpty()) continue;
            else if(directory.equals("..")){
                if(!st.isEmpty()) st.pop();
            }else{
                st.add(directory);
            }
        }
        StringBuilder sb = new StringBuilder();
        for(String dir : st){
            sb.append("/");
            sb.append(dir);
        }
        
        return sb.length() > 0 ? sb.toString() : "/";
    }
}
