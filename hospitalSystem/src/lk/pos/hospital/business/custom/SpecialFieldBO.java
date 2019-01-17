package lk.pos.hospital.business.custom;

import lk.pos.hospital.business.SuperBO;
import lk.pos.hospital.dto.SpecialFieldDTO;
import lk.pos.hospital.entity.SpecificField;

import java.util.List;

public interface SpecialFieldBO extends SuperBO {
    public  boolean saveSfield(SpecialFieldDTO dto)throws Exception;
    public  boolean updateSfield(SpecialFieldDTO dto)throws Exception;
    public  boolean deleteSField(String fid)throws Exception;
    public SpecialFieldDTO searchSField(String fid)throws Exception;
    public List<SpecialFieldDTO> getAllSField()throws Exception;
    public  String generateSFieldID()throws Exception;
}
