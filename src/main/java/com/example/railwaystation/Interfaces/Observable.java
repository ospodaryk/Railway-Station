package com.example.railwaystation.Interfaces;

import com.example.railwaystation.Helpers.UserProcessedEventArgs;

public interface Observable {

    public void subscribe(Observer observer);
    public void notifySubscribers(UserProcessedEventArgs user);
    public boolean unsubscribe(Observer observer);
}
