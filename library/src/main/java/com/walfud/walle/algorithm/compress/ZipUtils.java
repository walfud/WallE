package com.walfud.walle.algorithm.compress;

import com.walfud.walle.io.IoUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by walfud on 2015/12/21.
 */
public class ZipUtils {

    public static final String TAG = "ZipUtils";

    public static byte[] compress(byte[] data) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(0);
        ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
        try {
            zipOutputStream.write(data);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                byteArrayOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    /**
     *
     * @param src
     * @param dstZipFile should be a file path
     */
    public static void compress(File src, final File dstZipFile) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(dstZipFile);
            final ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);

            final String baseDir = src.getPath();
            IoUtils.foreachFile(src, new IoUtils.ForeachFileHandler() {
                @Override
                public boolean handle(File file) {
                    String entryName = file.getAbsolutePath().substring(baseDir.length());
                    ZipEntry zipEntry = new ZipEntry(entryName);

                    byte[] data = IoUtils.input(file);
                    try {
                        zipOutputStream.putNextEntry(zipEntry);
                        zipOutputStream.write(data);
                        zipOutputStream.closeEntry();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    return true;
                }
            });

            zipOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
