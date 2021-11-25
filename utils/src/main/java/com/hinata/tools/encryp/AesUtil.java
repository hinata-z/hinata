package com.hinata.tools.encryp;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;
import java.util.Arrays;

@Slf4j
public class AesUtil {
    /**
     * 默认的加密算法
     */
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS7Padding";
    private static Cipher cipher;

    //    "DESede/ECB/NoPadding"

    static {
        try {
            Security.addProvider(new BouncyCastleProvider());
            //  "  DESede/ECB/PKCS5Padding
            cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);

        } catch (Exception e) {

        }
    }

    /**
     * AES 加密操作
     *
     * @param content  待加密内容
     * @param password 加密密码
     * @return 返回Base64转码后的加密数据
     */
    public static String encrypt(String content, String password) {
        try {
            SecretKeySpec skeySpec = getKey(password);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] clearText = content.getBytes("UTF8");
            String encrypedValue = Base64.encodeBase64String(cipher.doFinal(clearText));
            return encrypedValue;
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return null;
    }

    /**
     * AES 解密操作
     *
     * @param content
     * @param password
     * @return
     */
    public static String decrypt(String content, String password) throws Exception {
        SecretKey key = getKey(password);
        // IMPORTANT TO GET SAME RESULTS ON iOS and ANDROID
        final byte[] iv = new byte[16];
        Arrays.fill(iv, (byte) 0x00);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        byte[] encrypedPwdBytes = Base64.decodeBase64(content);
        //below code must be added in java end
        Security.addProvider(new BouncyCastleProvider());
        // cipher is not thread safe
        Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decrypedValueBytes = (cipher.doFinal(encrypedPwdBytes));

        String decrypedValue = new String(decrypedValueBytes, "UTF-8");
        return decrypedValue;

    }

    private static SecretKeySpec getKey(String password) throws Exception {
        // You can change it to 128 if you wish
        int keyLength = 256;
        byte[] keyBytes = new byte[keyLength / 8];
        // explicitly fill with zeros
        Arrays.fill(keyBytes, (byte) 0x0);

        // if password is shorter then key length, it will be zero-padded
        // to key length
        byte[] passwordBytes = password.getBytes("UTF-8");
        int length = passwordBytes.length < keyBytes.length ? passwordBytes.length : keyBytes.length;
        System.arraycopy(passwordBytes, 0, keyBytes, 0, length);
        SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
        return key;
    }

    public static void main(String[] args) {
        long time=System.currentTimeMillis();
        for(int i=0;i<1000000;i++){
            AesUtil.encrypt("123456","32232323121");
        }
        System.out.println(System.currentTimeMillis()-time);
    }

}
