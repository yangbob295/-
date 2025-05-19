package view.game;

import view.FrameUtil;
import voice.BackgroundMusic;

import javax.swing.*;
import java.awt.*;

public class LoserFrame extends JFrame {
    private JButton backBth;

    private MutilchoiceFrame mutilchoiceFrame;
    private BackgroundMusic backgroundMusic;

    public LoserFrame(int height, int width){
        this.setSize(height, width);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);
        this.setTitle("败者食尘");

//        this.backgroundMusic=new BackgroundMusic("C:\\Users\\ASUS\\IdeaProjects\\Project2.0\\resource\\sound\\See-You-Again2.wav");
        this.backgroundMusic = new BackgroundMusic("resource/sound/See-You-Again2.wav");

        JLabel label = new JLabel("孩子们，扣1复活，佛祖陪你笑");
        label.setBounds(50, 50, 220, 50);
        this.add(label);
        this.setVisible(true);

        backBth = FrameUtil.createButton(this, "1……", new Point(40, 140), 100, 40);
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
