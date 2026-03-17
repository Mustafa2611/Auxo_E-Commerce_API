package com.example.Auxo_ECommerce_API.Domain.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
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
    private String nameAr;
    private String nameEn;
    private String descriptionAr;
    private String descriptionEn;
    private String InstallmentDetails;
    private double price;
    private int stock;
    private String imageUrl; // ← add this
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
    @Column(name = "ViewedCount", columnDefinition = "int default 0")
    private int ViewedCount;
}