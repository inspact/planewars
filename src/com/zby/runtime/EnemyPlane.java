package com.zby.runtime;

import com.zby.base.BaseSprite;
import com.zby.base.Drawable;
import com.zby.base.Moveable;

import java.awt.*;

public class EnemyPlane extends BaseSprite implements Moveable, Drawable {

    private Image image;

    public EnemyPlane() {

    }

    public EnemyPlane(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {

    }

    @Override
    public void move() {

    }
}
