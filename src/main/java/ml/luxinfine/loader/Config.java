package ml.luxinfine.loader;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static ml.luxinfine.loader.Main.getFileExtension;

class Config {
    static final String launcherName = "LuxinfineLauncher";
    static final String path = System.getenv("APPDATA") + File.separator + ".Luxinfine" + File.separator + "updates";
    static final List<String> urls = Arrays.asList(
                                                    "http://luxinfine.ml:82/updates/" + launcherName + getFileExtension(),
                                                    "https://luxinfine.ml/" + launcherName + getFileExtension(),
                                                    "http://luxinfine.ml/updates/" + launcherName + getFileExtension(),
                                                    "https://luxinfine.ml/updates/" + launcherName + getFileExtension(),
                                                    "https://luxinfine.ml:444/updates/" + launcherName + getFileExtension()
                                                    );
    static final String javaurl = "https://www.java.com/ru/download/manual.jsp";
    static final int minJavaVersion = 241;
}
