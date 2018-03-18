package ru.glassexpress.core.utils;

import java.util.concurrent.Semaphore;

public class ImageLoaderThread extends Thread {

    private Semaphore s;

    public ImageLoaderThread() {
    }

    public ImageLoaderThread(Semaphore s) {
        this.s = s;
    }

    public String getLinks() {
        return links;
    }

    private String links;
    @Override
    public void run() {
        System.out.println("Начал получать ссылки");
        try {
            Thread.sleep(1000);
            links="dsadsa";
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Закончил получать ссылки");
        notify();
        //s.release();
    }
}
