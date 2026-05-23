package com.yp.ahrenix.service;

import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.yp.ahrenix.entities.Transaction;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class ReceiptService {

    public byte[] generateReceipt(
            Transaction transaction
    ) {

        ByteArrayOutputStream outputStream =
                new ByteArrayOutputStream();

        PdfWriter writer =
                new PdfWriter(outputStream);

        PdfDocument pdf =
                new PdfDocument(writer);

        Document document =
                new Document(pdf);

        document.add(
                new Paragraph(
                        "AHrenix Transaction Receipt"
                )
        );

        document.add(
                new Paragraph(
                        "Reference ID: "
                                + transaction.getReferenceId()
                )
        );

        document.add(
                new Paragraph(
                        "Amount: "
                                + transaction.getAmount()
                )
        );

        document.close();

        return outputStream.toByteArray();
    }

}