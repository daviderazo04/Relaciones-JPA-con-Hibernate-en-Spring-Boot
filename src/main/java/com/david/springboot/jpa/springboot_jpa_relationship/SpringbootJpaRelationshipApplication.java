package com.david.springboot.jpa.springboot_jpa_relationship;

import java.util.Optional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.david.springboot.jpa.springboot_jpa_relationship.entities.Adress;
import com.david.springboot.jpa.springboot_jpa_relationship.entities.Client;
import com.david.springboot.jpa.springboot_jpa_relationship.entities.Invoice;
import com.david.springboot.jpa.springboot_jpa_relationship.repositories.ClientRepository;
import com.david.springboot.jpa.springboot_jpa_relationship.repositories.InvoiceRepository;

@SpringBootApplication
public class SpringbootJpaRelationshipApplication implements CommandLineRunner {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private InvoiceRepository invoiceRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaRelationshipApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		oneToManyInvoiceBidireccional();
	}

	@Transactional
	public void oneToManyInvoiceBidireccional() {
		Client client = new Client("Fran", "Moras");
		Invoice invoice1 = new Invoice("Compras casa", 100L);
		Invoice invoice2 = new Invoice("Compras oficina", 2500L);

		// Relacion oneToMany (un cliente tiene una o varias facturas)
		List<Invoice> invoices = new ArrayList<>();
		invoices.add(invoice1);
		invoices.add(invoice2);
		client.setInvoices(invoices);

		// Contraparte la factura es de un cliente
		invoice1.setClient(client);
		invoice2.setClient(client);

		clientRepository.save(client);

		System.out.println(client.toString());
	}

	@Transactional
	public void removeAddressFindById() {
		Optional<Client> optionalClient = clientRepository.findById(2L);
		optionalClient.ifPresent(client -> {
			Adress address1 = new Adress("El pinar", 324533);
			Adress address2 = new Adress("Vasco de Gama", 9584);

			client.setAddresses(Arrays.asList(address1, address2));

			clientRepository.save(client);

			System.out.println(client);

			Optional<Client> optionalClient2 = clientRepository.findOne(2L);
			optionalClient2.ifPresent(c -> {
				c.getAddresses().remove(address2);
				clientRepository.save(c);
				System.out.println(c);
			});
		});

	}

	@Transactional
	public void removeAddress() {
		Client client = new Client("Fran", "Moras");

		Adress address1 = new Adress("El pinar", 324533);
		Adress address2 = new Adress("Vasco de Gama", 9584);

		client.getAddresses().add(address1);
		client.getAddresses().add(address2);

		clientRepository.save(client);

		System.out.println(client);

		Optional<Client> optionalClient = clientRepository.findById(3L);
		optionalClient.ifPresent(c -> {
			c.getAddresses().remove(address1);
			clientRepository.save(c);
			System.out.println(c);
		});

	}

	@Transactional
	public void oneToMany() {
		Client client = new Client("Fran", "Moras");

		Adress address1 = new Adress("El pinar", 324533);
		Adress address2 = new Adress("Vasco de Gama", 9584);

		client.getAddresses().add(address1);
		client.getAddresses().add(address2);

		clientRepository.save(client);

		System.out.println(client);
	}

	@Transactional
	public void manyToOneFindById() {
		Optional<Client> optionalClient = clientRepository.findById(2L);
		optionalClient.ifPresent(client -> {
			Adress address1 = new Adress("El pinar", 324533);
			Adress address2 = new Adress("Vasco de Gama", 9584);

			client.setAddresses(Arrays.asList(address1, address2));

			clientRepository.save(client);

			System.out.println(client);
		});
	}

	@Transactional
	public void manyToOne() {
		Client client = new Client("Jhon", "Doe");
		clientRepository.save(client);

		Invoice invoice = new Invoice("compras de oficina", 2000L);
		invoice.setClient(client);
		Invoice invoiceDB = invoiceRepository.save(invoice);
		System.out.println(invoiceDB);
	}

	@Transactional
	public void manyToOneFindByIdClient() {
		Optional<Client> optionalClient = clientRepository.findById(1L);
		if (optionalClient.isPresent()) {
			Client client = optionalClient.orElseThrow();
			Invoice invoice = new Invoice("compras de oficina", 2000L);
			invoice.setClient(client);
			Invoice invoiceDB = invoiceRepository.save(invoice);
			System.out.println(invoiceDB);
		}

	}
}
