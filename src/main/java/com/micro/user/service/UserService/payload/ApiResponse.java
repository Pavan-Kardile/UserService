package com.micro.user.service.UserService.payload;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ApiResponse {
    private String message;
    private boolean success;
    private HttpStatus status;
}
