package md2html;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class AbstractHtml {
    String string;
    boolean noPictureFlag = false;
    public final String escapedSymbol = "\\";
    StringBuilder res = new StringBuilder();
    final Map<String, String> dict = Map.of(
            "*", "em",
            "_", "em",
            "**", "strong",
            "__", "strong",
            "--", "s",
            "`", "code"
    );
    final Map<Character, String> specialChars = Map.of(
            '<', "&lt;",
            '>', "&gt;",
            '&', "&amp;"
    );

    public AbstractHtml(String string){
        this.string = string;
    }

    public void parse(){
        ArrayList<String> stack = new ArrayList<>();
        int i = 0;
        while(i < string.length()){
            char chr = string.charAt(i);
            if(!noPictureFlag && checkPhoto(chr, i) == 1){// photo check
                int imageEnding = checkImageEnding(i, string);
                String image = string.substring(i, imageEnding);
                res.append(convertImage(image));
                i += image.length();
            }
            if(i < string.length()){ //if after photo check symbols are left
                String ch = Character.toString(string.charAt(i));
                if(isDoubled(i, ch)){ //check whether it is a doubled symbol
                    StringBuilder sb = new StringBuilder(ch);
                    sb.append(string.charAt(i + 1));
                    ch = sb.toString();
                    i ++;
                }
                if (isSingle(i, ch) && dict.containsKey(ch)){ //check whether symbol is surrounded with whitespaces
                    res.append(ch);
                    i ++;
                } else if(specialChars.containsKey(string.charAt(i))){
                    res.append(specialChars.get(string.charAt(i)));
                    i ++;
                } else if(!stack.isEmpty() && ch.equals(stack.get(stack.size() - 1))){
                    stack.remove((stack.size() - 1));
                    res.append("</").append(dict.get(ch)).append(">");
                    i ++;
                } else if(dict.containsKey(ch)){ // if stack top doesn't match
                    stack.add(ch);
                    res.append("<").append(dict.get(ch)).append(">");
                    i ++;
                } else if (isEscaped(i, ch) || ch.equals(escapedSymbol)) {
                    res.append(string.charAt(i + 1));
                    i += 2;
                } else{  //ordinary character
                    res.append(ch);
                    i ++;
                }
            }
        }
        string = res.toString();
        res.setLength(0);
    }

    private int checkPhoto(char chr, int i){
        int start = i;
        boolean rightSquared = false;
        boolean leftParenthesis = false;
        boolean rightParenthesis = false;
        if(chr == '!' && i + 1 < string.length() && string.charAt(i + 1) == '['){
            i += 2;
            while((!rightParenthesis || !leftParenthesis || !rightSquared) && i < string.length()){
                if(string.charAt(i) == ']'){
                    rightSquared = true;
                } else if(string.charAt(i) == '('){
                    leftParenthesis = true;
                } else if(string.charAt(i) == ')'){
                    rightParenthesis = true;
                } i++;
            }
            int imageEnding = checkImageEnding(start, string);
            if(imageEnding == -2){
                return -1;
            }
            if(rightParenthesis && leftParenthesis && rightSquared){
                return 1;
            } else{
                noPictureFlag = true;
                return 0;
            }
        } else{
            return 0;
        }
    }

    private int checkImageEnding(int start, String str){
        int i = start;
        while(i < str.length()) {
            if(i > 0 && Character.toString(str.charAt(i - 1)).equals(escapedSymbol) && ((str.charAt(i) == '!') ||(str.charAt(i) == '[') || (str.charAt(i) == ']')
                    || (str.charAt(i) == '(') || (str.charAt(i) == ')'))){
                return -2;
            }
            if (str.charAt(i) == ')') {
                return i + 1;
            }
            i ++;
        }
        return -1;
    }

    public String convertImage(String string){
        int index1 = 0;
        for(int i = 0; i < string.length(); i++ ){
            if(string.charAt(i) == ']'){
                index1 = i + 1;
            }
        }
        return "<img alt='" +
                string.substring(2, index1 - 1) +
                "' src='" +
                string.substring(index1 + 1, string.length() - 1) +
                "'>";
    }

    private boolean isDoubled(int i, String ch){
        return i < string.length() - 1
                && (dict.containsKey(ch) || dict.containsKey(ch + string.charAt(i + 1)))
                && ch.equals(Character.toString(string.charAt(i + 1)));
    }

    private boolean isSingle(int i, String ch){
        boolean leftWhitespace = (i == 0) || (Character.isWhitespace(string.charAt(i - 1)));
        boolean rightWhitespace = (i == string.length() - 1) || (Character.isWhitespace(string.charAt(i + 1)));
        return leftWhitespace && rightWhitespace;
    }

    private boolean isEscaped(int i, String ch){
        return i < string.length() - 1 && ch.equals(escapedSymbol) && dict.containsKey(Character.toString(string.charAt(i + 1)));
    }
}
