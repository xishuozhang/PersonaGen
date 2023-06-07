package com.demo.utils;

import java.util.Random;

/**
 *  * @classDesc:
 *  * @Date 2023/3/27 19:32
 *  * @Author:zxs
 *
 * @copyright
 */
public class RandomEmail {



        public static void main(String[] args) {
            System.out.println(generateEmail());
        }

        public static String generateEmail() {
            String[] providers = {"gmail.com", "yahoo.com", "hotmail.com", "outlook.com"};
            String[] names = {"john", "jane", "alice", "bob", "david", "emma", "peter", "susan"};

            Random random = new Random();
            int nameIndex = random.nextInt(names.length);
            int providerIndex = random.nextInt(providers.length);
            String name = names[nameIndex];
            String provider = providers[providerIndex];
            int randomNumber = random.nextInt(10000);

            String email = name + randomNumber + "@" + provider;

            return email;
        }


}
