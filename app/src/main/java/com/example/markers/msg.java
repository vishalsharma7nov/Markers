package com.example.markers;

import android.content.Context;

import java.util.ArrayList;

public class msg {
    Context c;
    ArrayList<msg_info> msg;


    public msg(ArrayList<msg_info> msg) {
        this.msg = msg;
    }

    public ArrayList<msg_info> getMsg() {
        return msg;
    }

    public void setMsg(ArrayList<msg_info> msg) {
        this.msg = msg;
    }


    public class msg_info {


        String id;
        String latitude;
        String longitude;
        String username;


        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public msg_info(String id, String latitude, String longitude, String username)
        {

            this.id = id;
            this.latitude  = latitude;
            this.longitude = longitude;
            this.username  = username;


        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }
    }

}