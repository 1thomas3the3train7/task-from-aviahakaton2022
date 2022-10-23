package com.example.tasksher.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "flight")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date;
    private String  type;
    private String terminal;
    private String codeAK;
    private int numberFlight;
    private String time;
    private int timeMinute;
    private int timeHour;
    private String codeAP;
    private String airport;
    private String typeBC;
    private String numberParkingPlace;
    private String numberGate;
    private int countPassenger;
    private int sumPassenger;
    private boolean performed;
    private boolean completed;
    private int countTime;

    public Flight(LocalDateTime date, String  type, String terminal, String codeAK,
                  int numberFlight, String time, String codeAP,
                  String airport, String typeBC, String numberParkingPlace,
                  String numberGate, int countPassenger,int timeMinute,int timeHour) {
        this.date = date;
        this.type = type;
        this.terminal = terminal;
        this.codeAK = codeAK;
        this.numberFlight = numberFlight;
        this.time = time;
        this.codeAP = codeAP;
        this.airport = airport;
        this.typeBC = typeBC;
        this.numberParkingPlace = numberParkingPlace;
        this.numberGate = numberGate;
        this.countPassenger = countPassenger;
        this.timeMinute = timeMinute;
        this.timeHour = timeHour;
        this.completed = false;
        this.performed = false;
        this.countTime = timeHour * 60 + timeMinute;
        this.sumPassenger = countPassenger;
    }
}
