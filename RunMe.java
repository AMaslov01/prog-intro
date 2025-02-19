import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Run this code with provided arguments.
 *
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
@SuppressWarnings("MagicNumber")
public final class RunMe {
    private RunMe() {
        // Utility class
    }

    public static void main(final String[] args) {
        final byte[] password = parseArgs(args);

        flag0(password);
        System.out.println("The first flag was low-hanging fruit, can you find others?");
        System.out.println("Try to read, understand and modify code in flagX(...) functions");

        //flag1(password);
        flag2(password);
        flag11(password);
        //flag3(password);
        //flag4(password);
        //flag5(password);
        flag6(password);
        flag7(password);
        flag8(password);
        flag9(password);
        /*flag10(password);
        flag12(password);
        flag13(password);
        flag14(password);*/
        flag15(password);
        flag16(password);
        flag17(password);
        flag18(password);
        flag19(password);
         flag20(password);
    }

    private static void flag0(final byte[] password) {
        // The result of print(...) function depends only on explicit arguments
        print(0, 0, password);
    }


    private static void flag1(final byte[] password) {

        print(1, -3703725051403425822L, password);
    }


    private static void flag2(final byte[] password) {
        int result = 0;
        for (int i = 0; i < 300; i++) {
            for (int j = 0; j < 300; j++) {
                for (int k = 0; k < 300; k++) {
                    result ^= (i * 7) | (j + k);
                    result ^= result << 1;
                }
            }
        }

        print(2, 8997336738979411726L, password);
    }


    private static void flag3(final byte[] password) {
        int result = 0;
        for (int i = 0; i < 2024; i++) {
            for (int j = 0; j < 2024; j++) {
                for (int k = 0; k < 2024; k++) {
                    for (int p = 0; p < 12; p++) {
                        result ^= (i * 13) | (j + k * 7) & ~p;
                        result ^= result << 1;
                    }
                }
            }
        }

        print(3, result, password);
    }


    private static void flag4(final byte[] password) {
        final long target = 607768613708938510L + getInt(password);
        for (long i = 0; i < Long.MAX_VALUE; i++) {
            if ((i ^ (i >>> 32)) == target) {
                print(4, i, password);
            }
        }
    }

    /* package-private */ static final long PRIME = 1073741827;

    private static void flag5(final byte[] password) {
        final long n = 1_000_000_000_000_000L + getInt(password);

        long result = 0;
        for (long i = 0; i < n; i++) {
            result = (result + i / 3 + i / 5 + i / 7 + i / 2024) % PRIME;
        }

        print(5, result, password);
    }


    private static void flag6(final byte[] password) {
//        /*
//            \u002a\u002f\u0077\u0068\u0069\u006c\u0065\u0020\u0028\u0022\u0031\u0022
//            \u002e\u006c\u0065\u006e\u0067\u0074\u0068\u0028\u0029\u0020\u003d\u003d
//            \u0020\u0031\u0029\u003b\u0020\u0020\u006c\u006f\u006e\u0067\u0020\u0009
//            \u0020\u0020\u0072\u0065\u0073\u0075\u006c\u0074\u0020\u003d\u0020\u000a
//            \u0034\u0035\u0034\u0035\u0034\u0032\u0035\u0032\u0034\u0034\u0033\u004c
//            \u002b\u0070\u0061\u0073\u0073\u0077\u006f\u0072\u0064\u005b\u0033\u005d
//            \u002b\u0070\u0061\u0073\u0073\u0077\u006f\u0072\u0064\u005b\u0034\u005d
//            \u003b\u002f\u002a
//        */
        long result = 45454252443L + password[3] + password[4];
        print(6, result, password);
    }


    private static void flag7(final byte[] password) {
        // Count the number of occurrences of the most frequent noun at the following page:
        // https://docs.oracle.com/javase/specs/jls/se21/html/jls-14.html

        // The singular form of the most frequent noun
        final String singular = "statement";
        // The plural form of themost frequent noun
        final String plural = "statements";
        // The total number of occurrences
        final int total = 740;
        if (total != 0) {
            print(7, (singular + ":" + plural + ":" + total).hashCode(), password);
        }
    }


    private static void flag8(final byte[] password) {
        // Count the number of red (#ff0021) pixes of this image:
        // https://i0.wp.com/blog.nashtechglobal.com/wp-content/uploads/2024/04/Screenshot-from-2024-04-29-23-33-40.png

        final int number = 160846;
        if (number != 0) {
            print(8, number, password);
        }
    }


    private static final String PATTERN = "Reading a documentation can be surprisingly helpful!";
    private static final int SMALL_REPEAT_COUNT = 10_000_000;

