package com.example.Auxo_ECommerce_API.Domain.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Products")

public class Product {
    @Id
    @UuidGenerator
    @Column(columnDefinition = "uniqueidentifier")
    private UUID id;
    private String name;
    private String description;
    private double price;
    private int stock;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}