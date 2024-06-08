package com.rail.app.db;

import com.rail.app.dto.Seat;

import java.util.HashMap;
import java.util.Map;

public class SeatTable {

    private final String[] SECTIONS={"SA","SB"};
    private final String[] COLUMNS ={"A","B","C","D","E","F"};
    private final int ROWS=10;

    class SeatAllocation {
        Seat seat;
        Boolean isBooked;

        SeatAllocation(Seat seat, Boolean isBooked){
            this.seat=seat;
            this.isBooked=false;
        }

        private void isBooked(){
            this.isBooked=true;
        }
    }

    private static final Map<String,SeatAllocation> table=new HashMap<>();

    SeatTable(){
        for(String sec:SECTIONS){
            for(int i=1;i<=ROWS;i++){
                for(String col:COLUMNS){
                    String seatId=sec + "-" + String.valueOf(i) + col;
                    SeatAllocation seatAllocation=new SeatAllocation(new Seat(seatId),false);
                    table.put(seatId,seatAllocation);
                    System.out.println(seatId+" is added");
                }
            }
        }
    }

    /**
     * Updates the seat to mark as booked, otherwise return already booked
     */
    public Boolean update(String seatId){
        SeatAllocation seatAllocation=table.get(seatId);
        if (seatAllocation.isBooked) return false;
        seatAllocation.isBooked=true;
        return true;
    }

    public Seat read(String seatId){
        return table.get(seatId).seat;
    }
}
