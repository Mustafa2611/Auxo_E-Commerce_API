package com.example.Auxo_ECommerce_API.Domain.Interfaces;

import com.example.Auxo_ECommerce_API.Domain.Entities.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IProductRepository extends JpaRepository<Product, String> {

    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.category WHERE p.id = :id")
    Optional<Product> findByIdWithCategory(String id);


    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.category")
    List<Product> findAll();



    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.category WHERE :CategoryId IS NULL OR p.category.id = :CategoryId")
    List<Product> findAllWithCategory(@Param("CategoryId") Long CategoryId);

    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.category WHERE p.category.id IN :categoryIds")
    List<Product> findByCategoryIds(@Param("categoryIds") List<Long> categoryIds);

    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.category")
    List<Product> findAllWithCategory();

    @Query("SELECT COUNT(p) FROM Product p WHERE p.category.id = :categoryId")
    int countByCategoryId(@Param("categoryId") Long categoryId);

    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.category ORDER BY p.ViewedCount DESC")
    List<Product> findTopByViewedCount(int limit);
// Spring Data doesn't support LIMIT in JPQL, use Pageable:

    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.category ORDER BY p.ViewedCount DESC")
    List<Product> findTopByViewedCount(Pageable pageable);
}

