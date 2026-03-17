package com.example.Auxo_ECommerce_API.Domain.Entities;

import com.example.Auxo_ECommerce_API.Domain.Enums.CategoryType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameAr;
    private String nameEn;

//    @Column(nullable = false, columnDefinition = "VARCHAR(50) DEFAULT 'MAIN'")
    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    @Builder.Default
    private CategoryType type = CategoryType.MAIN;


    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonBackReference
    private Category parent;

    // 🔹 Children Categories (One parent can have many children)
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Category> children;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}


