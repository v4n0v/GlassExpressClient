package ru.glassexpress.core.utils;

import java.util.List;
import java.util.concurrent.Semaphore;

public class ImageLoaderThread extends Thread {

    private Semaphore s;

    public ImageLoaderThread(List<String> links) {
        this.links = links;
    }

    public ImageLoaderThread(Semaphore s) {
        this.s = s;
    }


    private List<String> links;

    @Override
    public void run() {
        System.out.println("Начал загрузку картинки");
        //    try {
        for (int i = 0; i < links.size(); i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Гружу картинку " + links.get(i));
        }

        // Thread.sleep(1000);
        //   links="dsadsa";
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("Закончил  загрузку картинки");
        //    notify();
        //s.release();
    }
}
