package encryption;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.KeyGenerator;
import java.util.Base64;

public class DES {

    // Fixed DES key
    private static final SecretKey SECRET_KEY = generateKey();

    // Method to generate a fixed DES key
    private static SecretKey generateKey() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("DES");
            keyGen.init(56); // DES key size is 56 bits
            return keyGen.generateKey();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    // Method to encrypt a plain text using DES with the fixed key
    public static String encrypt(String plainText) {
        try {
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, SECRET_KEY);
            byte[] encryptedBytes = cipher.doFinal(plainText.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    // Method to decrypt an encrypted text using DES with the fixed key
    public static String decrypt(String encryptedText) {
        try {
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, SECRET_KEY);
            byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);
            byte[] decryptedBytes = cipher.doFinal(decodedBytes);
            return new String(decryptedBytes, "UTF-8");
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
