package lk.pos.hospital.model;

public class TBDoctorSchedule {
    private String scheduleid;
    private String regid;
    private String nic;
    private String mid;
    private String name;
    private String tele;

    public TBDoctorSchedule() {
    }

    public TBDoctorSchedule(String scheduleid, String regid, String nic, String mid, String name, String tele) {
        this.setScheduleid(scheduleid);
        this.setRegid(regid);
        this.setNic(nic);
        this.setMid(mid);
        this.setName(name);
        this.setTele(tele);
    }

    public String getScheduleid() {
        return scheduleid;
    }

    public void setScheduleid(String scheduleid) {
        this.scheduleid = scheduleid;
    }

    public String getRegid() {
        return regid;
    }

    public void setRegid(String regid) {
        this.regid = regid;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    @Override
    public String toString() {
        return "TBDoctorSchedule{" +
                "scid='" + scheduleid + '\'' +
                ", regid='" + regid + '\'' +
                ", nic='" + nic + '\'' +
                ", mid='" + mid + '\'' +
                ", name='" + name + '\'' +
                ", tele='" + tele + '\'' +
                '}';
    }
}
