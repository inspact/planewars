package com.zby.runtime;

import com.zby.base.BaseSprite;
import com.zby.base.Drawable;
import com.zby.base.Moveable;
import com.zby.constant.FrameConstant;
import com.zby.main.GameFrame;
import com.zby.util.DateStore;
import com.zby.util.ImageMap;

import java.awt.*;
import java.util.Random;

public class EnemyPlane extends BaseSprite implements Moveable, Drawable {

    private Image image;

    private int speed = FrameConstant.GAME_SPEED * 3;

    private Random random = new Random();

    public EnemyPlane() {
        this(0, 0, ImageMap.get("ep01"));
    }

    public EnemyPlane(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);
        move();
        fire();
    }

    public void fire() {
        GameFrame gameFrame = DateStore.get("gameFrame");
        if (random.nextInt(1000) > 985) {
            gameFrame.enemyBulletList.add(new EnemyBullet(
                    getX() + (image.getWidth(null) / 2) - (ImageMap.get("epb01").getWidth(null)/2),
                    getY() + image.getHeight(null),
                    ImageMap.get("epb01")));

        }


    }

    @Override
    public void move() {
        setY(getY() + speed);
        borderTesting();
    }


    public void borderTesting() {
        if (getY() > FrameConstant.FRAME_HEIGHT) {
            GameFrame gameFrame = DateStore.get("gameFrame");
            gameFrame.enemyPlaneList.remove(this);
        }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(),getY(),image.getWidth(null),image.getHeight(null));
    }
}
