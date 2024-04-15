package coding.tree;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
   *  https://www.geeksforgeeks.org/tree-traversals-inorder-preorder-and-postorder/
   */

public class BinaryTreeTest {
    // Root of Binary Tree
    Node root;

    public BinaryTreeTest() { root = null; }

    static public class Node {
        int key;
        Node left, right;

        public Node(int item)
        {
            key = item;
            left = right = null;
        }
    }

    void add(int value) {
        if(root == null) root = new Node(value);
        else add(root, value);
    }

    private void add(Node node, int value) {
        if(node.key == value) {
            System.out.println(" key already added :: " + value);
            return;
        }
        if(value < node.key) {
            if(node.left == null) {
                node.left = new Node(value);
            } else {
                add(node.left, value);
            }
        }
        if(value > node.key) {
            if(node.right == null) {
                node.right = new Node(value);
            } else {
                add(node.right, value);
            }
        }
    }

    // inorder implementation
    static public void print(Node node, List<Integer> list) {
        if (node == null) return;

        // recur on left child
        print(node.left, list);

        list.add(node.key);

        // recur on right child
        print(node.right, list);
    }

    void scan(Node node, List<Integer> list) {
        if (node == null) return;

        list.add(node.key);

        scan(node.left, list);
        scan(node.right, list);
    }

    //
    // balance simple
    //
    BinaryTreeTest balance(Node node) {
        var list = new ArrayList<Integer>();
        print(node, list);

        var output = new BinaryTreeTest();
        balance(output, list);

        return output;
    }

    void balance(BinaryTreeTest n, List<Integer> l) {
        if(l.isEmpty()) return;

        int mid = (l.size()) / 2;
        int value = l.get(mid);
        n.add(value);
        System.out.println("adding :: " + value);

        balance(n, l.stream().skip(mid + 1).toList());
        balance(n, l.stream().limit(mid).toList());
    }

    //
    // balance correct
    //
    Node build(Node root)
    {
        // Store nodes of given BST in sorted order
        List<Node> nodes = new ArrayList<>();
        storeNodes(root, nodes);

        int[] i = new int[10];
        // Constructs BST from nodes[]
        int n = nodes.size();
        return buildTree(nodes, 0, n - 1);
    }

    void storeNodes(Node root, List<Node> nodes)
    {
        if (root == null)   return;

        storeNodes(root.left, nodes);
        nodes.add(root);
        storeNodes(root.right, nodes);
    }

    Node buildTree(List<Node> nodes, int start, int end)
    {
        if (start > end)  return null;

        int mid = (start + end) / 2;
        Node node = nodes.get(mid);

        /* Using index in Inorder traversal, construct left and right sub-tress */
        node.left = buildTree(nodes, start, mid - 1);
        node.right = buildTree(nodes, mid + 1, end);

        return node;
    }


    @Test
    void run()
    {
        var list = new ArrayList<Integer>();

        BinaryTreeTest tree = new BinaryTreeTest();
        tree.add(1);
        tree.add(2);
        tree.add(22);
        tree.add(21);
        tree.add(23);
        tree.add(3);
        tree.add(4);
        tree.add(5);
        tree.add(55);
        tree.add(6);
        tree.add(7);

        print(tree.root, list);
        System.out.println(list.stream().map(x -> "" + x).collect(Collectors.joining(", ")));

//        var balanced = tree.balance(tree.root);
//        list.clear();
//        balanced.print(balanced.root, list);
//        System.out.println(list.stream().map(x -> "" + x).collect(Collectors.joining(", ")));

        var balanced = tree.build(tree.root);
        list.clear();
        print(balanced, list);
        System.out.println(list.stream().map(x -> "" + x).collect(Collectors.joining(", ")));
    }
}
