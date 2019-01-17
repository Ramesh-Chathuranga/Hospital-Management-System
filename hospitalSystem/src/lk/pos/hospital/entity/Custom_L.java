package lk.pos.hospital.entity;

import lk.pos.hospital.dto.SuperDTO;

import java.time.LocalDate;

public class Custom_L extends SuperEntity {
    private String pId;
    private String pname;
    private String pnic;
    private String sfID;
    private String sf;
    private String drID;
    private String dName;
    private String dNic;
    private LocalDate date;
    private String sid;

    public Custom_L() {
    }

    public Custom_L(String pId, String pname, String pnic, String sfID, String sf, String drID, String dName, String dNic, LocalDate date, String sid) {
        this.setpId(pId);
        this.setPname(pname);
        this.setPnic(pnic);
        this.setSfID(sfID);
        this.setSf(sf);
        this.setDrID(drID);
        this.setdName(dName);
        this.setdNic(dNic);
        this.setSid(sid);
        this.setDate(date);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
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

    public String getSfID() {
        return sfID;
    }

    public void setSfID(String sfID) {
        this.sfID = sfID;
    }

    public String getSf() {
        return sf;
    }

    public void setSf(String sf) {
        this.sf = sf;
    }

    public String getDrID() {
        return drID;
    }

    public void setDrID(String drID) {
        this.drID = drID;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public String getdNic() {
        return dNic;
    }

    public void setdNic(String dNic) {
        this.dNic = dNic;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    @Override
    public String toString() {
        return "Custom_L{" +
                "pId='" + pId + '\'' +
                ", pname='" + pname + '\'' +
                ", pnic='" + pnic + '\'' +
                ", sfID='" + sfID + '\'' +
                ", sf='" + sf + '\'' +
                ", drID='" + drID + '\'' +
                ", dName='" + dName + '\'' +
                ", dNic='" + dNic + '\'' +
                ", date=" + date +
                ", sid='" + sid + '\'' +
                '}';
    }
}
