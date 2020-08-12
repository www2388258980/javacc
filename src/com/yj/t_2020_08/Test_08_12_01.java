package com.yj.t_2020_08;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 写压缩包
 */
public class Test_08_12_01 {
    public static void main(String[] args) {
        ZipOutputStream zos = null;
        FileInputStream fis = null;
        ZipEntry entry = null;
        File file1 = new File("resources/txt/1.txt");
        File[] files = new File[]{new File("resources/txt/1.txt"), new File("resources/txt/2.txt"),
                new File("resources/txt/3.txt")};
        try {
            zos = new ZipOutputStream(new FileOutputStream("resources/zip/123.zip"));
            byte[] buffer = new byte[1024];
            int length = -1;
            for (int i = 0; i < files.length; i++) {
                fis = new FileInputStream(files[i]);
                entry = new ZipEntry(files[i].getName());
                zos.putNextEntry(entry);
                while ((length = fis.read(buffer)) != -1) {
                    zos.write(buffer, 0, length);
                }
                zos.closeEntry();
                fis.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
        System.out.println(file1.getAbsolutePath());

    }
}
