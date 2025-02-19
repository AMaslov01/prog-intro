package md2html;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Md2Html {
    public static void main(String[] args) {
        List<String> lines = null;
        try {
            lines = Files.lines(Path.of(args[0])).toList();
        } catch (IOException e) {
            System.err.println("Error while reading file: " + e.getMessage());
        }
        Converter converter = new Converter(lines);
        converter.processHtml();
        try {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), "utf8"));
            out.write(converter.getResult());
            out.close();
        } catch(IOException e){
            System.err.println("Error while writing file: " + e.getMessage());
        }
    }
}

