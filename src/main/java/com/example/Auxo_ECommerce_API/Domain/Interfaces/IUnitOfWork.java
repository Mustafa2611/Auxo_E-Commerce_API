package com.example.Auxo_ECommerce_API.Domain.Interfaces;

import com.example.Auxo_ECommerce_API.Application.Common.Result;

public interface IUnitOfWork {

    IProductRepository Products();
    IUserRepository Users();
    IOrderRepository Orders();
    IOrderItemRepository OrderItems();
    ICategoryRepository Categories();

    Result commit();
    void rollback();
}
