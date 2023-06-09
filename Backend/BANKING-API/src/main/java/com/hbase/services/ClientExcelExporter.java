package com.hbase.services;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.hbase.models.Client;


public class ClientExcelExporter {
	
	private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Client> listClients ;
	public ClientExcelExporter(List<Client> listClients) {
		this.listClients = listClients;
		workbook = new XSSFWorkbook();
	} 
	private void writeHeaderLine() {
        sheet = workbook.createSheet("Users");
         
        Row row = sheet.createRow(0);
         
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
         
        createCell(row, 0, "CLIENT ID", style);      
        createCell(row, 1, "E-mail", style);       
        createCell(row, 2, "Full Name", style);    
        createCell(row, 3, "Adresse", style);
        createCell(row, 4, "TEL", style);
        createCell(row, 5, "DATE NAISSANCE", style);
        createCell(row, 6, "DATE INSCRI", style);
         
    }
     
    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }
     
    private void writeDataLines() {
        int rowCount = 1;
 
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
                 
        for (Client client : listClients) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
             
            createCell(row, columnCount++, client.getIdClient(), style);
            createCell(row, columnCount++, client.getEmail(), style);
            createCell(row, columnCount++, client.getFirstname()+" "+client.getLastname(), style);
            createCell(row, columnCount++, client.getAdresse(), style);
            createCell(row, columnCount++, client.getTel(), style);
            createCell(row, columnCount++,client.getDate_inscription(), style);
            createCell(row, columnCount++,client.getDate_naissance(), style);
          
             
        }
    }
     
    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();
         
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
         
        outputStream.close();
         
    }
    
    

}
