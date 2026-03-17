package com.bhanu.dto;

import lombok.Data;

@Data
public class CreateShortLinkRequest {

    private Long userId;
    private String url;

}
