package com.jqorz.common;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;


/**
 * 文件操作类
 */
public class FileUtil {
    private static final int COLORDRAWABLE_DIMENSION = 2;
    private static final Bitmap.Config BITMAP_CONFIG = Bitmap.Config.ARGB_8888;


    /**
     * 判断SD卡是否准备完毕
     */
    public static boolean isSDCardReady() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * Return whether the file exists.
     *
     * @param filePath The path of file.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isFileExists(final String filePath) {
        return isFileExists(getFileByPath(filePath));
    }

    /**
     * Return the file by path.
     *
     * @param filePath The path of file.
     * @return the file
     */
    public static File getFileByPath(final String filePath) {
        return isSpace(filePath) ? null : new File(filePath);
    }

    private static boolean isSpace(final String s) {
        if (s == null) return true;
        for (int i = 0, len = s.length(); i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Return whether the file exists.
     *
     * @param file The file.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isFileExists(final File file) {
        return file != null && file.exists();
    }

    /**
     * 通过原路径和文件名构建新路径
     *
     * @param path1 原路径
     * @param path2 文件名
     * @return 新路径
     */
    public static String makePath(String path1, String path2) {
        if (path1.endsWith(File.separator))
            return path1 + path2;

        return path1 + File.separator + path2;
    }

    /**
     * 删除文件/文件夹
     *
     * @param file 路径
     * @return 是否成功
     */
    public static boolean deleteFileOrFolder(File file) {
        if (file.isFile()) {
            return file.delete();
        }

        if (file.isDirectory()) {
            File[] childFiles = file.listFiles();
            if (childFiles == null || childFiles.length == 0) {
                return file.delete();
            }

            for (File childFile : childFiles) {
                deleteFileOrFolder(childFile);
            }
            return file.delete();
        }
        return false;
    }

    /**
     * 从文件路径得到文件名字
     *
     * @param filepath 文件路径
     * @return 文件名字
     */
    public static String getNameFromFilePath(String filepath) {
        int pos = filepath.lastIndexOf('/');
        if (pos != -1) {
            return filepath.substring(pos + 1);
        }
        return "";
    }

    public static Bitmap getBitmapFromDrawable(Drawable drawable) {
        if (drawable == null) {
            return null;
        }

        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        try {
            Bitmap bitmap;

            if (drawable instanceof ColorDrawable) {
                bitmap = Bitmap.createBitmap(COLORDRAWABLE_DIMENSION, COLORDRAWABLE_DIMENSION, BITMAP_CONFIG);
            } else {
                bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), BITMAP_CONFIG);
            }

            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        } catch (OutOfMemoryError e) {
            return null;
        }
    }

    /**
     * 默认以jpg格式保存图片到指定路径
     */
    public static boolean saveFile(Context context, Bitmap bm, String path) {
        return saveFile(context, bm, path, Bitmap.CompressFormat.JPEG, 100);
    }

    public static boolean saveFile(Context context, Bitmap bm, String path, String fileName) {
        String filePath = path.endsWith(File.separator) ? path + fileName : path + File.separator + fileName;
        return saveFile(context, bm, filePath);
    }

    /**
     * 以指定格式保存图片到指定路径
     *
     * @param bm     原图
     * @param path   路径
     * @param format 格式
     * @return 结果
     */
    public static boolean saveFile(Context context, Bitmap bm, String path,
                                   Bitmap.CompressFormat format, int rate) {
        if (bm == null || TextUtils.isEmpty(path))
            return false;
        File myCaptureFile = new File(path);
        if (myCaptureFile.exists()) {
            myCaptureFile.delete();
        } else {
            myCaptureFile.getParentFile().mkdirs();
        }
        try {
            BufferedOutputStream bos = new BufferedOutputStream(
                    new FileOutputStream(myCaptureFile));
            bm.compress(format, rate, bos);
            bos.flush();
            bos.close();

            //保存图片后发送广播通知更新数据库
            Uri uri = Uri.fromFile(myCaptureFile);
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));


            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
