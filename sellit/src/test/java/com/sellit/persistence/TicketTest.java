package com.sellit.persistence;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.sellit.util.DoubleUtil;

public class TicketTest {

	@Test
	public void testEmptyTicketData() {
		Ticket ticket = new Ticket(null, null, null);

		ticket.calculateTotal();

		assertEquals(Double.valueOf(0), ticket.getTotalAmount());
	}

	@Test
	public void testTax1Total() {
		Product product = new Product();
		product.setUnitPrice(1.99);
		product.setTaxable1Flag(true);

		Double taxRate = 0.06;

		Ticket ticket = new Ticket(taxRate, null, null);

		ticket.createTicketData(product);

		ticket.calculateTotal();

		Double expected = DoubleUtil.truncate(product.getUnitPrice() + (product.getUnitPrice() * taxRate));

		assertEquals(expected, ticket.getTotalAmount());
	}

	@Test
	public void testTax2Total() {
		Product product = new Product();
		product.setUnitPrice(1.99);
		product.setTaxable2Flag(true);

		Double taxRate = 0.06;

		Ticket ticket = new Ticket(null, taxRate, null);

		ticket.createTicketData(product);

		ticket.calculateTotal();

		Double expected = DoubleUtil.truncate(product.getUnitPrice() + (product.getUnitPrice() * taxRate));

		assertEquals(expected, ticket.getTotalAmount());
	}

	@Test
	public void testTax3Total() {
		Product product = new Product();
		product.setUnitPrice(1.99);
		product.setTaxable3Flag(true);

		Double taxRate = 0.06;

		Ticket ticket = new Ticket(null, null, taxRate);

		ticket.createTicketData(product);

		ticket.calculateTotal();

		Double expected = DoubleUtil.truncate(product.getUnitPrice() + (product.getUnitPrice() * taxRate));

		assertEquals(expected, ticket.getTotalAmount());
	}

	@Test
	public void testTotal() {
		Product product = new Product();
		product.setUnitPrice(1.99);
		product.setTaxable1Flag(true);
		product.setTaxable3Flag(true);

		Double tax1Rate = 0.06;
		Double tax3Rate = 0.10;

		Ticket ticket = new Ticket(tax1Rate, null, tax3Rate);

		ticket.createTicketData(product);
		ticket.createTicketData(product);
		TicketData ticketData = ticket.createTicketData(product);
		ticketData.setQuantity(2);

		ticket.calculateTotal();

		Double subtotal = product.getUnitPrice() * 4;
		Double tax1 = DoubleUtil.truncate(subtotal * tax1Rate);
		Double tax3 = DoubleUtil.truncate(subtotal * tax3Rate);

		Double expected = DoubleUtil.truncate(subtotal + tax1 + tax3);

		assertEquals(expected, ticket.getTotalAmount());
	}
}
