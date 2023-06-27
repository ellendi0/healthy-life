package com.webapp.app_rest_api.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterDto implements Serializable {
    String username;
    String email;
    String password ;
}