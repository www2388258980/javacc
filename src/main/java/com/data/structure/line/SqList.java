package com.data.structure.line;

/**
 * 定义顺序表的操作集合
 *
 * @param <T>
 */
public interface SqList<T> {
    /**
     * 向顺序表添加一个元素
     *
     * @param arg
     */
    public void add(T arg);

    /**
     * 根据下标获取元素
     *
     * @param i
     * @return
     */
    T get(int i);

    /**
     * 根据下标移除元素并返回
     *
     * @param i
     * @return
     */
    T remove(int i);

    /**
     * 移除跟传入相等的参数并返回;
     *
     * @param arg
     * @return
     */
    T remove(T arg);

}
