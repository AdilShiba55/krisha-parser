package kz.loader.util;

import java.util.Date;

public class UtTime {

    public static final int FIVE_SECOND = 5 * 1000;
    public static final int TWENTY_SECOND = 20 * 1000;
    public static final int ONE_MINUTE = 60 * 1000;
    public static final int TWO_MINUTES = 2 * 60 * 1000;
    public static final int THREE_MINUTES = 3 * 60 * 1000;
    public static final int FIVE_MINUTES = 5 * 60 * 1000;
    public static final int TEN_MINUTES = 10 * 60 * 1000;
    public static final int FIFTEEN_MINUTES = 15 * 60 * 1000;
    public static final int ONE_HOUR = 60 * 60 * 1000;
    public static final int THREE_HOUR = 3 * 60 * 60 * 1000;
    public static final int FOUR_HOUR = 4 * 60 * 60 * 1000;
    public static final int TEN_HOUR = 10 * 60 * 60 * 1000;
    public static final int ONE_DAY = 24 * 60 * 60 * 1000;
    public static final int ONE_WEEK = 7 * 24 * 60 * 60 * 1000;

    public static Date getAsc(Date from, Integer offsetMillisecond) {
        return new Date(from.getTime() + offsetMillisecond);
    }

    public static Date getDesc(Date from, Integer offsetMillisecond) {
        return new Date(from.getTime() - offsetMillisecond);
    }

}
