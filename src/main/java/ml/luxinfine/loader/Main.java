package ml.luxinfine.loader;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URL;
import java.nio.channels.Channels;

import static com.sun.javafx.PlatformUtil.isWindows;

class Main {
    private static boolean isLoaded = false;
    private static final String fullpath = Config.path + File.separator + Config.launcherName + getFileExtension();

    public static void main(String[] args) throws IOException {
        if (!new File(fullpath).exists() || new File(fullpath).length() == 0) {
            new File(Config.path).mkdirs();
            for (String url : Config.urls) {
                try { new FileOutputStream(fullpath).getChannel().transferFrom(Channels.newChannel(new URL(url).openStream()), 0, Long.MAX_VALUE); } catch (Exception e) { continue; }
                isLoaded = true;
                break;
            }
        } else isLoaded = true;
        //* Проверка загруженного файла *//
        if(!isLoaded) setError("Не удалось загрузить файл лаунчера");
        //* Проверка доступности *//
        if(!new File(fullpath).canRead()) setError("Не удалось прочитать файл лаунчера");
        if(!new File(fullpath).canExecute()) setError("Не удалось запустить файл лаунчера");
        //* Проверка битности java *//
        StringBuilder answer = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("java -version").getInputStream()));
        while ((br.readLine())!=null) answer.append(br.readLine());
        if(!answer.toString().toLowerCase().contains("64-bit") && System.getProperty("os.arch").equals("64")) setInfo("Отсутствует java нужной разрядности");
        //* Проверка версии java *//
        if(Integer.parseInt(System.getProperty("java.version").split("_")[1]) < Config.minJavaVersion) setInfo("Отсутствует java нужной версии");
        Runtime.getRuntime().exec("java -jar " + fullpath);
        //Desktop.getDesktop().open(new File(fullpath));
    }

    private static void setError(String msg) throws IOException {
        JOptionPane.showMessageDialog(null, msg, "Ошибка!", JOptionPane.ERROR_MESSAGE);
        System.exit(0);
    }

    private static void setInfo(String msg) throws IOException {
        int selection = JOptionPane.showConfirmDialog(null, "Перейти на сайт для установки?", msg, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (selection == JOptionPane.YES_OPTION) {
            Desktop.getDesktop().browse(URI.create(Config.javaurl));
            System.exit(0);
        }
    }

    static String getFileExtension() { if(isWindows()) { return ".exe"; } else { return ".jar"; } }


}
