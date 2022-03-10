package com.gnz.pms.controller;
import com.gnz.pms.entities.*;
import com.gnz.pms.service.IContractService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import java.util.List;
@Controller
@RequestMapping("/contract/*")
@ResponseBody
public class ContractAction  extends  BaseController{
    @Resource
    private IContractService  contractService;
    @RequestMapping(value = "community",produces = {"text/plain;charset=UTF-8"})
    public List<Community> listCommunities() throws Exception {
        return  contractService.findCommunity();
    }
    @RequestMapping(value = "room",produces = {"text/plain;charset=UTF-8"})
    public List<Room> listRooms(int id) throws Exception{
        return  contractService.findRooms(id);
    }
    @RequestMapping(value = "floor",produces = {"text/plain;charset=UTF-8"} )
    public List<Floor> listFloors(int id) throws Exception{
        return   contractService.findFloors(id);
    }
    @RequestMapping(value = "building",produces = {"text/plain;charset=UTF-8"})
    public List<Building> listBuildings(int id)throws Exception {
        return contractService.findBuildings(id);
    }
    @RequestMapping("add")
    public  boolean  addContract(Contract vo) throws Exception{
        return  contractService.createContract(vo);
    }
    @Override
    public String getDir() {
        return null;
    }
}
