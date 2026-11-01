import java.util.*;

public class Q1Test {
     public static void main(String[] args) {

          String result, traversal1, traversal2, traversal3;

          traversal1 = "1-2";
          traversal2 = "2-1-3";
          traversal3 = "1-3-2";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println("Expected Result : Invalid traversals");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "1-2-3";
          traversal2 = "2-1-3";
          traversal3 = "1-3-2";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "1-2-3";
          traversal2 = "3-2-1";
          traversal3 = "2-3-1";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println("Expected Result : Invalid traversals");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "3-1-2-5-4";
          traversal2 = "2-1-4-5-3";
          traversal3 = "1-2-3-4-5";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Preorder, Traversal 2 - Postorder, Traversal 3 - Inorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "10-20-30-40-50";
          traversal2 = "20-10-30-40-50";
          traversal3 = "10-50-40-30-20";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();
     }

     // write your codes here

     // A node in the binary search tree
     static class Node {
          int val;
          Node left, right;

          Node(int v) {
               val = v;
          }
     }

     public static String verify(String traversal1, String traversal2, String traversal3) {
          String[] t = { traversal1, traversal2, traversal3 };

          // Every way to assign the 3 strings to {inorder, preorder, postorder}
          // Each row = {index of inorder, index of preorder, index of postorder}
          int[][] perms = { { 0, 1, 2 }, { 0, 2, 1 }, { 1, 0, 2 }, { 1, 2, 0 }, { 2, 0, 1 }, { 2, 1, 0 } };

          for (int[] p : perms) {
               if (matches(t[p[0]], t[p[1]], t[p[2]])) {
                    String[] names = new String[3];
                    names[p[0]] = "Inorder";
                    names[p[1]] = "Preorder";
                    names[p[2]] = "Postorder";
                    return "Traversal 1 - " + names[0]
                              + ", Traversal 2 - " + names[1]
                              + ", Traversal 3 - " + names[2];
               }
          }
          return "Invalid traversals";
     }

     // Build the BST from the preorder string, then check all three traversals match
     private static boolean matches(String in, String pre, String post) {
          List<Integer> preList = parse(pre);

          Node root = null;
          for (int v : preList) {
               root = insert(root, v);
          }

          List<Integer> realIn = new ArrayList<>();
          List<Integer> realPre = new ArrayList<>();
          List<Integer> realPost = new ArrayList<>();
          inorder(root, realIn);
          preorder(root, realPre);
          postorder(root, realPost);

          return realIn.equals(parse(in))
                    && realPre.equals(preList)
                    && realPost.equals(parse(post));
     }

     // "1-2-3" -> [1, 2, 3]
     private static List<Integer> parse(String s) {
          List<Integer> list = new ArrayList<>();
          for (String part : s.split("-")) {
               list.add(Integer.parseInt(part.trim()));
          }
          return list;
     }

     // Standard BST insert
     private static Node insert(Node node, int v) {
          if (node == null) {
               return new Node(v);
          }
          if (v < node.val) {
               node.left = insert(node.left, v);
          } else {
               node.right = insert(node.right, v);
          }
          return node;
     }

     private static void inorder(Node n, List<Integer> out) {
          if (n == null) return;
          inorder(n.left, out);
          out.add(n.val);
          inorder(n.right, out);
     }

     private static void preorder(Node n, List<Integer> out) {
          if (n == null) return;
          out.add(n.val);
          preorder(n.left, out);
          preorder(n.right, out);
     }

     private static void postorder(Node n, List<Integer> out) {
          if (n == null) return;
          postorder(n.left, out);
          postorder(n.right, out);
          out.add(n.val);
     }
}
