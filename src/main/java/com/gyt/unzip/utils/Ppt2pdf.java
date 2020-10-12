package com.gyt.unzip.utils;

import com.aspose.slides.Presentation;
import com.aspose.slides.SaveFormat;
import com.gyt.unzip.UnzipApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import com.aspose.slides.License;

public class Ppt2pdf {

    private static InputStream license;
    private static InputStream slides;

    /**
     * 获取license
     *
     * @return
     */
    public static boolean getLicense() {
        boolean result = false;
        try {
            // license路径
            license = UnzipApplication.class.getClassLoader().getResourceAsStream("licenseOfSlides.xml");
            License aposeLic = new License();
            aposeLic.setLicense(license);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * ppt2pdf
     * @param srcFile 原ppt文件
     */
    public static void ppt2pdf(String srcFile) {
        // 验证License，若不验证则转化出的pdf文档会有水印产生
        if (!getLicense()) {
            return;
        }

        try {
            long startTime = System.currentTimeMillis();
            File inFile = new File(srcFile);
            InputStream in = null;
            if (inFile != null) {
                in = new FileInputStream(inFile);
            }

            // 新创建一个空白pdf
            File file = new File("C:\\Users\\Administrator\\Desktop\\index.pdf");
            // 原始ppt
            Presentation pres = new Presentation(in);

            FileOutputStream os = new FileOutputStream(file);
            pres.save(os, SaveFormat.Pdf);
            os.close();

            long endTime = System.currentTimeMillis();
            System.out.println("转码成功，耗时：" + (endTime - startTime) + " ms");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}