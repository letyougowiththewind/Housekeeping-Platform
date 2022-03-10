package com.gnz.pms.service.impl;
import com.gnz.pms.dao.IContractDAO;
import com.gnz.pms.entities.*;
import com.gnz.pms.service.IContractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;
@Service
public class IContractServiceImpl  implements IContractService {
    @Resource
    private IContractDAO  contractDAO;
    @Override
    public List<Community> findCommunity() throws Exception {
        return contractDAO.selectCommunities();
    }
    @Override
    public List<Building> findBuildings(Integer cid) throws Exception {
        return  contractDAO.selectBuildings(cid);
    }
    @Override
    public List<Floor> findFloors(Integer bid) throws Exception {
        return  contractDAO.selectFloors(bid);
    }
    @Override
    public List<Room> findRooms(Integer fid) throws Exception {
        return  contractDAO.selectRooms(fid);
    }
    @Override
    @Transactional
    public boolean createContract(Contract contract) throws Exception {
        return  contractDAO.insert(contract)>0 && contractDAO.updateRoomStatus(contract.getRoom_id(),contract.getStatus())>0;
    }
}