package lk.pos.hospital.dao.custom;

import lk.pos.hospital.dao.CrudDAO;
import lk.pos.hospital.entity.Bed;

public interface BedDAO extends CrudDAO<Bed,String> {
    public boolean addPatienTOBED(Bed entity)throws Exception;
}
