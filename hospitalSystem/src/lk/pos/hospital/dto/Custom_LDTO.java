package lk.pos.hospital.dto;

import java.time.LocalDate;

public class Custom_LDTO extends SuperDTO {
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

    public Custom_LDTO() {
    }

    public Custom_LDTO(String pId, String pname, String pnic, String sfID, String sf, String drID, String dName, String dNic, LocalDate date, String sid) {
        this.pId = pId;
        this.pname = pname;
        this.pnic = pnic;
        this.sfID = sfID;
        this.sf = sf;
        this.drID = drID;
        this.dName = dName;
        this.dNic = dNic;
        this.date = date;
        this.sid = sid;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    @Override
    public String toString() {
        return "Custom_LDTO{" +
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
