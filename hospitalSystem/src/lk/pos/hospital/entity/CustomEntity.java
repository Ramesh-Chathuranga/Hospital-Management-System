package lk.pos.hospital.entity;

import java.time.LocalDate;

public class CustomEntity extends SuperEntity {
    private String cid;
    private String clinicName;
    private String fieldId;
    private String field;
    private double clinicCharge;
    private String sceduleid;


    public CustomEntity()  {
    }

    public CustomEntity(String cid, String clinicName, String fieldId, String field, double clinicCharge, String sceduleid) {
        this.cid = cid;
        this.clinicName = clinicName;
        this.fieldId = fieldId;
        this.field = field;
        this.clinicCharge = clinicCharge;
        this.sceduleid = sceduleid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public double getClinicCharge() {
        return clinicCharge;
    }

    public void setClinicCharge(double clinicCharge) {
        this.clinicCharge = clinicCharge;
    }

    public String getSceduleid() {
        return sceduleid;
    }

    public void setSceduleid(String sceduleid) {
        this.sceduleid = sceduleid;
    }

    @Override
    public String toString() {
        return "CustomEntity{" +
                "cid='" + cid + '\'' +
                ", clinicName='" + clinicName + '\'' +
                ", fieldId='" + fieldId + '\'' +
                ", field='" + field + '\'' +
                ", clinicCharge=" + clinicCharge +
                ", sceduleid='" + sceduleid + '\'' +
                '}';
    }
}
