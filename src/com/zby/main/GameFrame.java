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
import java.util.Random;
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

    //创建道具集合
    public final List<Prop> propList = new CopyOnWriteArrayList<>();
    //创建boss
    private Boss boss = new Boss();
    //创建boss子弹
    public final List<BossBullet> bossBullets = new CopyOnWriteArrayList<>();

    //爆炸
    private Explode explode = new Explode();
    public final List<Explode> explodes = new CopyOnWriteArrayList<>();


    //游戏是否结束
    public boolean gameOver = false;

    //血量
    public int hp = FrameConstant.HP;
    //分数
    public int score = 0;

    //消灭敌飞个数
    public int count = 0;

    //关卡
    public int pass = 1;

    //是否遭到攻击
    public volatile boolean attack = true;
    //给定一个变量让hp固定
    public int protect;
    //boss血量
    public int bhp = FrameConstant.BHP;

    //随机数
    Random random = new Random();


    //绘制飞机子弹
    @Override
    public void paint(Graphics g) {


        //explode.draw(g);

        if (!gameOver) {
            background.draw(g);
            plane.draw(g);
            /**
             * 绘制敌方飞机，不同阶段a
             * 随机数。每时段刷新
             */
            if (random.nextInt(1000) > 985) {
                if (count < 5) {
                    pass = 1;
                    enemyPlaneList.add(new EnemyPlane(
                            random.nextInt(FrameConstant.FRAME_WIDTH - ImageMap.get("ep01").getWidth(null)),
                            30 - ImageMap.get("ep01").getHeight(null), 1));

                }
                if (count >= 5 && count < 10) {
                    pass = 2;
                    enemyPlaneList.add(new EnemyPlane(
                            random.nextInt(FrameConstant.FRAME_WIDTH - ImageMap.get("ep02").getWidth(null)),
                            30 - ImageMap.get("ep02").getHeight(null), 2));

                }
                if (count >= 10 && count <= 15) {
                    pass = 3;
                    enemyPlaneList.add(new EnemyPlane(
                            random.nextInt(FrameConstant.FRAME_WIDTH - ImageMap.get("ep03").getWidth(null)),
                            30 - ImageMap.get("ep03").getHeight(null), 3));
                }
            }
            if (count >= 15) {
                boss.draw(g);
                //boss血量
                g.setFont(new Font("黑体", Font.BOLD, 20));
                g.setColor(new Color(124, 255, 223));
                g.drawString("boss血量:" + bhp, 80, 210);

            }

            //道具
            if (random.nextInt(1000) > 993) {
                if (pass >= 1) {
                    propList.add(new Prop(random.nextInt(FrameConstant.FRAME_WIDTH - ImageMap.get("hp").getWidth(null)),
                            30 - ImageMap.get("hp").getHeight(null), 1));
                }
                if (pass >=2) {
                    propList.add(new Prop(random.nextInt(FrameConstant.FRAME_WIDTH - ImageMap.get("defend").getWidth(null)),
                            30 - ImageMap.get("defend").getHeight(null), 2));
                }
            }


            //遍历，输出子弹
            for (Bullet bullet : bulletList) {
                bullet.draw(g);
            }
            //敌方子弹
            for (EnemyBullet enemyBullet : enemyBulletList) {
                enemyBullet.draw(g);
            }
            //敌方飞机
            for (EnemyPlane enemyPlane : enemyPlaneList) {
                enemyPlane.draw(g);
            }


            //我方子弹击打敌方飞机
            //我方子弹攻击boss
            for (Bullet bullet : bulletList) {
                bullet.collisionTesting(enemyPlaneList);
                bullet.collisionTesting1(boss);
            }
            //敌方子弹击打我方飞机
            for (EnemyBullet enemyBullet : enemyBulletList) {
                enemyBullet.collisionTestinp(plane);
            }
            //飞机碰撞飞机
            for (EnemyPlane enemyPlane : enemyPlaneList) {
                enemyPlane.collisionTesting(plane);
            }

            //boss子弹攻击我方飞机
            for (BossBullet bossBullet : bossBullets) {
                bossBullet.draw(g);
                bossBullet.collisionTesting(plane);
            }


            //道具
            for (Prop prop : propList) {
                prop.draw(g);
            }
            for (Prop prop : propList) {
                prop.collisionTesting(plane);
            }

            //关卡
            g.setFont(new Font("黑体", Font.BOLD, 20));
            g.setColor(new Color(124, 255, 223));
            g.drawString("游戏关卡:" + pass, 80, 190);

            //血量减少
            g.setFont(new Font("黑体", Font.BOLD, 20));
            g.setColor(new Color(124, 255, 223));
            g.drawString("我方血量:" + hp, 80, 100);

            //得分
            g.setFont(new Font("黑体", Font.BOLD, 20));
            g.setColor(new Color(124, 255, 223));
            g.drawString("游戏得分:" + score, 80, 130);

            //消灭敌人个数
            g.setFont(new Font("黑体", Font.BOLD, 20));
            g.setColor(new Color(124, 255, 223));
            g.drawString("灭敌数量:" + count, 80, 160);


            //测试是否移除
//        g.setColor(Color.RED);
//        g.drawString("" + enemyBulletList.size(), 100, 100);
        } else {
            g.setFont(new Font("楷体", Font.BOLD, 50));
            g.setColor(new Color(255, 255, 255));
            g.drawString("游戏结束", 300, 300);
            g.drawString("得分:" + score, 300, 400);
        }
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

        //线程启动
        new Thread() {
            @Override
            public void run() {
                repaint();
                while (true) {
                    repaint();
                    try {
                        Thread.sleep(20);//每秒刷新
                    } catch (InterruptedException e) {
                    }
                }
            }
        }.start();


        //游戏初始时添加敌方飞机,固定
//        enemyPlaneList.add(new EnemyPlane(100, 30, 1));
//        enemyPlaneList.add(new EnemyPlane(300, 40,1));
//        enemyPlaneList.add(new EnemyPlane(600, 30, 2));

        //     propList.add(new Prop(300,40,2));


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


    //保护道具生效
    public void realizeProp() {
        if (attack == true && hp > 0) {
            hp -= 1;
            if (hp == 0) {
                gameOver = true;
            }
        }
    }
}
