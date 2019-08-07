package com.zby.runtime;

import com.zby.base.BaseSprite;
import com.zby.base.Drawable;
import com.zby.base.Moveable;
import com.zby.constant.FrameConstant;
import com.zby.main.GameFrame;
import com.zby.util.DateStore;
import com.zby.util.ImageMap;

import java.awt.*;
import java.util.List;

public class Bullet extends BaseSprite implements Moveable, Drawable {

    private Image image;
    private Image image1;
    private Image image2;
    private Image image3;

    private int speed = FrameConstant.GAME_SPEED * 5;



    public Bullet() {
        this(0, 0, ImageMap.get("mb01"));
    }

    public Bullet(int x, int y, Image image) {
        super(x, y);
        this.image = image;
        this.image1 = ImageMap.get("mb02");
        this.image2 = ImageMap.get("mb03");
        this.image3 = ImageMap.get("mb04");
    }

    @Override
    public void draw(Graphics g) {
        move();
        borderTesting();
        GameFrame gameFrame = DateStore.get("gameFrame");

        if (gameFrame.pass == 1){
            g.drawImage(image, getX(), getY(), image.getWidth(null) / 2, image.getHeight(null) / 2, null);

        }if (gameFrame.pass ==2){
            g.drawImage(image1, getX(), getY(), image.getWidth(null) / 2, image.getHeight(null) / 2, null);

        }
        if (gameFrame.pass ==3){
            g.drawImage(image2, getX(), getY(), image.getWidth(null) / 2, image.getHeight(null) / 2, null);

        }
        if (gameFrame.pass ==4){
            g.drawImage(image3, getX(), getY(), image.getWidth(null) / 2, image.getHeight(null) / 2, null);

        }


    }

    @Override
    public void move() {
        setY(getY() - speed);
    }


    public void borderTesting() {
        if (getY() < 30 - image.getHeight(null)) {
            GameFrame gameFrame = DateStore.get("gameFrame");
            gameFrame.bulletList.remove(this);
        }
        if (getY() < 30 - image1.getHeight(null)) {
            GameFrame gameFrame = DateStore.get("gameFrame");
            gameFrame.bulletList.remove(this);
        }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), image.getWidth(null), image.getHeight(null));
    }

    //我方子弹碰到敌方飞机
    public void collisionTesting(List<EnemyPlane> enemyPlaneList) {
        GameFrame gameFrame = DateStore.get("gameFrame");
        for (EnemyPlane enemyPlane : enemyPlaneList) {
            if (enemyPlane.getRectangle().intersects(this.getRectangle())) {
                enemyPlaneList.remove(enemyPlane);
                gameFrame.bulletList.remove(this);
                gameFrame.count++;
                if (enemyPlane.type == 1) {
                    gameFrame.score += 1;
                }
                if (enemyPlane.type == 2) {
                    gameFrame.score += 2;
                }
                if (enemyPlane.type == 3) {
                    gameFrame.score += 3;
                }
            }
        }
    }

    //我方子弹碰到boss
    public void collisionTesting1(Boss bosses) {
        GameFrame gameFrame = DateStore.get("gameFrame");
        if (bosses.getRectangle().intersects(this.getRectangle())) {
            gameFrame.bulletList.remove(this);

                gameFrame.bhp -= 2;
                if (gameFrame.bhp <= 0) {
                    gameFrame.score += 50;
                    gameFrame.gameOver = true;
                }

        }
    }
}
