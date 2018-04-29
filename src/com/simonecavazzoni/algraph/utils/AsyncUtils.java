package com.simonecavazzoni.algraph.utils;

import javafx.application.Platform;

public abstract class AsyncUtils {

    public interface AsyncCallback {
        void onComplete();
        void onError(Exception e);
    }

    public static void setTimeout(Runnable beforeRunnable, int delay, AsyncCallback callback){
        beforeRunnable.run();

        new Thread(() -> {
            try {
                Thread.sleep(delay);
                Platform.runLater(callback::onComplete);
            }
            catch (Exception e){
                callback.onError(e);
            }
        }).start();
    }
}
