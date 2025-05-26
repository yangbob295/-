package time;

import javax.swing.Timer;
import java.util.function.Consumer;

public class GameTimer {
    private Timer timer;
    private int seconds = 0;
    private Consumer<Integer> timeUpdateCallback; // 新增回调接口

    public GameTimer(Consumer<Integer> callback) {
        this.timeUpdateCallback = callback;
        timer = new Timer(1000, e -> {
            seconds++;
            if (timeUpdateCallback != null) {
                timeUpdateCallback.accept(seconds); // 触发回调
            }
            System.out.println("已运行: " + seconds + "秒");
        });
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    public void setSeconds(int seconds){
        this.seconds=seconds;
    }
    public int getSeconds() {
        return seconds;
    }


}
