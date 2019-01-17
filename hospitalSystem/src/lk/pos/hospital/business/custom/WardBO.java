package lk.pos.hospital.business.custom;

import lk.pos.hospital.business.SuperBO;
import lk.pos.hospital.dto.WardDTO;

import java.util.List;

public interface WardBO extends SuperBO {
    public boolean addWard(WardDTO wardDTO)throws Exception;
    public boolean upDateWard(WardDTO wardDTO)throws Exception;
    public boolean deleteWard(String wid)throws Exception;
    public WardDTO searchWard(String wid)throws Exception;
    public List<WardDTO> getAllWard()throws Exception;
    public String getGenerateWardID()throws Exception;
}
