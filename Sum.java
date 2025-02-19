public class Sum {
    public static void main(String[] args) {
        int sum = 0;
        int i = 0;
        int start = 1;
        while(i < args.length){
            args[i] = " " + args[i] + "  ";
            for(int j = 1; j < args[i].length(); j++){
                if (Character.isWhitespace(args[i].charAt(j - 1))){
                    if(j - start > 1){
                        sum += Integer.parseInt(args[i].substring(start,j - 1));
                    }
                    start = j;
                }
            }
            i ++;
        }
        System.out.println(sum);
    }
}
