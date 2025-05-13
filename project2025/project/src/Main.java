import model.MapModel;
import view.game.GameFrame;
import view.game.VictoryFrame;
import view.login.LoginFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame(280, 280);
            loginFrame.setVisible(true);
            MapModel mapModel = new MapModel(new int[][]{
//                    {3, 4, 4, 7},
//                    {3, 4, 4, 7},
//                    {5, 0, 0, 6},
//                    {5, 2, 2, 6},
//                    {1, 1, 1, 1}

//                    {0, 0, 0, 0},
//                    {0, 0, 0, 0},
//                    {0, 0, 0, 0},
//                    {0, 2, 2, 0},
//                    {1, 1, 1, 1}

//                    {0, 0, 0, 0},
//                    {0, 0, 0, 0},
//                    {0, 3, 0, 0},
//                    {0, 3, 0, 0},
//                    {1, 1, 1, 1}

                    {0, 0, 0, 0},
                    {0, 0, 0, 1},
                    {0, 4, 4, 0},
                    {0, 4, 4, 0},
                    {0, 0, 0, 1}

            });
            GameFrame gameFrame = new GameFrame(600, 450, mapModel);
            gameFrame.setVisible(false);
            loginFrame.setGameFrame(gameFrame);

        });
    }
}
