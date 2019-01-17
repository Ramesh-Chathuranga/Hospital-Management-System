package lk.pos.hospital.dto;

import java.time.LocalDate;

public class CustomDTOTwo extends SuperDTO {
    private String pid;
    private String pname;
    private String wid;
    private String bid;
    private LocalDate date;
    private LocalDate discharchdate;

    public CustomDTOTwo() {
    }

    public CustomDTOTwo(String pid,String pname ,String wid, String bid, LocalDate date, LocalDate discharchdate) {
        this.pid = pid;
        this.pname=pname;
        this.wid = wid;
        this.bid = bid;
        this.date = date;
        this.discharchdate = discharchdate;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
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

    public LocalDate getDischarchdate() {
        return discharchdate;
    }

    public void setDischarchdate(LocalDate discharchdate) {
        this.discharchdate = discharchdate;
    }

    @Override
    public String toString() {
        return "CustomDTOTwo{" +
                "pid='" + pid + '\'' +
                ", wid='" + wid + '\'' +
                ", bid='" + bid + '\'' +
                ", date=" + date +
                ", discharchdate=" + discharchdate +
                '}';
    }
}
