package com.zby.util;


import java.awt.*;
import java.util.HashMap;
import java.util.Map;
public class ImageMap {


    public  static  final Map<String, Image> map = new HashMap<>();

    static {
        map.put("bg01",ImageUtil.getImage("com\\zby\\imgs\\bg\\bg01.png"));

        map.put("my01",ImageUtil.getImage("com\\zby\\imgs\\plane\\my_1.png"));

        map.put("mb01",ImageUtil.getImage("com\\zby\\imgs\\bullet\\myb_1.png"));

        map.put("ep01",ImageUtil.getImage("com\\zby\\imgs\\plane\\ep_1.png"));
        map.put("ep02",ImageUtil.getImage("com\\zby\\imgs\\plane\\ep_2.png"));
        map.put("ep03",ImageUtil.getImage("com\\zby\\imgs\\plane\\ep_3.png"));

        map.put("epb01",ImageUtil.getImage("com\\zby\\imgs\\bullet\\epb_1.png"));
        map.put("epb02",ImageUtil.getImage("com\\zby\\imgs\\bullet\\epb_2.png"));
        map.put("epb03",ImageUtil.getImage("com\\zby\\imgs\\bullet\\epb_3.png"));


        map.put("defend",ImageUtil.getImage("com\\zby\\imgs\\prop\\adddefense1.png"));
        map.put("hp",ImageUtil.getImage("com\\zby\\imgs\\prop\\addHP1.png"));


        map.put("boss1",ImageUtil.getImage("com\\zby\\imgs\\boss\\boss_A_01.png"));
        map.put("boss2",ImageUtil.getImage("com\\zby\\imgs\\boss\\boss_A_02.png"));
        map.put("boss3",ImageUtil.getImage("com\\zby\\imgs\\boss\\boss_A_03.png"));
        map.put("boss4",ImageUtil.getImage("com\\zby\\imgs\\boss\\boss_A_04.png"));
        map.put("boss5",ImageUtil.getImage("com\\zby\\imgs\\boss\\boss_A_05.png"));
        map.put("boss6",ImageUtil.getImage("com\\zby\\imgs\\boss\\boss_A_06.png"));
        map.put("boss7",ImageUtil.getImage("com\\zby\\imgs\\boss\\boss_A_07.png"));
        map.put("boss8",ImageUtil.getImage("com\\zby\\imgs\\boss\\boss_A_08.png"));
        map.put("boss9",ImageUtil.getImage("com\\zby\\imgs\\boss\\boss_A_09.png"));

        map.put("bb01",ImageUtil.getImage("com\\zby\\imgs\\bullet\\bossbullet.png"));




        map.put("e1",ImageUtil.getImage("com\\zby\\imgs\\explode\\e1.png"));
        map.put("e2",ImageUtil.getImage("com\\zby\\imgs\\explode\\e2.png"));
        map.put("e3",ImageUtil.getImage("com\\zby\\imgs\\explode\\e3.png"));
        map.put("e4",ImageUtil.getImage("com\\zby\\imgs\\explode\\e4.png"));
        map.put("e5",ImageUtil.getImage("com\\zby\\imgs\\explode\\e5.png"));
        map.put("e6",ImageUtil.getImage("com\\zby\\imgs\\explode\\e6.png"));
        map.put("e7",ImageUtil.getImage("com\\zby\\imgs\\explode\\e7.png"));
        map.put("e8",ImageUtil.getImage("com\\zby\\imgs\\explode\\e8.png"));
        map.put("e9",ImageUtil.getImage("com\\zby\\imgs\\explode\\e9.png"));




    }

    public static Image get(String key){
        return map.get(key);
    }


}
