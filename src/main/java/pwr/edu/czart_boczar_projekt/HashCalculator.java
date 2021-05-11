package pwr.edu.czart_boczar_projekt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Class responsible for password hashing
 */
public class HashCalculator {

    /**
     * Method that compares the hash password and the entered password
     * @param password password not encoded
     * @param hash encrypted password
     * @return
     */
    public static boolean validatePassword(String password, String hash){
        try {
            String passwordHash = hashSHA256(password);
            return passwordHash.equals(hash);
        } catch (NoSuchAlgorithmException e) {
            return false;
        }
    }

    /**
     * method responsible for encoding the password
     * @param password password not encoded
     * @return the encoded password with the method hashSHA256
     * @throws NoSuchAlgorithmException
     */
    public static String hashSHA256 (String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());
        byte[] hash = md.digest();
        return new String(hash);
    }
}
