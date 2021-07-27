package com.example.export_pdf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PDFController {

    @RequestMapping("/createPDFbyTemplate")
    public void createPDFbyTemplate() throws IOException, DocumentException {
        // use Excel Template
        // String filePath = FilePath.name("sample.pdf");
        // TemplateExportParams params = new TemplateExportParams(filePath);

        // For Self desktop to download the file and open without corrupted or crashed
        String filePath = "templates/sample.pdf";
        String targetPath = "C://Users//Koon Fung Yee//Desktop/";
        String fileName = "sample.pdf";

        List<EditPdfParam> list = new ArrayList<EditPdfParam>();
        EditPdfParam name = new EditPdfParam();
        name.setText("Hello World");
        name.setX(BigDecimal.valueOf(265));
        name.setY(BigDecimal.valueOf(408));
        name.setPage(1);
        list.add(name);

        EditPdfParam amount = new EditPdfParam();
        amount.setText("RM1.00");
        amount.setX(BigDecimal.valueOf(265));
        amount.setY(BigDecimal.valueOf(370));
        amount.setPage(1);
        list.add(amount);

        EditPdfParam status = new EditPdfParam();
        status.setText("Active");
        status.setX(BigDecimal.valueOf(265));
        status.setY(BigDecimal.valueOf(370));
        status.setPage(2);
        list.add(status);

        PdfReader reader = new PdfReader(filePath);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(targetPath + fileName));

        // Font font = new Font(Font.FontFamily.HELVETICA, 11, Font.NORMAL,
        // BaseColor.BLACK);
        // font.setStyle(Font.BOLD);

        float textX;
        float textY;
        PdfContentByte over;
        for (EditPdfParam param : list) {
            over = stamper.getOverContent(param.getPage());
            over.beginText();

            // Set font family and size
            if (param.getFont() != null) {
                over.setFontAndSize(param.getFont().getBaseFont(), param.getFontSize());
            } else {
                BaseFont baseFont = BaseFont.createFont(BaseFont.TIMES_ROMAN, "UTF-8", false);
                over.setFontAndSize(baseFont, 10);
            }

            // Set font color
            if (param.getColor() != null) {
                over.setColorFill(param.getColor());
            } else {
                over.setColorFill(BaseColor.BLACK);
            }

            textX = param.getX().floatValue();
            textY = param.getY().floatValue();

            over.setTextMatrix(textX, textY);
            param.getText().replace("", "\\u00A0");
            over.showText(param.getText());
            over.endText();
        }
        stamper.close();
        reader.close();
    }

    @RequestMapping("/openPDFinWebsite")
    public void openPDFinWebsite(HttpServletResponse response) {
        FileInputStream fin = null;
        OutputStream output = null;
        try {
            String targetPath = "C://Users//Koon Fung Yee//Desktop/";
            File pdfFile = new File(targetPath + "sample.pdf");
            // Open in website
            response.setContentType("application/pdf");

            // Force download
            // response.setContentType("application/x-download");
            // response.addHeader("Content-disposition", "attachment; filename=file.pdf");

            output = response.getOutputStream();
            fin = new FileInputStream(pdfFile);
            byte[] arr = new byte[1024 * 10];
            int n;
            while ((n = fin.read(arr)) != -1) {
                output.write(arr, 0, n);
            }
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                output.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
