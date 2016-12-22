package com.yxnne.criminalintent.db;

/**
 * Created by yxnne on 2016/12/22.
 * 存放数据库的表明和字段
 */

public class CrimeDBSchema {
    /**
     * Table： Crime
     */
    public static final class CrimeTable{
        public static final String NAME = "crimes";
        /**Crime 表中的字段 */
        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String TITTLE = "tittle";
            public static final String DATE = "date";
            public static final String SOVLED = "sovled";
        }
    }
}
