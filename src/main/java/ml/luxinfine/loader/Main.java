package ml.luxinfine.loader;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;

class Main {
    private static String url = Config.url;
    private static String path = Config.path;
    private static String fullpath = Config.fullpath;

    public static void main(String[] args) throws IOException {
        if (new File(fullpath).exists()) {
            Desktop.getDesktop().open(new File(fullpath));
        } else {
            try {
                new File(path).mkdirs();
                new FileOutputStream(fullpath).getChannel().transferFrom(Channels.newChannel(new URL(url).openStream()), 0, Long.MAX_VALUE);
                Desktop.getDesktop().open(new File(fullpath));
            } catch (IOException e) { e.printStackTrace(); }
        }
    }
}
