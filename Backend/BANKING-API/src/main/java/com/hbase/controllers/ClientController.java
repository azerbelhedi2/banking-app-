package com.hbase.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hbase.models.Client;
import com.hbase.services.ClientExcelExporter;
import com.hbase.services.ClientPDFExporter;
import com.hbase.services.ClientService;
import com.lowagie.text.DocumentException;

@RestController
@RequestMapping("/api/clients")
@CrossOrigin(origins = "*")
public class ClientController {

	@Autowired
	ClientService clientService;

	@GetMapping("")
	public List<Client> getAllClients() {
		return clientService.getAllClient();

	}

	@GetMapping("/all")
	public List<Client> getAllClientsQuery() {
		return clientService.getAllClients();

	}

	@GetMapping("/query")
	public List<Client> QueryAllClients() {
		return clientService.QueryAllClient();

	}

	@GetMapping("/{id}")
	public Client getById(@PathVariable("id") long id) {
		return clientService.getClient(id);

	}

	@GetMapping("/get/{cin}")
	public Client getByCin(@PathVariable("cin") String cin) {
		return clientService.getClientByCin(cin);
	}

	@PostMapping("")
	public Client addClient(@RequestBody Client client) {
		clientService.createClient(client);
		return client;
	}

	@PatchMapping("")
	public Client updateClient(@RequestBody Client client) {
		Client existingClient = clientService.getClient(client.getIdClient());
		existingClient.setFirstname(client.getFirstname());
		existingClient.setLastname(client.getLastname());
		existingClient.setEmail(client.getEmail());
		existingClient.setAdresse(client.getAdresse());
		existingClient.setDate_naissance(client.getDate_naissance());
		// existingClient.setDate_inscription(client.getDate_inscription());
		existingClient.setTel(client.getTel());
		existingClient.setCin(client.getCin());
		clientService.updateClient(existingClient);

		return existingClient;

	}

	@DeleteMapping("/delete/{id}")
	public void deleteClient(@PathVariable("id") long id) {
		clientService.deleteClient(id);
	}
	
	@GetMapping("/export/pdf")
	 public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
       response.setContentType("application/pdf");
       DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
       String currentDateTime = dateFormatter.format(new Date());
        
       String headerKey = "Content-Disposition";
       String headerValue = "attachment; filename=Clients_" + currentDateTime + ".pdf";
       response.setHeader(headerKey, headerValue);
        
       List<Client> listUsers = clientService.QueryAllClient();
       ClientPDFExporter exporter = new ClientPDFExporter(listUsers);
       exporter.export(response);
        
   }
	@GetMapping("/export/excel")
	 public void exportToExcel(HttpServletResponse response) throws IOException {
       response.setContentType("application/octet-stream");
       DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
       String currentDateTime = dateFormatter.format(new Date());
        
       String headerKey = "Content-Disposition";
       String headerValue = "attachment; filename=Clients_" + currentDateTime + ".xlsx";
       response.setHeader(headerKey, headerValue);
        
       List<Client> listClients =clientService.QueryAllClient();
        
       ClientExcelExporter excelExporter = new ClientExcelExporter(listClients);
        
       excelExporter.export(response);    
   }  

}
