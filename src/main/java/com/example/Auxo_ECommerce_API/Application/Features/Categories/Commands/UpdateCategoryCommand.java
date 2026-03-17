package com.example.Auxo_ECommerce_API.Application.Features.Categories.Commands;

import com.example.Auxo_ECommerce_API.Application.Common.ICommand;
import com.example.Auxo_ECommerce_API.Domain.Enums.CategoryType;
import lombok.Data;

@Data
public class UpdateCategoryCommand implements ICommand<Long> {
    private Long Id;
    private String NameAr;
    private String NameEn;
    // 🔹 Nullable
    private Long parentId;
    private CategoryType type = CategoryType.MAIN; // default
}
