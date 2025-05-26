package view.game;

import model.MapModel;
import user.User;
import view.FrameUtil;
import view.login.LoginFrame;
import voice.BackgroundMusic;
import Image.ImageLoader;

import javax.swing.*;
import java.awt.*;

public class MutilchoiceFrame extends JFrame {
    private int[][][] map;
    private int[][][] copy;
    private String path = null;

    private User currentUser; // 新增用户字段

    private JButton backBth;
    private JButton firstBth;
    private JButton secondBth;
    private JButton thirdBth;
    private JButton fourthBth;
    private JButton fifthBth;
    private JButton sixthBth;
    private JButton seventhBtn;
    private JButton eighthBtn;
    private JButton ninthBtn;
    private JButton tenthBth;

    private GameFrame gameframe;
    private LoginFrame loginframe;



    public MutilchoiceFrame(int height, int width, int[][][] map) {
        this.setSize(height, width);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);
        this.setTitle("关卡");

        this.map = map;


        JLabel backLabel = FrameUtil.createJLabel(this, new Point(300, 10), 70, 40, "关卡选择",Color.BLACK);

        //返回登录的按钮
        backBth = FrameUtil.createButton(this, "返回登录", new Point(275, 40), 100, 40);
        backBth.addActionListener(e -> {
            if (this.loginframe != null) {
                this.loginframe.setVisible(true);
                this.setVisible(false);
            }
        });


        // 第1关
        firstBth = FrameUtil.createButton(this, "横刀立马", new Point(150, 100), 90, 90);
        firstBth.addActionListener(e -> {
            int[][][] copy = new int[map.length][][];
            for (int i = 0; i < map.length; i++) {
                copy[i] = new int[map[i].length][];
                for (int j = 0; j < map[i].length; j++) {
                    copy[i][j] = map[i][j].clone();
                }
            }
            MapModel model = new MapModel(copy[0]);
            GameFrame gameFrame = new GameFrame(800, 500, model, this);
            gameFrame.setUser(this.currentUser);
            gameFrame.getGamePanel().setBackgroundMusic(new BackgroundMusic("resource\\sound\\森林.wav"));
            this.gameframe = gameFrame;
            this.loginframe.getBackgroundMusic().stop();
            this.setVisible(false);
            gameFrame.setVisible(true);
            path = "横刀立马";
        });

        // 第2关
        secondBth = FrameUtil.createButton(this, "指挥若定", new Point(280, 100), 90, 90);
        secondBth.addActionListener(e -> {
            int[][][] copy = new int[map.length][][];
            for (int i = 0; i < map.length; i++) {
                copy[i] = new int[map[i].length][];
                for (int j = 0; j < map[i].length; j++) {
                    copy[i][j] = map[i][j].clone();
                }
            }
            MapModel model = new MapModel(copy[1]);
            GameFrame gameFrame = new GameFrame(800, 500, model, this);
            gameFrame.setUser(this.currentUser);
            gameFrame.getGamePanel().setBackgroundMusic(new BackgroundMusic("resource\\sound\\森林.wav"));
            this.gameframe = gameFrame;
            this.loginframe.getBackgroundMusic().stop();
            this.setVisible(false);
            gameFrame.setVisible(true);
            path = "指挥若定";
        });

        // 第3关
        thirdBth = FrameUtil.createButton(this, "将拥曹营", new Point(410, 100), 90, 90);
        thirdBth.addActionListener(e -> {
            int[][][] copy = new int[map.length][][];
            for (int i = 0; i < map.length; i++) {
                copy[i] = new int[map[i].length][];
                for (int j = 0; j < map[i].length; j++) {
                    copy[i][j] = map[i][j].clone();
                }
            }
            MapModel model = new MapModel(copy[2]);
            GameFrame gameFrame = new GameFrame(800, 500, model, this);
            gameFrame.setUser(this.currentUser);
            gameFrame.getGamePanel().setBackgroundMusic(new BackgroundMusic("resource\\sound\\森林.wav"));
            this.gameframe = gameFrame;
            this.loginframe.getBackgroundMusic().stop();
            this.setVisible(false);
            gameFrame.setVisible(true);
            path = "将拥曹营";
        });

