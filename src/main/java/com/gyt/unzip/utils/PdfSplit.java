package com.gyt.unzip.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 应用pdf切片
 */
public class PdfSplit {

    public static String splitFile(String pdfFile) {
        Document document = null;
        PdfCopy copy = null;

        try {
            long startTime = System.currentTimeMillis();

            PdfReader reader = new PdfReader(pdfFile);
            // 获取pdf总页数
            int n = reader.getNumberOfPages();
            for (int i = 1; i <= n; i++) {
                List<String> savePaths = new ArrayList<String>();
                int a = pdfFile.lastIndexOf(".pdf");
                String staticPath = pdfFile.substring(0,a);
                String savePath = staticPath + "_" + i + ".pdf";
                savePaths.add(savePath);
                document = new Document(reader.getPageSize(1));
                copy = new PdfCopy(document, new FileOutputStream(savePaths.get(0)));
                document.open();
                document.newPage();
                PdfImportedPage page = copy.getImportedPage(reader, i);
                copy.addPage(page);
                document.close();
            }
            long endTime = System.currentTimeMillis();
            System.out.println("PDF切片完成，耗时：" + (endTime - startTime) + "ms");
            return "PDF切片成功";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}