# Railway-Ticketing-App
This project is to develop a train ticket booking system using spring boot application in java.
After understanding the specifications, this app has been built on following assumptions:
1) A single train is going from London to France
2) Only two sections("SA" & "SB") are available for booking. Each section has 10*6 seats with rows going from 1->10 and columns going from A->F.
3) Using /submit/receipt, only 1 seat can be booked at a time. Same user can do multiple bookings.
4) Payload of receipt will have station code in "from" & "to"
5) Seat allocation is automatic and user cannot choose manually
6) User can modify ticket by submitting modified ticket payload, but it will fail if seat is already booked

Sample receipt payload:
```
{
    "from":"LDN",
    "to":"FRA",
    "user":{
        "firstName":"John",
        "lastName":"Doe",
        "email":"johndoe@gmail.com"
    },
    "pricePaid":20
}
```

Sample Ticket Payload:

```
{
    "ticketId": "01HZWQSHENJKZMTWDTXZ8D3ZYA",
    "user": {
        "email": "rajthegame@gmail.com",
        "firstName": "RajKumar",
        "lastName": "Pandey"
    },
    "seat": {
        "seatId": "SA-6F",
        "type": "Window"
    },
    "from": {
        "stationId": "LDN",
        "name": "London"
    },
    "to": {
        "stationId": "FRA",
        "name": "France"
    }
}
```

## How To Run Project
### With Docker
- Goto root of this project directory
- Create docker image using: ``` docker build -t railway-ticketing-app . ```
- Run docker container using the built image: ``` docker run -p 8080:8080 railway-ticketing-app ```

### Without Docker
- Install Java17 and gradle
- Goto root of this project directory
- Start spring boot application using: ``` ./gradlew bootRun ```

Application will be started and running at http://localhost:8080/

### Swagger API Documents
Once the application is up and running, swagger API specifications for this project can be accessed at: http://localhost:8080/swagger-ui/index.html
