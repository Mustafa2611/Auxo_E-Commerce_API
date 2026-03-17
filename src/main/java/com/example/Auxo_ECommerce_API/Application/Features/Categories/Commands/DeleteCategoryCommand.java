package com.example.Auxo_ECommerce_API.Application.Features.Categories.Commands;

import com.example.Auxo_ECommerce_API.Application.Common.ICommand;
import lombok.Data;

@Data
public class DeleteCategoryCommand  implements ICommand<Long> {
    private Long Id;
}
