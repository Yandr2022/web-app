package org.example.util;

import java.util.Random;


public class PswGenerator {
    public static String getPsw(String base) {
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            if (i < 5) {
                if (i % 2 == 0) {
                    builder.append((char) (base.charAt(i) ^ random.nextInt(8)));
                } else {
                    builder.append((char) ((int) base.charAt(i) + random.nextInt(2) & 65503));
                }
            } else if (i < 7) {
                builder.append(random.nextInt(15));
            } else {
                builder.append((char) ('!' + random.nextInt(10)));
            }
        }

        return builder + "";
    }

    public static void main(String[] args) {
        System.out.println(getPsw(""));
    }
}
