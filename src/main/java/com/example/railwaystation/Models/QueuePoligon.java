package com.example.railwaystation.Models;

//клас для зображення черги, вигляд (координати) черги

import com.example.railwaystation.Interfaces.Rendering;

import java.util.Collection;

public class QueuePoligon extends GameObject {

    private int _potential_count = 0;
    private final Collection<GameObject> _queueCells;
    private volatile OurQueue _queue = new OurQueue();


    public QueuePoligon(Collection<GameObject> queueCells) {
        this._queueCells = queueCells;
    }

    @Override
    public void drawSprite(Rendering ctx){

        // draw N first users from the queue
        _queueCells.forEach(g -> g.drawSprite(ctx));

        if(_queue.getUsers().size() <= 0)
            return;

        var usersToDrawAtStart = getQueue().getUsers().stream()
                .limit(_queueCells.size() - 1)
                .toList();
        usersToDrawAtStart.forEach(u -> u.drawSprite(ctx));

        //if there are no users return

        //else draw the last user
        getQueue().getUsers().stream()
                .toList()
                .get(_queue.getUsers().size() - 1).drawSprite(ctx);
    }



    public Collection<GameObject> getQueueCells() {
        return _queueCells;
    }
    public int getPotentialCount() {
        return _potential_count;
    }
    public void setPotentialCount(int _potential_count) {
        this._potential_count = _potential_count;
    }
    public GameObject getQueueTailCoordinates() {
        GameObject lastElement = null;

        for (GameObject element : _queueCells) {
            lastElement = element;
        }

        return lastElement;
    }
    public synchronized void setQueue(OurQueue _queue) {
        this._queue = _queue;
    }
    public synchronized OurQueue getQueue() {
        return _queue;
    }
}
