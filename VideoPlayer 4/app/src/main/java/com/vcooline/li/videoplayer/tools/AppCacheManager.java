package com.vcooline.li.videoplayer.tools;

import android.content.Context;
import android.os.Build;
import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Trace on 2014/10/1.
 */
public class AppCacheManager {

    public static String rootPath;

    public static String getRootCacheDir(Context context) {
        if (rootPath != null && !rootPath.equals("")) {
            File rootFile = new File(rootPath);
            if (!rootFile.exists()) {
                makeRootDir(context);
            }
            return rootPath;
        } else {
            makeRootDir(context);
            return rootPath;
        }
    }

    public static void makeRootDir(Context context) {
        File cacheDir = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) ||
                !isExternalStorageRemovable()) {
            cacheDir = context.getExternalCacheDir();
        }
        if (cacheDir == null) {
            cacheDir = context.getCacheDir();
        }
        rootPath = cacheDir.getPath();
    }
    private static boolean isExternalStorageRemovable() {
        return Build.VERSION.SDK_INT < 9 || Environment.isExternalStorageRemovable();
    }

    public static String getVideoPath(Context context) {
        String path = getRootCacheDir(context) + "/video/";
        File file = new File(path);
        if(!file.exists()){
            file.mkdir();
        }
        return path;
    }

    public static String getImagePath(Context context) {
        String path = getRootCacheDir(context) + "/img/";
        File file = new File(path);
        if(!file.exists()){
            file.mkdir();
        }
        return path;
    }

    public static String getSketchBeanPath(Context context){
        String path = getRootCacheDir(context) + "/sketchcache/";
        File file = new File(path);
        if(!file.exists()){
            file.mkdir();
        }
        return path;
    }

    public static Object getCacheData(String fileName) {
        Object readob;
        try {
            if (new File(fileName).exists()) {
                FileInputStream fi = new FileInputStream(fileName);
                BufferedInputStream bi = new BufferedInputStream(fi, 10000);
                ObjectInputStream oi = new ObjectInputStream(bi);
                readob = oi.readObject();
                oi.close();
                bi.close();
                fi.close();
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
        return readob;
    }
    public static void cacheData(String fileName, Object object) {
        if (fileName != null && fileName.length() > 0) {
            try {
                FileOutputStream fo = new FileOutputStream(fileName);
                BufferedOutputStream bo = new BufferedOutputStream(fo, 10000);
                ObjectOutputStream oo = new ObjectOutputStream(bo);
                oo.writeObject(object);
                oo.close();
                fo.close();
            } catch (Exception e) {
		e.printStackTrace();
            }
        }
    }
}
