package com.lsh.ThreadTest;

import java.util.Arrays;

public class Main {

    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        if(matrix == null || matrix.length==0) return false;
        int n = matrix.length-1;
        int m = matrix[n].length-1;
        if(matrix[n][m] < target || matrix[0][0]>target) return false;
        //boolean [][] vist =
        System.out.println("ss");
        return find(matrix,target,0,0,n,m);

    }
    public static boolean find(int[][] matrix, int target,int i,int j,int n,int m){

        if(i > n && j>m) return false;
        if(i<=n && j<=m){
           int jta = search(matrix,j,m ,target,true,i,j);
           if(matrix[i][jta] == target) return  true;
           int ita = search(matrix,i,n,target,false,i,j);
            if(matrix[ita][j] == target) return  true;
            System.out.println( "[" +i + ","+j+  " ] -------" + "[" +ita + ","+jta+  " ] = " + matrix[ita][jta]);
            return find(matrix,target,i+1,j+1,ita,jta) ;

        }
        return false;
    }
    public static int search(int [][] arr,int le,int ri,int x,boolean tag,int i,int j){

        if(tag) {
            while(le <= ri){
                int mid = (le + ri) / 2;
                if(x == arr[i][mid]) return mid;
                if(x < arr[i][mid]) ri = mid-1;
                else le = mid+1;
            }
        }else {
            while(le <= ri){
                int mid = (le + ri) / 2;
                if(x == arr[mid][j]) return mid;
                if(x < arr[mid][j]) ri = mid-1;
                else le = mid+1;
            }
        }
        return ri;
    }
    public static void main(String[] args) {
	// write your code here
        int [][]  mar = {
                {1,   4,  7, 11, 15},
                {2,   5,  8, 12, 19},
                {3,   6,  9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}};
        System.out.println(findNumberIn2DArray(mar,5));
        int [][] arr = {{}};
        System.out.println(arr[0].length);

    }
}
