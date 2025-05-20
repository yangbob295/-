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
