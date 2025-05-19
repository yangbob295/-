package view.game;

import controller.GameController;
import model.Direction;
import model.MapModel;
import time.GameTimer;
import voice.BackgroundMan;
import voice.BackgroundMusic;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * It is the subclass of ListenerPanel, so that it should implement those four methods: do move left, up, down ,right.
 * The class contains a grids, which is the corresponding GUI view of the matrix variable in MapMatrix.
 */
//专指方格盘
public class GamePanel extends ListenerPanel {
    private List<BoxComponent> boxes;
    private MapModel model;
    private GameController controller;
    private JLabel stepLabel;
    private JLabel timeLabel;
    private int steps;
    private int steplimit=0;
    private int timeLimit=0;
    private final int GRID_SIZE = 50;
    private BoxComponent selectedBox;

    private MutilchoiceFrame mutilchoiceFrame;

    private BackgroundMusic backgroundMusic;

    private GameTimer gameTimer;



    public GamePanel(MapModel model) {
        boxes = new ArrayList<>();
        this.setVisible(true);
        this.setFocusable(true);
        this.setLayout(null);
        this.setSize(model.getWidth() * GRID_SIZE + 4, model.getHeight() * GRID_SIZE + 4);
        this.model = model;
        this.selectedBox = null;
        gameTimer = new GameTimer(seconds -> {
            timeLabel.setText(String.format("时间：%ds", seconds));

            if(this.timeLimit!=0){
                if(seconds > this.timeLimit) {
                    SwingUtilities.invokeLater(() -> {
                        Window parentWindow = SwingUtilities.getWindowAncestor(this);
                        if (parentWindow != null) parentWindow.setVisible(false);

                        LoserFrame loserFrame = new LoserFrame(600, 450);
                        loserFrame.setMutilchoiceFrame(mutilchoiceFrame);
                        loserFrame.setVisible(true);

                        this.backgroundMusic.stop();
                        this.gameTimer.stop();
                    });
                }
            }
        });
        this.gameTimer.start();
        initialGame(model.getMatrix(),0,0);
    }

