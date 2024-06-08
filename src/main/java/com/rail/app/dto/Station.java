package com.rail.app.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Station {

    private String stationId;
    private static Map<String,String> idToNameMap=new HashMap<>();

    static {
        idToNameMap.put("LDN","London");
        idToNameMap.put("FRA","France");
    }

    public String getName(){
        return idToNameMap.get(this.stationId);
    }
}
