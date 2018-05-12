package com.simonecavazzoni.algraph.utils;

import javafx.application.Platform;

/**
 * Set of asynchronous calls related utilities
 */
public abstract class AsyncUtils {

    /**
     * Custom callback for asynchronous calls
     */
    public interface AsyncCallback {
        /**
         * Success callback
         */
        void onComplete();
        /**
         * Error callback
         * @param e Exception thrown by the asynchronous call
         */
        void onError(Exception e);
    }

    /**
     * Runs a runnable, waits a delay and finally calls the callback
     * @param beforeRunnable Code to run before the timeout
     * @param delay Delay after executing the runnable and before calling the callback
     * @param callback Callback called after the delay
     */
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
