package expression.generic;

import expression.exceptions.NegativeModulusException;
import expression.exceptions.OverflowException;
import expression.exceptions.ZeroDivisionException;
import expression.generic.parser.ExpressionParser;
import expression.generic.types.*;

import java.util.Map;

public class GenericTabulator implements Tabulator{

    public GenericTabulator() {
    }

    private static final Map<String, Type<?>> TYPES = Map.of(
            "i", new CheckedIntegerType(),
            "d", new DoubleType(),
            "bi", new BigIntegerType(),
            "u", new IntegerType(),
            "l", new LongType()
    );

    // :NOTE: throws Exception
    public <T> Object[][][] innerTabulate(
            final Type<T> type, final String expression,
            final int x1, final int x2, final int y1, final int y2, final int z1, final int z2
    ) throws Exception {
        // :NOTE: abs?
        final Object[][][] array = new Object[Math.abs(x1 - x2) + 1][Math.abs(y1 - y2) + 1][Math.abs(z1 - z2) + 1];
        final ExpressionParser<T> parser = new ExpressionParser<>(type);
        // :NOTE: spaces
        for(int x = 0; x <= Math.abs(x1 - x2); x++) {
            for(int y = 0; y <= Math.abs(y1 - y2); y++) {
                for(int z = 0; z <= Math.abs(z1 - z2); z++) {
                    try {
                        assert array[x][y] != null; // :NOTE: ??
                        array[x][y][z] = parser.parse(expression).evaluate(type.parse( x1 + x), type.parse( y1 + y) , type.parse(z1 + z));
                        // :NOTE: exceptions design
                    } catch (final OverflowException | ZeroDivisionException | NegativeModulusException e) {
//                        array[x][y][z] = null;
                    }
                }
            }
        }
        return array;
    }


    @Override
    public Object[][][] tabulate(final String mode, final String expression, final int x1, final int x2, final int y1, final int y2, final int z1, final int z2) throws Exception {
        return innerTabulate(TYPES.get(mode), expression, x1, x2, y1, y2, z1, z2);
    }
}