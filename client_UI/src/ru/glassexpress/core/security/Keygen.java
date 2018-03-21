package ru.glassexpress.core.security;


import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Keygen {

    public static String generate() throws NoSuchAlgorithmException {

        SecureRandom random = SecureRandom.getInstanceStrong();
        byte[] values = new byte[32]; // 256 bit
        random.nextBytes(values);

        StringBuilder sb = new StringBuilder();
        for (byte b : values) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        try {
            System.out.println(Keygen.generate());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
