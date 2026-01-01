package com.example.Auxo_ECommerce_API.Infrustracture.Persistence;

import com.example.Auxo_ECommerce_API.Application.Common.Result;
import com.example.Auxo_ECommerce_API.Domain.Entities.Order;
import com.example.Auxo_ECommerce_API.Domain.Interfaces.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UnitOfWork implements IUnitOfWork {

    @PersistenceContext
    private EntityManager entityManager;

    private final IProductRepository productRepository;
    private final ICategoryRepository categoryRepository;
    private final IOrderRepository Orders;
    private final IOrderItemRepository OrderItems;
    private final IUserRepository Users;
    public UnitOfWork(
            IProductRepository productRepository,
            IOrderRepository orders,
            IOrderItemRepository orderItem,
            IUserRepository users,
            ICategoryRepository categoryRepository
            ) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        Users  = users;
        OrderItems = orderItem;
        Orders= orders;

    }

    @Override
    public IProductRepository Products() {
        return productRepository;
    }

    @Override
    public IUserRepository Users() {
        return null;
    }

    @Override
    public IOrderRepository Orders(){return Orders;}

    @Override
    public IOrderItemRepository OrderItems() {
        return null;
    }

    @Override
    public ICategoryRepository Categories() {
        return categoryRepository;
    }

    @Override
    @Transactional
    public Result commit() {
        try {
            entityManager.flush();
            return Result.success();
        } catch (Exception e) {
            return Result.failure("Failed to commit transaction: " + e.getMessage());
        }
    }

    @Override
    public void rollback() {
        entityManager.clear();
    }
}
