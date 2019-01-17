package lk.pos.hospital.model;

public class TBWard {
    private String wid;
    private String wname;

    public TBWard() {
    }

    public TBWard(String wid, String wname) {
        this.setWid(wid);
        this.setWname(wname);
    }

    public String getWid() {
        return wid;
    }

    public void setWid(String wid) {
        this.wid = wid;
    }

    public String getWname() {
        return wname;
    }

    public void setWname(String wname) {
        this.wname = wname;
    }

    @Override
    public String toString() {
        return "TBWard{" +
                "wid='" + wid + '\'' +
                ", wname='" + wname + '\'' +
                '}';
    }
}
