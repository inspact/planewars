package com.zby.runtime;

import com.zby.base.BaseSprite;
import com.zby.base.Drawable;
import com.zby.base.Moveable;
import com.zby.util.ImageMap;

import java.awt.*;

public class  Background extends BaseSprite implements Drawable, Moveable {


    private Image image;

    public Background() {
        this(0,0, ImageMap.get("bg01"));
    }

    public Background(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
          g.drawImage(image,getX(),getY(),image.getWidth(null),image.getHeight(null),null);
          move();
    }

    @Override
    public void move() {
        setY(getY()-1);

    }
}


