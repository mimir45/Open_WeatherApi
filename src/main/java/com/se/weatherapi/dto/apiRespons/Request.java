package com.se.weatherapi.dto.apiRespons;

import lombok.Getter;

@Getter
public class Request {
    private String type;
    private String query;
    private String language;
    private String unit;
}
