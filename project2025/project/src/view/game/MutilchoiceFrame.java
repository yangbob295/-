package view.game;

import model.MapModel;
import user.User;
import view.FrameUtil;
import view.login.LoginFrame;
import voice.BackgroundMusic;

import javax.swing.*;
import java.awt.*;

public class MutilchoiceFrame extends JFrame {
    private int[][][] map;
    private int[][][] copy;

    private User currentUser; // 新增用户字段

    private JButton backBth;
    private JButton firstBth;
    private JButton secondBtn;
    private JButton thirdBth;
    private JButton forthBth;

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


        JLabel backLabel = FrameUtil.createJLabel(this, new Point(280, 20), 70, 40, "关卡");

        //返回登录的按钮
        backBth = FrameUtil.createButton(this, "返回登录", new Point(250, 100), 100, 40);
        backBth.addActionListener(e -> {
            if (this.loginframe != null) {
                this.loginframe.setVisible(true);
                this.setVisible(false);
            }
        });


        // 第一关按钮
        firstBth = FrameUtil.createButton(this, "关卡1", new Point(250, 140), 100, 40);
        firstBth.addActionListener(e -> {
            int[][][] copy = new int[map.length][][];
            for (int i = 0; i < map.length; i++) {
                copy[i] = new int[map[i].length][];
                for (int j = 0; j < map[i].length; j++) {
                    copy[i][j] = map[i][j].clone();
                }
            }
            MapModel model = new MapModel(copy[0]);
            GameFrame gameFrame = new GameFrame(600, 450, model, this);
            gameFrame.setUser(this.currentUser);
            gameFrame.getGamePanel().setBackgroundMusic(new BackgroundMusic("resource\\sound\\森林.wav"));
            this.gameframe = gameFrame;
            this.loginframe.getBackgroundMusic().stop();
            this.setVisible(false);
            gameFrame.setVisible(true);
        });

        // 第二关按钮（修正变量名拼写为 secondBtn）
        secondBtn = FrameUtil.createButton(this, "关卡2", new Point(250, 180), 100, 40);
        secondBtn.addActionListener(e -> {
            int[][][] copy = new int[map.length][][];
            for (int i = 0; i < map.length; i++) {
                copy[i] = new int[map[i].length][];
                for (int j = 0; j < map[i].length; j++) {
                    copy[i][j] = map[i][j].clone();
                }
            }
            MapModel model = new MapModel(copy[1]);
            GameFrame gameFrame = new GameFrame(600, 450, model, this);
            gameFrame.setUser(this.currentUser);
            gameFrame.getGamePanel().setBackgroundMusic(new BackgroundMusic("resource\\sound\\一周目.wav"));
            gameFrame.getGamePanel().setSteplimit(20);
            gameFrame.getGamePanel().setTimeLimit(50);
            this.gameframe = gameFrame;
            this.loginframe.getBackgroundMusic().stop();
            this.setVisible(false);
            gameFrame.setVisible(true);
        });

        //第三关
        thirdBth = FrameUtil.createButton(this, "关卡3", new Point(250, 220), 100, 40);
        thirdBth.addActionListener(e -> {
            int[][][] copy = new int[map.length][][];
            for (int i = 0; i < map.length; i++) {
                copy[i] = new int[map[i].length][];
                for (int j = 0; j < map[i].length; j++) {
                    copy[i][j] = map[i][j].clone();
                }
            }
            MapModel model = new MapModel(copy[2]);
            GameFrame gameFrame = new GameFrame(600, 450, model, this);
            gameFrame.setUser(this.currentUser);
            gameFrame.getGamePanel().setBackgroundMusic(new BackgroundMusic("resource\\sound\\二周目.wav"));
            this.gameframe = gameFrame;
            this.loginframe.getBackgroundMusic().stop();
            this.setVisible(false);
            gameFrame.setVisible(true);
        });

        //第四关
        forthBth = FrameUtil.createButton(this, "关卡4", new Point(250, 260), 100, 40);
        forthBth.addActionListener(e -> {
            int[][][] copy = new int[map.length][][];
            for (int i = 0; i < map.length; i++) {
                copy[i] = new int[map[i].length][];
                for (int j = 0; j < map[i].length; j++) {
                    copy[i][j] = map[i][j].clone();
                }
            }
            MapModel model = new MapModel(copy[3]);
            GameFrame gameFrame = new GameFrame(600, 450, model, this);
            gameFrame.setUser(this.currentUser);
            gameFrame.getGamePanel().setBackgroundMusic(new BackgroundMusic("resource\\sound\\三周目.wav"));
            this.gameframe = gameFrame;
            this.loginframe.getBackgroundMusic().stop();
            this.setVisible(false);
            gameFrame.setVisible(true);
        });
    }

    public void setLoginframe(LoginFrame loginframe) {
        this.loginframe = loginframe;
    }
    public LoginFrame getLoginframe(){ return this.loginframe; }


    public void setCurrentUser(User user) {
        this.currentUser = user;
    }


}
