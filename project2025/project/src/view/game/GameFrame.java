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
import java.awt.event.KeyEvent;
import java.io.IOException;

//过了登陆界面后的那个面板
public class GameFrame extends JFrame {

    private GameController controller;

    private JButton restartBtn;
    private JButton loadBtn;
    private JButton saveBtn;
    private JButton backBth;
    private JButton withdrawBth;
    private JButton autoBtn;

    private JButton upBtn;
    private JButton downBtn;
    private JButton leftBtn;
    private JButton rightBtn;

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

        this.restartBtn = FrameUtil.createButton(this, "重振旗鼓", new Point(gamePanel.getWidth() + 80, 80), 100, 50);
        this.loadBtn = FrameUtil.createButton(this, "朝花夕拾", new Point(gamePanel.getWidth() + 80, 170), 100, 50);
        this.saveBtn = FrameUtil.createButton(this, "鸣金收兵", new Point(gamePanel.getWidth() + 80, 260), 100, 50);
        this.backBth=FrameUtil.createButton(this, "返回", new Point(gamePanel.getWidth() + 80, 350), 100, 50);
        this.withdrawBth=FrameUtil.createButton(this, "撤回", new Point(gamePanel.getWidth() + 270, 80), 100, 50);
        this.autoBtn = FrameUtil.createButton(this, "天命在我", new Point(gamePanel.getWidth() + 270, 350), 100, 50);

        this.upBtn=FrameUtil.createButton(this, "⇧", new Point(gamePanel.getWidth() + 290, 155), 50, 50);
        this.downBtn=FrameUtil.createButton(this, "⇩", new Point(gamePanel.getWidth() + 290, 275), 50, 50);
        this.leftBtn=FrameUtil.createButton(this, "⇦", new Point(gamePanel.getWidth() + 230, 215), 50, 50);
        this.rightBtn=FrameUtil.createButton(this, "⇨", new Point(gamePanel.getWidth() + 350, 215), 50, 50);

        //新增方向按钮


        this.rightBtn.addActionListener(e -> {
            gamePanel.doMoveRight();
            gamePanel.requestFocusInWindow();
        });
        this.leftBtn.addActionListener(e -> {
            gamePanel.doMoveLeft();
            gamePanel.requestFocusInWindow();
        });
        this.upBtn.addActionListener(e -> {
            gamePanel.doMoveUp();
            gamePanel.requestFocusInWindow();
        });
        this.downBtn.addActionListener(e -> {
            gamePanel.doMoveDown();
            gamePanel.requestFocusInWindow();
        });

        //进行方向按钮和实际操作之间的关联

        this.stepLabel = FrameUtil.createJLabel(this, "开始", new Font("serif", Font.ITALIC, 22), new Point(gamePanel.getWidth() + 80, 30), 180, 50);
        this.timeLabel=FrameUtil.createJLabel(this, "时间", new Font("serif", Font.ITALIC, 22), new Point(gamePanel.getWidth() + 270, 30), 180, 50);
        gamePanel.setStepLabel(stepLabel);
        gamePanel.setTimeLabel(timeLabel);


        this.restartBtn.addActionListener(e -> {
            controller.restartGame();
            gamePanel.requestFocusInWindow();//enable key listener
        });

        //todo: add other button here

        this.withdrawBth.addActionListener(e -> {
            if(this.gamePanel.getMapList().size()>=2){
                this.gamePanel.getMapList().remove(0);
                this.gamePanel.getStepList().remove(0);
                this.gamePanel.clearAllBoxFromPanel();
                this.controller.getModel().setMatrix(this.gamePanel.getMapList().get(0));
                this.gamePanel.initialGame(this.gamePanel.getMapList().get(0),this.gamePanel.getStepList().get(0),this.gamePanel.getGameTimer().getSeconds());
            }
            else {
                JOptionPane.showMessageDialog(this, "退无可退!");
            }
            gamePanel.requestFocusInWindow();
        });


        this.backBth.addActionListener(e -> {

            if (this.mutilchoiceFrame != null) {
                this.mutilchoiceFrame.setVisible(true);
                this.setVisible(false);

                this.gamePanel.getBackgroundMusic().stop();
                this.mutilchoiceFrame.getLoginframe().getBackgroundMusic().play();

                this.gamePanel.getGameTimer().stop();
            }

//            gamePanel.getGameTimer().stop();
            if (user.getUserName() == "Guest") {
                return;
            }
            String saveName = this.mutilchoiceFrame.getPath();
            if (saveName != null && !saveName.isEmpty()) {
                try {
                    String path = user.getSavePath(saveName);
                    SaveController.saveGame(controller.getModel(), path, gamePanel.getSteps(), gamePanel.getGameTimer().getSeconds());
                    JOptionPane.showMessageDialog(this, "已自动保存!");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "保存失败: " + ex.getMessage());
                }
            }
//            gamePanel.getGameTimer().start();
//            gamePanel.requestFocusInWindow();
//

        });

//        // 监测gameTimer的dertaSeconds
//        if (user.getUserName() != "Guest") {
//            if (gamePanel.getGameTimer().getDertaSeconds() == 29){
//                String saveName = this.mutilchoiceFrame.getPath();
//                if (saveName != null && !saveName.isEmpty()) {
//                    try {
//                        String path = user.getSavePath(saveName);
//
//                        SaveController.saveGame(controller.getModel(), path, gamePanel.getSteps(), gamePanel.getGameTimer().getSeconds());
//
//                        //更改：输入了时间
//
//                        JOptionPane.showMessageDialog(this, "保存成功!");
//                    } catch (IOException ex) {
//                        JOptionPane.showMessageDialog(this, "保存失败: " + ex.getMessage());
//                    }
//                }
//            }
//            return;
//        }


        // 保存按钮
        this.saveBtn.addActionListener(e -> {
            gamePanel.getGameTimer().stop();
            if (user.getUserName() == "Guest") {
                JOptionPane.showMessageDialog(this, "请先登录！");
                gamePanel.getGameTimer().start();
                return;
            }

//            String saveName = JOptionPane.showInputDialog(this, "输入存档名称:");
            String saveName = this.mutilchoiceFrame.getPath();
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
            if (user.getUserName() == "Guest") {
                JOptionPane.showMessageDialog(this, "请先登录！");
                gamePanel.getGameTimer().start();
                return;
            }
//            String saveName = JOptionPane.showInputDialog(this, "输入要加载的存档名称:");
            String saveName = this.mutilchoiceFrame.getPath();
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

    public GameController getController(){return controller; }

}
