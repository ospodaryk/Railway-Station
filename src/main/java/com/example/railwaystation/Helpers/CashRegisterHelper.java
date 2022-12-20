package com.example.railwaystation.Helpers;

import com.example.railwaystation.Models.TicketFIles.Ticket;
import com.example.railwaystation.MovingLogic.StopWatch;

import java.util.List;


public class CashRegisterHelper {
    private static long processTicketTime = 2;

    public static boolean userProcessingTimeExpired(long seconds, StopWatch timer) {
        if(timer.getElapsedTimeSecs() >= seconds)
            return true;
        return false;
    }

    public static long countTicketProcessTime(List<Ticket> tickets) {
        return tickets.size() * processTicketTime;
    }
}
