package lk.pos.hospital.dao.custom.impl;

import lk.pos.hospital.dao.CrudUtil;
import lk.pos.hospital.dao.custom.ScheduleDAO;
import lk.pos.hospital.entity.Schedule;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SceduleDAOImpl implements ScheduleDAO {
    @Override
    public boolean save(Schedule entity) throws Exception {
        String sql="INSERT INTO schedule VALUES(?,?,?,?,?,?)";
        return CrudUtil.updateQuery(sql,entity.getsId(),entity.getScheduleName(),entity.getDay(),entity.getDate(),entity.getStartTime(),entity.getEndTime())>0;
    }

    @Override
    public boolean updata(Schedule entity) throws Exception {
        String sql="UPDATE schedule SET scheduleName=?,day=?,date=?,startTIME=?,endTIME=? WHERE sid=?";
        return CrudUtil.updateQuery(sql,entity.getScheduleName(),entity.getDay(),entity.getDate(),entity.getStartTime(),entity.getEndTime(),entity.getsId())>0;
    }

    @Override
    public boolean delete(String object) throws Exception {
        String sql="DELETE FROM schedule WHERE sid=?";
        return CrudUtil.updateQuery(sql,object)>0;
    }

    @Override
    public Optional<Schedule> search(String object) throws Exception {
        String sql="SELECT * FROM schedule WHERE sid=?";
        ResultSet set = CrudUtil.exequteQuery(sql, object);
        Schedule schedule=null;
        if(set.next()){
            schedule= new Schedule(set.getString(1),set.getString(2),set.getString(3),set.getDate(4).toLocalDate(),set.getTime(5).toLocalTime(),set.getTime(6).toLocalTime());
        }
        return Optional.ofNullable(schedule);
    }

    @Override
    public Optional<List<Schedule>> getALL() throws Exception {
        String sql="SELECT * FROM schedule ";
        List<Schedule>schedules=new ArrayList<>();
        ResultSet set = CrudUtil.exequteQuery(sql);
        while (set.next()){
            schedules.add(new Schedule(set.getString(1),set.getString(2),set.getString(3),set.getDate(4).toLocalDate(),set.getTime(5).toLocalTime(),set.getTime(6).toLocalTime()));
        }
        return Optional.ofNullable(schedules);
    }
}
