package view.game;

import view.FrameUtil;
import voice.BackgroundMusic;
import Image.ImageLoader;

import javax.swing.*;
import java.awt.*;

public class LoserFrame extends JFrame {
    private JButton backBth;
    private MutilchoiceFrame mutilchoiceFrame;
    private BackgroundMusic backgroundMusic;

    public LoserFrame(int height, int width) {
        // 窗口初始化（保持不变）
        this.setSize(height, width);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null); // 使用绝对布局
        this.setTitle("败者食尘");

        // 加载图片（关键修改部分）
        Image image = ImageLoader.loadImage("/view/game/picture/曹操战败.jpg");
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

        // 文字标签（置于图片上层）
        JLabel label = new JLabel("孩子们，扣1复活，佛祖陪你笑");
        label.setForeground(Color.BLACK); // 白色文字确保可见
        label.setFont(new Font("微软雅黑", Font.BOLD, 16));
        label.setBounds(50, 50, 300, 50);
        this.add(label);

        // 按钮（置于图片上层）
        backBth = FrameUtil.createButton(this, "孩子们，扣1复活，佛祖陪你笑", new Point(215, 500), 220, 40);
        backBth.addActionListener(e -> handleButtonClick());

        // 音乐初始化
        this.backgroundMusic = new BackgroundMusic("resource/sound/See-You-Again2.wav");
        this.setVisible(true);
    }

    private void handleButtonClick() {
        if (this.mutilchoiceFrame != null) {
            this.mutilchoiceFrame.setVisible(true);
            this.backgroundMusic.stop();
            this.mutilchoiceFrame.getLoginframe().getBackgroundMusic().play();
            this.setVisible(false);
        }
    }

    public void setMutilchoiceFrame(MutilchoiceFrame mutilchoiceFrame){
        this.mutilchoiceFrame=mutilchoiceFrame;
    }
}
