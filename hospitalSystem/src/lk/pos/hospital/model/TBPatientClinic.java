package lk.pos.hospital.model;

import java.time.LocalDate;

public class TBPatientClinic {
    private String pid;
    private String pname;
    private String pnic;
    private String sfieldid;
    private String sfname;
    private String did;
    private String dname;
    private String dnic;
    private LocalDate date;
    private String scid;

    public TBPatientClinic() {
    }

    public TBPatientClinic(String pid, String pname, String pnic, String sfieldid, String sfname, String did, String dname, String dnic, LocalDate date, String scid) {
        this.setPid(pid);
        this.setPname(pname);
        this.setPnic(pnic);
        this.setSfieldid(sfieldid);
        this.setSfname(sfname);
        this.setDid(did);
        this.setDname(dname);
        this.setDnic(dnic);
        this.setDate(date);
        this.setScid(scid);
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

    public String getPnic() {
        return pnic;
    }

    public void setPnic(String pnic) {
        this.pnic = pnic;
    }

    public String getSfieldid() {
        return sfieldid;
    }

    public void setSfieldid(String sfieldid) {
        this.sfieldid = sfieldid;
    }

    public String getSfname() {
        return sfname;
    }

    public void setSfname(String sfname) {
        this.sfname = sfname;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDnic() {
        return dnic;
    }

    public void setDnic(String dnic) {
        this.dnic = dnic;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getScid() {
        return scid;
    }

    public void setScid(String scid) {
        this.scid = scid;
    }

    @Override
    public String toString() {
        return "TBPatientClinic{" +
                "pid='" + pid + '\'' +
                ", pname='" + pname + '\'' +
                ", pnic='" + pnic + '\'' +
                ", sfieldid='" + sfieldid + '\'' +
                ", sfname='" + sfname + '\'' +
                ", did='" + did + '\'' +
                ", dname='" + dname + '\'' +
                ", dnic='" + dnic + '\'' +
                ", date=" + date +
                ", scid='" + scid + '\'' +
                '}';
    }
}
