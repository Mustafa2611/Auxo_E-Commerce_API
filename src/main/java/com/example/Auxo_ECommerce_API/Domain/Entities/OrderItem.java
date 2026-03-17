package com.example.Auxo_ECommerce_API.Domain.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
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
//    @Id
//    @UuidGenerator
//    @Column(columnDefinition = "uniqueidentifier")
//    private UUID id;
//@Id
////@GeneratedValue(strategy = GenerationType.UUID)
////@Column(name = "id", columnDefinition = "VARCHAR(36)", updatable = false, nullable = false)
//@GeneratedValue(generator = "UUID")
//@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
//@Column(updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
//private UUID id;

    @Id
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false, length = 36)
    private String id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private int quantity;

}
