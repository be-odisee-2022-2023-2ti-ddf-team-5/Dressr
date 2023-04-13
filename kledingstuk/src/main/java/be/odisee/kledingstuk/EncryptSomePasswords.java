package be.odisee.kledingstuk;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class EncryptSomePasswords {

    public static String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static void main(String[] args) {

        String[] passwords = {"Manal2003", "Mikail2001", "odisee"};

        for (String pwd : passwords ) {
            System.out.printf("%s is {bcrypt}%s%n", pwd, hash(pwd));
        }
    }
}
