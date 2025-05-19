package view.game;

import controller.GameController;
import controller.SaveController;
import model.MapModel;
import time.GameTimer;
import user.User;
import view.FrameUtil;
import voice.BackgroundMusic;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

//过了登陆界面后的那个面板
public class GameFrame extends JFrame {

    private GameController controller;

    private JButton restartBtn;
    private JButton loadBtn;
    private JButton saveBtn;
    private JButton backBth;
    private JLabel stepLabel;
    private JLabel timeLabel;

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

        this.restartBtn = FrameUtil.createButton(this, "重振旗鼓", new Point(gamePanel.getWidth() + 80, 120), 100, 50);
        this.loadBtn = FrameUtil.createButton(this, "朝花夕拾", new Point(gamePanel.getWidth() + 80, 210), 100, 50);
        this.saveBtn = FrameUtil.createButton(this, "鸣金收兵", new Point(gamePanel.getWidth() + 80, 300), 100, 50);
        this.backBth=FrameUtil.createButton(this, "返回", new Point(gamePanel.getWidth() + 180, 300), 100, 50);
        this.stepLabel = FrameUtil.createJLabel(this, "你撞上了海带", new Font("serif", Font.ITALIC, 22), new Point(gamePanel.getWidth() + 80, 70), 180, 50);
        this.timeLabel=FrameUtil.createJLabel(this, "时间", new Font("serif", Font.ITALIC, 22), new Point(gamePanel.getWidth() + 270, 70), 180, 50);
        gamePanel.setStepLabel(stepLabel);
        gamePanel.setTimeLabel(timeLabel);


        this.restartBtn.addActionListener(e -> {
            controller.restartGame();
            gamePanel.requestFocusInWindow();//enable key listener
        });

        //todo: add other button here

        this.backBth.addActionListener(e -> {
            if (this.mutilchoiceFrame != null) {
                this.mutilchoiceFrame.setVisible(true);
                this.setVisible(false);

                this.gamePanel.getBackgroundMusic().stop();
                this.mutilchoiceFrame.getLoginframe().getBackgroundMusic().play();

                this.gamePanel.getGameTimer().stop();
            }
        });


        // 保存按钮
        this.saveBtn.addActionListener(e -> {
            gamePanel.getGameTimer().stop();
            if (user.getUserName() == "Guest") {
                JOptionPane.showMessageDialog(this, "请先登录！");
                return;
            }

            String saveName = JOptionPane.showInputDialog(this, "输入存档名称:");
            if (saveName != null && !saveName.isEmpty()) {
                try {
                    String path = user.getSavePath(saveName);

                    SaveController.saveGame(controller.getModel(), path, gamePanel.getSteps(), gamePanel.getGameTimer().getSeconds());

                    //更改：输入了时间

                    JOptionPane.showMessageDialog(this, "保存成功!");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "保存失败: " + ex.getMessage());
                }
            }
            gamePanel.getGameTimer().start();
            gamePanel.requestFocusInWindow();
        });

        // 加载按钮
        this.loadBtn.addActionListener(e -> {
            gamePanel.getGameTimer().stop();
            String saveName = JOptionPane.showInputDialog(this, "输入要加载的存档名称:");
            if (user == null) {
                JOptionPane.showMessageDialog(this, "请先登录！");
                return;
            }
            if (saveName != null && !saveName.isEmpty()) {
                try {
                    String path = user.getSavePath(saveName);
                    int[][] matrix = SaveController.loadGame(path).getMatrix();
                    int loadStep = SaveController.loadGame(path).getStep();
                    int loadTime = SaveController.loadGame(path).getStep();

                    //更改：输入了step和password
                    controller.loadGame(new MapModel(matrix), loadStep, loadTime);
                    JOptionPane.showMessageDialog(this, "加载成功!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "加载失败: " + ex.getMessage());
                }
            }
            gamePanel.getGameTimer().start();
            gamePanel.requestFocusInWindow();
        });


        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void setMutilchoiceFrame(MutilchoiceFrame mutilchoiceFrame) {
        this.mutilchoiceFrame = mutilchoiceFrame;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public User getUser() {
        return user;
    }

    public GamePanel getGamePanel(){ return gamePanel; }

}
