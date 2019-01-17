package lk.pos.hospital.business.custom.impl;

import lk.pos.hospital.business.Converter;
import lk.pos.hospital.business.custom.ScheduleBO;
import lk.pos.hospital.dao.DAOFactory;
import lk.pos.hospital.dao.custom.QueryDAO;
import lk.pos.hospital.dao.custom.ScheduleDAO;
import lk.pos.hospital.dto.ScheduleDTO;
import lk.pos.hospital.entity.Bed;
import lk.pos.hospital.entity.Schedule;

import java.util.List;

public class ScheduleBOImpl implements ScheduleBO {
    ScheduleDAO scheduleDAO= DAOFactory.getInstance().getDAO(DAOFactory.DAOTYPE.SCHEDULE);
    QueryDAO dao=DAOFactory.getInstance().getDAO(DAOFactory.DAOTYPE.QUERYDAO);
    @Override
    public boolean saveSchedule(ScheduleDTO dto) throws Exception {
        return scheduleDAO.save(Converter.getEntity(dto));
    }

    @Override
    public boolean updateSchedule(ScheduleDTO dto) throws Exception {
        return scheduleDAO.updata(Converter.getEntity(dto));
    }

    @Override
    public boolean deleteSchedule(String object) throws Exception {
        return scheduleDAO.delete(object);
    }

    @Override
    public ScheduleDTO searchSchedule(String object) throws Exception {
        return scheduleDAO.search(object).map(Converter::<ScheduleDTO>getDTO).orElse(null);
    }

    @Override
    public List<ScheduleDTO> getAllSchedule() throws Exception {
        return scheduleDAO.getALL().map(Converter::<ScheduleDTO>getDtoList).get();
    }

    @Override
    public String getRegisterID() throws Exception {
        List<Schedule> schedules =  scheduleDAO.getALL().get();
        int count = schedules.size();
        System.out.println(count+"count");

        if (schedules == null|| schedules.isEmpty()  || count <= 0) {
            return "T001";
        }
        String setRegID = null;
        Schedule register = schedules.get(count - 1);
        String wardRID = register.getsId();
        String firstIndex = wardRID.substring(0, 1);
        String otherIndexs = wardRID.substring(1);

        int y=0;
        int x=0;
        int n=0;

        // System.out.println(wardRID.length() + "" + otherIndexs.length());

        if (Integer.parseInt(wardRID.substring(1, 2)) <9) {
            y=Integer.parseInt(wardRID.substring(1, 2));
            if (Integer.parseInt(wardRID.substring(2, 3)) < 9) {
                //x
                x=Integer.parseInt(wardRID.substring(2, 3));
                if(Integer.parseInt(wardRID.substring(3, 4))<9){
                    //n
                    n=Integer.parseInt(wardRID.substring(3, 4))+1;
                    setRegID = firstIndex + y + x +n;
                }else{
                    //!n
                    n=0;
                    x=x+1;
                    setRegID=firstIndex+y+x+n;
                }

            } else{
                //!x
                x=Integer.parseInt(wardRID.substring(2, 3));

                if(Integer.parseInt(wardRID.substring(3, 4))<9){
                    //n
                    n=Integer.parseInt(wardRID.substring(3, 4))+1;
                    setRegID = firstIndex + y + x +n;
                }else{
                    //!n
                    n=0;
                    n=0;
                    y=y+1;
                    n=Integer.parseInt(wardRID.substring(3, 4));
                    x=x+1;
                    setRegID=firstIndex+y+x+n;
                }

            }
        } else {
            y=Integer.parseInt(wardRID.substring(1, 2));

            if (Integer.parseInt(wardRID.substring(2, 3)) < 9) {
                //x
                x=Integer.parseInt(wardRID.substring(2, 3));
                if(Integer.parseInt(wardRID.substring(3, 4))<9){
                    //n
                    n=Integer.parseInt(wardRID.substring(3, 4))+1;
                    setRegID = firstIndex + y + x +n;
                }else{
                    //!n
                    n=0;
                    x=x+1;
                    setRegID=firstIndex+y+x+n;
                }

            } else{
                //!x
                x=Integer.parseInt(wardRID.substring(2, 3));

                if(Integer.parseInt(wardRID.substring(3, 4))<9){
                    //n
                    n=Integer.parseInt(wardRID.substring(3, 4))+1;
                    setRegID = firstIndex + y + x +n;
                }else{
                    //!n
                    n=0;
                    n=0;
                    y=y+1;
                    n=Integer.parseInt(wardRID.substring(3, 4));
                    x=x+1;
                    setRegID=firstIndex+y+x+n;
                }

            }
        }

        return setRegID;

    }

    @Override
    public List<ScheduleDTO> getSchedulesOfDoctor(String id) throws Exception {
        return dao.getScheduleSOfDoctor(id).map(Converter::<ScheduleDTO>getDtoList).get();
    }
}
