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

    }

}
