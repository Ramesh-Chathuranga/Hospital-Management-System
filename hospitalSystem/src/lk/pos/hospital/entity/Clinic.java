package lk.pos.hospital.entity;

public class Clinic extends SuperEntity {
    String cId;
    String clinicName;
    String fieldID;
    double clinicCharge;

    public Clinic() {
    }

    public Clinic(String cId, String clinicName, String fieldID, double clinicCharge) {
        this.cId = cId;
        this.clinicName = clinicName;
        this.fieldID = fieldID;
        this.clinicCharge = clinicCharge;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public String getFieldID() {
        return fieldID;
    }

    public void setFieldID(String fieldID) {
        this.fieldID = fieldID;
    }

    public double getClinicCharge() {
        return clinicCharge;
    }

    public void setClinicCharge(double clinicCharge) {
        this.clinicCharge = clinicCharge;
    }

    @Override
    public String toString() {
        return "Clinic{" +
                "cId='" + cId + '\'' +
                ", clinicName='" + clinicName + '\'' +
                ", fieldID='" + fieldID + '\'' +
                ", clinicCharge=" + clinicCharge +
                '}';
    }
}
