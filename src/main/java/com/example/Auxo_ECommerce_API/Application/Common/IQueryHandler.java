package com.example.Auxo_ECommerce_API.Application.Common;

public interface IQueryHandler<TQuery,TReturn>{
    Result<TReturn> handle(TQuery query);
}
