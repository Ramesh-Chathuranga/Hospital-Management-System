package lk.pos.hospital.model;

public class TBPatientRegistry {
    private String id;
    private String nic;
    private String name;
    private String phone;
    private String email;
    private String gender;
    private String address;

    public TBPatientRegistry() {
    }

    public TBPatientRegistry(String id, String nic, String name, String phone, String email, String gender, String address) {
        this.setId(id);
        this.setNic(nic);
        this.setName(name);
        this.setPhone(phone);
        this.setEmail(email);
        this.setGender(gender);
        this.setAddress(address);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "TBPatientRegistry{" +
                "id='" + id + '\'' +
                ", nic='" + nic + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
