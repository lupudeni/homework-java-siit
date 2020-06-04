package com.homework.week13;

import lombok.SneakyThrows;

public class FestivalAttendeeThread extends Thread {
    private final TicketType ticketType;
    private final FestivalGate gate;

    public FestivalAttendeeThread(TicketType ticketType, FestivalGate gate) {
        this.ticketType = ticketType;
        this.gate = gate;
    }

    @SneakyThrows
    @Override
    public void run() {
        Thread.sleep(500);
        System.out.println("new attendee thread : " + Thread.currentThread().getName() + " " + ticketType);
        gate.validateTicket(ticketType);
    }
}
