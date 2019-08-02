package com.zby.runtime;

import com.zby.base.BaseSprite;
import com.zby.base.Drawable;
import com.zby.base.Moveable;
import com.zby.constant.FrameConstant;
import com.zby.main.GameFrame;
import com.zby.util.DateStore;
import com.zby.util.ImageMap;

import java.awt.*;


public class Prop extends BaseSprite implements Moveable, Drawable {

    private Image image;
    private Image image2;

    private int type;

    private int speed = FrameConstant.GAME_SPEED * 1;


    public Prop() {
        this(0, 0, 1);
    }

    public Prop(int x, int y, int type) {
        super(x, y);
        this.type = type;
        this.image = ImageMap.get("hp");
        this.image2 = ImageMap.get("defend");
    }

    @Override
    public void draw(Graphics g) {
        move();
        borderTesting();
        if (type == 1) {
            g.drawImage(image, getX(), getY(), image.getWidth(null), image.getWidth(null), null);

        }
        if (type == 2) {
            g.drawImage(image2, getX(), getY(), image2.getWidth(null), image2.getWidth(null), null);

        }


    }

    @Override
    public void move() {
        if (type == 1) {
            setY(getY() + speed);
        }
        if (type == 2) {
            setY(getY() + speed);
        }

    }

    public void borderTesting() {
        if (type == 1) {
            if (getY() > FrameConstant.FRAME_HEIGHT) {
                GameFrame gameFrame = new GameFrame();
                gameFrame.propList.remove(this);
            }
        }
        if (type == 2) {
            if (getY() > FrameConstant.FRAME_HEIGHT) {
                GameFrame gameFrame = new GameFrame();
                gameFrame.propList.remove(this);

            }
        }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), image.getWidth(null), image.getHeight(null));
    }

    public void collisionTesting(Plane plane) {
        GameFrame gameFrame = DateStore.get("gameFrame");
        if (plane.getRectangle().intersects(this.getRectangle())) {
            gameFrame.propList.remove(this);
            if (type == 1) {
                if (gameFrame.hp < 50) {
                    gameFrame.hp += 1;
                } else {
                    gameFrame.hp = 50;
                }
            }
            if (type == 2) {
                gameFrame.attack = false;
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(10000);
                            gameFrame.attack = true;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        }
    }
}
