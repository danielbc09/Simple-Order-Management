package com.dany.order.management.commons;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class APIResponse {
    private final boolean success;
    private final String message;

    public boolean isSuccess() {
        return success;
    }
    public String getTimestamp() {
        return LocalDateTime.now().toString();
    }

}
