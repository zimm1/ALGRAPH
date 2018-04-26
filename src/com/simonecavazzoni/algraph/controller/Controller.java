package com.simonecavazzoni.algraph.controller;

import javafx.scene.layout.Pane;

public abstract class Controller {
    protected Pane root;

    public final Pane get() {
        return this.root;
    }

    protected void setRoot(Pane root) {
        this.root = root;
        this.root.getStyleClass().add("controllerRoot");
    }
}
