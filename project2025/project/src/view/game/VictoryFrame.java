package view.game;

import javax.swing.*;

public class VictoryFrame extends JFrame {
    public VictoryFrame(int width, int height) {
        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);
        this.setTitle("Victory");

        JLabel label = new JLabel("You Win!");
        label.setBounds(50, 50, 200, 50);
        this.add(label);
        this.setVisible(true);
    }
}
