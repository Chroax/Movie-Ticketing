package com.binar.kampusmerdeka.service;

import java.io.ByteArrayInputStream;
import java.util.UUID;

import com.itextpdf.text.pdf.PdfPCell;

public interface InvoiceService {
    ByteArrayInputStream generateInvoice(UUID bookingId);
    PdfPCell getCell(String text);
}
