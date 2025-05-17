package view.game;

import controller.GameController;
import model.MapModel;
import user.User;
import view.FrameUtil;

import javax.swing.*;
import java.awt.*;
//过了登陆界面后的那个面板
public class GameFrame extends JFrame {

    private GameController controller;

    private JButton restartBtn;
    private JButton loadBtn;
    private JButton saveBtn;
    private JButton backBth;
    private JLabel stepLabel;
    private User user;

    private GamePanel gamePanel;

    private MutilchoiceFrame mutilchoiceFrame;


    public GameFrame(int width, int height, MapModel mapModel, MutilchoiceFrame mutilchoiceFrame) {
        this.setTitle("平静的海面培养不出优秀的水手");
        this.setLayout(null);
        this.setSize(width, height);
        this.mutilchoiceFrame=mutilchoiceFrame;

        gamePanel = new GamePanel(mapModel);
        gamePanel.setLocation(30, height / 2 - gamePanel.getHeight() / 2);
        //此处添加了location修改，初始为30
        gamePanel.setMutilchoiceFrame(this.mutilchoiceFrame);
        this.add(gamePanel);

        this.controller = new GameController(gamePanel, mapModel);

        this.restartBtn = FrameUtil.createButton(this, "Restart", new Point(gamePanel.getWidth() + 80, 120), 80, 50);
        this.loadBtn = FrameUtil.createButton(this, "Load", new Point(gamePanel.getWidth() + 80, 210), 80, 50);
        this.saveBtn = FrameUtil.createButton(this, "Save", new Point(gamePanel.getWidth() + 80, 300), 80, 50);
        this.backBth=FrameUtil.createButton(this, "返回", new Point(gamePanel.getWidth() + 180, 300), 80, 50);
        this.stepLabel = FrameUtil.createJLabel(this, "Start", new Font("serif", Font.ITALIC, 22), new Point(gamePanel.getWidth() + 80, 70), 180, 50);
        gamePanel.setStepLabel(stepLabel);

        this.restartBtn.addActionListener(e -> {
            controller.restartGame();
            gamePanel.requestFocusInWindow();//enable key listener
        });
        this.loadBtn.addActionListener(e -> {
            String string = JOptionPane.showInputDialog(this, "Input path:");
            System.out.println(string);
            gamePanel.requestFocusInWindow();//enable key listener
        });
        //todo: add other button here

        this.backBth.addActionListener(e -> {
            if (this.mutilchoiceFrame != null) {
                this.mutilchoiceFrame.setVisible(true);
                this.setVisible(false);
            }
        });

        this.saveBtn.addActionListener(e -> {

            gamePanel.requestFocusInWindow();//enable key listener
        });


        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void setMutilchoiceFrame(MutilchoiceFrame mutilchoiceFrame){

    }



}
