package community.whatever.onembackendjava.common.utils;

import java.math.BigInteger;

public class Base62Encoder {

    private static final String BASE62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String encode(long input) {
        StringBuilder sb = new StringBuilder();
        BigInteger num = BigInteger.valueOf(input);
        BigInteger base = BigInteger.valueOf(62);

        while (num.compareTo(BigInteger.ZERO) > 0) {
            BigInteger remainder = num.mod(base);
            sb.append(BASE62.charAt(remainder.intValue()));
            num = num.divide(base);
        }

        return sb.reverse().toString();
    }
}
