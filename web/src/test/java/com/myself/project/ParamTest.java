package com.myself.project;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2020/9/3.
 */
public class ParamTest {

    public static void main(String[] args) {

        BigDecimal decimal = new BigDecimal("0.008");
        BigDecimal decimal1 = new BigDecimal("0.009");
        System.out.println("decimal1 = " + decimal1);
        BigDecimal multiply = decimal.multiply(decimal1);
        System.out.println("multiply = " + multiply);

        System.out.println("multiply = " + (82 | 9));

        float a=1.2f;
        int b=Float.floatToIntBits(a);
        System.out.println(Integer.toBinaryString(b));

        int x = Integer.MAX_VALUE;
        System.out.println("x = " + x);
        System.out.println("x = " + Integer.toBinaryString(x));

        int y = Integer.MIN_VALUE;
        System.out.println("y = " + y);
        System.out.println("y = " + Integer.toBinaryString(y));
    }
}
