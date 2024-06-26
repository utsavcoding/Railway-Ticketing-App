package com.rail.app.db;

import com.rail.app.dto.Seat;
import com.rail.app.exception.ResourceNotAvailableException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
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
                    String seatId=sec + "-" + i + col;
                    SeatAllocation seatAllocation=new SeatAllocation(new Seat(seatId),false);
                    table.put(seatId,seatAllocation);
                }
            }
        }
    }

    public Seat getAvailable(String seatId) throws ResourceNotAvailableException {
        SeatAllocation seatAllocation=table.get(seatId);
        if(seatAllocation.isBooked)
            throw new ResourceNotAvailableException("Sorry! Booking is not available for seatId:"+seatAllocation.seat.getSeatId(), HttpStatus.CONFLICT);
        seatAllocation.isBooked=true;
        return seatAllocation.seat;
    }

    public Seat getFirstAvailableSeat() throws ResourceNotAvailableException {
        for(String seatId: table.keySet()){
            SeatAllocation seatAllocation=table.get(seatId);
            if(!seatAllocation.isBooked){
                seatAllocation.isBooked=true;
                return seatAllocation.seat;
            }
        }
        throw new ResourceNotAvailableException("Sorry! No seats available.");
    }
}
