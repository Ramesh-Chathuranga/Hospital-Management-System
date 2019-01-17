package lk.pos.hospital.model;

public class TBClinicDetail {
    private String clinicid;
    private String clinicname;
    private String specialfieldid;
    private String specialfield;
    private double fee;
    private String scheduleid;

    public TBClinicDetail() {
    }

    public TBClinicDetail(String clinicid, String clinicname, String specialfieldid, String specialfield, double fee, String scheduleid) {
        this.setClinicid(clinicid);
        this.setClinicname(clinicname);
        this.setSpecialfieldid(specialfieldid);
        this.setSpecialfield(specialfield);
        this.setFee(fee);
        this.setScheduleid(scheduleid);
    }

    public String getClinicid() {
        return clinicid;
    }

    public void setClinicid(String clinicid) {
        this.clinicid = clinicid;
    }

    public String getClinicname() {
        return clinicname;
    }

    public void setClinicname(String clinicname) {
        this.clinicname = clinicname;
    }

    public String getSpecialfieldid() {
        return specialfieldid;
    }

    public void setSpecialfieldid(String specialfieldid) {
        this.specialfieldid = specialfieldid;
    }

    public String getSpecialfield() {
        return specialfield;
    }

    public void setSpecialfield(String specialfield) {
        this.specialfield = specialfield;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public String getScheduleid() {
        return scheduleid;
    }

    public void setScheduleid(String scheduleid) {
        this.scheduleid = scheduleid;
    }

    @Override
    public String toString() {
        return "TBClinicDetail{" +
                "clinicid='" + clinicid + '\'' +
                ", clinicname='" + clinicname + '\'' +
                ", specialfieldid='" + specialfieldid + '\'' +
                ", specialfield='" + specialfield + '\'' +
                ", fee=" + fee +
                ", scid='" + scheduleid + '\'' +
                '}';
    }
}
