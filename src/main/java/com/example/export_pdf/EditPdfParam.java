package com.example.export_pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;

import java.io.Serializable;
import java.math.BigDecimal;

public class EditPdfParam implements Serializable {

    private static final long serialVersionUID = -6500636239583454158L;

    private BigDecimal x;
    private BigDecimal y;
    private int page;
    private int pdfHeight;
    private int pdfWidth;
    private String text;

    private Font font;
    private float fontSize;
    private BaseColor color;

    public EditPdfParam() {
    }

    public EditPdfParam(BigDecimal x, BigDecimal y, int page, int pdfHeight, int pdfWidth, String text) {
        super();
        this.x = x;
        this.y = y;
        this.page = page;
        this.pdfHeight = pdfHeight;
        this.pdfWidth = pdfWidth;
        this.text = text;
    }

    public BigDecimal getX() {
        return x;
    }

    public void setX(BigDecimal x) {
        this.x = x;
    }

    public BigDecimal getY() {
        return y;
    }

    public void setY(BigDecimal y) {
        this.y = y;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getPdfHeight() {
        return pdfHeight;
    }

    public void setPdfHeight(int pdfHeight) {
        this.pdfHeight = pdfHeight;
    }

    public int getPdfWidth() {
        return pdfWidth;
    }

    public void setPdfWidth(int pdfWidth) {
        this.pdfWidth = pdfWidth;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public BaseColor getColor() {
        return color;
    }

    public void setColor(BaseColor color) {
        this.color = color;
    }

    public float getFontSize() {
        return fontSize;
    }

    public void setFontSize(float fontSize) {
        this.fontSize = fontSize;
    }

}
