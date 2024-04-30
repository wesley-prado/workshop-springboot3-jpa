package com.codemages.course.entities;

import com.codemages.course.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long    id;
	@JsonFormat(
			shape = JsonFormat.Shape.STRING, pattern = "yyyy-mm-dd'T'HH:mm:ss'Z'", timezone = "GMT"
	)
	private Instant moment;
	private Integer orderStatus;

	@ManyToOne()
	@JoinColumn(name = "client_id")
	private User client;

	public Order() {
	}

	public Order(Long id, Instant moment, User client, OrderStatus orderStatus) {
		this.id = id;
		this.moment = moment;
		this.client = client;
		this.setOrderStatus(orderStatus);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public User getClient() {
		return client;
	}

	public OrderStatus getOrderStatus() {
		return OrderStatus.valueOf(orderStatus);
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		if (orderStatus != null) {
			this.orderStatus = orderStatus.getCode();
		}
	}

	@Override public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Order order = (Order) o;
		return Objects.equals(id, order.id);
	}

	@Override public int hashCode() {
		return Objects.hashCode(id);
	}

	@Override public String toString() {
		return "Order{" +
			   "id=" + id +
			   ", moment=" + moment +
			   ", client=" + client +
			   "}\n";
	}
}
