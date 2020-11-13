package com.example.appnuevo.pdfs;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class TickectPDF {
    private static final String TAG = "API";
    private Context context;
    private File pdfFile;
    private Document document;
    private PdfWriter pdfWriter;
    private Paragraph paragraph;
    private Font fTitle = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD);
    private Font fSubTitle = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
    private Font fText = new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.BOLD);
    private Font fHighText = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD);

    public TickectPDF(Context context) {
        this.context = context;
    }

    public void openDocument() {
        createFile();
        try {
            document = new Document(PageSize.A7);
            pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
            document.open();
        } catch (Exception e) {
            Log.e(TAG, "ERROR : " + e.getLocalizedMessage());
        }
    }

    private void createFile() {
        File folder = new File(Environment.getExternalStorageDirectory().toString(), "PDF");

        if (!folder.exists())
            folder.mkdirs();
            pdfFile = new File(folder, "TemplatePDF.pdf");

    }

    public void closeDocument() {
        document.close();
    }

    public void addMetaData(String title, String subject, String author) {
        document.addTitle(title);
        document.addSubject(subject);
        document.addAuthor(author);
    }

    public void addTitles(String title, String subTitle, String date) {

        try {
            paragraph = new Paragraph();
            addChildP(new Paragraph(title, fTitle));
            addChildP(new Paragraph(subTitle, fSubTitle));
            addChildP(new Paragraph(date, fHighText));
            paragraph.setSpacingAfter(30);
            document.add(paragraph);
        } catch (Exception e) {
            Log.e(TAG, "addTitles : " + e.getLocalizedMessage());
        }

    }

    private void addChildP(Paragraph childParagraph) {
        childParagraph.setAlignment(Element.ALIGN_BASELINE);
        paragraph.add(childParagraph);
    }

    public void addParagraph(String text) {
        try {
            paragraph = new Paragraph(text, fText);
            paragraph.setSpacingAfter(5);
            paragraph.setSpacingBefore(5);
            document.add(paragraph);
        } catch (Exception e) {
            Log.e(TAG, "addParagraph : " + e.getLocalizedMessage());
        }
    }

    public void createTable(String[] header, ArrayList<String[]> clients){
        try {
            paragraph = new Paragraph();
            paragraph.setFont(fText);
            PdfPTable pdfPTable = new PdfPTable(header.length);
            pdfPTable.setWidthPercentage(100);
            pdfPTable.setSpacingBefore(20);
            PdfPCell pdfPCell;
            int indexC = 0;
            while (indexC < header.length) {
                pdfPCell = new PdfPCell(new Phrase(header[indexC++], fSubTitle));
                pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfPCell.setBackgroundColor(BaseColor.GREEN);
                pdfPTable.addCell(pdfPCell);
            }
            for(int indexR=0; indexR< clients.size(); indexR++){
                String[] row = clients.get(indexR);
                for(indexC = 0; indexC< clients.size(); indexC++){
                    pdfPCell = new PdfPCell(new Phrase(row[indexC]));
                    pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfPCell.setFixedHeight(40);
                    pdfPTable.addCell(pdfPCell);
                }
            }

            paragraph.add(pdfPTable);
            document.add(paragraph);

        } catch (Exception e) {
            Log.e(TAG, "createTable : " + e.getLocalizedMessage());
        }
    }

    public void appViewPDF(Activity activity){
        if(pdfFile.exists()){
            //Uri uri = Uri.fromFile(pdfFile);
            Uri uri = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName()+".provider", pdfFile);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(uri, "application/pdf");
            try {
                activity.startActivity(intent);
            } catch (ActivityNotFoundException e){
                activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.google.android.apps.pdfviewer")));
                Toast.makeText(activity.getApplicationContext(), "No cuentas con una aplicación para visualizar PDF", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(activity.getApplicationContext(), "El archivo no se encontró", Toast.LENGTH_SHORT).show();
        }
    }



}
