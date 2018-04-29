package utils;

import javafx.scene.Scene;
import javafx.scene.text.Font;

public class CssUtils {

    public static void loadCss(Scene mainScene, String fileName) throws NullPointerException {
        mainScene.getStylesheets().add(CssUtils.class.getResource("/resources/style/" + fileName + ".css").toExternalForm());
    }

    public static void loadFont(String fontName) throws NullPointerException {
        Font.loadFont(CssUtils.class.getResourceAsStream("/resources/fonts/" + fontName + ".ttf"), 10);
    }

}
