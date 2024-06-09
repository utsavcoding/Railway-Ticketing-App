package com.rail.app;

import com.rail.app.dto.*;
import com.rail.app.service.Utils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;

public class SampleBaseTest {
    protected User user1;
    protected User user2;
    protected Receipt receipt;
    protected Seat seat;
    protected Ticket ticket;
    protected AutoCloseable autoCloseable;

    protected String ticketPayload="{\n" +
            "    \"ticketId\": \"01HZWQSHENJKZMTWDTXZ8D3ZYA\",\n" +
            "    \"user\": {\n" +
            "        \"email\": \"johndoe@gmail.com\",\n" +
            "        \"firstName\": \"John\",\n" +
            "        \"lastName\": \"Doe\"\n" +
            "    },\n" +
            "    \"seat\": {\n" +
            "        \"seatId\": \"SA-6F\",\n" +
            "        \"type\": \"Window\"\n" +
            "    },\n" +
            "    \"from\": {\n" +
            "        \"stationId\": \"LDN\",\n" +
            "        \"name\": \"London\"\n" +
            "    },\n" +
            "    \"to\": {\n" +
            "        \"stationId\": \"FRA\",\n" +
            "        \"name\": \"France\"\n" +
            "    }\n" +
            "}";

    protected String receiptPayload="{\n" +
            "    \"from\":\"LDN\",\n" +
            "    \"to\":\"FRA\",\n" +
            "    \"user\":{\n" +
            "        \"firstName\":\"John\",\n" +
            "        \"lastName\":\"Doe\",\n" +
            "        \"email\":\"johndoe@gmail.com\"\n" +
            "    },\n" +
            "    \"pricePaid\":20\n" +
            "}";

    @BeforeEach
    public void setup(){
        autoCloseable= MockitoAnnotations.openMocks(this);
        user1 =new User("johndoe@gmail.com","John","Doe");
        user2 =new User("pirates@gmail.com","Jack","Sparrow");
        receipt=new Receipt("LDN","FRA", user1,20.0);
        seat=new Seat("SA-2F");
        ticket=new Ticket(Utils.randomULIDGenerator(),user1,seat,new Station("LDN"),new Station("FRA"));
    }

    @AfterEach
    public void shutDown() throws Exception {
        autoCloseable.close();
    }
}
