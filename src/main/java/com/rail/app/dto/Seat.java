package com.rail.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seat {
    private String seatId;
    private static Map<String,String> seatType=new HashMap<>();

    static {
        seatType.put("A","Window");seatType.put("F","Window");
        seatType.put("B","Middle");seatType.put("E","Middle");
        seatType.put("C","Aisle");seatType.put("D","Aisle");
    }

    public String getType(){
        String column=seatId.substring(seatId.length()-1);
        return seatType.get(column);
    }
}
/*

XXXX
XXOX
XXOO

 */