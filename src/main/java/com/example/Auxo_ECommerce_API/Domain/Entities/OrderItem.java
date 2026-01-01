package com.example.Auxo_ECommerce_API.Domain.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Order_Items")
@Getter
@Setter
@Builder

public class OrderItem {
    @Id
    @UuidGenerator
    @Column(columnDefinition = "uniqueidentifier")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private int quantity;

}
