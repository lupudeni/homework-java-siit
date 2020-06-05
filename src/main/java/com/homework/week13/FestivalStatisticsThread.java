package com.homework.week13;


import lombok.SneakyThrows;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class FestivalStatisticsThread extends Thread {
    private final FestivalGate gate;
    private Map<TicketType, Integer> ticketCountMap;

    private int personCount = 0;
    private int lastPersonCount = 0;

    public FestivalStatisticsThread(FestivalGate gate) {
        this.gate = gate;
        ticketCountMap = Arrays.stream(TicketType.values())
                .collect(Collectors.toConcurrentMap(ticketType -> ticketType, v -> 0));
    }

    @SneakyThrows
    @Override
    public void run() {
        while (gate.hasTicket()) {
            Thread.sleep(10);
            TicketType ticketType = gate.getTicket();
            Integer currentCount = ticketCountMap.get(ticketType);
            ticketCountMap.put(ticketType, ++currentCount);
        }
        lastPersonCount = personCount;

        personCount = ticketCountMap.values().stream()
                .reduce(Integer::sum)
                .orElse(0);

        if (personCount == lastPersonCount) {
            synchronized (gate) {
                gate.notify();
            }
            return;
        }

        System.out.println(LocalTime.now() + " : " + personCount + " people entered");
        ticketCountMap.forEach((ticket, count) -> System.out.println(count + " people have " + ticket));
        System.out.println("");
    }

    public int getPersonCount() {
        return personCount;
    }
}
