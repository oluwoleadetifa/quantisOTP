package com.idquantique.quantis.tools;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashTool {
    public static String hashString(String input) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = messageDigest.digest(input.getBytes());

            StringBuilder hashStringBuilder = new StringBuilder();
            for (byte hashByte : hashBytes) {
                // Convert each byte to a hexadecimal representation
                hashStringBuilder.append(String.format("%02x", hashByte));
            }
            return hashStringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        String input = "mySecretPassword";
        String hashedPassword = hashString(input);
        System.out.println("Hashed password: " + hashedPassword);
    }
}
