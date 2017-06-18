package com.vcooline.li.videoplayer.tools;

import android.content.Context;
import android.os.Build;
import android.os.StatFs;

import java.io.File;

/**
 * Created by Trace on 2014/8/2.
 */
public class CacheTools {

    private static final String BIG_CACHE_PATH = "my-cache-dir";
    private static final float MAX_AVAILABLE_SPACE_USE_FRACTION = 0.9f;
    private static final float MAX_TOTAL_SPACE_USE_FRACTION = 0.25f;

    public static File createDefaultCacheDirExample(Context context) {
        File cache = new File(context.getApplicationContext().getCacheDir(), BIG_CACHE_PATH);
        if (!cache.exists()) {
            cache.mkdirs();
        }
        return cache;
    }

    /**
     * Calculates minimum of available or total fraction of disk space
     *
     * @param dir
     * @return space in bytes
     */
    public static long calculateAvailableCacheSize(File dir) {
        long size = 0;
        try {
            StatFs statFs = new StatFs(dir.getAbsolutePath());
            int sdkInt = Build.VERSION.SDK_INT;
            long totalBytes;
            long availableBytes;
            int blockSize = statFs.getBlockSize();
            availableBytes = ((long) statFs.getAvailableBlocks()) * blockSize;
            totalBytes = ((long) statFs.getBlockCount()) * blockSize;
            // Target at least 90% of available or 25% of total space
            size = (long) Math.min(availableBytes * MAX_AVAILABLE_SPACE_USE_FRACTION, totalBytes * MAX_TOTAL_SPACE_USE_FRACTION);
        } catch (IllegalArgumentException ignored) {
            // ignored
        }
        return size;
    }


}
