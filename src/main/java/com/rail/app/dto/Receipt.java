package com.rail.app.dto;

import lombok.Data;

@Data
public class Receipt {
    private String from;
    private String to;
    private User user;
    private Double pricePaid;
}