        // 第4关
        fourthBth = FrameUtil.createButton(this, "齐头并进", new Point(150, 230), 90, 90);
        fourthBth.addActionListener(e -> {
            int[][][] copy = new int[map.length][][];
            for (int i = 0; i < map.length; i++) {
                copy[i] = new int[map[i].length][];
                for (int j = 0; j < map[i].length; j++) {
                    copy[i][j] = map[i][j].clone();
                }
            }
            MapModel model = new MapModel(copy[3]);
            GameFrame gameFrame = new GameFrame(800, 500, model, this);
            gameFrame.setUser(this.currentUser);
            gameFrame.getGamePanel().setBackgroundMusic(new BackgroundMusic("resource\\sound\\森林.wav"));
            this.gameframe = gameFrame;
            this.loginframe.getBackgroundMusic().stop();
            this.setVisible(false);
            gameFrame.setVisible(true);
            path = "齐头并进";
        });

        // 第5关
        fifthBth = FrameUtil.createButton(this, "兵分三路", new Point(280, 230), 90, 90);
        fifthBth.addActionListener(e -> {
            int[][][] copy = new int[map.length][][];
            for (int i = 0; i < map.length; i++) {
                copy[i] = new int[map[i].length][];
                for (int j = 0; j < map[i].length; j++) {
                    copy[i][j] = map[i][j].clone();
                }
            }
            MapModel model = new MapModel(copy[4]);
            GameFrame gameFrame = new GameFrame(800, 500, model, this);
            gameFrame.setUser(this.currentUser);
            gameFrame.getGamePanel().setBackgroundMusic(new BackgroundMusic("resource\\sound\\森林.wav"));
            this.gameframe = gameFrame;
            this.loginframe.getBackgroundMusic().stop();
            this.setVisible(false);
            gameFrame.setVisible(true);
            path = "兵分三路";
        });

        // 第6关
        sixthBth = FrameUtil.createButton(this, "雨声淅沥", new Point(410, 230), 90, 90);
        sixthBth.addActionListener(e -> {
            int[][][] copy = new int[map.length][][];
            for (int i = 0; i < map.length; i++) {
                copy[i] = new int[map[i].length][];
                for (int j = 0; j < map[i].length; j++) {
                    copy[i][j] = map[i][j].clone();
                }
            }
            MapModel model = new MapModel(copy[5]);
            GameFrame gameFrame = new GameFrame(800, 500, model, this);
            gameFrame.setUser(this.currentUser);
            gameFrame.getGamePanel().setBackgroundMusic(new BackgroundMusic("resource\\sound\\森林.wav"));
            this.gameframe = gameFrame;
            this.loginframe.getBackgroundMusic().stop();
            this.setVisible(false);
            gameFrame.setVisible(true);
            path = "雨声淅沥";
        });

        // 第7关
        seventhBtn = FrameUtil.createButton(this, "限时模式", new Point(150, 360), 90, 90);
        seventhBtn.addActionListener(e -> {
            int[][][] copy = new int[map.length][][];
            for (int i = 0; i < map.length; i++) {
                copy[i] = new int[map[i].length][];
                for (int j = 0; j < map[i].length; j++) {
                    copy[i][j] = map[i][j].clone();
                }
            }
            MapModel model = new MapModel(copy[6]);
            GameFrame gameFrame = new GameFrame(800, 500, model, this);
            gameFrame.setUser(this.currentUser);
            gameFrame.getGamePanel().setBackgroundMusic(new BackgroundMusic("resource\\sound\\三周目.wav"));
//            gameFrame.getGamePanel().setSteplimit(5);
            gameFrame.getGamePanel().setTimeLimit(10);//限时
            //这两个移位置
            this.gameframe = gameFrame;
            this.loginframe.getBackgroundMusic().stop();
            this.setVisible(false);
            gameFrame.setVisible(true);
            path = "限时模式";
        });

