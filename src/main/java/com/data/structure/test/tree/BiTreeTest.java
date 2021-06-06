package com.data.structure.test.tree;

import com.data.structure.tree.BiTree;

import java.util.ArrayList;
import java.util.List;

public class BiTreeTest {
    public static void main(String[] args) {
        Character[] arr = new Character[]{'A', 'B', 'C', null, null, 'D', 'E', null, 'G', null, null, 'F', null, null, null};
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
        }
        BiTree<Character> tree = new BiTree<>(list);
        System.out.println(tree.traverByFirst());
        System.out.println(tree.traverByMiddle());
        System.out.println(tree.traverByLast());
        List<Character> list2 = new ArrayList<>();
        tree.getFirstSeq(list2);
        System.out.println(list2.toString());
        // ==================================================
        System.out.println("二叉树的深度: " + tree.countDepth());
        BiTree<Character> tree2 = tree.clone();
        System.out.println(tree2.traverByFirst());
        System.out.println("tree == tree? " + (tree == tree));
        System.out.println("tree == tree2? " + (tree == tree2));
        System.out.println("tree.equals(tree2): " + (tree.equals(tree2)));
        // ===============================================================
        Character[] arr3 = new Character[]{'A', 'B', 'C', null, null, 'D', 'E', null, 'G', null, null, 'F', null, null, 'H', null, null};
        List<Character> list3 = new ArrayList<>();
        for (int i = 0; i < arr3.length; i++) {
            list3.add(arr3[i]);
        }
        BiTree<Character> tree3 = new BiTree<>(list3);
        System.out.println(tree3.traverByFirst());
        System.out.println("tree.equals(tree3): " + (tree.equals(tree3)));
        // ===================================================================
        System.out.println("tree 的节点数: " + tree.countNode());
        System.out.println("tree3的节点数: " + tree3.countNode());
        System.out.println("tree 的叶子节点节点数: " + tree.countLeafNode());
        System.out.println("tree3的叶子节点节点数: " + tree3.countLeafNode());
    }
}
