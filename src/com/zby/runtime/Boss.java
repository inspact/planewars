package com.zby.runtime;

import com.zby.base.BaseSprite;
import com.zby.base.Drawable;
import com.zby.base.Moveable;
import com.zby.constant.FrameConstant;
import com.zby.main.GameFrame;
import com.zby.util.DateStore;
import com.zby.util.ImageMap;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Boss extends BaseSprite implements Moveable, Drawable {
    private int index;

    Random random = new Random();

    private boolean right = true;

    private int speed = FrameConstant.GAME_SPEED * 2;

    private List<Image> imageList = new ArrayList<>();

    public Boss() {
        for (int i = 1; i < 10; i++) {
            imageList.add(ImageMap.get("boss" + i));
        }

    }

    public Boss(int x, int y, int index) {
        super(x, y);
        this.index = index;
    }

    @Override
    public void draw(Graphics g) {
        move();
        borderTesting();
        fire();
        g.drawImage(imageList.get(index++ / 10), getX(), getY() + 100,
                imageList.get(0).getWidth(null) / 2,
                imageList.get(0).getHeight(null) / 2,
                null);
        if (index >= 90) {
            index = 0;
        }


    }


    public void fire() {
        GameFrame gameFrame = DateStore.get("gameFrame");
        if (random.nextInt(1000) > 985) {
            gameFrame.bossBullets.add(new BossBullet(
                    getX() + imageList.get(0).getWidth(null) / 2 - ImageMap.get("bb01").getHeight(null),
                    getY() + imageList.get(0).getHeight(null) - ImageMap.get("bb01").getHeight(null)
            ));
        }
    }

    @Override
    public void move() {
        if (right) {
            setX(getX() + speed);
        } else {
            setX(getX() - speed);
        }
    }

    public void borderTesting() {

        if (getX() + imageList.get(0).getWidth(null) >= FrameConstant.FRAME_WIDTH) {
            right = false;
        } else if (getX() < 0) {
            right = true;
        }

    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), imageList.get(0).getWidth(null), imageList.get(0).getHeight(null));
    }

}
