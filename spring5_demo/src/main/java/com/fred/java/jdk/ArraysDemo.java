package com.fred.java.jdk;

import com.fred.spring.transaction.demo.DemoApplication;
import org.springframework.boot.SpringApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArraysDemo {

    private void demo() {
        // 拷贝出一个新的数组
        String[] src = { "1", "4", "3" };
        String[] dst = Arrays.copyOf(src, src.length);

        // 数组排序
        Arrays.sort(dst);
        // OUT: [1, 3, 4]
        System.out.print(Arrays.toString(dst));
    }

    public static void main(String[] args) {
        new ArraysDemo().demo();
    }
}
