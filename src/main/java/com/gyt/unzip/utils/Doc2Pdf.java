package com.gyt.unzip.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import com.aspose.words.Document;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;
import com.gyt.unzip.UnzipApplication;


/**
 * Doc转PDF工具类
 */
public class Doc2Pdf {

    /**
     * 校验License
     * @return
     */
    public static boolean getLicense() {
        boolean result = false;
        try {
            InputStream is = UnzipApplication.class.getClassLoader().getResourceAsStream("licenseOfWord.xml");
            License asposeLic = new License();
            asposeLic.setLicense(is);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * doc2pdf
     * @param srcFile 原word文件
     */
    public static void doc2pdf(String srcFile) {
        // 验证License，若不验证则转化出的pdf文档会有水印产生
        if (!getLicense()) {
            return;
        }
        try {
            long old = System.currentTimeMillis();
            File file = new File("C:\\Users\\Administrator\\Desktop\\docTest.pdf");
            FileOutputStream os = new FileOutputStream(file);
            Document doc = new Document(srcFile);
            doc.save(os, SaveFormat.PDF);
            long now = System.currentTimeMillis();
            System.out.println("转码成功，耗时：" + (now - old) + " ms");
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}