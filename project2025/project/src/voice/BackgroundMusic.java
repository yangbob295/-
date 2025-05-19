package voice;

// 导入Java音频处理相关包
import javax.sound.sampled.*;
import java.io.*;

/**
 * BackgroundMusic 类用于加载和播放背景音乐，支持循环播放和启停控制。
 * 注意：仅支持WAV格式音频文件（Java原生支持）。
 */
public class BackgroundMusic {
    // Clip对象用于存储和播放音频数据
    private Clip clip;

    /**
     * 构造函数：加载音频文件并初始化播放器
     * @param filePath 音频文件路径（如"sounds/background.wav"）
     */
    public BackgroundMusic(String filePath) {
        try {
            // 1. 获取音频输入流（从文件系统加载音频）
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                    new File(filePath).getAbsoluteFile());

            // 2. 获取音频格式信息（采样率、位深、声道数等）
            AudioFormat format = audioInputStream.getFormat();

            // 3. 创建数据行信息对象，指定需要Clip类型的音频行
            DataLine.Info info = new DataLine.Info(Clip.class, format);

            // 4. 从音频系统获取可用的Clip对象
            clip = (Clip) AudioSystem.getLine(info);

            // 5. 打开Clip并加载音频数据
            clip.open(audioInputStream);

            // 6. 设置循环播放模式（无限循环）
            clip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (UnsupportedAudioFileException e) {
            System.err.println("不支持的音频格式（请使用WAV文件）: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("文件加载失败: " + e.getMessage());
        } catch (LineUnavailableException e) {
            System.err.println("音频线不可用: " + e.getMessage());
        }
    }

    /**
     * 播放音乐（从头开始）
     */
    public void play() {
        if (clip != null) {
            clip.setFramePosition(0); // 重置播放位置到起点
            clip.start();            // 开始播放
        }
    }

    /**
     * 停止音乐播放
     */
    public void stop() {
        if (clip != null) {
            clip.stop(); // 立即停止播放
        }
    }

    /**
     * 可选：释放音频资源（游戏退出时调用）
     */
    public void close() {
        if (clip != null) {
            clip.stop();
            clip.close(); // 释放系统音频资源
        }
    }
}
