package at.htl.workshopsystem.pdf;

import at.htl.workshopsystem.model.Customer;
import at.htl.workshopsystem.model.Invoice;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
/*import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;*/

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public final class PDFFactory {
    public static void CreateInvoicePDF(Invoice invoice) throws IOException, DocumentException {
        //invoice with leading zeros, e.g. 0000001, max is 9999999
        String invoiceId = String.format("%07d", invoice.getId());
        String pdfName = "invoice_" + invoiceId + ".pdf";
        //Image barcode = CreateBarcodeImage(invoice.getId().toString());

        //Create and save PDF
        var doc = new Document();
        String home = System.getProperty("user.home");
        String path = home + "/Downloads/" + pdfName + ".pdf";
        PdfWriter.getInstance(doc, new FileOutputStream(path));
        doc.open();

        var bold = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
        var paragraph = new Paragraph("Test", bold);

        doc.add(paragraph);
        //doc.add(barcode);
        doc.close();

        OpenPDF(pdfName);
    }
    /*public static Image CreateBarcodeImage(String barcodeText) throws IOException, DocumentException {
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
    }*/

    public static void OpenPDF(String pdfName) throws IOException {
        String home = System.getProperty("user.home");
        File pdfFile = new File(home + pdfName);
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
