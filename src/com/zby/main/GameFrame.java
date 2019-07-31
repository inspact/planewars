package com.zby.main;

import com.zby.constant.FrameConstant;
import com.zby.runtime.*;
import com.zby.util.ImageMap;


import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class GameFrame extends Frame {

    //创建背景
    private Background background = new Background();
    //创建飞机
    private Plane plane = new Plane();

    //创建子弹集合
    public final List<Bullet> bulletList = new CopyOnWriteArrayList<>();

    //敌方子弹集合
    public final List<EnemyBullet> enemyBulletList = new CopyOnWriteArrayList<>();

    //创建敌方飞机集合
    public final List<EnemyPlane> enemyPlaneList = new CopyOnWriteArrayList<>();

    public boolean gameOver = false;

    @Override
    public void paint(Graphics g) {
        if (!gameOver){
            background.draw(g);
            plane.draw(g);
            for (Bullet bullet : bulletList){
                bullet.draw(g);
            }

            for (EnemyBullet enemyBullet : enemyBulletList) {
                enemyBullet.draw(g);
            }

            for (EnemyPlane enemyPlane : enemyPlaneList) {
                enemyPlane.draw(g);
            }

            for (Bullet bullet : bulletList) {
                bullet.collisionTesting(enemyPlaneList);

            }

            for (EnemyBullet enemyBullet : enemyBulletList) {
                enemyBullet.collisionTestinp(plane);
            }
        }



       //测试是否移除
        g.setColor(Color.RED);
        g.drawString("" + enemyBulletList.size(),100,100);

    }

    /**
     * 使用这个方法初始化窗口
     */

    public void init() {
        //设置尺寸
        setSize(FrameConstant.FRAME_WIDTH, FrameConstant.FRAME_HEIGHT);

        //居中
        setLocationRelativeTo(null);

        //禁用输入法
        enableInputMethods(false);

        //关闭窗口
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        //添加键盘监听
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                plane.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                plane.keyReleased(e);
            }
        });


        new Thread() {
            @Override
            public void run() {
                repaint();
                while (true) {
                    repaint();
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {

                    }
                }
            }
        }.start();

        //游戏初始时添加敌方飞机
        enemyPlaneList.add(new EnemyPlane(300,30, ImageMap.get("ep01")));
        enemyPlaneList.add(new EnemyPlane(300,-50, ImageMap.get("ep01")));
        enemyPlaneList.add(new EnemyPlane(600,30, ImageMap.get("ep01")));
        enemyPlaneList.add(new EnemyPlane(450,20, ImageMap.get("ep01")));

        //显示窗口
        setVisible(true);


    }

    private Image offScreenImage = null;//创建缓冲区

    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(FrameConstant.FRAME_WIDTH, FrameConstant.FRAME_HEIGHT);

        }
        Graphics gOff = offScreenImage.getGraphics();//创建离线图片的实例，在图片缓冲区绘图

        paint(gOff);
        g.drawImage(offScreenImage, 0, 0, null);//将缓冲区图片绘制到窗口目标
    }


}
