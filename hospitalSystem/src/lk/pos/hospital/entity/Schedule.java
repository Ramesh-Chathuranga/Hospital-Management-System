package lk.pos.hospital.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Schedule extends SuperEntity {
   private String sId;
   private String scheduleName;
   private String day;
   private LocalDate date;
   private LocalTime startTime;
   private LocalTime endTime;

    public Schedule() {
    }

    public Schedule(String sId, String scheduleName, String day, LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.sId = sId;
        this.scheduleName = scheduleName;
        this.day = day;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getScheduleName() {
        return scheduleName;
    }

    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "sId='" + sId + '\'' +
                ", scheduleName='" + scheduleName + '\'' +
                ", day='" + day + '\'' +
                ", date=" + date +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
