package lk.pos.hospital.business.custom;

import lk.pos.hospital.business.SuperBO;
import lk.pos.hospital.dto.ScheduleDTO;
import lk.pos.hospital.entity.Schedule;

import java.util.List;

public interface ScheduleBO extends SuperBO {
    public boolean saveSchedule(ScheduleDTO dto)throws Exception;
    public boolean updateSchedule(ScheduleDTO dto)throws Exception;
    public  boolean deleteSchedule(String object)throws Exception;
    public ScheduleDTO searchSchedule(String object)throws  Exception;
    public List<ScheduleDTO> getAllSchedule()throws Exception;
    public String getRegisterID()throws Exception;
    public List<ScheduleDTO> getSchedulesOfDoctor(String id)throws Exception;
}
