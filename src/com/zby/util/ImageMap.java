package com.zby.util;


import java.awt.*;
import java.util.HashMap;
import java.util.Map;
public class ImageMap {


    public  static  final Map<String, Image> map = new HashMap<>();

    static {
        //map.put("bg01",ImageUtil.getImage("com\\zby\\imgs\\bg\\bg01.png"));
        map.put("bg01",ImageUtil.getImage("com\\zby\\imgs\\bg\\bg02.jpg"));

    }

    public static Image get(String key){
        return map.get(key);
    }


}
