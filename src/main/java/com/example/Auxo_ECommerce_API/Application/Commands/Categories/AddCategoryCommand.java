package com.example.Auxo_ECommerce_API.Application.Commands.Categories;

import com.example.Auxo_ECommerce_API.Application.Common.ICommand;
import com.example.Auxo_ECommerce_API.Application.Dtos.Cateories.AddCateoryDto;
import lombok.Data;

import java.util.UUID;

@Data
public class AddCategoryCommand implements ICommand<Long> {
    private AddCateoryDto Dto;
}

