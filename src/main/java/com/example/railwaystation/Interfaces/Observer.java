package com.example.railwaystation.Interfaces;

import com.example.railwaystation.Helpers.UserProcessedEventArgs;

public interface Observer {

    public void process(UserProcessedEventArgs user);
}
