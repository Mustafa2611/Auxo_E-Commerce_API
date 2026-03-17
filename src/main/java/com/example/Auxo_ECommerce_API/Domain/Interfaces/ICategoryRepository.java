package com.example.Auxo_ECommerce_API.Domain.Interfaces;

import com.example.Auxo_ECommerce_API.Domain.Entities.Category;
import com.example.Auxo_ECommerce_API.Domain.Entities.Product;
import com.example.Auxo_ECommerce_API.Domain.Enums.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c LEFT JOIN FETCH c.parent WHERE :id IS NULL OR c.id != :id")
    List<Category> findAllWithout(@Param("id") Long id);

    @Query("SELECT c FROM Category c LEFT JOIN FETCH c.parent")
    List<Category> findAll();

    @Query("SELECT c FROM Category c LEFT JOIN FETCH c.parent WHERE c.type = 'MAIN'")
    List<Category> findAllMainSections();

    @Query("SELECT c FROM Category c LEFT JOIN FETCH c.parent WHERE c.id = :id")
    Optional<Category> findById(@Param("id") Long id);

//    @Query("SELECT c FROM Category c WHERE c.type = :type")
//    List<Category> findByType(@Param("type") CategoryType type);

    @Query("SELECT c FROM Category c WHERE c.type = :type AND (:excludeId IS NULL OR c.id != :excludeId)")
    List<Category> findByType(@Param("type") CategoryType type, @Param("excludeId") Long excludeId);

    // Get all main sections with their subsections eagerly
//    @Query("SELECT c FROM Category c LEFT JOIN FETCH c.children ch WHERE c.type = 'MAIN'")
//    List<Category> findMainSectionsWithChildren();
    @Query("SELECT c FROM Category c LEFT JOIN FETCH c.children ch ")
    List<Category> findMainSectionsWithChildren();

    @Query("SELECT c FROM Category c LEFT JOIN FETCH c.parent WHERE c.parent.id = :parentId")
    List<Category> findByParentId(@Param("parentId") Long parentId);

//    @Query("SELECT c FROM Category c LEFT JOIN FETCH c.children ch  ")
}
