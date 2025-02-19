package md2html;
import java.util.List;

public class Converter {
    List<String> lines;
    StringBuilder res = new StringBuilder();
    StringBuilder processor = new StringBuilder();
    boolean inParagraph = false;
    boolean inHeading = false;
    final int MAX_DEPTH = 6;
    public int depth = 0;
    public Converter(List<String> lines){
        this.lines = lines;
    }

    public String getResult(){
        return res.toString();
    }

    public void addLine(String str){
        processor.append(System.lineSeparator());
        processor.append(str);
    }

    public void processHtml(){
        for(int i = 0; i < lines.size(); i ++){
            String str = lines.get(i);
            if(inHeading && !str.isBlank()){
                addLine(str);
            } else if(!str.isBlank() && checkHeading(str)){
                processor.append(str);
                inHeading = true;
            } else if (inParagraph && !str.isBlank()) {
                addLine(str);
            } else if(!str.isBlank()){
                processor.append(str);
                inParagraph = true;
            }
            if(str.isBlank() || i == lines.size() - 1){
                if(inHeading){
                    HeadingHtml heading = new HeadingHtml(processor.toString());
                    heading.parse();
                    res.append(heading.convertHeading(depth));
                }
                if(inParagraph){
                    ParagraphHtml paragraphHtml = new ParagraphHtml(processor.toString());
                    paragraphHtml.parse();
                    res.append(paragraphHtml.convertParagraph());
                }
                inHeading = false;
                inParagraph = false;
                processor.setLength(0);
            }
        }
    }

    private boolean checkHeading(String str){
        int i = 0;
        if(str.charAt(0) != '#'){
            return false;
        }
        while(str.charAt(i) == '#'){
            if(i >= MAX_DEPTH){
                return false;
            }
            i ++;
        }
        if(!Character.isWhitespace(str.charAt(i))){
            return false;
        }
        depth = i;
        return true;
    }
}