    private static void flag9(final byte[] password) {
        StringBuilder repeated = new StringBuilder(PATTERN.length() * SMALL_REPEAT_COUNT);
        for (int i = 0; i < SMALL_REPEAT_COUNT; i++) {
            repeated.append(PATTERN);
        }

        print(9, repeated.hashCode(), password);
    }


    private static final long LARGE_REPEAT_SHIFT = 28;
    private static final long LARGE_REPEAT_COUNT = 1L << LARGE_REPEAT_SHIFT;

    private static void flag10(final byte[] password) {
        String repeated = "";
        for (long i = 0; i < LARGE_REPEAT_COUNT; i++) {
            repeated += PATTERN;
        }

        print(10, repeated.hashCode(), password);
    }


    private static void flag11(final byte[] password) {
        print(11, -3421800510071217219L, password);
    }


    private static void flag12(final byte[] password) {
        final BigInteger year = BigInteger.valueOf(-2024);
        final BigInteger term = BigInteger.valueOf(PRIME + Math.abs(getInt(password)) % PRIME);

        final long result = Stream.iterate(BigInteger.ZERO, BigInteger.ONE::add)
                .filter(i -> year.multiply(i).add(term).multiply(i).compareTo(BigInteger.ZERO) > 0)
                .mapToLong(i -> i.longValue() * password[i.intValue() % password.length])
                .sum();

        print(12, result, password);
    }


    private static final long MAX_DEPTH = 100_000_000L;

    private static void flag13(final byte[] password) {
        try {
            flag13(password, 0, 0);
        } catch (final StackOverflowError e) {
            System.err.println("Stack overflow :((");
        }
    }

    private static void flag13(final byte[] password, final long depth, final long result) {
        if (depth < MAX_DEPTH) {
            flag13(password, depth + 1, (result ^ PRIME) | (result << 2) + depth * 17);
        } else {
            print(13, result, password);
        }
    }


    private static void flag14(final byte[] password) {
        final Instant today = Instant.parse("2024-09-10T12:00:00Z");
        final BigInteger hours = BigInteger.valueOf(Duration.between(Instant.EPOCH, today).toHours());

        final long result = Stream.iterate(BigInteger.ZERO, BigInteger.ONE::add)
                .map(hours::multiply)
                .reduce(BigInteger.ZERO, BigInteger::add)
                .longValue();

        print(14, result, password);
    }

    private static void flag15(final byte[] password) {
        print(15, 3519786268141933778L + password[3], password);
    }

    private static void flag16(final byte[] password) {
        byte[] a = {
                (byte) (password[0] + password[3]),
                (byte) (password[1] + password[4]),
                (byte) (password[2] + password[5])
        };

        for (long i = 1_000_000_000_000_000_000L + getInt(password); i >= 0; i--) {
            flag16Update(a);
        }

        print(16, flag16Result(a), password);
    }

    /* package-private */ static void flag16Update(byte[] a) {
        a[0] ^= a[1];
        a[1] += a[2] | a[0];
        a[2] *= a[0];
    }

    /* package-private */ static int flag16Result(byte[] a) {
        return (a[0] + " " + a[1] + " " + a[2]).hashCode();
    }

    private static void flag17(final byte[] password) {
        final int n = Math.abs(getInt(password) % 2024) + 2024;
        print(17, calc17(n), password);
    }

    /**
     * Write me
     * <pre>
     *    0: iconst_0
     *    1: istore_1
     *    2: iload_1
     *    3: bipush        25
     *    5: idiv
     *    6: iload_0
     *    7: isub
     *    8: ifge          17
     *   11: iinc          1, 1
     *   14: goto          2
     *   17: iload_1
     *   18: ireturn
     * </pre>
     */
    private static int calc17(final int n) {
        return n;
    }


    private static void flag18(final byte[] password) {
        final int n = 2024 + getInt(password) % 2024;
        // Find the number of factors of n! modulo PRIME
        final int factors = 0;
        if (factors != 0) {
            print(18, factors, password);
        }
    }


    private static void flag19(final byte[] password) {
        // Let n = 2024 * 10**24 + getInt(password).
        // Consider the sequence of numbers (n + i) ** 2.
        // Instead of each number, we write the number that is obtained from it by discarding the last 24 digits.
        // How many of the first numbers of the resulting sequence will form an arithmetic progression?
        final long result = 0;
        if (result != 0) {
            print(19, result, password);
        }
    }

    private static void flag20(final byte[] password) {
        final Collection<Long> longs = new Random(getInt(password)).longs(1_000_000)
                .map(n -> n % 1000)
                .boxed()
                .collect(Collectors.toCollection(LinkedList::new));

        // Calculate the number of objects (recursively) accessible by "longs" reference.
        final int result = 0;

        if (result != 0) {
            print(20, result, password);
        }
    }

