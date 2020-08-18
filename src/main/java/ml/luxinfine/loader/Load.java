package ml.luxinfine.loader;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.file.Path;

public class Load {
    public static final boolean isWindows = System.getProperty("os.name").contains("win");
    private static boolean isSystem64bit;

    public static void main(String[] args) {
        Config.path.toFile().mkdirs();
        String extension = isWindows ? ".exe" : ".jar";
        Path launcher_path = Config.path.resolve(Config.launcherName + extension);
        for(final String url : Config.urls) {
            String full_url = url + Config.launcherName + extension;
            if((!launcher_path.toFile().exists() || launcher_path.toFile().length() == 0 || (Config.checksize && launcher_path.toFile().length() != getFilesize(full_url))) && download(launcher_path.toString(), full_url)) break;
        }
        //* Проверка доступности файла *//
        if(!launcher_path.toFile().exists()) setError("Не удалось загрузить файл лаунчера");
        if(!launcher_path.toFile().canRead()) setError("Не удалось прочитать файл лаунчера");
        if(!launcher_path.toFile().canExecute()) setError("Не удалось запустить файл лаунчера");
        if(Config.java) {
            //* Проверка битности java *//
            boolean isJava64bit = System.getProperty("os.arch").contains("64");
            isSystem64bit = isWindows ? System.getenv("ProgramFiles(x86)") != null : System.getProperty("os.arch").contains("64");
            if(!isJava64bit && isSystem64bit) setJavaError("Отсутствует java нужной разрядности");
            //* Проверка версии java *//
            if(Integer.parseInt(System.getProperty("java.version").split("_")[1]) < Config.minJavaVersion) setJavaError("Отсутствует java нужной версии");
        }
        try { Runtime.getRuntime().exec("java -jar " + launcher_path); } catch (Exception e) { setError("Не удалось запустить файл лаунчера"); }
    }

    public static String getMinecraftDir() {
        return Load.isWindows ? System.getenv("APPDATA" ) : System.getProperty("user.dir");
    }

    private static void setError(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Ошибка!", JOptionPane.ERROR_MESSAGE);
        System.exit(0);
    }

    private static void setJavaError(String msg) {
        final int selection = JOptionPane.showConfirmDialog(null, "Установить сейчас?", msg, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(selection == JOptionPane.YES_OPTION) {
            if(isWindows) {
                final String installer_path = System.getProperty("user.dir") + File.separator + "javainstaller.exe";
                final File installer = new File(installer_path);
                JOptionPane.showConfirmDialog(null, "Сейчас начнется установка джавы, пожалуйста, подождите пару минут", "", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if(!download(installer_path, isSystem64bit ? Config.javax64 : Config.javax32) || !installer.exists()) setError("Не удалось загрузить установщик джавы");
                if(!installer.canRead()) setError("Не удалось прочитать установщик джавы");
                if(!installer.canExecute()) setError("Не удалось запустить установщик джавы");
                JOptionPane.showConfirmDialog(null, "Продолжите установку в ручном режиме, а после заного запустите лаунчер", "", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
                try { Desktop.getDesktop().open(new File(installer_path)); } catch (IOException e) {}
            } else try { Desktop.getDesktop().browse(URI.create(Config.javaurl)); } catch (IOException e) {}
            System.exit(0);
        }
    }

    private static boolean download(String path, String url) {
        try {
            final FileOutputStream fos = new FileOutputStream(path);
            fos.getChannel().transferFrom(Channels.newChannel(new URL(url).openStream()), 0, Long.MAX_VALUE);
            fos.close();
            return true;
        } catch (IOException ignored) { return false; }
    }

    private static int getFilesize(String url) {
        try { return new URL(url).openConnection().getContentLength(); } catch (IOException e) { return 0; }
    }

}
