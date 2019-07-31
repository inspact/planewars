package com.zby.main;

import com.zby.constant.FrameConstant;
import com.zby.runtime.Background;
import com.zby.runtime.Bullet;
import com.zby.runtime.Plane;


import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class GameFrame extends Frame {
    //创建背景
    private Background background = new Background();
    //创建飞机
    private Plane plane = new Plane();

    //创建子弹集合
    public final List<Bullet> bulletList = new CopyOnWriteArrayList<>();


    @Override
    public void paint(Graphics g) {
        background.draw(g);
        plane.draw(g);
        for (Bullet bullet : bulletList){
            bullet.draw(g);
        }

       g.setColor(Color.RED);
        g.drawString("" + bulletList.size(),100,100);

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
