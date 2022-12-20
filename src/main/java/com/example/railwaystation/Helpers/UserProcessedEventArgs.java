package com.example.railwaystation.Helpers;

import com.example.railwaystation.Models.UserFiles.User;

public class UserProcessedEventArgs {
    private long _startTime;
    private long _endTime;
    private User _user;

    public UserProcessedEventArgs(long _startTime, long _endTime, User _user) {
        this._startTime = _startTime;
        this._endTime = _endTime;
        this._user = _user;
    }

    public long get_startTime() {
        return _startTime;
    }

    public void set_startTime(long _startTime) {
        this._startTime = _startTime;
    }

    public long get_endTime() {
        return _endTime;
    }

    public void set_endTime(long _endTime) {
        this._endTime = _endTime;
    }

    public User get_user() {
        return _user;
    }

    public void set_user(User _user) {
        this._user = _user;
    }
}
