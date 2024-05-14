package com.codemages.course.entities;

import com.codemages.course.entities.pk.OrderItemPK;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_order_item")
public class OrderItem implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private final OrderItemPK id = new OrderItemPK();

	private Integer quantity;
	private Double  price;

	public OrderItem() {}

	public OrderItem(Order order, Product product, Integer quantity, Double price) {
		setOrder(order);
		setProduct(product);
		this.quantity = quantity;
		this.price = price;
	}

	@JsonIgnore
	public Order getOrder() {
		return id.getOrder();
	}

	public void setOrder(Order order) {
		Objects.requireNonNull(order, "Order cannot be null");
		id.setOrder(order);
	}

	public Product getProduct() {
		return id.getProduct();
	}

	public void setProduct(Product product) {
		Objects.requireNonNull(product, "Product cannot be null");
		id.setProduct(product);
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getSubTotal() {
		return getQuantity() * getPrice();
	}

	@Override public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		OrderItem orderItem = (OrderItem) o;
		return Objects.equals(id, orderItem.id);
	}

	@Override public int hashCode() {
		return Objects.hashCode(id);
	}

	@Override public String toString() {
		return "OrderItem{" +
			   "id=" + id.hashCode() +
			   ", quantity=" + quantity +
			   ", price=" + price +
			   "}\n";
	}
}