    /*
                        {1, 2, 2, 1, 1},
                        {3, 4, 4, 2, 2},
                        {3, 4, 4, 1, 0},
                        {1, 2, 2, 1, 0},
                        {1, 1, 1, 1, 1}
     */
    //初始化游戏
    public void initialGame(int[][] matrix, int step, int seconds) {
        this.steps = step;
        this.gameTimer.setSeconds(seconds);
        if (this.stepLabel != null) {
            this.stepLabel.setText(String.format("步数: %d", this.steps));
        }
        if(this.timeLabel !=null){
            this.timeLabel.setText(String.format("时间：%d",this.gameTimer.getSeconds()));
        }
        //copy a map
        int[][] map = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = matrix[i][j];
            }
        }
        //build Component
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                BoxComponent box = null;
                if (map[i][j] == 1) {
                    box = new BoxComponent(Color.ORANGE, i, j);
                    box.setSize(GRID_SIZE, GRID_SIZE);
                    map[i][j] = 0;
                } else if (map[i][j] == 2) {
                    box = new BoxComponent(Color.PINK, i, j);
                    box.setSize(GRID_SIZE * 2, GRID_SIZE);
                    map[i][j] = 0;
                    map[i][j + 1] = 0;
                } else if (map[i][j] == 3) {
                    box = new BoxComponent(Color.BLUE, i, j);
                    box.setSize(GRID_SIZE, GRID_SIZE * 2);
                    map[i][j] = 0;
                    map[i + 1][j] = 0;
                } else if (map[i][j] == 4) {
                    box = new BoxComponent(Color.GREEN, i, j);
                    box.setSize(GRID_SIZE * 2, GRID_SIZE * 2);
                    map[i][j] = 0;
                    map[i + 1][j] = 0;
                    map[i][j + 1] = 0;
                    map[i + 1][j + 1] = 0;
                } else if (map[i][j] == 5) {
                    box = new BoxComponent(Color.RED, i, j);
                    box.setSize(GRID_SIZE, GRID_SIZE * 2);
                    map[i][j] = 0;
                    map[i + 1][j] = 0;
                } else if (map[i][j] == 6) {
                    box = new BoxComponent(Color.YELLOW, i, j);
                    box.setSize(GRID_SIZE, GRID_SIZE * 2);
                    map[i][j] = 0;
                    map[i + 1][j] = 0;
                } else if (map[i][j] == 7) {
                    box = new BoxComponent(Color.CYAN, i, j);
                    box.setSize(GRID_SIZE, GRID_SIZE * 2);
                    map[i][j] = 0;
                    map[i + 1][j] = 0;
                }
                if (box != null) {
                    box.setLocation(j * GRID_SIZE + 2, i * GRID_SIZE + 2);
                    boxes.add(box);
                    this.add(box);
                }
            }
        }
        this.repaint();
    }

    public void clearAllBoxFromPanel(){
        for (BoxComponent box : boxes) {
            removeBoxFromPanel(box);
        }
        this.boxes.clear();
        this.repaint();
    }

    public BoxComponent removeBoxFromPanel(BoxComponent box){
        this.remove(box);
        this.revalidate();
        return box;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        Border border = BorderFactory.createLineBorder(Color.DARK_GRAY, 2);
        this.setBorder(border);
    }

    @Override
    public void doMouseClick(Point point) {
        Component component = this.getComponentAt(point);
        if (component instanceof BoxComponent clickedComponent) {
            if (selectedBox == null) {
                selectedBox = clickedComponent;
                selectedBox.setSelected(true);
            } else if (selectedBox != clickedComponent) {
                selectedBox.setSelected(false);
                clickedComponent.setSelected(true);
                selectedBox = clickedComponent;
            } else {
                clickedComponent.setSelected(false);
                selectedBox = null;
            }
        }
    }

    @Override
    public void doMoveRight() {
        System.out.println("Click VK_RIGHT");
        if (selectedBox != null) {
            if (controller.doMove(selectedBox.getRow(), selectedBox.getCol(), Direction.RIGHT)) {
                afterMove();
            }
        }
    }

    @Override
    public void doMoveLeft() {
        System.out.println("Click VK_LEFT");
        if (selectedBox != null) {
            if (controller.doMove(selectedBox.getRow(), selectedBox.getCol(), Direction.LEFT)) {
                afterMove();
            }
        }
    }

    @Override
    public void doMoveUp() {
        System.out.println("Click VK_Up");
        if (selectedBox != null) {
            if (controller.doMove(selectedBox.getRow(), selectedBox.getCol(), Direction.UP)) {
                afterMove();
            }
        }
    }

    @Override
    public void doMoveDown() {
        System.out.println("Click VK_DOWN");
        if (selectedBox != null) {
            if (controller.doMove(selectedBox.getRow(), selectedBox.getCol(), Direction.DOWN)) {
                afterMove();
            }
        }
    }

    public void afterMove() {
        this.steps++;
        this.stepLabel.setText(String.format("Step: %d", this.steps));
        BackgroundMan backgroundMan=new BackgroundMan("resource\\sound\\man.wav");
        this.whetherVictoryOrNot();
        if(this.steplimit!=0){
            this.whetherLoserStep();
        }
    }

    public void whetherVictoryOrNot() {
        if (model.isVictory()) {
            System.out.println("You win!");
            SwingUtilities.invokeLater(() -> {
                // 关闭当前 GameFrame
                Window parentWindow = SwingUtilities.getWindowAncestor(this);
                if (parentWindow != null) {
                    parentWindow.setVisible(false);
                }
                // 显示 VictoryFrame 并关联 MutilchoiceFrame
                VictoryFrame victoryFrame = new VictoryFrame(600, 450);
                victoryFrame.setMutilchoiceFrame(mutilchoiceFrame);
                victoryFrame.setVisible(true);

                //音效
                this.backgroundMusic.stop();
                this.gameTimer.stop();
            });
        }
    }

    public void whetherLoserStep(){
        if(this.steps>this.steplimit){
            SwingUtilities.invokeLater(() -> {
                Window parentWindow = SwingUtilities.getWindowAncestor(this);
                if (parentWindow != null) {
                    parentWindow.setVisible(false);
                }

                LoserFrame loserFrame=new LoserFrame(600,450);
                loserFrame.setMutilchoiceFrame(mutilchoiceFrame);
                loserFrame.setVisible(true);

                this.backgroundMusic.stop();
                this.gameTimer.stop();
            });
        }
    }



    public void setStepLabel(JLabel stepLabel) {
        this.stepLabel = stepLabel;
    }

    public void setTimeLabel(JLabel timeLabel){
        this.timeLabel=timeLabel;
    }


    public void setController(GameController controller) {
        this.controller = controller;
    }

    public BoxComponent getSelectedBox() {
        return selectedBox;
    }

    public int getGRID_SIZE() {
        return GRID_SIZE;
    }

    public void setMutilchoiceFrame(MutilchoiceFrame mutilchoiceFrame){
        this.mutilchoiceFrame=mutilchoiceFrame;
    }

    public MutilchoiceFrame getMutilchoiceFrame() {
        return this.mutilchoiceFrame;
    }

    public void setModel(MapModel model){
        this.model=model;
    }

    public int getSteps() {
        return steps;
    }

    public void setBackgroundMusic(BackgroundMusic backgroundMusic){
        this.backgroundMusic=backgroundMusic;
    }
    public BackgroundMusic getBackgroundMusic(){
        return backgroundMusic;
    }

    public void setSteplimit(int steplimit){ this.steplimit=steplimit; }
    public int getSteplimit(){return steplimit; }

    public void setTimeLimit(int timeLimit){this.timeLimit=timeLimit; }
    public int getTimeLimit(){return timeLimit; }

    public void setGameTimer(GameTimer gameTimer){this.gameTimer=gameTimer; }
    public GameTimer getGameTimer(){return this.gameTimer; }
}
