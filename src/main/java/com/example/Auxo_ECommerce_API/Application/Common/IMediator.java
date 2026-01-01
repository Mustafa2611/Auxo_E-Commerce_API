package com.example.Auxo_ECommerce_API.Application.Common;

public interface IMediator {
    <TResponse> Result<TResponse> send(ICommand<TResponse> command);
    <TResponse> Result<TResponse> send(IQuery<TResponse> query);
}

