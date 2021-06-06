package com.yj.t_2020_08;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.util.Date;

/**
 * 写压缩包
 */
public class Test_08_12_01 {
    public static void main(String[] args) {
        ZipOutputStream zos = null;
        FileInputStream fis = null;
        ZipEntry entry = null;
        File[] files = new File[]{new File("resources/csv/0000000558_RA_Endo_Fee.csv"),
                new File("resources/csv/0000000558_RA_Pol_Main.csv"),
                new File("resources/csv/0000000558_RA_Voucher_Info.csv")};

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
            zos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
