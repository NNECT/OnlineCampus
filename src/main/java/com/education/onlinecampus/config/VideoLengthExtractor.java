package com.education.onlinecampus.config;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import java.io.InputStream;

public class VideoLengthExtractor {
    public static double getVideoLength(InputStream videoStream) {
        try {
            FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(videoStream);
            grabber.start();
            double videoLength = grabber.getLengthInTime() / 1000000.0; // in seconds
            grabber.stop();
            return videoLength+1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // Handle error
        }
    }
}
