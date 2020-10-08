package com.hsw.tdd.utils;

public class HexUtil {
    public static String byteArrayToHex(byte[] byteArray) {
        if (byteArray == null || byteArray.length == 0) {
            return null;
        }
        StringBuilder stringBuffer = new StringBuilder(byteArray.length * 2);
        String hexNumber;
        for (byte aBa : byteArray) {
            hexNumber = "0" + Integer.toHexString(0xff & aBa);

            stringBuffer.append(hexNumber.substring(hexNumber.length() - 2));
        }
        return stringBuffer.toString();
    }

    public static byte[] hexStringToByte(String hexStr) {
        return new java.math.BigInteger(hexStr, 16).toByteArray();
    }
}
