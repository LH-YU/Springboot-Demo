package com.example.demo.DemoTest;

import java.util.ArrayList;
import java.util.List;

public class TestLambda {

    /**
     * 判断list数组中是否包含某个值
     * lambda表达式判断
     * @param args
     */
    public static void main(String[] args) {

        String checkStr = "113";

        String str1 = "111";
        String str2 = "112";
        String str3 = "113";
        String str4 = "114";
        String str5 = "115";

        List<String> list = new ArrayList<>();
        list.add(str1);
        list.add(str2);
        list.add(str3);
        list.add(str4);
        list.add(str5);

        System.out.println(list.stream().anyMatch(str -> str.equals(checkStr)));

    }

}
