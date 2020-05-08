package com.example.taskmanager;

public class myproduct {


    private int _id;
    private String _title;
    private String _description;
    private String _date;
    private String _time;
    private String _pend;

    public myproduct(){}

    public myproduct(String title, String description, String date, String time, String pend/*, String oritime*/) {

        this._title = title;
        this._description = description;
        this._date = date;
        this._time = time;
        this._pend = pend;
    }



    public int get_id() {
        return _id;
    }

    public String get_pend() {
        return _pend;
    }

    public void set_pend(String _pend) {
        this._pend = _pend;
    }

    public String get_title() {
        return _title;
    }

    public String get_description() {
        return _description;
    }

    public String get_date() {
        return _date;
    }

    public String get_time() {
        return _time;
    }


    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_title(String _title) {
        this._title = _title;
    }

    public void set_description(String _description) {
        this._description = _description;
    }

    public void set_date(String _date) {
        this._date = _date;
    }

    public void set_time(String _time) {
        this._time = _time;
    }
}

