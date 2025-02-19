import java.io.*;
import java.util.ArrayList;
import java.util.Collections;


public class WordStatWordsSuffix {
    public static void main(String[] args) {
        ArrayList<String> arrayListWords = new ArrayList<>();
        try{
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(args[0]),
                            "utf8"
                    ), 1024
            );
            try {
                BufferedWriter out = new BufferedWriter(
                        new OutputStreamWriter(
                                new FileOutputStream(args[1]),
                                "utf8"
                        ), 1024
                );
                try {
                    char[] buffer = new char[1024];
                    StringBuilder sb = new StringBuilder();
                    while(true){
                        int read = in.read(buffer);
                        if(read < 0){
                            break;
                        } else{
                            int start = 0;
                            for (int j = 0; j < read; j++) {
                                if ((Character.isLetter(buffer[j])
                                        || Character.getType(buffer[j]) == Character.DASH_PUNCTUATION
                                        || buffer[j] == '\'')) {
                                    sb.append(buffer[j]);
                                } else{
                                    if (sb.length() >= 3) {
                                        arrayListWords.add(sb.substring(sb.length() - 3, sb.length()).toLowerCase());
                                    } else if(sb.isEmpty()){
                                        continue;
                                    }else {
                                        arrayListWords.add((sb.toString().toLowerCase()));
                                    }
                                    sb.setLength(0);
                                }
                            }
                        }
                    }
                    String[] array = new String[arrayListWords.size()];
                    for (int i = 0; i < array.length; i ++){
                        array[i] = arrayListWords.get(i);
                    }
                    Collections.sort(arrayListWords, Collections.reverseOrder());

                    int cnt = 1;
                    int i = 1;

                    while (i < arrayListWords.size()) {
                        if (arrayListWords.get(i).equals(arrayListWords.get(i - 1))) {
                            cnt++;
                        } else {
                            out.write(arrayListWords.get(i - 1) + " " + cnt);
                            out.newLine();
                            cnt = 1;
                        }
                        i++;
                    }
                    out.write(arrayListWords.get(i - 1) + " " + cnt);
                    out.newLine();
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
}