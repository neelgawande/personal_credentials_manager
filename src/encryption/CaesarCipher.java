package encryption;

public class CaesarCipher {

    private static final int SHIFT = 3; // Fixed shift value for encryption/decryption

    // Encrypts the given text using the Caesar cipher with a fixed shift
    public static String encrypt(String text) {
        return shiftText(text, SHIFT);
    }

    // Decrypts the given text using the Caesar cipher with the same fixed shift
    public static String decrypt(String encryptedText) {
        return shiftText(encryptedText, -SHIFT); // Decrypting is just shifting in the opposite direction
    }

    // Helper method to shift the characters in the text by the specified amount
    private static String shiftText(String text, int shift) {
        StringBuilder shiftedText = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            if (Character.isLetter(currentChar)) {
                char shiftedChar = (char) ('A' + (currentChar - 'A' + shift + 26) % 26);
                shiftedText.append(shiftedChar);
            } else {
                shiftedText.append(currentChar);
            }
        }
        return shiftedText.toString();
    }
}
