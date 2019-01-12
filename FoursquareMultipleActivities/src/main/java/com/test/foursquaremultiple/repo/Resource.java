package com.test.foursquaremultiple.repo;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * A generic class that contains data and status about loading this data.
 * Enums show current state of fetching data.
 * <br><strong>Loading</strong>: Data fetch is on progress. Progress dialog can be shown at this state to notify users
 * <br><strong>Canceled</strong>: Progress is canceled before getting the result
 * <br><strong>Error</strong>: There occurred some error. Notify users with message
 * <br><strong>Success</strong>: Data is retrieved successfully. Display or use data as required
 */
public class Resource<T> {

    @NonNull
    public final Status status;
    @Nullable
    public final T data;
    @Nullable
    public final String message;

    private Resource(@NonNull Status status, @Nullable T data,
                     @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(Status.LOADING, data, null);
    }

    public static <T> Resource<T> success(@NonNull T data) {
        return new Resource<>(Status.SUCCESS, data, null);
    }

    public static <T> Resource<T> error(String msg, @Nullable T data) {
        return new Resource<>(Status.ERROR, data, msg);
    }

    public static <T> Resource<T> canceled(@Nullable String msg) {
        return new Resource<>(Status.CANCELED, null, msg);
    }

    public enum Status {IDLE, LOADING, SUCCESS, ERROR, CANCELED}
}