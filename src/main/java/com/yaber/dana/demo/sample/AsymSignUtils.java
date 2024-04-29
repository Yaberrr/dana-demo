package com.yaber.dana.demo.sample;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * 非对称加密
 * @author tangyabo
 * @date 2024/4/23
 */
public class AsymSignUtils {
    private static final String KEY_ALGO_NAME = "RSA";

    private static final char[] DIGITS_LOWER = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};



    public static void main(String[] args) throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048); // You can adjust the key size
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        // Get public and private keys
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // REPLACE with your actual keys
        // private key = key generated on merchant side and used to generate signature
        String privateKeyStr = new String(Base64.getEncoder().encode(privateKey.getEncoded()));
        System.out.println("Using private key: " + privateKeyStr);

        // public key = key given to DANA and will be used to verify generated signature
        String publicKeyStr = new String(Base64.getEncoder().encode(publicKey.getEncoded()));
        System.out.println("Using public key: " + publicKeyStr);

        // REPLACE with actual value that you need to compose string to sign
        String payload = "{\"name\":\"John\",\"age\":30,\"city\":\"New York\"}";
        String httpMethod = "POST";
        String url = "https://api.example.com/resource";
        String timestamp = "2024-02-27T12:00:00Z";

        String signature = generateAsymmetricSignature(payload, httpMethod, url, timestamp, privateKeyStr);

        System.out.println("Signature generated: " + signature);

        verifyAsymmetricSignature(signature, payload, httpMethod, url, timestamp, publicKeyStr);
    }

    /**
     * generate asymmetric signature
     *
     * @param payload
     * @param httpMethod
     * @param url
     * @param timestamp
     * @param privateKey
     * @return
     */
    public static String generateAsymmetricSignature(String payload, String httpMethod, String url,
                                                     String timestamp, String privateKey) {
        payload = payload == null ? "" : payload;
        try {
            String stringToSign = composeAsymStringtoSign(payload, url, httpMethod, timestamp);
            //sign with privateKey
            return signByPrivateKey(stringToSign, privateKey, "SHA256withRSA");
        } catch (NoSuchAlgorithmException | SignatureException | InvalidKeyException
                 | InvalidKeySpecException e) {
            throw new RuntimeException("Failed to generate signature, payload: " + payload, e);
        }
    }

    /**
     * verifyAsymmetricSignature
     *
     * @param signature
     * @param payload
     * @param httpMethod
     * @param url
     * @param timestamp
     * @param publicKey
     */
    public static void verifyAsymmetricSignature(String signature, String payload, String httpMethod,
                                                 String url, String timestamp, String publicKey) {
        try {
            String stringToSign = composeAsymStringtoSign(payload, url, httpMethod, timestamp);
            System.out.println("String to sign = " + stringToSign);

            //verify signature
            boolean isVerified = verify(signature, publicKey, stringToSign);
            if (!isVerified) {
                throw new RuntimeException("Failed to verify signature: " + signature);
            }

            System.out.println("Successfully verify signature: " + signature);
        } catch (NoSuchAlgorithmException | SignatureException | InvalidKeyException
                 | InvalidKeySpecException e) {

            throw new RuntimeException("Failed to generate asymmetric signature signature: " + payload, e);
        }
    }


    /**
     * compose asymmetric signature's string to sign
     * @param payload
     * @param url
     * @param httpMethod
     * @param timestamp
     * @return
     * @throws NoSuchAlgorithmException
     */
    private static String composeAsymStringtoSign(String payload, String url, String httpMethod,
                                                  String timestamp) throws NoSuchAlgorithmException {
        StringBuilder sb = new StringBuilder();
        sb
                .append(httpMethod)
                .append(":")
                .append(url)
                .append(":")
                .append(digestHexSHA256(minifyJsonString(payload)).toLowerCase()).append(":")
                .append(timestamp);
        return sb.toString();
    }


    /**
     * calculates the SHA-256 hash of a string and returns its hexadecimal representation in lowercase.
     * @param str
     * @return
     * @throws NoSuchAlgorithmException
     */
    private static String digestHexSHA256(String str) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(str.getBytes(StandardCharsets.UTF_8));
        byte[] digest = md.digest();
        return new String(encodeHex(digest, DIGITS_LOWER));
    }

    /**
     * encodes a byte array into a char array containing the hexadecimal representation of each byte.
     * @param data
     * @param toDigits
     * @return
     */
    private static char[] encodeHex(byte[] data, char[] toDigits) {
        int l = data.length;
        char[] out = new char[l << 1];
        int i = 0;

        for (int j = 0; i < l; ++i) {
            out[j++] = toDigits[(240 & data[i]) >>> 4];
            out[j++] = toDigits[15 & data[i]];
        }

        return out;
    }

    /**
     * minify json string
     * @param json
     * @return
     */
    private static String minifyJsonString(String json) {
        try {
            // Remove whitespaces and newlines
            StringBuilder result = new StringBuilder();
            boolean inString = false;
            for (int i = 0; i < json.length(); i++) {
                char c = json.charAt(i);
                if (c == '"') {
                    inString = !inString;
                }
                if (!Character.isWhitespace(c) || inString) {
                    result.append(c);
                }
            }
            return result.toString();
        } catch (Exception e) {
            throw new RuntimeException("Parse JSON failed. Value: " + json, e);
        }
    }


    /**
     * Sign content by given private key
     *
     * @param signContent signContent
     * @param privateKey  private key
     * @param algorithm   algorithm to sign
     * @throws InvalidKeyException      InvalidKeyException
     * @throws NoSuchAlgorithmException NoSuchAlgorithmException
     * @throws SignatureException       SignatureException
     * @throws InvalidKeySpecException  InvalidKeySpecException
     */
    private static String signByPrivateKey(String signContent, String privateKey,
                                          String algorithm) throws InvalidKeyException,
            NoSuchAlgorithmException, SignatureException,
            InvalidKeySpecException {
        if (signContent == null) {
            signContent = "";
        }
        byte[] signContentByte = signContent.getBytes();
        byte[] signResult = sign(signContentByte, Base64.getDecoder().decode((privateKey.getBytes())),
                algorithm);
        String signStr = new String(Base64.getEncoder().encode(signResult));
        return signStr;
    }

    /**
     * @param data      data
     * @param key       key
     * @param algorithm algorithm
     * @return signature
     * @throws NoSuchAlgorithmException NoSuchAlgorithmException
     * @throws InvalidKeyException      InvalidKeyException
     * @throws SignatureException       SignatureException
     * @throws InvalidKeySpecException  InvalidKeySpecException
     */
    private static byte[] sign(byte[] data, byte[] key, String algorithm)
            throws NoSuchAlgorithmException,
            InvalidKeyException,
            SignatureException,
            InvalidKeySpecException {
        PrivateKey privateKey = transformPrivateKey(key);
        Signature signature = Signature.getInstance(algorithm);
        signature.initSign(privateKey);
        signature.update(data);
        return signature.sign();
    }

    /**
     * Transform private key
     *
     * @param data
     * @return
     * @throws NoSuchAlgorithmException NoSuchAlgorithmException
     * @throws InvalidKeySpecException  InvalidKeySpecException
     */
    private static PrivateKey transformPrivateKey(byte[] data) throws NoSuchAlgorithmException,
            InvalidKeySpecException {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(data);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGO_NAME);
        return keyFactory.generatePrivate(pkcs8EncodedKeySpec);
    }

    /**
     * @param publicKey publicKey
     * @return true or false
     * @throws InvalidKeyException      InvalidKeyException
     * @throws NoSuchAlgorithmException NoSuchAlgorithmException
     * @throws InvalidKeySpecException  InvalidKeySpecException
     * @throws SignatureException       SignatureException
     */
    private static boolean verify(String signature, String publicKey, String signContent)
            throws InvalidKeyException,
            NoSuchAlgorithmException,
            InvalidKeySpecException,
            SignatureException {
        if (signature != null) {
            byte[] signContentByte = signContent.getBytes();
            return verify(signContentByte, Base64.getDecoder().decode(signature.getBytes()),
                    Base64.getDecoder().decode(publicKey.getBytes()), "SHA256withRSA");
        }
        return false;
    }

    /**
     * Verify the signature
     *
     * @param unSignedData
     * @param signedData
     * @param key
     * @param algorithm
     * @return
     * @throws NoSuchAlgorithmException NoSuchAlgorithmException
     * @throws InvalidKeySpecException  InvalidKeySpecException
     * @throws InvalidKeyException      InvalidKeyException
     * @throws SignatureException       SignatureException
     */
    private static boolean verify(byte[] unSignedData, byte[] signedData, byte[] key,
                                 String algorithm) throws NoSuchAlgorithmException,
            InvalidKeySpecException, InvalidKeyException,
            SignatureException {
        PublicKey publicKey = transformPublicKey(key);
        Signature signature = Signature.getInstance(algorithm);
        signature.initVerify(publicKey);
        signature.update(unSignedData);
        return signature.verify(signedData);
    }

    /**
     * Transform public key
     *
     * @param data
     * @return
     * @throws NoSuchAlgorithmException NoSuchAlgorithmException
     * @throws InvalidKeySpecException  InvalidKeySpecException
     */
    private static PublicKey transformPublicKey(byte[] data) throws NoSuchAlgorithmException,
            InvalidKeySpecException {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(data);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGO_NAME);
        return keyFactory.generatePublic(keySpec);
    }


}
