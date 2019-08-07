package com.zby.runtime;

import com.zby.base.BaseSprite;
import com.zby.base.Drawable;
import com.zby.base.Moveable;
import com.zby.util.ImageMap;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Explode extends BaseSprite implements Drawable, Moveable {


    private int index;

    public boolean Live;

    private List<Image> imageList = new ArrayList<>();


    public Explode() {

        for (int i = 1; i < 10; i++) {
            imageList.add(ImageMap.get("e" + i));
        }

    }

    public Explode(int x, int y, int index) {
        super(x, y);
        this.index = index;
    }

    @Override
    public void draw(Graphics g) {

        if (index < 9) {
            g.drawImage(imageList.get(index++ / 2), getX()+100, getY()+100,
                    imageList.get(0).getWidth(null),
                    imageList.get(0).getHeight(null),
                    null);
        }

    }

    @Override
    public void move() {

    }
}
