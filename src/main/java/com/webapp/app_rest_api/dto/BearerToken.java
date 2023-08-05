package com.webapp.app_rest_api.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BearerToken {
    private String accessToken;
    private String tokenType = "Bearer";
}