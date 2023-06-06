package at.htl.workshopsystem.pdf;

import at.htl.workshopsystem.model.Customer;
import at.htl.workshopsystem.model.Invoice;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class PDFFactory {
    //invoice id muss noch besser durchdacht werden, weil es sollte mindestens eine 5-Stellige id sein!!!
    public static void CreateInvoicePDF(Invoice invoice) throws IOException, DocumentException {
        String pdfName = "invoice_" + invoice.getId() + ".pdf";
        Image barcode = CreateBarcodeImage(invoice.getId().toString());

        //Create PDF
        var doc = new Document();
        PdfWriter.getInstance(doc, new FileOutputStream(pdfName));
        doc.open();

        var bold = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
        var paragraph = new Paragraph("Test", bold);

        doc.add(paragraph);
        doc.add(barcode);
        doc.close();
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

    public static void OpenPDF() throws IOException {
        File pdfFile = new File("duHs.pdf");
        if (pdfFile.exists()) {

            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(pdfFile);
            } else {
                System.out.println("Awt Desktop is not supported!");
            }

        } else {
            System.out.println("File is not exists!");
        }
    }

    public static void DeletePDF() {
        File pdfFile = new File("duHs.pdf");
        if (pdfFile.delete()) {
            System.out.println("File deleted successfully");
        } else {
            System.out.println("Failed to delete the file");
        }
    }
}
