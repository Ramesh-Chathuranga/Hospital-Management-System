package lk.pos.hospital.dto;

public class SpecialFieldDTO extends SuperDTO {
    private String fId;
    private String fieldName;
    private String wardId;

    public SpecialFieldDTO() {
    }

    public SpecialFieldDTO(String fId, String fieldName, String wardId) {
        this.setfId(fId);
        this.setFieldName(fieldName);
        this.setWardId(wardId);
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
        return "SpecialFieldDTO{" +
                "fId='" + fId + '\'' +
                ", fieldName='" + fieldName + '\'' +
                ", wardId='" + wardId + '\'' +
                '}';
    }
}
