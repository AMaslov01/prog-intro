import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ReverseSumAbs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<int[]> arrayList = new ArrayList<>();
        while (scanner.hasNextLine()) {
            arrayList.add(transformStringToIntArray(scanner.nextLine()));
        }
        scanner.close();
        int[] arrayVertex = getVertexArray(arrayList);
        int[] arrayHorizontal = getHorizontalArray(arrayList);

        getResult(arrayVertex, arrayHorizontal, arrayList);
    }

    public static int[] transformStringToIntArray(String str) {
        int[] array = new int[1];
        Scanner scanner = new Scanner(str);
        int i = 0;
        int cnt = 0;
        while(scanner.hasNextInt()){
            int num = scanner.nextInt();
            if(i >= array.length){
                array = expandArray(array, Math.abs(num));
            }
            else{
                array[i] = Math.abs(num);
            }
            i ++;
            cnt ++;
        }
        array = Arrays.copyOf(array, cnt);
        return array;
    }

    public static int[] expandArray(int[] array, int num) {
        // :NOTE: x2
        int[] newArray = Arrays.copyOf(array, array.length * 2);
        newArray[array.length] = num;
        return newArray;
    }

    public static int[] getVertexArray(ArrayList<int[]> arrayList) {
        int[] arrayVertex = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            for (int j = 0; j < arrayList.get(i).length; j++) {
                arrayVertex[i] += arrayList.get(i)[j];
            }
        }
        return arrayVertex;
    }

    public static int[] getHorizontalArray(ArrayList<int[]> arrayList) {
        int max = -1;
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).length > max) {
                max = arrayList.get(i).length;
            }
        }
        int[] arrayHorizontal = new int[max];
        for (int i = 0; i < max; i++) {
            for (int j = 0; j < arrayList.size(); j++) {
                if (i < arrayList.get(j).length) {
                    arrayHorizontal[i] += arrayList.get(j)[i];
                }
            }
        }
        return arrayHorizontal;
    }

    public static void getResult(int[] arrayVertex, int[] arrayHorizontal, ArrayList<int[]> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            for (int j = 0; j < arrayList.get(i).length; j++) {
                System.out.print(arrayVertex[i] + arrayHorizontal[j] - arrayList.get(i)[j] + " ");
            }
            System.out.println();
        }
    }

}


