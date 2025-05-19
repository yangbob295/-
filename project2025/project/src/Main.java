import view.game.MutilchoiceFrame;
import view.login.LoginFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame(280, 280);
            loginFrame.setVisible(true);
            int[][][] map={
                            {{3, 4, 4, 7}, {3, 4, 4, 7}, {5, 0, 0, 6}, {5, 2, 2, 6}, {1, 1, 1, 1}}, {{0, 0, 0, 0}, {0, 0, 0, 1}, {0, 4, 4, 0}, {0, 4, 4, 0}, {0, 0, 0, 1}},{{3, 4, 4, 7}, {3, 4, 4, 7}, {5, 0, 0, 6}, {5, 2, 2, 6}, {1, 1, 1, 1}},{{3, 4, 4, 7}, {3, 4, 4, 7}, {5, 0, 0, 6}, {5, 2, 2, 6}, {1, 1, 1, 1}}
            };
            MutilchoiceFrame mutilchoiceFrame=new MutilchoiceFrame(600,400,map);
            mutilchoiceFrame.setVisible(false);
            mutilchoiceFrame.setLoginframe(loginFrame);
            loginFrame.setMutilchoiceFrame(mutilchoiceFrame);
        });
    }
}
