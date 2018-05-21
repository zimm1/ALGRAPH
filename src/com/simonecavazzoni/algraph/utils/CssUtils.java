package com.simonecavazzoni.algraph.utils;

import javafx.scene.Scene;
import javafx.scene.text.Font;

/**
 * Set of style related utilities.
 */
public final class CssUtils {

    /**
     * Adds a css file to a scene.
     * @param mainScene Scene to add css to
     * @param fileName Name of the css file without path and extension
     * @throws NullPointerException If css file doesn't exist
     */
    public static void loadCss(Scene mainScene, String fileName) throws NullPointerException {
        mainScene.getStylesheets().add(CssUtils.class.getResource("/com/simonecavazzoni/algraph/res/style/" + fileName + ".css").toExternalForm());
    }

    /**
     * Adds a font to an application.
     * @param fontName Name of the font file without path and extension
     * @throws NullPointerException If font file doesn't exist
     */
    public static void loadFont(String fontName) throws NullPointerException {
        Font.loadFont(CssUtils.class.getResourceAsStream("/com/simonecavazzoni/algraph/res/fonts/" + fontName + ".ttf"), 10);
    }

}
