package ml.luxinfine.loader;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static ml.luxinfine.loader.Main.getFileExtension;

class Config {
    //*    Название файла лаунчера    *//
    static final String launcherName = "LuxinfineLauncher";
    //*    Путь к месту хранения файла лаунчера    *//
    static final String path = System.getenv("APPDATA") + File.separator + ".Luxinfine" + File.separator + "updates";
    //*    Можно указать список ссылок откуда качается лаунчер, можно одну    *//
    static final List<String> urls = Arrays.asList(
                                                    "http://luxinfine.ml:82/updates/" + launcherName + getFileExtension(),
                                                    "https://luxinfine.ml/" + launcherName + getFileExtension(),
                                                    "http://luxinfine.ml/updates/" + launcherName + getFileExtension(),
                                                    "https://luxinfine.ml/updates/" + launcherName + getFileExtension(),
                                                    "https://luxinfine.ml:444/updates/" + launcherName + getFileExtension()
                                                    );
    //*    Ссылки для загрузки джавы    *//
    static final String javaurl = "https://www.java.com/ru/download/manual.jsp";
    static final String javax64 = "https://javadl.oracle.com/webapps/download/AutoDL?BundleId=242060_3d5a2bb8f8d4428bbe94aed7ec7ae784";
    static final String javax32 = "https://javadl.oracle.com/webapps/download/AutoDL?BundleId=242057_3d5a2bb8f8d4428bbe94aed7ec7ae784";
    //*    Минимальная версия джавы    *//
    static final int minJavaVersion = 251;
}
