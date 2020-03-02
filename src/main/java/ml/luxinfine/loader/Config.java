package ml.luxinfine.loader;

import java.io.File;

import static com.sun.javafx.PlatformUtil.isWindows;

class Config {
    static final String appdataPath = System.getenv("APPDATA");
    static final String launcherName = "LuxinfineLauncher";
    static final String path = appdataPath + File.separator + ".Luxinfine" + File.separator + "updates";;
    static final String fullpath = path + File.separator + launcherName + getFileExtension();
    static final String url = "https://luxinfine.ml/" + launcherName + getFileExtension();
    static String getFileExtension() { if(isWindows()) { return ".exe"; } else { return ".jar"; } }
}
