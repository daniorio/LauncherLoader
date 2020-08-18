package ml.luxinfine.loader;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

class Config {
    //*    Название файла лаунчера    *//
    public static final String launcherName = "LuxinfineLauncher";
    //*    Путь к месту хранения файла лаунчера    *//
    public static final Path path = Paths.get(Load.getMinecraftDir(), "Luxinfine", "updates");
    //*    Можно указать список ссылок откуда качается лаунчер, можно одну    *//
    public static final List<String> urls = Arrays.asList(
                                                    "https://luxinfine.ml/updates/",
                                                    "https://connect.luxinfine.ml/updates/",
                                                    "https://luxinfine.ml:444/updates/"
                                                    );
    //*    Ссылки для загрузки джавы    *//
    public static final String javaurl = "https://www.java.com/ru/download/manual.jsp";
    public static final String javax64 = "https://javadl.oracle.com/webapps/download/AutoDL?BundleId=242060_3d5a2bb8f8d4428bbe94aed7ec7ae784";
    public static final String javax32 = "https://javadl.oracle.com/webapps/download/AutoDL?BundleId=242057_3d5a2bb8f8d4428bbe94aed7ec7ae784";
    //*    Минимальная версия джавы    *//
    public static final int minJavaVersion = 251;
    //*    При изменении размерафайла загружать новую новую копию лаунчера    *//
    public static final boolean checksize = true;
    //*    Обновлять джаву при несостыковке версии или битности    *//
    public static final boolean java = true;
}
