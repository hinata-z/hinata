package com.hinata.netty;

import java.math.BigDecimal;

public class Test {

    public static void main(String[] args) {
        BigDecimal result=new BigDecimal(153.12);
        BigDecimal result2=new BigDecimal(153.11);

        BigDecimal total=result.divide(new BigDecimal(0.3967),6,BigDecimal.ROUND_CEILING);
        System.out.println(total);





    }
}
