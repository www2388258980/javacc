package com.data.structure.test;

import com.data.structure.line.Polynomial;

import java.util.Arrays;

public class PolynomialTest {
    public static void main(String[] args) {
        Polynomial p1 = new Polynomial();
        Polynomial p2 = new Polynomial();

        int[] num1 = new int[]{1, 5, 3, 7, 9, 13, 11};
        Arrays.sort(num1);
        System.out.println(Arrays.toString(num1));
    }
}
