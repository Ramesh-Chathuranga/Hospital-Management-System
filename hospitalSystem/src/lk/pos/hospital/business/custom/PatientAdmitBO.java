package lk.pos.hospital.business.custom;

import lk.pos.hospital.business.SuperBO;
import lk.pos.hospital.dto.BedDTO;
import lk.pos.hospital.dto.CustomDTOTwo;
import lk.pos.hospital.dto.HospitalAdmitDTO;

import java.util.List;

public interface PatientAdmitBO extends SuperBO {
    public  boolean savePatentHospitalAdmit(HospitalAdmitDTO dto, BedDTO bdto)throws Exception;
    public  boolean updatePatentHospitalAdmit(HospitalAdmitDTO dto)throws Exception;
    public  boolean deletePatentHospitalAdmit(HospitalAdmitDTO dto)throws Exception;
    public  HospitalAdmitDTO searchPatentHospitalAdmit(HospitalAdmitDTO dto)throws Exception;
    public List<HospitalAdmitDTO> getAllPatentHospitalAdmit()throws Exception;
    public  boolean dischargePatent(HospitalAdmitDTO dto, BedDTO bdto)throws Exception;
    public List<CustomDTOTwo> getAllForTABLE()throws Exception;
}
