package view.game;

import model.MapModel;
import view.FrameUtil;
import view.login.LoginFrame;

import javax.swing.*;
import java.awt.*;

public class MutilchoiceFrame extends JFrame {
    private int[][][] map;
    private int[][][] copy;

    private JButton backBth;
    private JButton firstBth;
    private JButton sccedBth;

    private GameFrame gameframe;
    private LoginFrame loginframe;

    public MutilchoiceFrame(int height, int width, int[][][] map){
        this.setSize(height, width);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);
        this.setTitle("关卡");

        this.map=map;


        JLabel backLabel= FrameUtil.createJLabel(this, new Point(50, 20), 70, 40, "关卡");

        //返回登录的按钮
        backBth=FrameUtil.createButton(this, "返回登录", new Point(40, 100), 100, 40);
        backBth.addActionListener(e -> {
            if (this.loginframe != null) {
                this.loginframe.setVisible(true);
                this.setVisible(false);
            }
        });

        //第一关的按钮
        firstBth=FrameUtil.createButton(this, "关卡1", new Point(40, 140), 100, 40);
        firstBth.addActionListener(e -> {
            int[][][] copy = new int[map.length][map[0].length][map[0][0].length];
            for (int i=0; i<map.length; i++){
                for (int j=0; j<map[0].length; j++){
                    for (int k=0; k<map[0][0].length; k++){
                        copy[i][j][k]=map[i][j][k];
                    }
                }
            }
            MapModel model=new MapModel(copy[0]);
            this.gameframe=new GameFrame(600, 450, model, this);
            this.gameframe.setVisible(true);
            this.setVisible(false);
        });

        sccedBth=FrameUtil.createButton(this, "关卡2", new Point(40, 180), 100, 40);
        sccedBth.addActionListener(e -> {
            int[][][] copy = new int[map.length][map[0].length][map[0][0].length];
            for (int i=0; i<map.length; i++){
                for (int j=0; j<map[0].length; j++){
                    for (int k=0; k<map[0][0].length; k++){
                        copy[i][j][k]=map[i][j][k];
                    }
                }
            }
            MapModel model=new MapModel(copy[1]);
            this.gameframe=new GameFrame(600, 450, model, this);
            this.gameframe.setVisible(true);
            this.setVisible(false);
        });
    }

    public void setLoginframe(LoginFrame loginframe){
        this.loginframe=loginframe;
    }

    public GameFrame getGameframe() {
        return gameframe;
    }
}
