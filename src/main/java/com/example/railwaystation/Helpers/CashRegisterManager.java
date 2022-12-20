package com.example.railwaystation.Helpers;

import com.example.railwaystation.Models.CashRegister;
import com.example.railwaystation.Models.State;
import com.example.railwaystation.Interfaces.Observable;
import com.example.railwaystation.Interfaces.Observer;

import java.util.ArrayList;
import java.util.List;

public class CashRegisterManager implements Observable {
    private QueueManager manager;

    public CashRegisterManager(QueueManager manager) {
        this.manager = manager;
    }

    public void processUser(CashRegister register){

        if(register.getState() == State.CLOSE) {
            return;
        }
        else if(register.getState() == State.OPEN) {
            startProcessingNewUser(register);
        }
        else if (register.getState() == State.WORKING) {

            processUserTickets(register);
        }
    }

    private void startProcessingNewUser(CashRegister register) {
        register.setProcessingUser(register.getOurQueue().getFirsUser());

        if(register.getProcessingUser() == null)
            return;

        //register.getOurQueue().removeFirsUser();

        register.setState(State.WORKING);
        register.setSecondsToProcessUser(
                CashRegisterHelper.countTicketProcessTime(
                        register.getProcessingUser()
                                .getTickets()
                )
        );
        register.getTimer().start();
    }

    private void processUserTickets(CashRegister register) {
        if(CashRegisterHelper.userProcessingTimeExpired(register.getSecondsToProcessUser(), register.getTimer())) {
            var timer =  register.getTimer();
            timer.stop();

            var removedUser = register.getOurQueue().removeFirsUser();
            notifySubscribers(
                    new UserProcessedEventArgs(
                        timer.getStartTime(),
                        timer.getStopTime(),
                        removedUser
                    )
            );

            register.setProcessingUser(null);
            register.setState(State.OPEN);
            // Add event to update Queue Polygon
            manager.moveUsersInQueuePolygon(register.getOurQueue());
        }
    }

    public void updateQueue(){
        //TODO: повідомляє про те що обробили ще одного юзера або взяли ще одного юзера
    }

    public void closeCashRegister(CashRegister register){

        register.setState(State.CLOSE);
        //TODO: закрити касу
//        register.setSprite(new Image("file:src/main/resources/com/example/railwaystation/img/icons/nocash.png"));
    }

    public void openCashRegister(CashRegister register) {

        register.setState(State.OPEN);

        //TODO: відкрити касу
//        register.setSprite(new Image("file:src/main/resources/com/example/railwaystation/img/icons/cash.png"));

    }

    //TODO:move to top
    private List<Observer> _observers = new ArrayList<>();
    @Override
    public void subscribe(Observer observer) {
        if(!_observers.contains(observer))
            _observers.add(observer);
    }
    @Override
    public boolean unsubscribe(Observer observer) {
        return _observers.remove(observer);
    }
    @Override
    public void notifySubscribers(UserProcessedEventArgs args){
        this._observers.forEach(o -> o.process(args));
    }
}
