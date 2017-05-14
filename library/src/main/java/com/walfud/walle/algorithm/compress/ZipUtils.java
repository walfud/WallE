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
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(0)) {
            ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
            zipOutputStream.write(data);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     *
     * @param src
     * @param dstZipFile should be a file path
     *
     * @return `true` if everything is ok, otherwise is `false`
     */
    public static boolean compress(File src, final File dstZipFile) {
        final boolean[] ret = {true};

        try (FileOutputStream fileOutputStream = new FileOutputStream(dstZipFile)) {
            final ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);

            final String baseDir = src.getPath();
            IoUtils.foreachFile(src, file -> {
                String entryName = file.getAbsolutePath().substring(baseDir.length());
                ZipEntry zipEntry = new ZipEntry(entryName);

                byte[] data = IoUtils.input(file);
                try {
                    zipOutputStream.putNextEntry(zipEntry);
                    zipOutputStream.write(data);
                    zipOutputStream.closeEntry();
                } catch (Exception e) {
                    e.printStackTrace();
                    ret[0] = false;
                }

                return true;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ret[0];
    }
}
