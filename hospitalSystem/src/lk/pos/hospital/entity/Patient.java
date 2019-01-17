package lk.pos.hospital.entity;

public class Patient extends  SuperEntity{
    private String pid;
    private String nic;
    private String fullName;
    private String address;
    private String telephoneNO;
    private String email;
    private String gender;

    public Patient() {
    }

    public Patient(String pid, String nic, String fullName, String address, String telephoneNO, String email, String gender) {
        this.pid = pid;
        this.nic = nic;
        this.fullName = fullName;
        this.address = address;
        this.telephoneNO = telephoneNO;
        this.email = email;
        this.gender = gender;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephoneNO() {
        return telephoneNO;
    }

    public void setTelephoneNO(String telephoneNO) {
        this.telephoneNO = telephoneNO;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "pid='" + pid + '\'' +
                ", nic='" + nic + '\'' +
                ", fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                ", telephoneNO='" + telephoneNO + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
