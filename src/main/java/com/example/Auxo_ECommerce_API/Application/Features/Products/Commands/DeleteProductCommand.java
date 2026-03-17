package com.example.Auxo_ECommerce_API.Application.Features.Products.Commands;

import com.example.Auxo_ECommerce_API.Application.Common.ICommand;
import lombok.Data;

import java.util.UUID;

@Data
public class DeleteProductCommand implements ICommand<UUID> {
    private String Id;
}
