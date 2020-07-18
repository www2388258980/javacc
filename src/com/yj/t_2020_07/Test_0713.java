package com.yj.t_2020_07;

import java.util.ArrayList;

public class Test_0713 {

    public static void main(String[] args) {
        System.out.println(solution(275));
        System.out.println(solution(-999));
        System.out.println(solution(-123));
        System.out.println(solution2(-123));
    }


    /**
     * @param n [-8000,8000]
     * @return
     */
    public static int solution(int n) {
        int m = Math.abs(n);
        ArrayList<Integer> list = new ArrayList<>();
        while (m != 0) {
            list.add(m % 10);
            m /= 10;
        }
        System.out.println("list: " + list.toString());
        int k = 0;
        if (n >= 0) {
            for (int j = list.size() - 1; j >= 0; j--) {
                if (list.get(j) < 5) {
                    k = (int) (5 * Math.pow(10, j + 1));
                    break;
                }
            }
        } else {
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j) > 5) {
                    k = -(int) (5 * Math.pow(10, list.size() - j));
                    break;
                }
            }
        }
        return n + k;
    }

    public static  int solution2(int test){

        //取绝对值
        int trans = Math.abs(test);
        ArrayList<Integer> arrayList = new ArrayList<>();
        while (trans > 0) {
            int a = trans % 10;
            arrayList.add(a);
            trans = trans / 10;
        }
        if (test == 0) return 50;
        int sum=0;
        if (test > 0) {
            System.out.println(arrayList);
            int index = -1;
            System.out.println(arrayList.size());
            for (int i = arrayList.size() - 1; i >= 0; i--) {
                if ((int) arrayList.get(i) < 5) {
                    index = i;
                    System.out.println(index);
                    break;
                }
            }
            System.out.println(index);
            arrayList.add(index + 1, 5);

            System.out.println(arrayList);
            for (int j = 0; j < arrayList.size(); j++) {
                sum += arrayList.get(j) * Math.pow(10, j);
            }
            System.out.println(sum);
        } else if(test<0){
            int index1=-1;

            System.out.println(arrayList);
            for (int i = arrayList.size() - 1; i >= 0; i--) {
                if ((int) arrayList.get(i) >=5) {
                    index1= i;
                    System.out.println(index1);
                    break;
                }
            }
            arrayList.add(index1 + 1, 5);
            for (int j = 0; j < arrayList.size(); j++) {
                sum += arrayList.get(j) * Math.pow(10, j);
            }

            sum*=(-1);
        }
        return  sum;

    }
}
