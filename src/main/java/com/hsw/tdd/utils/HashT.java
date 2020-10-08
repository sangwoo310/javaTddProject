package com.hsw.tdd.utils;

import io.github.novacrypto.hashing.Hash160;
import io.github.novacrypto.hashing.Sha256;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HashT {
    public static void sha256T(int shaCount, String valueStr) {
        byte[] valueByte = HexUtil.hexStringToByte(valueStr);
//        byte[] valueByte = valueStr.getBytes();

        if (shaCount == 1) {
            byte[] shaByte = Sha256.sha256(valueByte, 0, valueByte.length);
            log.info("shaByte : {}", shaByte);
            log.info("shaStr : {}", HexUtil.byteArrayToHex(shaByte));

        } else {
            byte[] shaByte = Sha256.sha256Twice(valueByte, 0, valueByte.length);
            log.info("shaByte : {}", shaByte);
            log.info("shaStr : {}", HexUtil.byteArrayToHex(shaByte));
        }
    }

    public static void sha256R(int shaCount, String valueStr) {
        byte[] valueByte = HexUtil.hexStringToByte(valueStr);
//        byte[] valueByte = valueStr.getBytes();

        byte[] shaByte = Sha256.sha256(valueByte);
        log.info("shaByte : {}", shaByte);
        log.info("shaStr : {}", HexUtil.byteArrayToHex(shaByte));

        if (shaCount == 1) {
            return;
        }

        sha256R(shaCount - 1, HexUtil.byteArrayToHex(shaByte));
    }

    public static void hash160T(String valueStr) {
        byte[] valueByte = HexUtil.hexStringToByte(valueStr);

        byte[] hash160Byte = Hash160.hash160(valueByte);
        log.info("hash160Byte : {}", hash160Byte);
        log.info("hash160Str : {}", HexUtil.byteArrayToHex(hash160Byte));
    }
}
