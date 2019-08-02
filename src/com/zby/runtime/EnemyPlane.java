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
    private Image image2;
    private Image image3;

    private int speed = FrameConstant.GAME_SPEED * 2;

    private Random random = new Random();

    private boolean right = true;

    public int type;

    public EnemyPlane() {
        this(0, 0, 1);
    }

    public EnemyPlane(int x, int y, int type) {
        super(x, y);
        this.type = type;
        this.image = ImageMap.get("ep01");
        this.image2 = ImageMap.get("ep02");
        this.image3 = ImageMap.get("ep03");
    }

    @Override
    public void draw(Graphics g) {
        move();
        fire();
        if (type == 1) {
            g.drawImage(image, getX(), getY(), image.getWidth(null) / 2, image.getHeight(null) / 2, null);

        } else if (type == 2) {
            g.drawImage(image2, getX(), getY(), image2.getWidth(null) / 2, image2.getHeight(null) / 2, null);


        } else if (type == 3) {
            g.drawImage(image3, getX(), getY(), image3.getWidth(null) / 2, image3.getHeight(null) / 2, null);

        }


    }

    public void fire() {
        GameFrame gameFrame = DateStore.get("gameFrame");

        if (random.nextInt(1000) > 985) {
            if (type == 1) {
                gameFrame.enemyBulletList.add(new EnemyBullet(
                        getX() + (image.getWidth(null) / 4) - (ImageMap.get("epb01").getWidth(null) / 4),
                        getY() + image.getHeight(null), 1));
            }
            if (type == 2) {
                gameFrame.enemyBulletList.add(new EnemyBullet(
                        getX() + (image.getWidth(null) / 4) - (ImageMap.get("epb02").getWidth(null) / 4),
                        getY() + image.getHeight(null), 2));
            }
            if (type == 3) {
                gameFrame.enemyBulletList.add(new EnemyBullet(
                        getX() + (image.getWidth(null) / 4) - (ImageMap.get("epb03").getWidth(null) / 4),
                        getY() + image.getHeight(null), 3));
            }


        }


    }


    @Override
    public void move() {
        if (type == 1) {
            setY(getY() + speed);
        } else if (type == 2) {
            setY(getY() + speed);
        } else if (type == 3) {
            if (right) {
                setX(getX() + speed);
                setY(getY() + speed);
            } else {
                setX(getX() - speed);
                setY(getY() + speed);
            }
        }

        borderTesting();
    }


    public void borderTesting() {
        if (type == 1) {
            if (getY() > FrameConstant.FRAME_HEIGHT) {
                GameFrame gameFrame = DateStore.get("gameFrame");
                gameFrame.enemyPlaneList.remove(this);
            }
        } else if (type == 2) {
            if (getY() > FrameConstant.FRAME_HEIGHT) {
                GameFrame gameFrame = DateStore.get("gameFrame");
                gameFrame.enemyPlaneList.remove(this);
            }

        } else if (type == 3) {

            if (getX() + image3.getWidth(null) >= FrameConstant.FRAME_WIDTH) {
                right = false;
            } else if (getX() < 0) {
                right = true;
            }
//            if (getY() > FrameConstant.FRAME_HEIGHT) {
//                GameFrame gameFrame = DateStore.get("gameFrame");
//                gameFrame.enemyPlaneList.remove(this);
//            }
        }

    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), image.getWidth(null), image.getHeight(null));
    }

    //两飞机相撞，敌方死，我方血量减
    public void collisionTesting(Plane plane) {
        GameFrame gameFrame = DateStore.get("gameFrame");
        if (plane.getRectangle().intersects(this.getRectangle())) {

            gameFrame.enemyPlaneList.remove(this);

            gameFrame.realizeProp();

        }

    }
}
