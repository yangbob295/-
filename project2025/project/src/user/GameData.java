package user;
import java.io.Serializable;//序列化-->blog上看到的，问AI教的，让Gamedata类型可以具体实现序列化

public class GameData implements Serializable {
    private final int[][] matrix;
    private final int step;
    private final int time;

    public GameData(int[][] matrix, int step, int time) {
        this.matrix = matrix;
        this.step = step;
        this.time = time;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public int getStep() {
        return step;
    }
}