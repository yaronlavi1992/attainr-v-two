package attainrvtwo

import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class PasswordHasher {

    private static final String encryptionKey           = "ABCDEFGHIJKLMNOP";
    private static final String characterEncoding       = "UTF-8";
    private static final String cipherTransformation    = "AES/CBC/PKCS5PADDING";
    private static final String aesEncryptionAlgorithem = "AES";


    def static encodePass(String pass) {
        return pass + '3'
    }

    public static String encrypt(String plainText) {
        String encryptedText = ""
        try {
            Cipher cipher   = Cipher.getInstance(cipherTransformation)
            byte[] key      = encryptionKey.getBytes(characterEncoding)
            SecretKeySpec secretKey = new SecretKeySpec(key, aesEncryptionAlgorithem)
            IvParameterSpec ivparameterspec = new IvParameterSpec(key)
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivparameterspec)
            byte[] cipherText = cipher.doFinal(plainText.getBytes("UTF8"))
            Base64.Encoder encoder = Base64.getEncoder()
            encryptedText = encoder.encodeToString(cipherText)

        } catch (Exception E) {
            System.err.println("Encrypt Exception : "+E.getMessage());
        }
        return encryptedText;
    }

}
