package com.example.particlephoton;

public class User {
    private String username;
    private String password;
    private String deviceID;
    private boolean in;

    public User(String username, String password, String deviceID, boolean in) {
        this.username = username;
        this.password = password;
        this.deviceID = deviceID;
        this.in = in;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean getIn() { return in; }

    public String getDeviceID() { return deviceID; }
}
