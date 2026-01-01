package com.example.Auxo_ECommerce_API.Application.Common;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Result<T> {
    private final boolean isSuccess;
    private final T value;
    private final String error;
    private final List errors;

    private Result(boolean isSuccess, T value, String error, List errors) {
        this.isSuccess = isSuccess;
        this.value = value;
        this.error = error;
        this.errors = errors != null ? errors : new ArrayList<>();
    }

    @Contract(value = "_ -> new", pure = true)
    public static <T> @NotNull Result success(T value) {
        return new Result<>(true, value, null, null);
    }

    @Contract(value = " -> new", pure = true)
    public static @NotNull Result success() {
        return new Result<>(true, null, null, null);
    }

    @Contract(value = "_ -> new", pure = true)
    public static @NotNull Result failure(String error) {
        return new Result<>(false, null, error, null);
    }

    @Contract(value = "_ -> new", pure = true)
    public static @NotNull Result failure(List errors) {
        return new Result<>(false, null, null, errors);
    }

    public boolean isSuccess() { return isSuccess; }
    public boolean isFailure() { return !isSuccess; }
    public T getValue() { return value; }
    public String getError() { return error; }
    public List getErrors() { return errors; }
}
