package com.example.Auxo_ECommerce_API.Infrustracture.Mediator;

import com.example.Auxo_ECommerce_API.Application.Common.*;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Mediator implements IMediator {

    private final ApplicationContext context;

    public Mediator(ApplicationContext context) {
        this.context = context;
    }



    @Override
    @SuppressWarnings("unchecked")
    public <TResponse> Result<TResponse> send(ICommand<TResponse> command) {
        String handlerName = command.getClass().getSimpleName() + "Handler";
        ICommandHandler<ICommand<TResponse>, TResponse> handler =
                (ICommandHandler<ICommand<TResponse>, TResponse>) context.getBean(handlerName);
        return handler.handle(command);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <TResponse> Result<TResponse> send(IQuery<TResponse> query) {
        String handlerName = query.getClass().getSimpleName() + "Handler";
        IQueryHandler<IQuery<TResponse>, TResponse> handler =
                (IQueryHandler<IQuery<TResponse>, TResponse>) context.getBean(handlerName);
        return handler.handle(query);
    }
}