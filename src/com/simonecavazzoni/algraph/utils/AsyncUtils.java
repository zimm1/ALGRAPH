package com.simonecavazzoni.algraph.utils;

import javafx.application.Platform;

public abstract class AsyncUtils {

    public interface AsyncCallback {
        void onComplete();
        void onError(Exception e);
    }

    public static void setTimeout(Runnable runnable, int delay, AsyncCallback callback){
        new Thread(() -> {
            try {
                Platform.runLater(runnable::run);
                Thread.sleep(delay);
                Platform.runLater(callback::onComplete);
            }
            catch (Exception e){
                callback.onError(e);
            }
        }).start();
    }
}
