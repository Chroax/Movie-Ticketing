package com.binar.kampusmerdeka.service.impl;

import com.binar.kampusmerdeka.model.*;
import com.binar.kampusmerdeka.repository.*;
import com.binar.kampusmerdeka.service.InvoiceService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.itextpdf.text.pdf.PdfPTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    BookingRepository bookingRepository;
    @Autowired

    BookingDetailsRepository bookingDetailsRepository;
    @Autowired
    CinemaHallRepository cinemaHallRepository;
    @Autowired
    SchedulesRepository schedulesRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    FilmRepository filmRepository;
    @Autowired
    SeatRepository seatRepository;

    @Override
    public ByteArrayInputStream generateInvoice(UUID bookingId) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DecimalFormat indonesiaCurrency = (DecimalFormat) NumberFormat.getNumberInstance();
        Optional<Booking> booking = bookingRepository.findById(bookingId);

        if(booking.isPresent())
        {
            Booking bookings = booking.get();
            Users user = null;
            Schedules schedules = null;
            Films films = null;
            CinemaHall cinemaHall = null;
            List<BookingDetails> bookingDetails;
            bookingDetails = bookingDetailsRepository.findAllBookingDetailById(bookings.getBookingId());
            Optional<Users> isUser = userRepository.findById(bookings.getUserBooking().getUserId());
            if(isUser.isPresent())
                user = isUser.get();

            Optional<Schedules> isSchedule = schedulesRepository.findById(bookings.getSchedulesBook().getScheduleId());
            if(isSchedule.isPresent())
                schedules = isSchedule.get();

            if(isSchedule.isPresent())
            {
                Optional<Films> isFilm = filmRepository.findById(schedules.getFilms().getFilmId());
                if(isFilm.isPresent())
                    films = isFilm.get();
                Optional<CinemaHall> isCinemaHall = cinemaHallRepository.findById(schedules.getCinemaHall().getCinemaHallId());
                if(isCinemaHall.isPresent())
                    cinemaHall = isCinemaHall.get();
            }

            Document document = new Document(PageSize.A4, 0f, 0f, 0f, 0f);
            try{
                PdfPTable table = new PdfPTable(4);
                table.setSpacingBefore(8);
                table.setWidthPercentage(60);
                table.setWidths(new int[]{1, 2, 2, 3});

                Font headFont = new Font(Font.FontFamily.UNDEFINED, 13.5f, Font.BOLD, BaseColor.BLACK);

                PdfPCell hcell;
                hcell = new PdfPCell(new Phrase("No.", headFont));
                hcell.setPadding(3);
                hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(hcell);

                hcell = new PdfPCell(new Phrase("Row", headFont));
                hcell.setPadding(3);
                hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(hcell);

                hcell = new PdfPCell(new Phrase("Seats", headFont));
                hcell.setPadding(3);
                hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(hcell);

                hcell = new PdfPCell(new Phrase("Price", headFont));
                hcell.setPadding(3);
                hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(hcell);

                for (int i = 0; i < bookingDetails.size(); i++) {
                    BookingDetails bookingDetail = bookingDetails.get(i);
                    PdfPCell cell;
                    cell = new PdfPCell(new Phrase(i + 1 + ""));
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);

                    Seats seats = null;

                    if(bookingDetail != null)
                    {
                        Optional<Seats> isSeat = seatRepository.findById(bookingDetail.getSeats().getSeatId());
                        if(isSeat.isPresent())
                            seats = isSeat.get();
                    }

                    if(seats != null)
                    {
                        cell = new PdfPCell(new Phrase(seats.getSeatNumber().charAt(0) + ""));
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);

                        cell = new PdfPCell(new Phrase(seats.getSeatNumber().substring(1)));
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);
                    }

                    if(schedules != null)
                    {
                        String price = "Rp " + indonesiaCurrency.format(schedules.getPrice());
                        cell = new PdfPCell(new Phrase(price));
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);
                    }
                }

                PdfWriter.getInstance(document, out);

                Font titleFont = new Font(Font.FontFamily.UNDEFINED, 18.5f, Font.BOLD, BaseColor.BLACK);
                Font bodyFont = new Font(Font.FontFamily.UNDEFINED, 13.0f, Font.NORMAL, BaseColor.BLACK);
                document.open();

                Paragraph title = new Paragraph("\nInvoice MTicket", titleFont);
                title.setAlignment(Element.ALIGN_CENTER);
                title.setSpacingAfter(10);
                LineSeparator line = new LineSeparator();
                line.setPercentage(90);
                document.add(title);
                document.add(line);

                Paragraph spacing = new Paragraph();
                spacing.setSpacingBefore(7);

                document.add(spacing);
                float[] colWidth = {115, 245, 105, 155};
                PdfPTable customer = new PdfPTable(colWidth);

                customer.addCell(getCell("Book Number"));
                customer.addCell(getCell(": " + bookings.getBookingId()));
                customer.addCell(getCell("Name"));
                if(user != null)
                    customer.addCell(getCell(": " + user.getName()));

                customer.addCell(getCell("Film"));
                if(films != null)
                    customer.addCell(getCell(": " + films.getFilmName()));

                customer.addCell(getCell("Date"));
                if(schedules != null)
                {
                    LocalDate localDate = schedules.getDate();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
                    String showTime = localDate.format(formatter);
                    customer.addCell(getCell(": " + showTime));
                }

                customer.addCell(getCell("Cinema Hall"));
                if(cinemaHall != null)
                    customer.addCell(getCell(": " + cinemaHall.getCinemaHallName()));

                customer.addCell(getCell("Start Time"));
                if(schedules != null)
                {
                    String showTime = schedules.getStartTime() + " - " + schedules.getEndTime();
                    customer.addCell(getCell(": " + showTime));
                }

                document.add(customer);

                document.add(table);

                float[] colWidths = {340, 115, 165};
                PdfPTable payment = new PdfPTable(colWidths);
                payment.setSpacingAfter(10);
                payment.setSpacingBefore(10);
                payment.addCell(getCell(""));
                payment.addCell(getCell("Total Payment"));
                if(schedules != null)
                {
                    float totalPayment = schedules.getPrice() * bookings.getTotalSeat();
                    payment.addCell(getCell(": Rp " + indonesiaCurrency.format(totalPayment)));

                }
                document.add(payment);

                LineSeparator footerLine = new LineSeparator();
                footerLine.setPercentage(38);
                document.add(footerLine);

                Paragraph closingParagraph = new Paragraph("Thank you for your reservation", bodyFont);
                closingParagraph.setAlignment(Element.ALIGN_CENTER);
                document.add(closingParagraph);

                document.close();
            }catch (DocumentException e) {
                return null;
            }
        }
        return new ByteArrayInputStream(out.toByteArray());
    }

    @Override
    public PdfPCell getCell(String text) {
        Font bodyFont = new Font(Font.FontFamily.UNDEFINED, 13.0f, Font.NORMAL, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, bodyFont));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setPadding(4);
        return cell;
    }
}