package com.data.structure.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉链表
 *
 * @param <T>
 * @author 杨吉
 * @Date 2020-09-02
 */
public class BiTree<T> {
    // 根节点
    BiTNode<T> root;

    public BiTree() {
    }

    /**
     * 先序遍历建立二叉树
     * 如果某个节点的左孩子或右孩子为空,在先序序列集合中填充null
     * 给定一个字符二叉树(先序: ABCDEGF,中序: CBEGDFA)
     * 那么它的先序序列集合为['A', 'B', 'C', null, null, 'D', 'E', null, 'G', null, null, 'F', null, null, null]
     *
     * @param list
     */
    public BiTree(List<T> list) {
        // 复制list集合给list2集合,不改变外来传入的list集合
        List<T> list2 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            list2.add(list.get(i));
        }
        this.root = createBiTreeByFirst(list2);

    }

    // 二叉树的存储结构
    private static class BiTNode<E> {
        E data;
        BiTNode<E> lchild;
        BiTNode<E> rchild;

        public BiTNode() {
        }

        public BiTNode(E data) {
            this.data = data;
        }
    }


    /**
     * 先序建立二叉树
     * <p>
     * 如果list.get(0)中的元素为null,则递归返回
     * 否则执行以下操作:
     * **** 申请一个节点空间t,将list.get(0)赋值给t.data
     * **** 移除list.get(0),递归创建左子树
     * **** 移除list.get(0),递归创建右子树
     *
     * @param list
     * @return
     */
    private BiTNode<T> createBiTreeByFirst(List<T> list) {
        if (list.get(0) == null) {
            return null;
        }
        // 创建根节点
        BiTNode<T> t = new BiTNode<>(list.get(0));
        // 递归创建左子树
        list.remove(0);
        t.lchild = createBiTreeByFirst(list);
        // 递归创建右子树
        list.remove(0);
        t.rchild = createBiTreeByFirst(list);
        return t;
    }

    /**
     * 先序遍历二叉树
     *
     * @return
     */
    public String traverByFirst() {
        return traverByFirst(this.root);
    }

    private String traverByFirst(BiTNode<T> t) {
        if (t == null) {
            return "";
        } else {
            StringBuilder str = new StringBuilder(t.data.toString());
            // 函数等价关系式
            str.append(traverByFirst(t.lchild));
            str.append(traverByFirst(t.rchild));
            return str.toString();
        }
    }

    /**
     * 中序遍历二叉树
     *
     * @return
     */
    public String traverByMiddle() {
        return this.traverByMiddle(this.root);
    }

    private String traverByMiddle(BiTNode<T> t) {
        if (t == null) {
            return "";
        } else {
            StringBuilder str = new StringBuilder();
            // 函数等价关系式
            str.append(traverByMiddle(t.lchild));
            str.append(t.data.toString());
            str.append(traverByMiddle(t.rchild));
            return str.toString();
        }
    }

    /**
     * 后序遍历二叉树
     *
     * @return
     */
    public String traverByLast() {
        return this.traverByLast(this.root);
    }

    private String traverByLast(BiTNode<T> t) {
        if (t == null) {
            return "";
        } else {
            StringBuilder str = new StringBuilder();
            // 函数等价关系式
            str.append(traverByLast(t.lchild));
            str.append(traverByLast(t.rchild));
            str.append(t.data.toString());
            return str.toString();
        }
    }

    /**
     * 获取先序序列
     * 如果list没有初始化,该方法返回空
     *
     * @param list
     * @return
     */
    public List<T> getFirstSeq(List<T> list) {
        return this.getFirstSeq(this.root, list);
    }

    private List<T> getFirstSeq(BiTNode<T> t, List<T> list) {
        if (t == null || list == null) {
            return null;
        } else {
            list.add(t.data);
            getFirstSeq(t.lchild, list);
            getFirstSeq(t.rchild, list);
            return list;
        }
    }


    /**
     * 计算二叉树的深度
     *
     * @return
     */
    public int countDepth() {
        return this.countDepth(this.root);
    }

    private int countDepth(BiTNode<T> t) {
        if (t == null) {
            return 0;
        } else {
            /*
             * 递归计算左子树的深度和右子树的深度,取其中最大然后加上根节点1
             */
            return Math.max(countDepth(t.lchild), countDepth(t.rchild)) + 1;
        }
    }


    /**
     * 判断二叉树是否为空
     * 二叉树空,返回true,否则返回false
     *
     * @return
     */
    public boolean isEmpty() {
        return this.root == null;
    }

    /**
     * 复制二叉树
     *
     * @return
     */
    public BiTree<T> clone() {
        BiTree<T> biTree = new BiTree<>();
        biTree.root = this.clone(this.root);
        return biTree;
    }

    private BiTNode<T> clone(BiTNode<T> t) {
        if (t == null) {
            return null;
        } else {
            // 先序复制二叉树
            BiTNode<T> p = new BiTNode<>(t.data);
            // 复制左子树
            p.lchild = clone(t.lchild);
            // 复制右子树
            p.rchild = clone(t.rchild);
            return p;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BiTree) {
            BiTree<T> t = (BiTree<T>) obj;
            try {
                this.equals(this.root, t.root);
                return true;
            } catch (CustomException e) {
                // 抛出异常代表两个二叉树某个节点不相等;
                return false;
            }

        }
        return false;
    }

    // 自定义异常
    private static class CustomException extends Exception {

        public CustomException(String message) {
            super(message);
        }
    }

    private boolean equals(BiTNode<T> t1, BiTNode<T> t2) throws CustomException {
        if (t1 == null && t2 == null) {
            return true;
        } else if (t1 == null || t2 == null) {
            throw new CustomException("二叉树不相等!");
        } else {
            /*
             * 函数等价关系式
             * 如果t1和t2数据域不相等,则抛出自定义异常
             * 否则继续比较t1和t2的左子树和右子树
             */
            if (!t1.data.equals(t2.data)) {
                // 不需要退栈,可直接退出递归
                throw new CustomException("二叉树不相等!");
            }
            return equals(t1.lchild, t2.lchild) && equals(t1.rchild, t2.rchild);
        }
    }

    /**
     * 返回二叉树节点数
     *
     * @return
     */
    public int countNode() {
        return this.countNode(this.root);
    }

    private int countNode(BiTNode<T> t) {
        if (t == null) {
            return 0;
        } else {
            return countNode(t.lchild) + countNode(t.rchild) + 1;
        }
    }

    /**
     * 返回二叉树叶子节点数
     *
     * @return
     */
    public int countLeafNode() {
        return this.countLeafNode(this.root);
    }

    private int countLeafNode(BiTNode<T> t) {
        if (t == null) {
            return 0;
        } else if (t.lchild == null && t.rchild == null) {
            return 1;
        } else {
            return countLeafNode(t.lchild) + countLeafNode(t.rchild);
        }
    }

}
