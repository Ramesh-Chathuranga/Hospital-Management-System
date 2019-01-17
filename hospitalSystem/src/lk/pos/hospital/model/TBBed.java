package lk.pos.hospital.model;

public class TBBed {
    private String wardid;
    private String wardname;
    private String bedid;

    public TBBed() {
    }

    public TBBed(String wardid, String wardname, String bedid) {
        this.setWardid(wardid);
        this.setWardname(wardname);
        this.setBedid(bedid);
    }

    public String getWardid() {
        return wardid;
    }

    public void setWardid(String wardid) {
        this.wardid = wardid;
    }

    public String getWardname() {
        return wardname;
    }

    public void setWardname(String wardname) {
        this.wardname = wardname;
    }

    public String getBedid() {
        return bedid;
    }

    public void setBedid(String bedid) {
        this.bedid = bedid;
    }

    @Override
    public String toString() {
        return "TBBed{" +
                "wardid='" + wardid + '\'' +
                ", wardname='" + wardname + '\'' +
                ", bedid='" + bedid + '\'' +
                '}';
    }
}
