package lk.pos.hospital.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class TBClinicTimeTable {
    private String scheduleid;
    private String day;
    private LocalDate date;
    private LocalTime starttime;
    private LocalTime endtime;
    private String clinicid;
    private String clinicname;
    private String clinicspecialfield;

    public TBClinicTimeTable() {
    }

    public TBClinicTimeTable(String scheduleid, String day, LocalDate date, LocalTime starttime, LocalTime endtime, String clinicid, String clinicname, String clinicspecialfield) {
        this.setScheduleid(scheduleid);
        this.setDay(day);
        this.setDate(date);
        this.setStarttime(starttime);
        this.setEndtime(endtime);
        this.setClinicid(clinicid);
        this.setClinicname(clinicname);
        this.setClinicspecialfield(clinicspecialfield);
    }

    public String getScheduleid() {
        return scheduleid;
    }

    public void setScheduleid(String scheduleid) {
        this.scheduleid = scheduleid;
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

    public String getClinicid() {
        return clinicid;
    }

    public void setClinicid(String clinicid) {
        this.clinicid = clinicid;
    }

    public String getClinicname() {
        return clinicname;
    }

    public void setClinicname(String clinicname) {
        this.clinicname = clinicname;
    }

    public String getClinicspecialfield() {
        return clinicspecialfield;
    }

    public void setClinicspecialfield(String clinicspecialfield) {
        this.clinicspecialfield = clinicspecialfield;
    }

    @Override
    public String toString() {
        return "TBClinicTimeTable{" +
                "scid='" + scheduleid + '\'' +
                ", day='" + day + '\'' +
                ", date=" + date +
                ", starttime=" + starttime +
                ", endtime=" + endtime +
                ", clinicid='" + clinicid + '\'' +
                ", clinicname='" + clinicname + '\'' +
                ", clinicspecialfield='" + clinicspecialfield + '\'' +
                '}';
    }
}
