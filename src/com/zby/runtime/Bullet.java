package com.zby.runtime;

import com.zby.base.BaseSprite;
import com.zby.base.Drawable;
import com.zby.base.Moveable;
import com.zby.constant.FrameConstant;
import com.zby.main.GameFrame;
import com.zby.util.DateStore;
import com.zby.util.ImageMap;

import java.awt.*;

public class Bullet extends BaseSprite implements Moveable, Drawable {

    private Image image;

    private int speed  = FrameConstant.GAME_SPEED*5;

    public Bullet() {
        this(0,0, ImageMap.get("mb01"));
    }

    public Bullet(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
       g.drawImage(image,getX(),getY(),image.getWidth(null),image.getHeight(null),null);
       move();
       borderTesting();

    }

    @Override
    public void move() {
        setY(getY() - speed);
    }


    public  void borderTesting(){
        if (getY() < 30 -image.getHeight(null)){
            GameFrame gameFrame = DateStore.get("gameFrame");
            gameFrame.bulletList.remove(this);
        }
    }
}
