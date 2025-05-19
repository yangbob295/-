package view.game;

import view.FrameUtil;
import voice.BackgroundMan;
import voice.BackgroundMusic;

import javax.swing.*;
import java.awt.*;

public class VictoryFrame extends JFrame {
    private JButton backBth;

    private MutilchoiceFrame mutilchoiceFrame;
    private BackgroundMusic backgroundMusic;


    public VictoryFrame(int height, int width) {
        this.setSize(height, width);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);
        this.setTitle("胜利");

//        this.backgroundMusic=new BackgroundMusic("C:\\Users\\ASUS\\IdeaProjects\\Project2.0\\resource\\sound\\pirate.wav");
        this.backgroundMusic = new BackgroundMusic("resource/sound/pirate.wav");

        JLabel label = new JLabel("孩子们，三段超长绝美蠕动，如何呢？");
        label.setBounds(50, 50, 220, 50);
        this.add(label);
        this.setVisible(true);

        //加入一个返回键，点击返回键就回到多选界面
        backBth = FrameUtil.createButton(this, "返回", new Point(40, 140), 100, 40);
        backBth.addActionListener(e -> {
            if (this.mutilchoiceFrame != null) {
                this.mutilchoiceFrame.setVisible(true);
                this.backgroundMusic.stop();
                this.mutilchoiceFrame.getLoginframe().getBackgroundMusic().play();
                this.setVisible(false);
            }
        });

    }

    public void setMutilchoiceFrame(MutilchoiceFrame mutilchoiceFrame){
        this.mutilchoiceFrame=mutilchoiceFrame;
    }

}