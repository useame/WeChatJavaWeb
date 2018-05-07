package com.wxtrytry.wechat.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class SignUtil {
    private static String token = "SpArKaRoNsHaRkArOn";//token that wechat requires

    /**
     * check the signature if the information is from Tencent server
     * @param signature the encoded information Tencent offers
     * @param timestamp the encoded information of the request time
     * @param nonce a random number
     * @return if the signature is effective
     * @throws NoSuchAlgorithmException when a particular cryptographic algorithm is requested but is not available in the environment.
     */
    public static boolean checkSignature(String signature, String timestamp,String nonce){
        // sort the params in Lexicographical oder
        String[] arr = new String[] { token, timestamp , nonce };
        Arrays.sort(arr);

        // append three strings, append is more efficient than +
        StringBuilder content = new StringBuilder();
        for(int i=0;i < arr.length;i++){
            content.append(arr[i]);
        }
        MessageDigest md = null;// provides message digest algorithms including SHA-1 we need
        String tmpStr = null;
        try {
            md = MessageDigest.getInstance("SHA-1"); // digest the String with SHA-1 method
            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = ToHexString(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false; // compare with signature we received
    }

    /**
     * to parse array from bytes to hex string
     * @param digest an array we got before
     * @return the String we want in hex type
     * @throws
     */
    private static String ToHexString(byte[] digest) {
        StringBuilder strDigest = new StringBuilder();
        for(int i = 0; i < digest.length; i++) {
            strDigest.append(ToHex(digest[i]));
        }
        return strDigest.toString();
    }

    /**
     * to parse byte to string
     * @param b from our byte array digest
     * @return String form of hex number
     */
    private static String ToHex(byte b) {
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        char[] temp = new char[2];
        temp[0] = Digit[(b >>> 4) & 0X0F];   // unsined Arithmetic shift right to get lower bits then and 00001111 and transfer to hex
        temp[1] = Digit[b & 0X0F];
        String s = new String(temp);
        return s;
    }
}
