package com.practice.utils;

import java.io.File;

public class IsFileDwldwd {

    public static boolean isFileDownloaded(String fileName, int timeoutSeconds) {
        // Define the path to your download folder
        String downloadPath = System.getProperty("user.home") + "/Downloads/";
        File dir = new File(downloadPath);
        File file = new File(downloadPath + fileName);

        long startTime = System.currentTimeMillis();
        long endTime = startTime + (timeoutSeconds * 1000);

        while (System.currentTimeMillis() < endTime) {
            if (file.exists()) {
                // Check if file is still downloading (size might be 0 initially)
                if (file.length() > 0) {
                    return true;
                }
            }
            try {
                Thread.sleep(1000); // Check every second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}