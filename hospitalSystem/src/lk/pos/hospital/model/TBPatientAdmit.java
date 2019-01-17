package lk.pos.hospital.model;

import java.time.LocalDate;

public class TBPatientAdmit {
    private String pid;
    private String pname;
    private String wid;
    private String bid;
    private LocalDate date;
    private LocalDate discharge;
    public TBPatientAdmit() {
    }

    public TBPatientAdmit(String pid, String pname, String wid, String bid, LocalDate date, LocalDate discharge) {
        this.pid = pid;
        this.pname = pname;
        this.wid = wid;
        this.bid = bid;
        this.date = date;
        this.discharge = discharge;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getWid() {
        return wid;
    }

    public void setWid(String wid) {
        this.wid = wid;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDischarge() {
        return discharge;
    }

    public void setDischarge(LocalDate discharge) {
        this.discharge = discharge;
    }

    @Override
    public String toString() {
        return "TBPatientAdmit{" +
                "pid='" + pid + '\'' +
                ", pname='" + pname + '\'' +
                ", wid='" + wid + '\'' +
                ", bid='" + bid + '\'' +
                ", date=" + date +
                ", discharge=" + discharge +
                '}';
    }
}
