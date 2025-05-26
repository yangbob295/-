package view.game;

import Image.ImageLoader;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class BoxComponent extends JComponent {
    private Image image;  // 改为存储图片
    private int row;
    private int col;
    private boolean isSelected;

    public BoxComponent(String imagePath, int row, int col) {
        this.image = ImageLoader.loadImage(imagePath);
        this.row = row;
        this.col = col;
        isSelected = false;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 绘制图片
        if (image != null) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        } else {
            // 图片加载失败时的备用方案
            g.setColor(Color.GRAY);
            g.fillRect(0, 0, getWidth(), getHeight());
        }

        // 设置边框（保持原有选中状态效果）
        Border border;
        if(isSelected) {
            border = BorderFactory.createLineBorder(Color.red, 3);
        } else {
            border = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);
        }
        this.setBorder(border);
    }

    // 可以添加设置图片的方法
    public void setImage(String imagePath) {
        this.image = ImageLoader.loadImage(imagePath);
        this.repaint();
    }

    // 其他原有方法保持不变...
    public void setSelected(boolean selected) {
        isSelected = selected;
        this.repaint();
    }

    public void animateMoveTo(int targetX, int targetY) {
        int delay = 10;
        int duration = 300;
        int steps = Math.max(2, duration / delay);

        Point start = getLocation();
        final double startX = start.x;
        final double startY = start.y;
        final double dx = targetX - startX;
        final double dy = targetY - startY;

        final int[] count = {0};

        // ✅ 设置高亮边框，提示用户正在移动
        setBorder(BorderFactory.createLineBorder(Color.ORANGE, 2));

        Timer timer = new Timer(delay, null);
        timer.addActionListener(e -> {
            if (count[0] <= steps) {
                double t = (double) count[0] / steps;

                // ✅ 使用缓动函数（easeOut）: 快到慢
                double easedT = t * (2 - t);

                int newX = (int) (startX + dx * easedT);
                int newY = (int) (startY + dy * easedT);
                setBounds(newX, newY, getWidth(), getHeight());

                Container parent = getParent();
                if (parent != null) {
                    parent.repaint();
                }

                count[0]++;
                repaint();
            } else {
                setBounds(targetX, targetY, getWidth(), getHeight());

                // ✅ 动画结束后恢复普通边框
                setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
                ((Timer) e.getSource()).stop();
                repaint();
            }
        });

        timer.start();
    }








    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
