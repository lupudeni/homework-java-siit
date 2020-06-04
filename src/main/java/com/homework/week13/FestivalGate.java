package com.homework.week13;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class FestivalGate {
    private Queue<TicketType> ticketQueue = new ConcurrentLinkedDeque<>();

    public void validateTicket(TicketType ticketType) {
        ticketQueue.add(ticketType);
    }

    public TicketType getTicket() {
        return ticketQueue.poll();
    }

    public boolean hasTicket() {
        return !ticketQueue.isEmpty();
    }

}
