package lk.pos.hospital.model;

public class TBSpecialField {
    private String fid;
    private String fname;
    private String wid;

    public TBSpecialField() {
    }

    public TBSpecialField(String fid, String fname, String wid) {
        this.setFid(fid);
        this.setFname(fname);
        this.setWid(wid);
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getWid() {
        return wid;
    }

    public void setWid(String wid) {
        this.wid = wid;
    }

    @Override
    public String toString() {
        return "TBSpecialField{" +
                "fid='" + fid + '\'' +
                ", fname='" + fname + '\'' +
                ", wid='" + wid + '\'' +
                '}';
    }
}
