package qqai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 描述：12345按要求排序
 *
 * @author qqai
 * @createTime 2020-09-17 10:30
 */

public class Graph {

    /**
     * 题目:用1、2、2、3、4、5这六个数字，用java写一个main函数，打印出所有不同的排列，如：512234、412325等.
     * 要求："4"不能在第三位，"3"与"5"不能相连
     * A(6,6)-A(5,5)-2*5*A(4,4)+2*3*A(3,3)=396,396/2=198
     * two solutions:
     * 1.Permutation
     * 2.Graph,depthFirst
     */

    private static final int[] DATA={1,2,2,3,4,5};
    private static final int LENGTH=DATA.length;
    private boolean[] visited;
    private int[][] matrix;
    private StringBuilder resultString;
    private Set<String> resultSet;//use 'Set' to reject duplicate string.

    public static void main(String[] args) {
        Graph graph=new Graph();
        graph.initial();
        for(int i=0;i<LENGTH;i++){
            graph.depthFirst(i);//start from 1,2,2,3,4,5,find their corresponding DFS
        }
        graph.print();
    }

    public void initial(){
        resultString=new StringBuilder();
        resultSet=new HashSet<String>();
        int n=LENGTH;
        visited=new boolean[n];
        matrix=new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(i==j){
                    matrix[i][j]=0;
                }else{
                    matrix[i][j]=1;
                }
            }
        }
        //"3"与"5"不能相连
        matrix[3][5]=0;
        matrix[5][3]=0;
    }

    public void depthFirst(int origin){
        //case 1.resultString includes DATA[origin]
        resultString.append(DATA[origin]);
        visited[origin]=true;
        if(resultString.length()==LENGTH){
            boolean ok=resultString.charAt(2)!='4';//"4"不能在第三位
            if(ok){
                resultSet.add(resultString.toString());
            }
        }
        for(int i=0;i<LENGTH;i++){
            if(!visited[i]&&matrix[origin][i]==1){
                depthFirst(i);
            }else{
                continue;
            }
        }
        //case 2.resultString don't include DATA[origin]
        resultString.deleteCharAt(resultString.length()-1);//remove DATA[origin]
        visited[origin]=false;
    }

    public void print(){
        for(String str:resultSet){
            System.out.println(str);
        }
        System.out.println(resultSet.size());
    }
}