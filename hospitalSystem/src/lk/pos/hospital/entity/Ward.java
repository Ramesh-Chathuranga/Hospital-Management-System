package lk.pos.hospital.entity;

public class Ward extends SuperEntity{
   private String wardId;
   private String wardName;

    public Ward() {
    }

    public Ward(String wardId, String wardName) {
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
        return "Ward{" +
                "wardId='" + wardId + '\'' +
                ", wardName='" + wardName + '\'' +
                '}';
    }
}
