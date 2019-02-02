package com.fred.java.enumtype;

import com.fred.java.jdk.ArraysDemo;

import java.util.Arrays;

public enum EnumDemo {

    /**
     * 枚举一定要注释，没办法:)
     */
    SMALL("S"), LARGE("L");

    private String abbreviation;

    private EnumDemo(String abbr) { this.abbreviation=abbr; }
    public String getAbbreviation() { return this.abbreviation; }

    private static void demo() {
        // OUT: SMALL
        System.out.println(EnumDemo.SMALL.toString());
        // 通过枚举的字符串形式来返回枚举值
        EnumDemo s = Enum.valueOf(EnumDemo.class, "SMALL");
        // OUT: SMALL
        System.out.println(s);
        // OUT: SMALL
        System.out.println(s.toString());
        // OUT: [SMALL, LARGE]
        System.out.println(Arrays.toString(EnumDemo.values()));
    }

    public static void main(String[] args) {
        EnumDemo.demo();
    }
}
