package view.login;

import user.User;
import view.FrameUtil;
import view.game.GameFrame;
import view.game.MutilchoiceFrame;
import voice.BackgroundMusic;
import Image.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class LoginFrame extends JFrame {
    private JTextField username;
    private JTextField password;
    private JButton submitBtn;
    private JButton resetBtn;

    private MutilchoiceFrame mutilchoiceFrame;

    private BackgroundMusic backgroundMusic;


    public LoginFrame(int width, int height) {
        this.setTitle("梦开始的地方");
        this.setLayout(null);
        this.setSize(width, height);

        this.backgroundMusic=new BackgroundMusic("resource\\sound\\zheng3.wav");
        JLabel userLabel = FrameUtil.createJLabel(this, new Point(50, 20), 70, 40, "用户名:",Color.WHITE);
        JLabel passLabel = FrameUtil.createJLabel(this, new Point(50, 80), 70, 40, "密码:",Color.WHITE);
        username = FrameUtil.createJTextField(this, new Point(120, 20), 120, 40);
        password = FrameUtil.createJTextField(this, new Point(120, 80), 120, 40);

        submitBtn = FrameUtil.createButton(this, "登录/注册", new Point(40, 140), 100, 40);
        resetBtn = FrameUtil.createButton(this, "清空", new Point(160, 140), 100, 40);

        //导入背景图
        // 加载图片（关键修改部分）

        Image image = ImageLoader.loadImage("/view/game/picture/CaoCao.png");
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

        // 验证用户密码
        submitBtn.addActionListener(e -> {
            String usernameText = username.getText().trim();
            String passwordText = password.getText().trim();

            //有空位也不return，设置为游客模式（可以提前预设一个游客的user？）×

            User currentUser = new User(usernameText, passwordText);
            if (isValidUser(currentUser)) {

                // 设置游客模式
                if (usernameText.isEmpty()) {
                    currentUser.setUserName("Guest");  // 设置默认游客名称
                    currentUser.setPassword("");      // 不设置密码--》注意：不会保存
                }

                mutilchoiceFrame.setCurrentUser(currentUser);
                mutilchoiceFrame.setVisible(true);
                this.setVisible(false);
                //如果验证成功，设置当前用户并显示多选框
                username.setText("");
                password.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "用户名或密码错误！注意用户名不能为Guest！");
            }
        });

        resetBtn.addActionListener(e -> {
            username.setText("");
            password.setText("");
        });

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }



    private boolean isValidUser(User user) {
        String userName = user.getUserName();
        String password = user.getPassword();

        // 检查名字是否为空-->游客模式直接通过验证
        if (userName.isEmpty()) {
            return true;
        }

        if(userName.equals("Guest")){
            return false;
        }

        File userDir = new File("saves" + File.separator + userName);
        //创建一个表示用户目录的 File 对象，路径为 saves/用户名。
        File passwordFile = new File(userDir, ".password");
        //在该用户目录下，创建一个表示 .password 文件的 File 对象，用于存储或读取该用户的密码。

        try {
            if (userDir.exists()) {
                // 已有用户-->检查现有用户密码

                try (Scanner scanner = new Scanner(passwordFile)) {//读取文件中的密码
                    String storedPassword = scanner.nextLine();
                    return storedPassword.equals(password);
                    // 比较该用户密码
                }
            } else {
                // 无用户-->创建新用户并保存密码
                if (password.isEmpty()) return false;  // 注册用户需要密码
                if (!userDir.mkdirs()) return false;
                try (PrintWriter out = new PrintWriter(passwordFile)) {
                    out.println(password); // 写入密码
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
            //用来捕获和处理文件操作时可能出现的输入输出异常。
            //如果不写这段代码，遇到异常时程序会直接崩溃，无法优雅地提示用户或返回登录失败。
        }
    }

    public void setMutilchoiceFrame(MutilchoiceFrame mutilchoiceFrame){
        this.mutilchoiceFrame=mutilchoiceFrame;
    }

    public void setBackgroundMusic(BackgroundMusic backgroundMusic){
        this.backgroundMusic=backgroundMusic;
    }
    public BackgroundMusic getBackgroundMusic(){
        return backgroundMusic;
    }

}
