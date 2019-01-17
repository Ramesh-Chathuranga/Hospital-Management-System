package lk.pos.hospital.business.custom;

import lk.pos.hospital.business.SuperBO;
import lk.pos.hospital.dto.BedDTO;
import lk.pos.hospital.entity.Bed;

import java.util.List;

public interface BedBO extends SuperBO {
    public boolean addBED(BedDTO dto)throws Exception;
    public boolean upDateBEDWithOUTPatient(BedDTO dto)throws Exception;
    public boolean deleteBED(String bedID)throws Exception;
    public BedDTO searchBED(String bedID)throws Exception;
    public List<BedDTO> getALLBED()throws Exception;
    public String generateBEDID()throws Exception;
    public boolean upDateBEDWithPatient(BedDTO dto)throws Exception;
}
