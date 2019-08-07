package com.zby.runtime;

import com.zby.base.BaseSprite;
import com.zby.base.Drawable;
import com.zby.base.Moveable;
import com.zby.constant.FrameConstant;
import com.zby.main.GameFrame;
import com.zby.util.DateStore;
import com.zby.util.ImageMap;

import java.awt.*;

public class EnemyBullet extends BaseSprite implements Moveable, Drawable {

    private int speed = FrameConstant.GAME_SPEED * 5;

    private int type;

    private Image image;
    private Image image2;
    private Image image3;

    private boolean time = true;//计数器生效


    public EnemyBullet() {
        this(0, 0, 1);
    }

    public EnemyBullet(int x, int y, int type) {
        super(x, y);
        this.type = type;
        this.image = ImageMap.get("epb01");
        this.image2 = ImageMap.get("epb02");
        this.image3 = ImageMap.get("epb03");
    }

    @Override
    public void draw(Graphics g) {
        move();
        borderTesting();
        if (type == 1) {
            g.drawImage(image, getX(), getY(), image.getWidth(null) / 2, image.getHeight(null) / 2, null);

        }
        if (type == 2) {
            g.drawImage(image2, getX(), getY(), image2.getWidth(null) / 2, image2.getHeight(null) / 2, null);

        }
        if (type == 3) {
            g.drawImage(image3, getX(), getY(), image3.getWidth(null) / 2, image3.getHeight(null) / 2, null);

        }

    }

    @Override
    public void move() {
        setY(getY() + speed);

    }


    public void borderTesting() {
        if (getY() > FrameConstant.FRAME_HEIGHT) {
            GameFrame gameFrame = DateStore.get("gameFrame");
            gameFrame.enemyBulletList.remove(this);
        }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), image.getWidth(null)/2, image.getHeight(null)/2);
    }

    //敌方子弹攻击我方飞机
    public void collisionTestinp(Plane plane) {
        GameFrame gameFrame = DateStore.get("gameFrame");
        if (plane.getRectangle().intersects(this.getRectangle())) {
            gameFrame.enemyBulletList.remove(this);

            gameFrame.realizeProp();

        }

    }

}
