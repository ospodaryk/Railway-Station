package com.example.railwaystation.Models;

import com.example.railwaystation.Models.UserFiles.User;
import com.example.railwaystation.Helpers.Coordinates;
import com.example.railwaystation.MovingLogic.StopWatch;
import javafx.scene.image.Image;

public class CashRegister extends GameObject {
    private volatile OurQueue ourQueue;
    private User processingUser;
    private long secondsToProcessUser = 0;
    private State state;
    private StopWatch timer;
    private volatile boolean isOpen;
    private final Object syncObject = new Object();



    public CashRegister() {
        ourQueue = new OurQueue();
        this.timer = new StopWatch();
    }

    public CashRegister(OurQueue ourQueue, State state, boolean isOpen) {
        this.ourQueue = ourQueue;
        this.state = state;
        this.isOpen = isOpen;
    }

    public CashRegister(Coordinates position, double width, double height, Image sprite, double angle, OurQueue ourQueue, State state, boolean isOpen) {
        super(position, width, height, sprite, angle);
        this.ourQueue = ourQueue;
        this.state = state;
        this.isOpen = isOpen;
    }

    public synchronized OurQueue getOurQueue() {
        return ourQueue;
    }

    public State getState() {
        return state;
    }

    public synchronized boolean isOpen() {
        return isOpen;
    }

    public synchronized void setOurQueue(OurQueue ourQueue) {
        synchronized (syncObject) {
            this.ourQueue = ourQueue;
        }
    }



    public synchronized void setOpen(boolean open) {
        isOpen = open;
        if(!open){
            this.setSprite(new Image("file:src/main/resources/com/example/railwaystation/img/icons/nocash.png"));
        }else {
            this.setSprite(new Image("file:src/main/resources/com/example/railwaystation/img/icons/cash.png"));
        }

    }



    public User getProcessingUser() {
        return processingUser;
    }

    public void setProcessingUser(User processingUser) {
        this.processingUser = processingUser;
    }

    public long getSecondsToProcessUser() {
        return secondsToProcessUser;
    }

    public void setSecondsToProcessUser(long secondsToProcessUser) {
        this.secondsToProcessUser = secondsToProcessUser;
    }

    public void setState(State state) {
        this.state = state;
    }

    public StopWatch getTimer() {
        return timer;
    }

    public void setTimer(StopWatch timer) {
        this.timer = timer;
    }


}
