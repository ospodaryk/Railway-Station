package com.example.railwaystation.Game;

import com.example.railwaystation.Helpers.CashRegisterManager;
import com.example.railwaystation.Helpers.GeneratingManager;
import com.example.railwaystation.Helpers.MovementManager;
import com.example.railwaystation.Helpers.QueueManager;
import com.example.railwaystation.Interfaces.Generator;
import com.example.railwaystation.Interfaces.Rendering;
import com.example.railwaystation.UI.CanvasManager.Camera2D;
import com.example.railwaystation.UI.DrawingManagement.DrawingManager;
import com.example.railwaystation.Models.QueuePoligon;
import com.example.railwaystation.Models.State;


import java.util.List;

public class GameLoop {
    private boolean _isRunning = true;
    private final List<Generator> _userSources;
    private final Rendering _renderingUnit;
    private final QueueManager _queueManager;
    private final CashRegisterManager _cashRegisterManager;
    private final GeneratingManager _generatingManager;
    public GameLoop(List<Generator> userSources, Rendering renderingUnit, Camera2D camera, QueueManager qManager, CashRegisterManager crMannager){
        if(userSources == null || renderingUnit == null)
            throw new IllegalArgumentException("Parameters can't be null!");
        this._userSources = userSources;
        this._renderingUnit = renderingUnit;
        this._generatingManager = new GeneratingManager(Game.get_currentLevel(), this._userSources);
        this._queueManager = qManager;
        this._cashRegisterManager = crMannager;
    }

    public void animation_step(){
        updateStationState();
        renderNewFrame();
    }

    public void restore(){
        this._isRunning = true;
        Game.get_currentLevel().get_cashRegistersList().forEach(c->c.setState(State.OPEN));

        for (int i = 0; i < Game.get_currentLevel().get_cashRegistersList().size(); i++)
            Game.get_currentLevel().get_cashRegistersList().get(i).setOurQueue(Game.get_currentLevel().get_poligons().get(i).getQueue());

    }

    private void updateStationState(){
        checkDoorsAndNewUsers();
        moveUsers();
        checkRegistryServices();
    }
    private void checkDoorsAndNewUsers(){

        int userNumber = _generatingManager.countUsersInStation();
        boolean isCrowded = _generatingManager.isCrowded(Game.getMaxUserCount());
        if(isCrowded)
            _generatingManager.closeDoors();
        else
            _generatingManager.openDoors();

        int freeSpots = Game.getMaxUserCount() - userNumber;
        var newUsers = _generatingManager.collectUsers(freeSpots);

        Game.setUsersCount(userNumber + newUsers.size());  // better way to control the number of users
    }

    private void checkRegistryServices(){
        var cashRegisters = Game.get_currentLevel().get_cashRegistersList();
        cashRegisters.forEach(_cashRegisterManager::processUser);
    }
    private void moveUsers() {
        var lvl = Game.get_currentLevel();
        QueuePoligon correct_queue;
        var it = lvl.get_movingUsers().iterator();

        while(it.hasNext()) {
            var el = it.next();
            if (el.get_target() != null) {
                correct_queue = el.get_target();
            } else {
                correct_queue = _queueManager.getCorrectQueue(el, lvl.get_poligons());
                el.set_target(correct_queue);
            }

            correct_queue.setPotentialCount(correct_queue.getPotentialCount() + 1);
            try {
                if (el.get_path() == null) {
                    var p = Game.getResolver().get(el.get_birth_place()).get(el.get_target().getQueueTailCoordinates().getPosition());
                    el.set_path(p);
                }

                MovementManager.findNextPos(lvl, correct_queue, el, this._queueManager, it);


            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void renderNewFrame(){
        var drawingManager = new DrawingManager(Game.get_currentLevel(), _renderingUnit);
        drawingManager.clearCanvas();
        drawingManager.drawFrame();
    }

    public boolean isRunning(){
        return _isRunning;
    }
    public void stop(){
        this._isRunning = false;
    }

}

