package lk.pos.hospital.entity;

public class SpecificField extends SuperEntity {
    String fId;
    String fieldName;
    String wardId;

    public SpecificField() {
    }

    public SpecificField(String fId, String fieldName, String wardId) {
        this.fId = fId;
        this.fieldName = fieldName;
        this.wardId = wardId;
    }

    public String getfId() {
        return fId;
    }

    public void setfId(String fId) {
        this.fId = fId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getWardId() {
        return wardId;
    }

    public void setWardId(String wardId) {
        this.wardId = wardId;
    }

    @Override
    public String toString() {
        return "SpecificField{" +
                "fId='" + fId + '\'' +
                ", fieldName='" + fieldName + '\'' +
                ", wardId='" + wardId + '\'' +
                '}';
    }
}
