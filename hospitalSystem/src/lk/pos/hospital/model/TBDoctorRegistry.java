package lk.pos.hospital.model;

public class TBDoctorRegistry {
    private String rid;
    private String nic;
    private String mid;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String wardid;
    private String wardname;
    private String  sfield;
    private String gender;

    public TBDoctorRegistry() {
    }

    public TBDoctorRegistry(String rid, String nic, String mid, String name, String address, String phone, String email, String wardId, String wardname, String sfield, String gender) {
        this.setRid(rid);
        this.setNic(nic);
        this.setMid(mid);
        this.setName(name);
        this.setAddress(address);
        this.setPhone(phone);
        this.setEmail(email);
        this.setWardid(wardId);
        this.setWardname(wardname);
        this.setSfield(sfield);
        this.setGender(gender);
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getSfield() {
        return sfield;
    }

    public void setSfield(String sfield) {
        this.sfield = sfield;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "TBDoctorRegistry{" +
                "rid='" + rid + '\'' +
                ", nic='" + nic + '\'' +
                ", mid='" + mid + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", wardid='" + wardid + '\'' +
                ", wardname='" + wardname + '\'' +
                ", sfield='" + sfield + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
