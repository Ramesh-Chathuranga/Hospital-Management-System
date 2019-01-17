package lk.pos.hospital.dao.custom;

import lk.pos.hospital.dao.SuperDAO;
import lk.pos.hospital.entity.CustomEntity;
import lk.pos.hospital.entity.CustomEntityTwo;
import lk.pos.hospital.entity.Custom_L;
import lk.pos.hospital.entity.Schedule;

import java.util.List;
import java.util.Optional;

public interface QueryDAO extends SuperDAO {

    public Optional<List<CustomEntity>> getClinc()throws Exception;
    public Optional<List<CustomEntityTwo>>getHospitalAdmitDetails()throws Exception;
    public Optional<List<CustomEntity>> getCustomEntityByWidOFField(String id)throws Exception;
    public Optional<List<Schedule>> getScheduleSOfDoctor(String did)throws Exception;
    public Optional<List<Custom_L>> getAllPatient()throws Exception;
}
