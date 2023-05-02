package com.hbase.services;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.hbase.models.Client;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class ClientPDFExporter {
	
	
	private List<Client> listClients ; 
	private XSSFWorkbook workbook;
    private XSSFSheet sheet;
	
	public ClientPDFExporter(List<Client> listClients) {
		super();
		this.listClients = listClients;
	}


	


	public  void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.GRAY);
        cell.setPadding(5);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("CLIENT ID", font));
        table.addCell(cell);
        
//        cell.setPhrase(new Phrase("CIN", font));
//        table.addCell(cell);  
         
        cell.setPhrase(new Phrase("E-mail", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Firstname", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Lastname", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("ADRESSE", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("TEL", font));
        table.addCell(cell);   
        
        cell.setPhrase(new Phrase("DATE NAISSANCE", font));
        table.addCell(cell);   
        
        cell.setPhrase(new Phrase("DATE INSCRIPTION", font));
        table.addCell(cell);  
    }

	
	 private void writeTableData(PdfPTable table) {
	        for (Client client : listClients) {
	            table.addCell(String.valueOf(client.getIdClient()));
	            table.addCell(client.getEmail());
	            table.addCell(client.getFirstname());
	            table.addCell(String.valueOf(client.getLastname()));
	            table.addCell(String.valueOf(client.getAdresse()));
	            table.addCell(String.valueOf(client.getTel()));
	            table.addCell(String.valueOf(client.getDate_naissance()));
	            table.addCell(String.valueOf(client.getDate_inscription()));
	            
	        }
	    }
	     
	    public void export(HttpServletResponse response) throws DocumentException, IOException {
	        Document document = new Document(PageSize.A4);
	        PdfWriter.getInstance(document, response.getOutputStream());
	         
	        document.open();
	        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
	        font.setSize(12);
	        font.setColor(Color.GRAY);
	         
	        Paragraph p = new Paragraph("List of Clients", font);
	        p.setAlignment(Paragraph.ALIGN_CENTER);
	         
	        document.add(p);
	         
	        PdfPTable table = new PdfPTable(8);
	        table.setWidthPercentage(100f);
	        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f, 1.5f, 1.5f, 1.5f});
	        table.setSpacingBefore(10);
	         
	        writeTableHeader(table);
	        writeTableData(table);
	         
	        document.add(table);
	         
	        document.close();
	         
	    }

}
