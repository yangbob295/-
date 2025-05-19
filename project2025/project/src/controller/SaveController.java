package controller;

import model.MapModel;
import java.io.*;


public class SaveController {
    public static void saveGame(MapModel model, String path, int step, int time) throws IOException {
        File dir = new File(path).getParentFile();
        //获取路径的父目录
        //上面加入了新的传入量：gamepanel的step
        if (!dir.exists()) dir.mkdirs();
        //如果不存在该路径：创建新的
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(new user.GameData(model.getMatrix(), step, time));
        }
    }

    public static user.GameData loadGame(String path) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            return (user.GameData) ois.readObject();
        }
    }
}