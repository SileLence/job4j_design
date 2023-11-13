package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());
    
    public static void main(String[] args) {
        String str = "Word";
        char aChar = '$';
        boolean aBoolean = true;
        byte aByte = 125;
        short aShort = 32000;
        int anInt = 2147000000;
        long aLong = 512730000000L;
        float aFloat = 12.75F;
        double aDouble = 36.14;
        
        LOG.debug("Logging with parameters.");
        LOG.debug("String: {}, char: {}, boolean: {}", str, aChar, aBoolean);
        LOG.debug("byte: {}, short: {}, int: {}, long: {}", aByte, aShort, anInt, aLong);
        LOG.debug("float: {}, double: {}", aFloat, aDouble);
    }
}
