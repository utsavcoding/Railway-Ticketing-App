package com.rail.app.dto;


import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
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
