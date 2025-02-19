import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;


public class WordStatInput {
    public static void main(String[] args) {
        Map<String, String> dict = new LinkedHashMap<String, String>();
        try{
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(args[0]),
                            StandardCharsets.UTF_8

                    ), 1024
            );
            try {
                BufferedWriter out = new BufferedWriter(
                        new OutputStreamWriter(
                                new FileOutputStream(args[1]),
                                StandardCharsets.UTF_8
                        ), 1024
                );
                try {
                    while(true){
                        String line = in.readLine();
                        //System.out.println(line);
                        if(line == null){
                            break;
                        } else{
                            int start = 1;
                            line = " " + line + "  ";
                            for (int j = 1; j < line.length(); j++) {
                                if (!(Character.isLetter(line.charAt(j - 1))
                                        || Character.getType(line.charAt(j - 1)) == Character.DASH_PUNCTUATION
                                        || line.charAt(j - 1) == '\'')) {
                                    if (j - start > 1) {
                                        String str = line.substring(start, j - 1).toLowerCase();
                                        if(dict.containsKey(str)){
                                            String cnt = Integer.toString(Integer.parseInt(dict.get(str)) + 1);
                                            dict.put(str, cnt);
                                        }
                                        else{
                                            dict.put(str, "1");
                                        }
                                    }
                                    start = j;
                                }
                            }
                        }
                    }
                    for(Map.Entry<String, String> str : dict.entrySet()){
                        out.write(str.getKey() + " " + str.getValue());
                        out.newLine();
                    }
                } finally {
                    out.close();
                }
            } finally {
                in.close();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    public static int isInDict(ArrayList<String> arrayListWords, String str){
        for(int i = 0; i < arrayListWords.size(); i++){
            if (arrayListWords.get(i).equals(str.toLowerCase()) == true){
                return i;
            }
        }
        return -1;
    }

    public static void printArrays(ArrayList arrayListWords, ArrayList arrayListCnt){
        for(int i = 0; i < arrayListWords.size(); i++){
            System.out.println(arrayListWords.get(i) + ": " + arrayListCnt.get(i));
        }
    }

}