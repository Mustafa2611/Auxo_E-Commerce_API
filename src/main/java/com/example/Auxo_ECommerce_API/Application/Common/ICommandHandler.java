package com.example.Auxo_ECommerce_API.Application.Common;

public interface ICommandHandler<TCommand,TReturn> {
    Result<TReturn> handle(TCommand command);
}
