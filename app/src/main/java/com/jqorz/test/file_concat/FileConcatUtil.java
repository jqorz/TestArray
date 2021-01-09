package com.jqorz.test.file_concat;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author jqorz
 * @since 2021/1/9
 */
class FileConcatUtil {
    /**
     * 文件合并
     * @param fileName 指定合并文件
     * @param targetFile 分割前的文件
     * @param cutSize 分割文件的大小
     */
    public static void merge(String fileName, File targetFile , long cutSize) {


        int tempCount = targetFile.length() % cutSize == 0 ? (int) (targetFile.length() / cutSize) :
                (int) (targetFile.length() / cutSize + 1);
        //文件名
        String a=targetFile.getAbsolutePath().split(targetFile.getName())[0];

        RandomAccessFile raf = null;
        try {
            //申明随机读取文件RandomAccessFile
            raf = new RandomAccessFile(new File(fileName+ (targetFile)), "rw");
            //开始合并文件，对应切片的二进制文件
            for (int i = 0; i < tempCount; i++) {
                //读取切片文件
                File mFile = new File(a + "_" + i + ".tmp");
                //
                RandomAccessFile reader = new RandomAccessFile(mFile, "r");
                byte[] b = new byte[1024];
                int n = 0;
                //先读后写
                while ((n = reader.read(b)) != -1) {//读
                    raf.write(b, 0, n);//写
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                raf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
