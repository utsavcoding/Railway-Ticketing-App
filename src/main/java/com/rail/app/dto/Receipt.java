package com.rail.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Receipt {
    private String from;
    private String to;
    private User user;
    private Double pricePaid;
}
