package expression.generic;

import expression.generic.types.Type;

import java.util.Arrays;

public class Main{
    public static <T> void main(String[] args) throws Exception {
        Object[][][] array = new GenericTabulator().tabulate(args[0], args[1], -2, 2, -2, 2, -2, 2);
        for(int i = 0; i <= 4; i ++){
            System.out.println("-------");
            System.out.println("x = " + i);
            print(array[i]);
        }
    }

    public static void print(Object[][] array){
        for(Object[] inner : array){
            System.out.println(Arrays.toString(inner));
        }
    }
}
