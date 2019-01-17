package lk.pos.hospital.entity;

public class Doctor extends SuperEntity {
  private   String drId;
  private   String nic;
  private   String medId;
  private   String fullName;
  private   String address;
  private   String telephone;
  private   String email;
  private   String wardId;
  private   String fieldId;
  private   String gender;
    public Doctor() {
    }

    public Doctor(String drId, String nic, String medId, String fullName, String address, String telephone, String email, String wardId, String fieldId, String gender) {
        this.setDrId(drId);
        this.setNic(nic);
        this.setMedId(medId);
        this.setFullName(fullName);
        this.setAddress(address);
        this.setTelephone(telephone);
        this.setEmail(email);
        this.setWardId(wardId);
        this.setFieldId(fieldId);
        this.setGender(gender);
    }

    public String getDrId() {
        return drId;
    }

    public void setDrId(String drId) {
        this.drId = drId;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getMedId() {
        return medId;
    }

    public void setMedId(String medId) {
        this.medId = medId;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWardId() {
        return wardId;
    }

    public void setWardId(String wardId) {
        this.wardId = wardId;
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "drId='" + drId + '\'' +
                ", nic='" + nic + '\'' +
                ", medId='" + medId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", wardId='" + wardId + '\'' +
                ", fieldId='" + fieldId + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
