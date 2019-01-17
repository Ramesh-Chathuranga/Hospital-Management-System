package lk.pos.hospital.model;

public class TBPatientDetail {
    private String pid;
    private String cid;
    private String description;

    public TBPatientDetail() {
    }

    public TBPatientDetail(String pid, String cid, String description) {
        this.setPid(pid);
        this.setCid(cid);
        this.setDescription(description);
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "TBPatientDetail{" +
                "pid='" + pid + '\'' +
                ", cid='" + cid + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
