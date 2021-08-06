package com.hsw.tdd.crypto;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;


public class Aes256Cipher {
    private byte[] iv;
    private Key keySpec;

    /**
     * 16자리의 키값을 입력하여 객체를 생성한다.
     *
     * @param keyByte 암/복호화를 위한 키값
     * @throws UnsupportedEncodingException 키값의 길이가 16이하일 경우 발생
     */

    public Aes256Cipher(byte[] keyByte, byte[] ivByte) {
        byte[] pkByte = new byte[32];
        byte[] copyByte = new byte[16];
        System.arraycopy(ivByte, 0, copyByte, 0, 16);

        this.iv = copyByte;

        /*
        * aes128 방식 keySpec 을 iv값 그대로 생성
        byte[] keyBytes = new byte[16];
        byte[] b = pkByte;
        int len = b.length;
        if (len > keyBytes.length) {
            len = keyBytes.length;
        }
        System.arraycopy(b, 0, keyBytes, 0, len);
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
        */

        // key 값의 길이가 32 이하일때 padding 값으로 추가
        if (keyByte.length == 32) {
            pkByte = keyByte;

        } else if (keyByte.length < 32) {
            System.arraycopy(keyByte, 0, pkByte, 0, keyByte.length);

            int padding = 32 - keyByte.length;

            byte[] paddingByte = new byte[1];
            paddingByte[0] = (byte) (padding & 0x000000FF);

            for (int i = 0; i < padding; i++) {
                pkByte[keyByte.length + i] = paddingByte[0];
            }

        } else {
            throw new Error("out of range key size. key size must under 32byte");
        }

        // aes256 32byte 값을 keySpec 생성
        SecretKeySpec keySpec = new SecretKeySpec(pkByte, "AES");

        this.keySpec = keySpec;
    }

    /**
     * AES256 으로 암호화 한다.
     *
     * @param plainByte 암호화할 바이트 배열
     * @return
     * @throws NoSuchAlgorithmException
     * @throws GeneralSecurityException
     */
    public byte[] encrypt(byte[] plainByte) throws NoSuchAlgorithmException, GeneralSecurityException {
        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv));
        byte[] encrypted = c.doFinal(plainByte);

        return encrypted;
    }

    /**
     * AES256으로 암호화된 txt 를 복호화한다.
     *
     * @param encryptedByte 복호화할 바이트 배열
     * @return
     * @throws NoSuchAlgorithmException
     * @throws GeneralSecurityException
     * @throws UnsupportedEncodingException
     */
    public byte[] decrypt(byte[] encryptedByte) throws NoSuchAlgorithmException, GeneralSecurityException, UnsupportedEncodingException {
        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv));

        byte[] plainByte = c.doFinal(encryptedByte);

        return plainByte;
    }
}
