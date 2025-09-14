package com.api;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponse {
    private int id;
    private String name;
    private String gender;
    private String email;
    private String status;
}
