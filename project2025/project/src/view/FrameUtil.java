package view;

import javax.swing.*;
import java.awt.*;

/**
 * 该工具类提供静态方法来创建和配置基本的Swing组件（JLabel、JTextField、JButton），
 * 并设置它们的属性，然后将它们添加到指定的JFrame容器中。
 */
public class FrameUtil {

    /**
     * 创建一个带有指定文本、大小和位置的JLabel，并将其添加到给定的JFrame中。
     *
     * @param frame    要添加标签的JFrame容器
     * @param location 指定标签位置的(x,y)坐标点
     * @param width    标签的宽度
     * @param height   标签的高度
     * @param text     要在标签上显示的文本
     * @return 创建的JLabel实例
     */
    public static JLabel createJLabel(JFrame frame, Point location, int width, int height,
                                      String text, Color textColor) {
        JLabel jLabel = new JLabel(text);
        jLabel.setSize(width, height);
        jLabel.setLocation(location);

        // 设置文本颜色（如果参数不为null）
        if (textColor != null) {
            jLabel.setForeground(textColor);
        }


        frame.add(jLabel);
        return jLabel;
    }

    /**
     * 创建一个带有指定名称、字体、大小和位置的JLabel，并将其添加到给定的JFrame中。
     *
     * @param frame    要添加标签的JFrame容器
     * @param name     要在标签上显示的文本
     * @param font     要应用于标签文本的字体
     * @param location 指定标签位置的(x,y)坐标点
     * @param width    标签的宽度
     * @param height   标签的高度
     * @return 创建的JLabel实例
     */
    public static JLabel createJLabel(JFrame frame, String name, Font font, Point location, int width, int height) {
        JLabel label = new JLabel(name);   // 创建带有指定名称/文本的新JLabel
        label.setFont(font);               // 设置标签文本的字体
        label.setLocation(location);       // 设置标签的位置
        label.setSize(width, height);      // 设置标签的尺寸
        frame.add(label);                  // 将标签添加到框架中
        return label;                      // 返回创建的标签，以便后续可能的配置
    }

    /**
     * 创建一个默认大小和位置的JTextField，然后将其添加到给定的JFrame中。
     *
     * @param frame    要添加文本字段的JFrame容器
     * @param location 指定文本字段位置的(x,y)坐标点
     * @param width    文本字段的宽度
     * @param height   文本字段的高度
     * @return 创建的JTextField实例
     */
    public static JTextField createJTextField(JFrame frame, Point location, int width, int height) {
        JTextField jTextField = new JTextField();  // 创建一个新的JTextField（默认为空）
        jTextField.setSize(width, height);        // 设置文本字段的尺寸
        jTextField.setLocation(location);         // 设置文本字段的位置
        frame.add(jTextField);                    // 将文本字段添加到框架中
        return jTextField;                        // 返回创建的文本字段，以便后续可能的配置
    }

    /**
     * 创建一个带有指定名称、大小和位置的JButton，并将其添加到给定的JFrame中。
     *
     * @param frame    要添加按钮的JFrame容器
     * @param name     要在按钮上显示的文本
     * @param location 指定按钮位置的(x,y)坐标点
     * @param width    按钮的宽度
     * @param height   按钮的高度
     * @return 创建的JButton实例
     */
    public static JButton createButton(JFrame frame, String name, Point location, int width, int height) {
        JButton button = new JButton(name);  // 创建带有指定名称的新JButton
        button.setLocation(location);       // 设置按钮的位置
        button.setSize(width, height);      // 设置按钮的尺寸
        frame.add(button);                  // 将按钮添加到框架中
        return button;                      // 返回创建的按钮，以便后续可能的配置
    }
}