import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Reverse{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<String> arrayList = new ArrayList<>();
        while (scanner.hasNextLine()){
            arrayList.add(scanner.nextLine());
        }
        scanner.close();
        for(int i = arrayList.size() - 1; i >= 0; i--){
            printString(arrayList.get(i));
        }

    }

    public static void printString(String str){
        str = " " + str + " ";
        int end = str.length() - 1;
        for (int i = str.length() - 1; i >= 0; i--) {
            if (Character.isWhitespace(str.charAt(i))) {
                if (end - i > 1){
                    System.out.print(str.substring(i + 1, end) + " ");
                }
                end = i;
            }
            if(i == 0){
                System.out.println();
            }
        }
    }
}