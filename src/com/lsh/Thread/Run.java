package com.lsh.Thread;

import org.omg.PortableInterceptor.INACTIVE;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Run {


    public static  int[] reversePrint(ListNode head) {
        ArrayList<Integer> result = new ArrayList<>() ;
        int count = 0;
            if(head == null)  return new int[0];
        else {
            while(head != null)
            {
               // ListNode newnode = new ListNode(r.val);
                result.add(head.val);
                head = head.next;
            }

        }
        int [] array  = new int[result.size()];
        for(int i = result.size()-1,j = 0;i>=0 && j<result.size();i--,j++){
            array[j] = result.get(i).intValue();
            System.out.println("new " + array[j]);
        }

        return array;
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        int middle = 0,i=0,n = inorder.length ;
        for( i = 0; i<n;i++)
        {
            if(preorder[middle] == inorder[i]) break;
        }
        TreeNode fateher = new TreeNode(inorder[i]);
        //TreeNode left;
        if(i==0){
            fateher.left = null;
        }
        else{
            int [] leftpre = new int[i];int [] leftin = new int[i];

            System.arraycopy(preorder,1,leftpre,0,i);
            System.arraycopy(inorder,0,leftin,0,i);
            fateher.left= buildTree(leftpre,leftin);
        }
        if(i==n-1){
            fateher.right = null;
        }
        else {
            int [] rightpre = new int[n-i-1];int [] rightin = new int[n-i-1];
            System.arraycopy(preorder,i+1,rightpre,0,n-i-1);
            System.arraycopy(inorder,i+1,rightin,0,n-i-1);
            fateher.right = buildTree(rightpre,rightin);
        }


        return  fateher;
    }


        public TreeNode buildTree2(int[] preorder, int[] inorder) {
            if (preorder == null || preorder.length == 0) {
                return null;
            }
            Map<Integer, Integer> indexMap = new HashMap<Integer, Integer>();
            int length = preorder.length;
            for (int i = 0; i < length; i++) {
                indexMap.put(inorder[i], i);
            }
            TreeNode root = buildTree(preorder, 0, length - 1, inorder, 0, length - 1, indexMap);
            return root;
        }

    /**
     * @param preorder
     * @param preorderStart
     * @param preorderEnd
     * @param inorder
     * @param inorderStart
     * @param inorderEnd
     * @param indexMap
     * @return
     */
        public TreeNode buildTree(int[] preorder, int preorderStart, int preorderEnd, int[] inorder, int inorderStart, int inorderEnd, Map<Integer, Integer> indexMap) {
            if (preorderStart > preorderEnd) {
                return null;
            }
            int rootVal = preorder[preorderStart];
            TreeNode root = new TreeNode(rootVal);
            if (preorderStart == preorderEnd) {
                return root;
            } else {
                int rootIndex = indexMap.get(rootVal);
                int leftNodes = rootIndex - inorderStart, rightNodes = inorderEnd - rootIndex;
                TreeNode leftSubtree = buildTree(preorder, preorderStart + 1, preorderStart + leftNodes, inorder, inorderStart, rootIndex - 1, indexMap);
                TreeNode rightSubtree = buildTree(preorder, preorderEnd - rightNodes + 1, preorderEnd, inorder, rootIndex + 1, inorderEnd, indexMap);
                root.left = leftSubtree;
                root.right = rightSubtree;
                return root;
            }
        }

    public static String getValue(String key) throws IOException {
        Properties pro = new Properties();//获取配置文件的对象
        FileReader in = new FileReader("pro.txt");//获取输入流
        pro.load(in);//将流加载到配置文件对象中
        in.close();
        return pro.getProperty(key);//返回根据key获取的value值
    }



    public static void main(String[] args)throws Exception {
        Class stuClass = Class.forName(getValue("className"));
        Method method = stuClass.getMethod(getValue("show"));
        method.invoke(stuClass.getConstructor().newInstance());


    }
}
