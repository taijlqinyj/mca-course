package com.mca.algorithm.interviem;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        char[] chars = str.toCharArray();
        if(chars.length == 0){
            System.out.println("");
        }else if(chars.length == 1){
            System.out.println(chars[0]);
        }else{
            char[] ans = new char[chars.length];
            for(int i = chars.length - 1; i >= 0; i--){
                ans[chars.length - 1 - i] = chars[i];
            }
            System.out.println(ans);
        }
    }
}
