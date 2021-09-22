package com.data.struct.stack;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 广度优先算法
 *计算岛屿数量，每个岛屿
 */
public class DFSStack {

    static int[][]  arr={{1,1,0,0,1},{0,1,1,0,1},{0,1,0,0,1},{0,1,0,0,1},{0,1,0,0,1},{1,0,0,0,1},{1,0,1,0,1},{0,0,1,0,1},{0,0,1,0,1},{0,0,0,0,1}};
    static class  Node{
        String value;
        List<Node> nextValue;
    }
    public static int numIslands(int[][] grid) {
        //边界条件判断
        if (grid == null || grid.length == 0)
            return 0;
        //统计岛屿的个数
        int count = 0;
        //两个for循环遍历每一个格子
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++) {
                //只有当前格子是1才开始计算
                if (grid[i][j] == 1) {
                    //如果当前格子是1，岛屿的数量加1
                    count++;
                    //然后通过dfs把当前格子的上下左右4
                    //个位置为1的都要置为0，因为他们是连着
                    //一起的算一个岛屿，
                    dfs(grid, i, j);
                }
            }
        //最后返回岛屿的数量
        return count;
    }

    static void dfs(int[][] grid, int i, int j){
        if(i<0 || j<0  || i>=grid.length || j>=grid[0].length || grid[i][j]==0)
            return;
        grid[i][j]=0;
        dfs(grid, i-1, j);
        dfs(grid, i+1, j);
        dfs(grid, i, j-1);
        dfs(grid, i, j+1);
    }

    /**
     * 图像渲染：
     *  有一幅以二维整数数组表示的图画，每一个整数表示该图画的像素值大小，数值在 0 到 65535 之间。
     *
     * 给你一个坐标 (sr, sc) 表示图像渲染开始的像素值（行 ，列）和一个新的颜色值 newColor，让你重新上色这幅图像。
     *
     * 为了完成上色工作，从初始坐标开始，记录初始坐标的上下左右四个方向上像素值与初始坐标相同的相连像素点，接着再记录这四个方向上符合条件的像素点与他们对应四个方向上像素值与初始坐标相同的相连像素点，……，重复该过程。将所有有记录的像素点的颜色值改为新的颜色值。
     *
     * 最后返回经过上色渲染后的图像。
     */
    int oldColor;
    public int[][] floodFill(int[][] image, int i, int j, int newColor) {
        oldColor = image[i][j];
        if (oldColor != newColor) {
            dfsImage(image, i, j, newColor);
        }
        return image;

    }

    void dfsImage(int[][] image, int i, int j, int newColor){
        if(i<0 || j<0  || i>=image.length || j>=image[0].length || image[i][j]==newColor)
            return;
        image[i][j]=newColor;
        try{
            dfsImage(image, i-1, j,newColor);
            dfsImage(image, i+1, j,newColor);
            dfsImage(image, i, j-1,newColor);
            dfsImage(image, i, j+1,newColor);
        }catch (Exception e){
            System.out.println("i=="+i+"==j=="+j);
        }

    }

    /**
     * 给定一个由 0 和 1 组成的矩阵，找出每个元素到最近的 0 的距离。
     *
     * 两个相邻元素间的距离为 1 。
     *
     * 要记录一个值遍历过的距离，
     */
    public int[][] updateMatrix() {
        int[][] mat={{1,1,0},{1,1,0},{0,0,0}};
        int m=mat.length,n=mat[0].length;
        int[][] res = new int[m][n];
        int[][] hasFind=new int[m][n];
        Queue<int[]> queue=new LinkedList<>();
        for(int i=0;i<mat.length;i++){
            for(int j=0;j<mat[0].length;j++){
                if(mat[i][j]==0){
                    queue.add(new int[]{i,j});
                    res[i][j]=0;
                    hasFind[i][j]=0;
                }
            }
        }
        while (!queue.isEmpty()){
            int[] poll=queue.poll();
            int x=poll[0],y=poll[1];
            for(int i=0;i<m;i++){
                int x1 = x + mat[i][0], y1 = y + mat[i][1];
                if(x1<m || y1<n || hasFind[x1][y1]!=0){
                    res[x][y] = res[x][y] + 1;
                    queue.offer(new int[]{x1,y1});
                    hasFind[x1][y1]=0;
                }
            }
        }
        return mat;
    }





    public static void main(String[] args) {
        DFSStack dfsStack=new DFSStack();
        int[][] image = {{1,1,1},{1,1,0},{1,0,1}};

        System.out.println(Arrays.deepToString(dfsStack.updateMatrix()));

    }
}
