package com.example.export_pdf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PDFController {

    @RequestMapping("/createPDFbyTemplate")
    public void createPDFbyTemplate(HttpServletResponse response) throws IOException, DocumentException {
        // use Excel Template
        // String filePath = FilePath.name("sample.pdf");
        // TemplateExportParams params = new TemplateExportParams(filePath);

        // For Self desktop to download the file and open without corrupted or crashed
        String filePath = "templates/sample.pdf";
        String targetPath = "C://Users//Koon Fung Yee//Desktop/";
        String fileName = "sample1.pdf";

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

        String path = "";
        try {
            ClassPathResource classPathResource = new ClassPathResource("templates/image.png");
            path = classPathResource.getFile().getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Image image = Image.getInstance(path);
        image.scaleToFit(100, 100);
        image.setAbsolutePosition(300, 650);
        over = stamper.getOverContent(1);
        over.addImage(image);

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

    @RequestMapping("/autoTextPDF")
    public void autoTextPDF(HttpServletResponse response) throws FileNotFoundException, DocumentException {
        String targetPath1 = "C://Users//Koon Fung Yee//Desktop/";
        File file = new File(targetPath1 + "sample.pdf");
        FileOutputStream fos = new FileOutputStream(file);
        Document document = new Document(PageSize.A4, 0, 0, 0, 0);
        PdfWriter writer = PdfWriter.getInstance(document, fos);
        document.open();

        PdfContentByte cb = writer.getDirectContent();
        ColumnText ct = new ColumnText(cb);
        String text = "You want to add this text in a rectangle defined by the coordinates You want to add You want to add this text in a rectangle defined by the coordinates You want to add You want to add this text in a rectangle defined by the coordinates You want to add You want to add this text in a rectangle defined by the coordinates You want to add You want to add this text in a rectangle defined by the coordinates You want to add You want to add this text in a rectangle defined by the coordinates You want to add You want to add this text in a rectangle defined by the coordinates You want to add You want to add this text in a rectangle defined by the coordinates You want to add You want to add this text in a rectangle defined by the coordinates You want to add You want to add this text in a rectangle defined by the coordinates You want to add You want to add this text in a rectangle defined by the coordinates You want to add You want to add this text in a rectangle defined by the coordinates You want to add You want to add this text in a rectangle defined by the coordinates You want to add You want to add this text in a rectangle defined by the coordinates You want to add You want to add this text in a rectangle defined by the coordinates You want to add You want to add this text in a rectangle defined by the coordinates You want to add You want to add this text in a rectangle defined by the coordinates You want to add You want to add this text in a rectangle defined by the coordinates You want to add You want to add this text in a rectangle defined by the coordinates You want to add You want to add this text in a rectangle defined by the coordinates You want to add You want to add this text in a rectangle defined by the coordinates You want to add You want to add this text in a rectangle defined by the coordinates You want to add You want to add this text in a rectangle defined by the coordinates You want to add You want to add this text in a rectangle defined by the coordinates You want to add You want to add this text in a rectangle defined by the coordinates You want to add You want to add this text in a rectangle defined by the coordinates You want to add You want to add this text in a rectangle defined by the coordinates You want to add You want to add this text in a rectangle defined by the coordinates You want to add You want to add this text in a rectangle defined by the coordinates You want to add You want to add this text in a rectangle defined by the coordinates You want to add You want to add this text in a rectangle defined by the coordinates You want to add You want to add this text in a rectangle defined by the coordinates You want to add You want to add this text in a rectangle defined by the coordinates You want to add You want to add this text in a rectangle defined by the coordinates You want to add You want to add this text in a rectangle defined by the coordinates You want to add You want to add this text in a rectangle defined by the coordinates You want to add You want to add this text in a rectangle defined by the coordinates You want to add You want to add this text in a rectangle defined by the coordinates You want to add You want to add this text in a rectangle defined by the coordinates You want to add You want to add this text in a rectangle defined by the coordinates You want to add You want to add this text in a rectangle defined by the coordinates You want to add You want to add this text in a rectangle defined by the coordinates You want to add You want to add this text in a rectangle defined by the coordinates You want to add You want to add this text in a rectangle defined by the coordinates You want to add You want to add this text in a rectangle defined by the coordinates You want to add You want to add this text in a rectangle defined by the coordinates You want to add You want to add this text in a rectangle defined by the coordinates You want to add You want to add this text in a rectangle defined by the coordinates You want to add You want to add this text in a rectangle defined by the coordinates You want to add this text in a rectangle defined by the coordinates You want to add this text in a rectangle defined by the coordinates You want to add this text in a rectangle defined by the coordinates You want to add this text in a rectangle defined by the coordinates You want to add this text in a rectangle defined by the coordinates You want to add this text in a rectangle defined by the coordinates You want to add this text in a rectangle defined by the coordinates llx = 36, lly = 600, urx = 200, ury = 800";

        // llx = left
        // lly = top height, up(0 = bottom, 100 = up 100)
        // urx = text width
        // ury = bottom height
        ct.setSimpleColumn(new Phrase(new Chunk(text, FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.NORMAL))),
                30, PageSize.A4.getHeight() - 30, PageSize.A4.getWidth() - 30, 60, 25,
                Element.ALIGN_LEFT | Element.ALIGN_TOP);
        int status1 = ct.go();
        System.out.println(status1);

        document.close();
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

    @RequestMapping("/createPDFTable")
    public void createPDFTable(HttpServletResponse response) throws IOException, DocumentException {
        String title = "\n\n\n\n\n\n\n\nReport\n\nMerchant: AAA SDN BHD\n\nDate : 04-07-2021";
        String[] head = { "Outlet", "Collection Date", "User ID", "Agreement No", "Name", "Amount (RM)" };
        List<String[]> list = new ArrayList<String[]>();
        String[] data = { "00002-000", "10-11-2021", "AAA", "12345678901", "Hello World", "2910.00" };
        for (int i = 0; i < 50; i++) {
            list.add(data);
        }

        String targetPath = "C://Users//Koon Fung Yee//Desktop/";
        String fileName = "sample2.pdf";
        String targetFilePath = targetPath + fileName;

        // Self font
        // String ttfPath = "src/main/resources/templates/" + "SIMYOU.TTF";
        // BaseFont baseFont = BaseFont.createFont(ttfPath, BaseFont.COURIER,
        // BaseFont.NOT_EMBEDDED);

        // System font
        Font titlefont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 15, Font.BOLD);
        Font headerfont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD);
        Font textfont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.BOLD);

        Document document = new Document();
        try {
            int colNum = head.length;
            File file = new File(targetFilePath);
            file.createNewFile();

            document.setPageSize(PageSize.A4);
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();

            // Create table
            PdfPTable table = new PdfPTable(colNum);
            float[] f = new float[6];
            f[0] = 60F;
            f[1] = 80F;
            f[2] = 80F;
            f[3] = 70F;
            f[4] = 170F;
            f[5] = 60F;
            table.setTotalWidth(520);
            table.setLockedWidth(true);
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.setWidths(f);
            table.getDefaultCell().setBorder(1);

            // Create title
            table.addCell(createCell(title, titlefont, Element.ALIGN_LEFT, colNum, false));

            // Create table header
            for (int i = 0; i < colNum; i++) {
                table.addCell(createCell(head[i], headerfont, Element.ALIGN_CENTER, 1, true));
            }

            if (null != list && list.size() > 0) {
                for (String[] strs : list) {
                    for (int i = 0; i < strs.length; i++) {
                        if (i == 0 || i == 1 || i == 3) {
                            table.addCell(createCell(strs[i], textfont, Element.ALIGN_CENTER, 1, true));
                        } else if (i == strs.length - 1) {
                            table.addCell(createCell(strs[i], textfont, Element.ALIGN_RIGHT, 1, true));
                        } else {
                            table.addCell(createCell(strs[i], textfont, Element.ALIGN_LEFT, 1, true));
                        }
                    }
                }
            }
            document.add(table);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (document != null) {
                document.close();
            }
        }
    }

    static private PdfPCell createCell(String value, Font font, int align, int colspan, boolean boderFlag) {
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(align);
        cell.setColspan(colspan);
        cell.setPhrase(new Phrase(value, font));
        cell.setPadding(3.0f);
        if (!boderFlag) {
            cell.setBorder(0);
            cell.setPaddingTop(15.0f);
            cell.setPaddingBottom(8.0f);
        }
        return cell;
    }
}
