package com.example.root.saving_pdf_android;

import android.app.TimePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.root.saving_pdf_android.permission.FileUtils;
import com.example.root.saving_pdf_android.permission.PermissionsActivity;
import com.example.root.saving_pdf_android.permission.PermissionsChecker;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import static com.example.root.saving_pdf_android.permission.LogUtils.LOGE;
import static com.example.root.saving_pdf_android.permission.PermissionsActivity.PERMISSION_REQUEST_CODE;
import static com.example.root.saving_pdf_android.permission.PermissionsChecker.REQUIRED_PERMISSION;

public class MainActivity extends AppCompatActivity {

    private EditText edtNo;
    private EditText edtWaktuKeluar;
    private EditText edtWaktuMasuk;
    private EditText edtUraian;

    Context mContext;
    PermissionsChecker checker;
    Random random;
    String kosong;
    int r;
    private Button btnSave;
    private Calendar calendar;
    private LinearLayout divKetukWaktuMasuk;
    private TextView tvWaktuMasuk;
    private LinearLayout divKetukWaktuKeluar;
    private TextView tvWaktuKeluar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        mContext = getApplicationContext();
        checker = new PermissionsChecker(this);
//        r = (random.nextInt(80) + 65 * 600 - 39);
        calendar = Calendar.getInstance();

        divKetukWaktuMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);
                        tvWaktuMasuk.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                }, Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), true).show();
            }
        });

        divKetukWaktuKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);
                        tvWaktuKeluar.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                }, Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), true).show();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checker.lacksPermissions(REQUIRED_PERMISSION)) {
                    PermissionsActivity.startActivityForResult(MainActivity.this, PERMISSION_REQUEST_CODE, REQUIRED_PERMISSION);
                } else {

//                    if (tvWaktuMasuk.getText().toString().equalsIgnoreCase("ketuk untuk menentukan waktu") && tvWaktuKeluar.getText().toString().equalsIgnoreCase("ketuk untuk menentukan waktu")){
//                        kosong = "-";
//                    }
//                    else {
//
//                    }
                    createPdf(FileUtils.getAppPath(mContext) + tvWaktuMasuk.getText().toString().trim() + ".pdf");
                }

            }
        });

    }

    public void createPdf(String dest) {

        if (new File(dest).exists()) {
            new File(dest).delete();
        }

        try {
            /**
             * Creating Document
             */
            Document document = new Document();

            // Location to save
            PdfWriter.getInstance(document, new FileOutputStream(dest));

            // Open to write
            document.open();

            // Document Settings
            document.setPageSize(PageSize.LEGAL);
            document.addCreationDate();
            document.addAuthor("Android School");
            document.addCreator("Pratik Butani");

            /***
             * Variables for further use....
             */
            BaseColor mColorAccent = new BaseColor(0, 153, 204, 255);
            float mHeadingFontSize = 20.0f;
            float mValueFontSize = 26.0f;

            /**
             * How to USE FONT....
             */
            BaseFont urName = BaseFont.createFont("assets/fonts/brandon_medium.otf", "UTF-8", BaseFont.EMBEDDED);

            // LINE SEPARATOR
            LineSeparator lineSeparator = new LineSeparator();
            lineSeparator.setLineColor(new BaseColor(0, 0, 0, 68));

            // Title Order Details...
            // Adding Title....
            Font mOrderDetailsTitleFont = new Font(urName, 36.0f, Font.NORMAL, BaseColor.BLACK);
            Chunk mOrderDetailsTitleChunk = new Chunk("Order Details", mOrderDetailsTitleFont);
            Paragraph mOrderDetailsTitleParagraph = new Paragraph(mOrderDetailsTitleChunk);
            mOrderDetailsTitleParagraph.setAlignment(Element.ALIGN_CENTER);
            document.add(mOrderDetailsTitleParagraph);

//            // Fields of Order Details...
//            // Adding Chunks for Title and value
//            Font mOrderIdFont = new Font(urName, mHeadingFontSize, Font.NORMAL, mColorAccent);
//            Chunk mOrderIdChunk = new Chunk("Order No:", mOrderIdFont);
//            Paragraph mOrderIdParagraph = new Paragraph(mOrderIdChunk);
//            document.add(mOrderIdParagraph);
//
//            Font mOrderIdValueFont = new Font(urName, mValueFontSize, Font.NORMAL, BaseColor.BLACK);
//            Chunk mOrderIdValueChunk = new Chunk("#123123", mOrderIdValueFont);
//            Paragraph mOrderIdValueParagraph = new Paragraph(mOrderIdValueChunk);
//            document.add(mOrderIdValueParagraph);
//
//            // Adding Line Breakable Space....
//            document.add(new Paragraph(""));
//            // Adding Horizontal Line...
//            document.add(new Chunk(lineSeparator));
//            // Adding Line Breakable Space....
//            document.add(new Paragraph(""));
//
//            // Fields of Order Details...
//            Font mOrderDateFont = new Font(urName, mHeadingFontSize, Font.NORMAL, mColorAccent);
//            Chunk mOrderDateChunk = new Chunk("Order Date:", mOrderDateFont);
//            Paragraph mOrderDateParagraph = new Paragraph(mOrderDateChunk);
//            document.add(mOrderDateParagraph);
//
//            Font mOrderDateValueFont = new Font(urName, mValueFontSize, Font.NORMAL, BaseColor.BLACK);
//            Chunk mOrderDateValueChunk = new Chunk("06/07/2017", mOrderDateValueFont);
//            Paragraph mOrderDateValueParagraph = new Paragraph(mOrderDateValueChunk);
//            document.add(mOrderDateValueParagraph);
//
//            document.add(new Paragraph(""));
//            document.add(new Chunk(lineSeparator));
//            document.add(new Paragraph(""));
//
//            // Fields of Order Details...
//            Font mOrderAcNameFont = new Font(urName, mHeadingFontSize, Font.NORMAL, mColorAccent);
//            Chunk mOrderAcNameChunk = new Chunk("Account Name:", mOrderAcNameFont);
//            Paragraph mOrderAcNameParagraph = new Paragraph(mOrderAcNameChunk);
//            document.add(mOrderAcNameParagraph);
//
//            Font mOrderAcNameValueFont = new Font(urName, mValueFontSize, Font.NORMAL, BaseColor.BLACK);
//            Chunk mOrderAcNameValueChunk = new Chunk("Pratik Butani", mOrderAcNameValueFont);
//            Paragraph mOrderAcNameValueParagraph = new Paragraph(mOrderAcNameValueChunk);
//            document.add(mOrderAcNameValueParagraph);


            // Fields of Order Details...
            Font mOrderAcNameFont = new Font(urName, mHeadingFontSize, Font.NORMAL, mColorAccent);
            Chunk mOrderAcNameChunk = new Chunk("JURNAL HARIAN", mOrderAcNameFont);
            Paragraph mOrderAcNameParagraph = new Paragraph(mOrderAcNameChunk);
            mOrderAcNameParagraph.setAlignment(Element.ALIGN_CENTER);
            document.add(mOrderAcNameParagraph);
            document.add(new Paragraph(""));
            document.add(new Paragraph(""));


            PdfPTable tabel = new PdfPTable(4);
            tabel.addCell("No");
            tabel.addCell("Masuk");
            tabel.addCell("Keluar");
            tabel.addCell("Uraian");
//            tabel.set

            tabel.addCell(edtNo.getText().toString().trim());
            tabel.addCell(tvWaktuMasuk.getText().toString().trim());
            tabel.addCell(tvWaktuKeluar.getText().toString().trim());
            tabel.addCell(edtUraian.getText().toString().trim());

            document.add(tabel);
//            document.add(new Paragraph(""));
//            document.add(new Chunk(lineSeparator));
//            document.add(new Paragraph(""));

            document.close();

            Toast.makeText(mContext, "Created... :)", Toast.LENGTH_SHORT).show();

            FileUtils.openFile(mContext, new File(dest));

        } catch (IOException | DocumentException ie) {
            LOGE("createPdf: Error " + ie.getLocalizedMessage());
        } catch (ActivityNotFoundException ae) {
            Toast.makeText(mContext, "No application found to open this file.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == PermissionsActivity.PERMISSIONS_GRANTED) {
            Toast.makeText(mContext, "Permission Granted to Save", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mContext, "Permission not granted, Try again!", Toast.LENGTH_SHORT).show();
        }
    }


    private void initView() {
        edtNo = findViewById(R.id.edt_no);
        edtWaktuKeluar = findViewById(R.id.edt_waktu_keluar);
        edtWaktuMasuk = findViewById(R.id.edt_waktu_masuk);
        edtUraian = findViewById(R.id.edt_uraian);
        btnSave = findViewById(R.id.btn_save);
        divKetukWaktuMasuk = findViewById(R.id.div_ketuk_waktu_masuk);
        tvWaktuMasuk = findViewById(R.id.tv_waktu_masuk);
        divKetukWaktuKeluar = findViewById(R.id.div_ketuk_waktu_keluar);
        tvWaktuKeluar = findViewById(R.id.tv_waktu_keluar);
    }
}