        //第8关
        eighthBtn = FrameUtil.createButton(this, "限步模式", new Point(280, 360), 90, 90);
        eighthBtn.addActionListener(e -> {
            int[][][] copy = new int[map.length][][];
            for (int i = 0; i < map.length; i++) {
                copy[i] = new int[map[i].length][];
                for (int j = 0; j < map[i].length; j++) {
                    copy[i][j] = map[i][j].clone();
                }
            }
            MapModel model = new MapModel(copy[7]);
            GameFrame gameFrame = new GameFrame(800, 500, model, this);
            gameFrame.setUser(this.currentUser);
            gameFrame.getGamePanel().setBackgroundMusic(new BackgroundMusic("resource\\sound\\二周目.wav"));
            gameFrame.getGamePanel().setSteplimit(5);//限步数
            this.gameframe = gameFrame;
            this.loginframe.getBackgroundMusic().stop();
            this.setVisible(false);
            gameFrame.setVisible(true);
            path = "限步模式";
        });

        //第9关
        ninthBtn = FrameUtil.createButton(this, "障碍物", new Point(410, 360), 90, 90);
        ninthBtn.addActionListener(e -> {
            int[][][] copy = new int[map.length][][];
            for (int i = 0; i < map.length; i++) {
                copy[i] = new int[map[i].length][];
                for (int j = 0; j < map[i].length; j++) {
                    copy[i][j] = map[i][j].clone();
                }
            }
            MapModel model = new MapModel(copy[8]);
            GameFrame gameFrame = new GameFrame(800, 500, model, this);
            gameFrame.setUser(this.currentUser);
            gameFrame.getGamePanel().setBackgroundMusic(new BackgroundMusic("resource\\sound\\三周目.wav"));
            this.gameframe = gameFrame;
            this.loginframe.getBackgroundMusic().stop();
            this.setVisible(false);
            gameFrame.setVisible(true);
            path = "障碍物";
        });

        //第10关
        tenthBth = FrameUtil.createButton(this, "功能展示", new Point(280, 490), 90, 90);
        tenthBth.addActionListener(e -> {
            int[][][] copy = new int[map.length][][];
            for (int i = 0; i < map.length; i++) {
                copy[i] = new int[map[i].length][];
                for (int j = 0; j < map[i].length; j++) {
                    copy[i][j] = map[i][j].clone();
                }
            }
            MapModel model = new MapModel(copy[9]);
            GameFrame gameFrame = new GameFrame(800, 500, model, this);
            gameFrame.setUser(this.currentUser);
            gameFrame.getGamePanel().setBackgroundMusic(new BackgroundMusic("resource\\sound\\三周目.wav"));
            gameFrame.getGamePanel().setSteplimit(5);
            gameFrame.getGamePanel().setTimeLimit(10);//限时+限步数
            //这两个移位置
            this.gameframe = gameFrame;
            this.loginframe.getBackgroundMusic().stop();
            this.setVisible(false);
            gameFrame.setVisible(true);
            path = "功能展示关卡";
        });

        Image image = ImageLoader.loadImage("/view/game/picture/五虎.jpg");
        if (image != null) {
            // 方案1：强制缩放图片到窗口大小（简单粗暴）
            Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(scaledImage)); // 使用缩放后的图片
            imageLabel.setBounds(0, 0, width, height);
            this.add(imageLabel);
        } else {
            System.err.println("图片加载失败，使用默认背景");
            this.getContentPane().setBackground(Color.BLACK);
        }
    }

    public void setLoginframe(LoginFrame loginframe) {
        this.loginframe = loginframe;
    }
    public LoginFrame getLoginframe(){ return this.loginframe; }

    public GameFrame getGameframe(){return this.gameframe; }



    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public String getPath() {
        return path;
    }
}
