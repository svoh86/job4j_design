package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {

        String name = "Petr Arsentev";
        int age = 33;
        short sht = 100;
        long lng = 5000L;
        double dbl = 10.37D;
        byte bt = 10;
        char ch = 'c';
        boolean bln = false;
        float flt = 55.5F;
        LOG.debug(
                "User info name : {}, age : {}, sht : {}, lng : {}, dbl : {}, bt : {}, ch : {}, bln : {}, flt : {}",
                name, age, sht, lng, dbl, bt, ch, bln, flt);
        try {
            throw new Exception("Not supported code");
        } catch (Exception e) {
            LOG.error("Exception in log example", e);
        }
    }
}
