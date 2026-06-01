package org.scoula.jdbc_ex.temp;

public class Calculator {
    public int add(int a, int b) {
        return a + b;
    }

    public int divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("0으로 나눌 수 없습니다."); // s 안붙으면 예외를 그냥 발생시키는 것
        }
        return a / b;
    }
}