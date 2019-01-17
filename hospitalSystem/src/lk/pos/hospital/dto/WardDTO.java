package lk.pos.hospital.dto;

public class WardDTO extends SuperDTO {
    private String wardId;
    private String wardName;

    public WardDTO() {
    }

    public WardDTO(String wardId, String wardName) {
        this.wardId = wardId;
        this.wardName = wardName;
    }

    public String getWardId() {
        return wardId;
    }

    public void setWardId(String wardId) {
        this.wardId = wardId;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    @Override
    public String toString() {
        return "WardDTO{" +
                "wardId='" + wardId + '\'' +
                ", wardName='" + wardName + '\'' +
                '}';
    }
}
