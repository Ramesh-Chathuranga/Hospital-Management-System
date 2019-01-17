package lk.pos.hospital.business.custom;

import lk.pos.hospital.business.SuperBO;
import lk.pos.hospital.dto.ClinicDTO;
import lk.pos.hospital.dto.ClinicTimeTableDTO;
import lk.pos.hospital.dto.CustomDTO;

import java.util.List;

public interface ClinicBO extends SuperBO {
    public boolean saveClinic(ClinicDTO dto)throws Exception;
    public boolean updateClinic(ClinicDTO dto)throws Exception;
    public  boolean deleteClinic(ClinicTimeTableDTO object)throws Exception;
    public ClinicDTO searchClinic(String object)throws  Exception;
    public List<ClinicDTO> getAllClinic()throws Exception;
    public String getRegisterID()throws Exception;
    public boolean saveClincToSCXhedule(ClinicTimeTableDTO dto)throws Exception;
    public boolean upDateClinicSchedule(ClinicTimeTableDTO dto)throws Exception;
    public boolean deleteClinicSchedule(ClinicTimeTableDTO dto)throws Exception;
    public ClinicTimeTableDTO getClincSchedule(ClinicTimeTableDTO dto)throws Exception;
    public List<ClinicTimeTableDTO> getAllClinicSchedule()throws Exception;
    public List<CustomDTO> getALLForTable()throws Exception;
}
