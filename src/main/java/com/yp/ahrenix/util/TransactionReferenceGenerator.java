package com.yp.ahrenix.util;

import java.util.UUID;

public class TransactionReferenceGenerator {

    public static String generateReference() {

        return "TXN-" +
                UUID.randomUUID()
                        .toString()
                        .replace("-", "")
                        .substring(0, 16)
                        .toUpperCase();
    }

}