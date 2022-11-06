package com.binar.kampusmerdeka.controller;

import com.binar.kampusmerdeka.dto.MessageModel;
import com.binar.kampusmerdeka.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.util.UUID;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    InvoiceService invoiceService;

    @GetMapping("/generate/{bookingId}")
    public ResponseEntity generatePdf(@PathVariable UUID bookingId)
    {
        MessageModel messageModel = new MessageModel();
        ByteArrayInputStream inputStream = invoiceService.generateInvoice(bookingId);

        if (inputStream.available() > 0) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=Invoice-MTicket-" + bookingId + ".pdf");

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(new InputStreamResource(inputStream));
        } else {
            messageModel.setStatus(HttpStatus.NO_CONTENT.value());
            messageModel.setMessage("Booking data not found");
            return ResponseEntity.ok().body(messageModel);
        }
    }
}
