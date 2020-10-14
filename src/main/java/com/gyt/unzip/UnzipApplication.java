package com.gyt.unzip;


import com.gyt.unzip.utils.Doc2Pdf;
import com.gyt.unzip.utils.PdfSplit;
import com.gyt.unzip.utils.Ppt2pdf;
import com.gyt.unzip.utils.UnzipAndRar;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@EnableAsync
@SpringBootApplication
public class UnzipApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(UnzipApplication.class, args);
        testUnZip();
//        testUnRar();
//        Doc2Pdf.doc2pdf("C:\\Users\\Administrator\\Desktop\\代码走查管理规范 .docx");
//        Ppt2pdf.ppt2pdf("C:\\Users\\Administrator\\Desktop\\人力系统开发处专业化分工2.0.pptx");
        // pdf切片
//        PdfSplit.splitFile("C:\\Program Files\\Apache Software Foundation\\Tomcat 8.0\\webapps\\ROOT\\courseware\\pdf\\26dab46f995433e7bd9dd4a85938ae2b\\obs-productdesc-zh.pdf");
    }


    public static void testUnZip() throws IOException {
        File srcFile = new File("C:\\Users\\Administrator\\Desktop\\nginx-1.18.0.zip");
        String outDir = "C:\\Program Files\\Apache Software Foundation\\Tomcat 8.0\\webapps\\ROOT\\courseware\\scorm";
        UnzipAndRar.unZip(srcFile, outDir, "5436534562342342334gfd");
    }

    public static void testUnRar() throws Exception {
        File srcFile = new File("C:\\Users\\Administrator\\Desktop\\scorm源文件\\B787 FireProtection - Non-Normal Operation - H5.rar");
        String outDir = "C:\\Program Files\\Apache Software Foundation\\Tomcat 8.0\\webapps\\ROOT\\courseware\\scorm\\";
        UnzipAndRar.unRar(srcFile, outDir, "abcd123");
    }
}
//