    // ---------------------------------------------------------------------------------------------------------------
    // You may ignore all code below this line.
    // It is not required to get any of the flags.
    // ---------------------------------------------------------------------------------------------------------------

    private static void print(final int no, long result, final byte[] password) {
        System.out.format("flag %d: https://www.kgeorgiy.info/courses/prog-intro/hw1/%s%n", no, flag(result, password));
    }

    /* packge-private */ static String flag(long result, byte[] password) {
        final byte[] flag = password.clone();
        for (int i = 0; i < 6; i++) {
            flag[i] ^= result;
            result >>>= 8;
        }

        return flag(SALT, flag);
    }

    /* package-private */ static String flag(final byte[] salt, final byte[] data) {
        DIGEST.update(salt);
        DIGEST.update(data);
        DIGEST.update(salt);
        final byte[] digest = DIGEST.digest();

        return IntStream.range(0, 6)
                .map(i -> (((digest[i * 2] & 255) << 8) + (digest[i * 2 + 1] & 255)) % KEYWORDS.size())
                .mapToObj(KEYWORDS::get)
                .collect(Collectors.joining("-"));
    }

    /* package-private */ static byte[] parseArgs(final String[] args) {
        if (args.length != 6) {
            throw error("Expected 6 command line arguments, found: %d", args.length);
        }

        final byte[] bytes = new byte[args.length];
        for (int i = 0; i < args.length; i++) {
            final Byte value = VALUES.get(args[i].toLowerCase(Locale.US));
            if (value == null) {
                throw error("Expected keyword, found: %s", args[i]);
            }
            bytes[i] = value;
        }
        return bytes;
    }

    private static AssertionError error(final String format, final Object... args) {
        System.err.format(format, args);
        System.err.println();
        System.exit(1);
        throw new AssertionError();
    }

    /* package-private */ static int getInt(byte[] password) {
        return IntStream.range(0, password.length)
                .map(i -> password[i])
                .reduce((a, b) -> a * KEYWORDS.size() + b)
                .getAsInt();
    }

    private static final MessageDigest DIGEST;
    static {
        try {
            DIGEST = MessageDigest.getInstance("SHA-256");
        } catch (final NoSuchAlgorithmException e) {
            throw new AssertionError("Cannot create SHA-256 digest", e);
        }
    }

    private static final byte[] SALT = "raceipkebrAdLenEzSenickTejtainulhoodrec6".getBytes(StandardCharsets.US_ASCII);

    private static final List<String> KEYWORDS = List.of(
            "abstract",
            "assert",
            "boolean",
            "break",
            "byte",
            "case",
            "catch",
            "char",
            "class",
            "const",
            "new",
            "package",
            "private",
            "protected",
            "public",
            "return",
            "short",
            "static",
            "strictfp",
            "super",
            "for",
            "goto",
            "if",
            "implements",
            "import",
            "instanceof",
            "int",
            "interface",
            "long",
            "native",
            "continue",
            "default",
            "do",
            "double",
            "else",
            "enum",
            "extends",
            "final",
            "finally",
            "float",
            "switch",
            "synchronized",
            "this",
            "throw",
            "throws",
            "transient",
            "try",
            "void",
            "volatile",
            "while",
            "record",
            "Error",
            "AssertionError",
            "OutOfMemoryError",
            "StackOverflowError",
            "ArrayIndexOutOfBoundsException",
            "ArrayStoreException",
            "AutoCloseable",
            "Character",
            "CharSequence",
            "ClassCastException",
            "Comparable",
            "Exception",
            "IllegalArgumentException",
            "IllegalStateException",
            "IndexOutOfBoundsException",
            "Integer",
            "Iterable",
            "Math",
            "Module",
            "NegativeArraySizeException",
            "NullPointerException",
            "Number",
            "NumberFormatException",
            "Object",
            "Override",
            "RuntimeException",
            "StrictMath",
            "String",
            "StringBuilder",
            "StringIndexOutOfBoundsException",
            "SuppressWarnings",
            "System",
            "Thread",
            "Throwable",
            "ArithmeticException",
            "ClassLoader",
            "ClassNotFoundException",
            "Cloneable",
            "Deprecated",
            "FunctionalInterface",
            "InterruptedException",
            "Process",
            "ProcessBuilder",
            "Runnable",
            "SafeVarargs",
            "StackTraceElement",
            "Runtime",
            "ThreadLocal",
            "UnsupportedOperationException"
    );

    private static final Map<String, Byte> VALUES = IntStream.range(0, KEYWORDS.size())
            .boxed()
            .collect(Collectors.toMap(index -> KEYWORDS.get(index).toLowerCase(Locale.US), Integer::byteValue));
}
