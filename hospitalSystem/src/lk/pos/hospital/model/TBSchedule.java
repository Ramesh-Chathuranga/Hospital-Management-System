package lk.pos.hospital.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class TBSchedule {
    private String scheduleid;
    private String schedulename;
    private String day;
    private LocalDate date;
    private LocalTime starttime;
    private LocalTime endtime;

    public TBSchedule() {
    }

    public TBSchedule(String scheduleid, String schedulename, String day, LocalDate date, LocalTime starttime, LocalTime endtime) {
        this.setScheduleid(scheduleid);
        this.setSchedulename(schedulename);
        this.setDay(day);
        this.setDate(date);
        this.setStarttime(starttime);
        this.setEndtime(endtime);
    }

    public String getScheduleid() {
        return scheduleid;
    }

    public void setScheduleid(String scheduleid) {
        this.scheduleid = scheduleid;
    }

    public String getSchedulename() {
        return schedulename;
    }

    public void setSchedulename(String schedulename) {
        this.schedulename = schedulename;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStarttime() {
        return starttime;
    }

    public void setStarttime(LocalTime starttime) {
        this.starttime = starttime;
    }

    public LocalTime getEndtime() {
        return endtime;
    }

    public void setEndtime(LocalTime endtime) {
        this.endtime = endtime;
    }
}
