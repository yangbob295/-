package view.game;

import view.FrameUtil;

import javax.swing.*;
import java.awt.*;

public class VictoryFrame extends JFrame {
    private JButton backBth;

    private MutilchoiceFrame mutilchoiceFrame;


    public VictoryFrame(int height, int width) {
        this.setSize(height, width);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);
        this.setTitle("Victory");

        JLabel label = new JLabel("You Win!");
        label.setBounds(50, 50, 200, 50);
        this.add(label);
        this.setVisible(true);

        //加入一个返回键，点击返回键就回到多选界面
        backBth = FrameUtil.createButton(this, "返回", new Point(40, 140), 100, 40);
        backBth.addActionListener(e -> {
            if (this.mutilchoiceFrame != null) {
                this.mutilchoiceFrame.setVisible(true);
                this.setVisible(false);
            }
        });

    }

    public void setMutilchoiceFrame(MutilchoiceFrame mutilchoiceFrame){
        this.mutilchoiceFrame=mutilchoiceFrame;
    }

}