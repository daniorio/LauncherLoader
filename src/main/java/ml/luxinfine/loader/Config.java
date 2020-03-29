package ml.luxinfine.loader;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static com.sun.javafx.PlatformUtil.isWindows;

class Config {
    static final String appdataPath = System.getenv("APPDATA");
    static final String launcherName = "LuxinfineLauncher";
    static final String path = appdataPath + File.separator + ".Luxinfine" + File.separator + "updates";;
    static final String fullpath = path + File.separator + launcherName + getFileExtension();
    static final List<String> urls = Arrays.asList("https://luxinfine.ml/" + launcherName + getFileExtension(),
                                                    "http://luxinfine.ml:82/updates/" + launcherName + getFileExtension(),
                                                    "http://luxinfine.ml/updates/" + launcherName + getFileExtension(),
                                                    "https://luxinfine.ml/updates/" + launcherName + getFileExtension(),
                                                    "https://luxinfine.ml:444/updates/" + launcherName + getFileExtension());
    static String getFileExtension() { if(isWindows()) { return ".exe"; } else { return ".jar"; } }
    static final String javaurl = "https://www.java.com/ru/download/manual.jsp";
    static final int minJavaVersion = 241;
}
