package lk.pos.hospital.business.custom.impl;

import lk.pos.hospital.business.Converter;
import lk.pos.hospital.business.custom.BedBO;
import lk.pos.hospital.dao.DAOFactory;
import lk.pos.hospital.dao.custom.BedDAO;
import lk.pos.hospital.dto.BedDTO;
import lk.pos.hospital.entity.Bed;

import java.util.List;

public class BedBOImpl implements BedBO {
    BedDAO bedDAO= DAOFactory.getInstance().getDAO(DAOFactory.DAOTYPE.BED);
    @Override
    public boolean addBED(BedDTO dto) throws Exception {
        return bedDAO.save(Converter.getEntity(dto));
    }

    @Override
    public boolean upDateBEDWithOUTPatient(BedDTO dto) throws Exception {
        return bedDAO.updata(Converter.getEntity(dto));
    }

    @Override
    public boolean deleteBED(String bedID) throws Exception {
        return bedDAO.delete(bedID);
    }

    @Override
    public BedDTO searchBED(String bedID) throws Exception {
        return bedDAO.search(bedID).map( Converter::<BedDTO>getDTO).orElse(null);
    }

    @Override
    public List<BedDTO> getALLBED() throws Exception {
        return bedDAO.getALL().map(Converter::<BedDTO>getDtoList).get();
    }

    @Override
    public String generateBEDID() throws Exception {
        List<Bed> beds =  bedDAO.getALL().get();
        int count = beds.size();
        System.out.println(count+"count");

        if (beds == null|| beds.isEmpty()  || count <= 0) {
            return "B001";
        }
        String setRegID = null;
        Bed register = beds.get(count - 1);
        String wardRID = register.getBesID();
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
    public boolean upDateBEDWithPatient(BedDTO dto) throws Exception {
        return bedDAO.addPatienTOBED(Converter.getEntity(dto));
    }
}
