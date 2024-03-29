package at.htl.workshopsystem.pdf;

import at.htl.workshopsystem.controller.database.PartRepository;
import at.htl.workshopsystem.controller.database.TaskPartMappingRepository;
import at.htl.workshopsystem.model.Customer;
import at.htl.workshopsystem.model.Invoice;
import at.htl.workshopsystem.view.controller.FinishTaskId;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import static java.awt.SystemColor.text;

public final class PDFFactory {
    static PartRepository partRepository = new PartRepository();
    static TaskPartMappingRepository taskPartMappingRepository = new TaskPartMappingRepository();
    public static void CreateInvoicePDF(Invoice invoice) throws IOException, DocumentException {
        // Invoice with leading zeros, e.g. 0000001, max is 9999999
        invoice.setId(FinishTaskId.getId());
        String invoiceId = String.format("%07d", invoice.getId());
        String pdfName = "invoice_" + invoiceId + ".pdf";
        Image barcode = CreateBarcodeImage(invoiceId);

        // Create and save PDF
        var doc = new Document();
        String home = System.getProperty("user.home");
        String path = home + "/Downloads/" + pdfName;
        PdfWriter.getInstance(doc, new FileOutputStream(path));
        doc.open();

        // Set the page size and margins
        doc.setPageSize(PageSize.A4);
        doc.setMargins(50, 50, 50, 50);

        // Title
        var titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 22);
        var title = new Paragraph("WorkshopSystem - Invoice", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(20);

        // Invoice ID
        var idFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
        var idParagraph = new Paragraph("Invoice ID: " + invoiceId, idFont);
        idParagraph.setSpacingAfter(10);

        // Customer details
        var customerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
        var customerParagraph = new Paragraph("Customer: " + invoice.getCustomer(), customerFont);
        customerParagraph.setSpacingAfter(10);

        // Invoice details
        var detailsFont = FontFactory.getFont(FontFactory.HELVETICA, 12);
        var dateParagraph = new Paragraph("Date: " + invoice.getDate(), detailsFont);
        var durationParagraph = new Paragraph("Duration: " + invoice.getTotalDuration(), detailsFont);
        var totalParagraph = new Paragraph("Total: " + invoice.getTotalCost(), detailsFont);
        dateParagraph.setSpacingAfter(10);
        durationParagraph.setSpacingAfter(10);
        totalParagraph.setSpacingAfter(10);

        // Table for invoice items
        float[] columnWidths = {1f, 3f, 2f};
        var table = new PdfPTable(columnWidths);
        table.setWidthPercentage(100);
        table.setSpacingAfter(20);

        Font bold = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

        // Header row
        table.addCell(createCell("Item", bold, Element.ALIGN_LEFT));
        table.addCell(createCell("Description", bold, Element.ALIGN_LEFT));
        table.addCell(createCell("Amount", bold, Element.ALIGN_RIGHT));

        // Invoice used Parts, id got from taskpartmappingrepository, then get the part from partrepository

        /*for (int i = 0; i < taskPartMappingRepository.getPartsByTaskId(invoice.getFkTask()).size(); i++) {
            table.addCell(createCell(partRepository.g(taskPartMappingRepository.GetTaskPartMappingByTaskId(invoice.getTask().getId()).get(i).getPartId()).getName(), null, Element.ALIGN_LEFT));
            table.addCell(createCell(partRepository.GetPartById(taskPartMappingRepository.GetTaskPartMappingByTaskId(invoice.getTask().getId()).get(i).getPartId()).getDescription(), null, Element.ALIGN_LEFT));
            table.addCell(createCell(String.valueOf(partRepository.GetPartById(taskPartMappingRepository.GetTaskPartMappingByTaskId(invoice.getTask().getId()).get(i).getPartId()).getPrice()), null, Element.ALIGN_RIGHT));
        }*/

        // Add more rows as needed for your invoice items

        doc.add(title);
        doc.add(idParagraph);
        doc.add(customerParagraph);
        doc.add(dateParagraph);
        doc.add(durationParagraph);
        doc.add(totalParagraph);
        doc.add(table);
        doc.add(barcode);
        // doc.add(barcode);
        doc.close();

        OpenPDF(pdfName);
    }
    static PdfPCell createCell(String text, Font font, int alignment) {
        PdfPCell cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setPadding(5);
        if (font != null) {
            Phrase phrase = new Phrase(text, font);
            cell.addElement(phrase);
        } else {
            cell.addElement(new Phrase(text));
        }
        cell.setHorizontalAlignment(alignment);
        return cell;
    }
    public static Image CreateBarcodeImage(String barcodeText) throws IOException, DocumentException {
        Code39Bean bean = new Code39Bean();

        final int dpi = 100;

        //Configure the barcode generator
        bean.setModuleWidth(UnitConv.in2mm(1.0f / dpi)); //makes the narrow bar, width exactly one pixel
        bean.setWideFactor(3);
        bean.doQuietZone(false);

        //Open output file
        File outputFile = new File("barcode.png");
        OutputStream out = new FileOutputStream(outputFile);

        try {

            //Set up the canvas provider for monochrome PNG output
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(
                    out, "image/x-png", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);

            //Generate the barcode
            bean.generateBarcode(canvas, barcodeText);

            //Signal end of generation
            canvas.finish();
        } finally {
            out.close();
        }

        Image image = Image.getInstance("barcode.png");
        return image;
    }

    public static void OpenPDF(String pdfName) throws IOException {
        String home = System.getProperty("user.home");
        File pdfFile = new File(home + "/Downloads/" + pdfName);
        System.out.println(pdfFile.getAbsolutePath());
        if (pdfFile.exists()) {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(pdfFile);
            } else {
                System.out.println("Awt Desktop is not supported!");
            }

        } else {
            System.out.println("File is not existing!");
        }
    }
}
