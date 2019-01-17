package lk.pos.hospital.business.custom.impl;

import lk.pos.hospital.business.Converter;
import lk.pos.hospital.business.custom.WardBO;
import lk.pos.hospital.dao.DAOFactory;
import lk.pos.hospital.dao.custom.WardDAO;
import lk.pos.hospital.dto.WardDTO;
import lk.pos.hospital.entity.Ward;

import java.util.List;

public class WardBOImpl implements WardBO {
    WardDAO wardDAO= DAOFactory.getInstance().getDAO(DAOFactory.DAOTYPE.WARD);

    @Override
    public boolean addWard(WardDTO wardDTO) throws Exception {
        return wardDAO.save(Converter.getEntity(wardDTO));
    }

    @Override
    public boolean upDateWard(WardDTO wardDTO) throws Exception {
        return wardDAO.updata(Converter.getEntity(wardDTO));
    }

    @Override
    public boolean deleteWard(String wid) throws Exception {
        return wardDAO.delete(wid);
    }

    @Override
    public WardDTO searchWard(String wid) throws Exception {
        return wardDAO.search(wid).map(Converter::<WardDTO>getDTO).orElse(null);
    }

    @Override
    public List<WardDTO> getAllWard() throws Exception {
        return wardDAO.getALL().map(Converter::<WardDTO>getDtoList).get();
    }

    @Override
    public String getGenerateWardID() throws Exception {
        List<Ward> wards =  wardDAO.getALL().get();
        int count = wards.size();
        System.out.println(count+"count");

        if (wards == null|| wards.isEmpty()  || count <= 0) {
            return "W001";
        }
        String setRegID = null;
        Ward register = wards.get(count - 1);
        String wardRID = register.getWardId();
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
}
