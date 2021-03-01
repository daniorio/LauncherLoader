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
                                                    "https://connect.luxinfine.ml/updates/",
                                                    "https://luxinfine.ml/updates/"
                                                    );
    //*    Ссылки для загрузки джавы    *//
    public static final String javaurl = "https://www.java.com/ru/download/manual.jsp";
    public static final String javax64 = "https://javadl.oracle.com/webapps/download/AutoDL?BundleId=242990_a4634525489241b9a9e1aa73d9e118e6";
    public static final String javax32 = "https://javadl.oracle.com/webapps/download/AutoDL?BundleId=242988_a4634525489241b9a9e1aa73d9e118e6";
    //*    Минимальная версия джавы    *//
    public static final int minJavaVersion = 250;
    //*    При изменении размера файла загружать новую новую копию лаунчера    *//
    public static final boolean checksize = true;
    //*    Обновлять джаву при несостыковке версии или битности    *//
    public static final boolean java = true;
}
