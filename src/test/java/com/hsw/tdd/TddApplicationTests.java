package com.hsw.tdd;

import com.hsw.tdd.utils.Base58T;
import com.hsw.tdd.utils.HashT;
import com.hsw.tdd.utils.HexUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.MessageDigest;
import java.util.Base64;

@Slf4j
//@SpringBootTest
class TddApplicationTests {

    @Test
    void contextLoads() throws Exception {
//        int padding = 32 - 10;
//        byte[] bytes=new byte[4];
//        bytes[0]=(byte)((padding&0xFF000000)>>24);
//        bytes[1]=(byte)((padding&0x00FF0000)>>16);
//        bytes[2]=(byte)((padding&0x0000FF00)>>8);
//        bytes[3]=(byte) (padding&0x000000FF);
//        for(int i=0; i<bytes.length; i++) {
//            System.out.println(bytes[i]);
//        }
        String value = "3511ab0f17a45ef74d00451b34fceb7395b1c9e9113dd17370dfb3bc2d7b42c7";
        HashT.sha256T(1, value);
        HashT.sha256T(2, value);


        MessageDigest sh = MessageDigest.getInstance("SHA-256");
        sh.update(HexUtil.hexStringToByte(value));
        byte byteData[] = sh.digest();
        log.info("sha : {}", HexUtil.byteArrayToHex(byteData));


//        String hashVal = "a914de3cd85e7dcd769095c8be45a8eb1570dbc3f75e87";
//        String hashVal = "5273d025971c20e5748831720e9f24e05c7a215a";
//        String hashVal = "14a935e6d492d75b80b8912e6ba690bc278bfd3a8c7987";
        String hashVal = "a914ca624595f549b309f0627e2c2e5a505577c2c21c87";
        HashT.hash160T(hashVal);

        String addr = "3L989LdNpNtKRrNqe5hC9PLYgyZBCyXkM6";
        byte[] deAddr = Base58T.decodeToByte(addr, "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz");
        log.info("addr de : {}", HexUtil.byteArrayToHex(deAddr));


        String base64Str = "123";
        byte[] base64Byte = Base64.getDecoder().decode(base64Str);
    }
/*
    @Test
    public void doubleSha256Test() throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] message = hexStringToByteArray("88130000000000001976a914acd38373fbada0f3fb51de0756836321dffb952688ac7e0900000000000017a914ca624595f549b309f0627e2c2e5a505577c2c21c8702");
        md.update(message);
        md.update(md.digest());

        System.out.println(byteArrayToHexString(md.digest()).toLowerCase());
    }

    @Test
    public void oneSha256Test() throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] message = hexStringToByteArray("0200000001bdd36fa2b5c9d65edfc6bd1f175d69219d7d376cf2c18a2193bb1788825f2b1a000000001ca91405ca624595f549b309f0627e2c2e5a505577c2c21cb3d5442987feffffff0288130000000000001976a914acd38373fbada0f3fb51de0756836321dffb952688ac7e0900000000000017a914ca624595f549b309f0627e2c2e5a505577c2c21c8700000000");
        md.update(message);
//        md.update(md.digest());

        System.out.println(byteArrayToHexString(md.digest()).toLowerCase());
    }


    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    public static String byteArrayToHexString(byte[] bytes){
        StringBuilder sb = new StringBuilder();
        for(byte b : bytes){
            sb.append(String.format("%02X", b&0xff));
        }
        return sb.toString();
    }
*/

}
