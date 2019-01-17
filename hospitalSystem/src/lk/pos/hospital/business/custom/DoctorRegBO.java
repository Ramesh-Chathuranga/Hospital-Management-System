package lk.pos.hospital.business.custom;

import lk.pos.hospital.business.SuperBO;
import lk.pos.hospital.dto.CustomDTO;
import lk.pos.hospital.dto.DoctorDTO;
import lk.pos.hospital.dto.DoctorTimeTableDTO;
import lk.pos.hospital.entity.CustomEntity;

import java.util.List;

public interface DoctorRegBO extends SuperBO {
    public boolean saveDoctor(DoctorDTO dto) throws Exception;
    public boolean updateDoctor(DoctorDTO dto) throws Exception;
    public boolean deleteDoctor(String rid) throws Exception;
    public DoctorDTO searchDoctor(String rid) throws Exception;
    public List<DoctorDTO> getAllDoctor() throws Exception;
    public String autoDocRegIdDoctor() throws Exception;
    public List<CustomDTO> getAllDetailsByWid(String wid)throws Exception;


    public boolean saveDoctorSchedule(DoctorTimeTableDTO dto)throws Exception;
    public boolean deleteoctorSchedule(DoctorTimeTableDTO dto)throws Exception;
    public DoctorTimeTableDTO searchSchedule(DoctorTimeTableDTO dto)throws Exception;
    public List<DoctorTimeTableDTO> getoctorScheduleDetails()throws Exception;
}