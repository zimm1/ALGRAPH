package utils;

public class CssUtils {

    public static String getCssFilePaths(Object context, String fileName) throws NullPointerException {
        return context.getClass().getResource("/resources/style/" + fileName + ".css").toExternalForm();
    }

}
