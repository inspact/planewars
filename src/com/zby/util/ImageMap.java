package com.zby.util;


import java.awt.*;
import java.util.HashMap;
import java.util.Map;
public class ImageMap {


    public  static  final Map<String, Image> map = new HashMap<>();

    static {
        map.put("bg01",ImageUtil.getImage("com\\zby\\imgs\\bg\\bg01.png"));
      //  map.put("bg01",ImageUtil.getImage("com\\zby\\imgs\\bg\\bg02.jpg"));

        map.put("my01",ImageUtil.getImage("com\\zby\\imgs\\plane\\my_1.png"));


        map.put("mb01",ImageUtil.getImage("com\\zby\\imgs\\bullet\\myb_1.png"));
    }

    public static Image get(String key){
        return map.get(key);
    }


}
