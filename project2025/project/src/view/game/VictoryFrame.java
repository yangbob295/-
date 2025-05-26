package view.game;

import view.FrameUtil;
import voice.BackgroundMan;
import voice.BackgroundMusic;

import Image.ImageLoader;

import javax.swing.*;
import java.awt.*;

public class VictoryFrame extends JFrame {
    private JButton backBth;

    private MutilchoiceFrame mutilchoiceFrame;
    private BackgroundMusic backgroundMusic;


    public VictoryFrame(int height, int width, int step) {
        this.setSize(height, width);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);
        this.setTitle("胜利");

//        this.backgroundMusic=new BackgroundMusic("C:\\Users\\ASUS\\IdeaProjects\\Project2.0\\resource\\sound\\pirate.wav");
        this.backgroundMusic = new BackgroundMusic("resource/sound/pirate.wav");

        JLabel label = new JLabel("孩子们，三段超长绝美蠕动，如何呢？");
        label.setBounds(50, 50, 600, 50);
        label.setFont(new Font("微软雅黑", Font.BOLD, 30));
//        label.setForeground(Color.WHITE);
        this.add(label);

        JLabel labelstep = new JLabel(String.format("获胜步数：%d", step));
        labelstep.setBounds(205, 100, 200, 50);
        labelstep.setFont(new Font("微软雅黑", Font.BOLD, 30));
//        labelstep.setForeground(Color.YELLOW);
        this.add(labelstep);

        // 加载图片（关键修改部分）
        Image image = ImageLoader.loadImage("/view/game/picture/曹操胜利.jpg");
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




//        JLabel label = new JLabel("孩子们，三段超长绝美蠕动，如何呢？");
//        label.setBounds(50, 50, 220, 50);
//        JLabel labelstep = new JLabel(String.format("获胜步数：%d",step));
//        label.setBounds(205, 100, 220, 50);
//        label.setFont(new Font("微软雅黑", Font.BOLD, 30));

        this.setVisible(true);

        //加入一个返回键，点击返回键就回到多选界面
        backBth = FrameUtil.createButton(this, "再次开启伟大的航路", new Point(230, 280), 180, 40);
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