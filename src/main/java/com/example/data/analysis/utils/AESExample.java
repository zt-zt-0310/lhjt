package com.example.data.analysis.utils;


import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Random;


/**
 * @Author zt
 * @Description AES 加密解密
 * @Time 2025/1/21 19:21
 */


public class AESExample {

    //加密模式之 ECB，AES/ECB/PKCS5Padding-----算法/模式/补码方式
    private static final String AES_ECB = "AES/ECB/PKCS5Padding";

    //加密模式之 CBC，算法/模式/补码方式
    private static final String AES_CBC = "AES/CBC/PKCS5Padding";

    //加密模式之 CFB，算法/模式/补码方式
    private static final String AES_CFB = "AES/CFB/PKCS5Padding";

    //AES 中的 IV 必须是 16 字节（128位）长
    private static final Integer IV_LENGTH = 16;

    /***
     * 空值校验
     * @param str 需要判断的值
     */
    public static boolean isEmpty(Object str) {
        return null == str || "".equals(str);
    }

    /***
     * 字符串转byte字节
     * @param str 需要转换的字符串
     */
    public static byte[] getBytes(String str){
        if (isEmpty(str)) {
            return null;
        }

        try {
            return str.getBytes(StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /***
     * 初始化向量（IV），它是一个随机生成的字节数组，用于增加加密和解密的安全性
     */
    public static String getIV(){
        String str = "e165666005912d7c";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for(int i = 0 ; i < IV_LENGTH ; i++){
            int number = random.nextInt(str.length());
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    /***
     * 获取一个 AES 密钥规范
     */
    public static SecretKeySpec getSecretKeySpec(String key){
        SecretKeySpec secretKeySpec = new SecretKeySpec(getBytes(key), "AES");
        return secretKeySpec;
    }

    /**
     * 加密模式 --- ECB
     * @param text 需要加密的文本内容
     * @param key 加密的密钥 key
     * */
    public static String encrypt(String text, String key){
        if (isEmpty(text) || isEmpty(key)) {
            return null;
        }

        try {
            // 创建AES加密器
            Cipher cipher = Cipher.getInstance(AES_ECB);
            // 将密钥转换为SecretKeySpec格式
            SecretKeySpec secretKeySpec = getSecretKeySpec(key);
            // 初始化Cipher为加密模式，传入密钥
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            // 加密字节数组，将明文转换为字节数组进行加密
            byte[] encryptedBytes = cipher.doFinal(getBytes(text));
            // 将密文转换为 Base64 编码字符串
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 解密模式 --- ECB
     * @param text 需要解密的文本内容
     * @param key 解密的密钥 key
     * */
    public static String decrypt(String text, String key){
        if (isEmpty(text) || isEmpty(key)) {
            return null;
        }

        // 将密文转换为16字节的字节数组
        byte[] textBytes = Base64.getDecoder().decode(text);

        try {
            // 创建AES加密器
            Cipher cipher = Cipher.getInstance(AES_ECB);
            // 将密钥转换为SecretKeySpec格式
            SecretKeySpec secretKeySpec = getSecretKeySpec(key);
            // 初始化Cipher为加密模式，传入密钥
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

            // 解密字节数组,将明文转换为字节数组进行解密
            byte[] decryptedBytes = cipher.doFinal(textBytes);

            // 将明文转换为字符串
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 加密 - 自定义加密模式
     * @param text 需要加密的文本内容
     * @param key 加密的密钥 key
     * @param iv 初始化向量
     * @param mode 加密模式
     * */
    public static String encrypt(String text, String key, String iv, String mode){
        if (isEmpty(text) || isEmpty(key) || isEmpty(iv)) {
            return null;
        }

        try {
            // 创建AES加密器
            Cipher cipher = Cipher.getInstance(mode);
            // 将密钥转换为SecretKeySpec格式
            SecretKeySpec secretKeySpec = getSecretKeySpec(key);
            // 初始化Cipher为加密模式，传入密钥和初始化向量
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, new IvParameterSpec(getBytes(iv)));

            // 加密字节数组，将明文转换为字节数组进行加密
            byte[] encryptedBytes = cipher.doFinal(getBytes(text));

            // 将密文转换为 Base64 编码字符串
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 解密 - 自定义加密模式
     * @param text 需要解密的文本内容
     * @param key 解密的密钥 key
     * @param iv 初始化向量
     * @param mode 加密模式
     * */
    public static String decrypt(String text, String key, String iv, String mode){
        if (isEmpty(text) || isEmpty(key) || isEmpty(iv)) {
            return null;
        }

        // 将密文转换为16字节的字节数组
        byte[] textBytes = Base64.getDecoder().decode(text);

        try {
            // 创建AES加密器
            Cipher cipher = Cipher.getInstance(mode);
            // 将密钥转换为SecretKeySpec格式
            SecretKeySpec secretKeySpec = getSecretKeySpec(key);
            // 初始化Cipher为加密模式，传入密钥和初始化向量
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, new IvParameterSpec(getBytes(iv)));

            // 解密字节数组，将明文转换为字节数组进行解密
            byte[] decryptedBytes = cipher.doFinal(textBytes);

            // 将明文转换为字符串
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        String text = "123456";
        String key  =  "b79784a829fec55d";
        String iv  = getIV();
        //ECB
        String encryptTextECB = encrypt(text, key);
        System.out.println("ECB 加密后内容：" + encryptTextECB);
        System.out.println("ECB 解密后内容：" + decrypt("3QNC3YqoyNzYwCF7RHNaJw==", key));
        System.out.println();

        //CBC
        String encryptTextCBC = encrypt(text, key, iv, AES_CBC);
        System.out.println("CBC 加密IV：" + iv);
        System.out.println("CBC 加密后内容：" + encryptTextCBC);
        System.out.println("CBC 解密后内容：" + decrypt(encryptTextCBC, key, iv, AES_CBC));
        System.out.println();

        //CFB
        String encryptTextCFB = encrypt(text, key, iv, AES_CFB);
        System.out.println("CFB 加密IV：" + iv);
        System.out.println("CFB 加密后内容：" + encryptTextCFB);
        System.out.println("CFB 解密后内容：" + decrypt(encryptTextCFB, key, iv, AES_CFB));

    }
//
//    public static void main(String[] args) throws Exception {
//        // 生成 AES-256 密钥
//        SecretKey secretKey = generateAES256Key();
//        // 将密钥转换为 Base64 编码的字符串，方便存储和传输
//        String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
//        System.out.println("AES-256 密钥: " + encodedKey);
//
//        String originalText = "Hello, World!";
//        System.out.println("原始文本: " + originalText);
//
//        // 加密
//        byte[] encryptedBytes = encrypt(originalText.getBytes(), secretKey);
//        String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);
//        System.out.println("加密后的文本: " + encryptedText);
//
//        // 解密
//        byte[] decryptedBytes = decrypt(Base64.getDecoder().decode(encryptedText), secretKey);
//        String decryptedText = new String(decryptedBytes);
//        System.out.println("解密后的文本: " + decryptedText);
//    }
//
//    // 生成 AES-256 密钥
//    public static SecretKey generateAES256Key() throws Exception {
//        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
//        keyGenerator.init(256);
//        return keyGenerator.generateKey();
//    }
//
//    // 加密方法
//    public static byte[] encrypt(byte[] data, SecretKey secretKey) throws Exception {
//        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//        // 生成初始化向量 IV
//        byte[] iv = new byte[16];
//        IvParameterSpec ivspec = new IvParameterSpec(iv);
//        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
//        return cipher.doFinal(data);
//    }
//
//    // 解密方法
//    public static byte[] decrypt(byte[] encryptedData, SecretKey secretKey) throws Exception {
//        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//        // 使用相同的初始化向量 IV
//        byte[] iv = new byte[16];
//        IvParameterSpec ivspec = new IvParameterSpec(iv);
//        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
//        return cipher.doFinal(encryptedData);
//    }
//}
}

