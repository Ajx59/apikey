package fr.mb.security;

import org.bouncycastle.util.encoders.Hex;
import java.security.SecureRandom;

public class ApiKey {
    public static String generateKey(){
        SecureRandom random = new SecureRandom();
        byte[] apiKeyBytes = new byte[32];
        random.nextBytes(apiKeyBytes);
        return Hex.toHexString(apiKeyBytes);
    }
}
