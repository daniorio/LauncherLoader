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
        boolean isSystem64bit;
        boolean isJava64bit = System.getProperty("os.arch").contains("64");
        if (System.getProperty("os.name").contains("Windows")) { isSystem64bit = (System.getenv("ProgramFiles(x86)") != null); } else isSystem64bit = (System.getProperty("os.arch").indexOf("64") != -1);
        if(!isJava64bit && isSystem64bit) setInfo("Отсутствует java нужной разрядности", "Установить сейчас?");
        //* Проверка версии java *//
        if(Integer.parseInt(System.getProperty("java.version").split("_")[1]) < Config.minJavaVersion) setInfo("Отсутствует java нужной версии", "Установить сейчас?");
        try { Runtime.getRuntime().exec("java -jar " + fullpath); } catch (Exception e) { setError("Не удалось запустить файл лаунчера"); }
    }

    private static void setError(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Ошибка!", JOptionPane.ERROR_MESSAGE);
        System.exit(0);
    }

    private static void setInfo(String msg1, String msg2) throws IOException {
        final int selection = JOptionPane.showConfirmDialog(null, msg2, msg1, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (selection == JOptionPane.YES_OPTION) {
            if(isWindows()) {
                final String url = System.getProperty("os.arch").equals("64") ? Config.javax64 : Config.javax32;
                final String filepath = System.getProperty("user.dir") + File.separator + "javainstaller.exe";
                JOptionPane.showConfirmDialog(null, "Сейчас начнется установка джавы, пожалуйста, подождите пару минут", "", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
                FileOutputStream fos = new FileOutputStream(filepath);
                fos.getChannel().transferFrom(Channels.newChannel(new URL(url).openStream()), 0, Long.MAX_VALUE);
                fos.close();
                if(!new File(filepath).exists()) setError("Не удалось загрузить установщик джавы");
                if(!new File(filepath).canRead()) setError("Не удалось прочитать установщик джавы");
                if(!new File(filepath).canExecute()) setError("Не удалось запустить установщик джавы");
                JOptionPane.showConfirmDialog(null, "Продолжите установку в ручном режиме, а после заного запустить лаунечер", "", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
                Desktop.getDesktop().open(new File(filepath));
            } else Desktop.getDesktop().browse(URI.create(Config.javaurl));
            System.exit(0);
        }
    }

    static String getFileExtension() { if(isWindows()) { return ".exe"; } else { return ".jar"; } }


}
