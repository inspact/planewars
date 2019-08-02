package com.zby.runtime;

import com.zby.base.BaseSprite;
import com.zby.base.Drawable;
import com.zby.base.Moveable;
import com.zby.constant.FrameConstant;
import com.zby.main.GameFrame;
import com.zby.util.DateStore;
import com.zby.util.ImageMap;

import java.awt.*;

public class BossBullet extends BaseSprite implements Moveable, Drawable {

    private Image image;

    private int speed = FrameConstant.GAME_SPEED * 2;

    public BossBullet(int x, int y) {
        this(x, y, ImageMap.get("bb01"));
    }

    public BossBullet(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {

        move();
        borderTesting();
        g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);

    }

    @Override
    public void move() {
        setY(getY() + speed);
    }

    public void borderTesting() {
        if (getY() > FrameConstant.FRAME_HEIGHT) {
            GameFrame gameFrame = DateStore.get("gameFrame");
            gameFrame.bossBullets.remove(this);
        }

    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), image.getWidth(null), image.getHeight(null));
    }

    public void collisionTesting(Plane plane) {
        GameFrame gameFrame = DateStore.get("gameFrame");
        if (plane.getRectangle().intersects(this.getRectangle())) {
            gameFrame.bossBullets.remove(this);
            if (gameFrame.attack == true && gameFrame.hp > 0) {
                gameFrame.hp -= 5;
                if (gameFrame.hp == 0) {
                    gameFrame.gameOver = true;
                }
            }
        }
    }


}
