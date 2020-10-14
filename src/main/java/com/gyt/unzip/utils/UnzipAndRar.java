package com.gyt.unzip.utils;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import com.github.junrar.Archive;
import com.github.junrar.exception.RarException;
import com.github.junrar.rarfile.FileHeader;

public class UnzipAndRar {

    private static final int BUFFER_SIZE = 2 * 1024;

    private static final String SCORM_TYPE1 = "imsmanifest.xml";
    private static final String SCORM_TYPE2 = "cbtstructure.xml";

    /**
     * 解压zip
     * @param srcFile
     * @param outDir
     * @throws IOException
     */
    public static void unZip(File srcFile, String outDir, String coursewareId) throws IOException {
        long startTime = System.currentTimeMillis();

        // 判断源文件是否存在
        if (!srcFile.exists()) {
            throw new RuntimeException(srcFile.getPath() + "所指文件不存在");
        }

        // 开始解压
        ZipFile zipFile =  null;
        ZipFile zipDir = null;

        String srcFilePath = null;

        try {
            // 获取顶级目录路径，用于后续更换成CoursewareID
            zipDir = new ZipFile(srcFile);
            Enumeration<?> entriesDir = zipDir.entries();
            if (entriesDir.hasMoreElements()) {
                ZipEntry entryDir = (ZipEntry) entriesDir.nextElement();
                if (entryDir.isDirectory()) {
                    srcFilePath = outDir + "\\" + entryDir.getName();
                }
            }

            zipFile = new ZipFile(srcFile);
            Enumeration<?> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                System.out.println("解压" + entry.getName());

                // 如果是文件夹，就创建个文件夹
                if (entry.isDirectory()) {
                    String dirPath = outDir + "\\" + entry.getName();
                    File dir = new File(dirPath);
                    dir.mkdirs();
                } else {
                    // 如果是文件，就先创建一个文件，然后用io流把内容copy过去
                    File targetFile = new File(outDir + "\\" + entry.getName());
                    System.err.println("文件名称：" + targetFile.getName());
                    // 保证这个文件的父文件夹必须存在
                    if (!targetFile.getParentFile().exists()) {
                        targetFile.getParentFile().mkdirs();
                    }
                    targetFile.createNewFile();
                    // 将压缩文件内容写入到这个文件中
                    InputStream is = zipFile.getInputStream(entry);
                    FileOutputStream fos = new FileOutputStream(targetFile);
                    int len;
                    byte[] buf = new byte[BUFFER_SIZE];
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                    }
                    // 关流顺序，先打开的后关闭
                    fos.close();
                    is.close();
                }
            }
            // 解压后更换顶级文件夹名称，例如Nginx-1.5.1更名为32位UUID
            fixFileName(srcFilePath, coursewareId);

            // 回写加压后保存地址和转码状态（成功2，失败-1）, 更新courseware表
            //--------------------------

            long endTime = System.currentTimeMillis();
            System.out.println("解压Zip完成，耗时：" + (endTime - startTime) + " ms");
        } catch (Exception e) {
            throw new RuntimeException("unzip error from UnzipAndRar", e);
        } finally {
            if (zipFile != null) {
                try {
                    zipFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 解压Rar
     * @param srcFile
     * @param outDir
     */
    public static void unRar(File srcFile, String outDir, String coursewareId) throws Exception {
        long startTime = System.currentTimeMillis();

        // 去掉文件后缀
        String fileName = srcFile.getName().substring(0, srcFile.getName().lastIndexOf("."));

        // 判断源文件是否存在
        if (!srcFile.exists()) {
            throw new RuntimeException(srcFile.getPath() + "所指文件不存在");
        }

        File outFileDir = new File(outDir);
        if (!outFileDir.exists()) {
            boolean isMakDir = outFileDir.mkdirs();
            if (isMakDir) {
                System.out.println("创建压缩目录成功");
            }
        }

        try {
            Archive archive = new Archive(new FileInputStream(srcFile));
            FileHeader fileHeader = archive.nextFileHeader();
            System.out.println("文件夹路径：" + fileHeader.getFileNameString());
            while (fileHeader != null) {
                if (fileHeader.isDirectory()) {
                    fileHeader = archive.nextFileHeader();
                    continue;
                }

                File out = new File(outDir + fileHeader.getFileNameString());
                System.err.println("读取的文件名称：" + out.getName());
                if (!out.exists()) {
                    if (!out.getParentFile().exists()) {
                        out.getParentFile().mkdirs();
                    }
                    out.createNewFile();
                }
                FileOutputStream os = new FileOutputStream(out);
                archive.extractFile(fileHeader, os);

                os.close();

                fileHeader = archive.nextFileHeader();
            }
            Long endTime = System.currentTimeMillis();
            System.out.println("解压Rar完成，耗时：" + (endTime - startTime) + " ms");
            fixFileName(outDir + "\\" + fileName, coursewareId);
            archive.close();
        } catch (RarException e) {
            System.err.println("不支持rar5压缩包格式: " + e.getMessage());
        }

    }

    public static String fixFileName(String filePath, String newFileName) {
        File file = new File(filePath);
        if (!file.exists()) {
            return null;
        }

        newFileName = newFileName.trim();

        if ("".equals(newFileName) || newFileName == null) {
            // 文件名不能为空
            return null;
        }

        String newFilePath = null;
        if (file.isDirectory()) {
            newFilePath = filePath.substring(0, filePath.lastIndexOf("\\")) + "\\" + newFileName;
        }
        File nf = new File(newFilePath);
        if (!file.exists()) {
            // 判断需要修改为的文件是否存在，防止文件名冲突
            return null;
        }
        try {
            file.renameTo(nf);
        } catch (Exception err) {
            err.printStackTrace();
            return null;
        }
        return newFilePath;
    }

}