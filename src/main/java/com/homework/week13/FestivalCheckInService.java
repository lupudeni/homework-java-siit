package com.homework.week13;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FestivalCheckInService {

    public static final int POOL_SIZE = 100;

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        TicketType[] ticketTypes = TicketType.values();
        FestivalGate gate = new FestivalGate();

        ScheduledExecutorService attendeeExecutor = Executors.newScheduledThreadPool(POOL_SIZE);
        ScheduledExecutorService statisticExecutor = Executors.newSingleThreadScheduledExecutor();

        FestivalStatisticsThread statsThread = new FestivalStatisticsThread(gate);
        ScheduledFuture<?> statisticsFuture = statisticExecutor.scheduleAtFixedRate(statsThread, 5, 5, TimeUnit.SECONDS);

        List<Future<?>> attendeeFutures = new ArrayList<>();
        for (int i = 0; i < POOL_SIZE; i++) {
            int ticketIndex = getRandomNumberWithLimit(ticketTypes.length);
            TicketType ticket = ticketTypes[ticketIndex];
            attendeeFutures.add(attendeeExecutor.schedule(new FestivalAttendeeThread(ticket, gate), getRandomNumberWithLimit(12), TimeUnit.SECONDS));
        }

        for (Future<?> future : attendeeFutures) {
            future.get();
        }

        synchronized (gate) {
            gate.wait();
        }

        statisticsFuture.cancel(false);

        attendeeExecutor.shutdown();
        statisticExecutor.shutdown();

    }

    public static int getRandomNumberWithLimit(int limit) {
        return (int) (Math.random() * limit);
    }
}
