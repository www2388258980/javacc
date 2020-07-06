package com.data.structure.line;

import java.util.Arrays;

/**
 * 一元多项式
 */
public class Polynomial {

    // 系数
    private int[] coefficient;
    // 指数
    private int[] index;
    // 长度
    private int length;
    // 大小
    private int size;

    public static int defaultSize = 16;

    public Polynomial() {
        /*
         * 默认大小为16
         */
        this(defaultSize);
    }

    public Polynomial(int size) {
        coefficient = new int[size];
        index = new int[size];
        length = 0;
        this.size = size;
    }

    public Polynomial(int[] coe, int ind[]) {
        Arrays.sort(coe);
        Arrays.sort(ind);
        coefficient = coe;
        index = ind;
        length = coe.length;
        this.size = 16;
    }

    public void add(int coe, int ind) {
        if (length == size) {
            // 需要扩容
            reSize();
        } else {
            coefficient[length] = coe;
            index[length] = ind;
            length++;
        }
    }

    public Polynomial union(Polynomial p) {
        int i = 0;
        int j = 0;
        while (i < this.length && j < p.length) {
            if (this.index[i] == p.index[j]) {
                this.coefficient[i] += p.coefficient[j];
                i++;
                j++;
            } else if (this.index[i] < p.index[j]) {
                i++;
            } else {
                j++;
            }
        }
        // 如果多项式p指数比较高;
        for (; j < this.length; j++) {
            this.add(p.coefficient[j], p.index[j]);
        }
        return this;
    }

    public int getLength() {
        return length;
    }

    public void reSize() {
        int[] coefficientNew = new int[size * 2];
        int[] indexNew = new int[size * 2];
        for (int i = 0; i < this.length; i++) {
            coefficientNew[i] = this.coefficient[i];
            indexNew[i] = this.index[i];
        }
        this.coefficient = coefficientNew;
        this.index = indexNew;
        this.size *= 2;
    }
}
