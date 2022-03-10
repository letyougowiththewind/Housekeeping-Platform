package com.gnz.pms.controller;
import com.gnz.pms.entities.*;
import com.gnz.pms.service.IResidenceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/residence/*")
public class ResidenceAaction {
    @Resource
    private IResidenceService  residenceService;
     @RequestMapping("addCommunity")
     public  boolean   createCommunity(Community  community)  throws  Exception{
         return  residenceService.addCommunity(community);
     }
     @RequestMapping("addBuilding")
     public  boolean   createBuilding(Building building) throws  Exception {
         return  residenceService.addBuilding(building);
     }
     @RequestMapping("addFloor")
     public  boolean   createFloor(Floor floor)  throws  Exception{
         return residenceService.addFloor(floor);
     }
     @RequestMapping("addRoom")
     public  boolean   createRoom(Room room)  throws  Exception{
         return  residenceService.addRoom(room);
     }
     @RequestMapping("developer")
     public Developer   getDeveloperByCid(Integer did)  throws  Exception{
         return   residenceService.findDeveloperById(did);
     }
     @RequestMapping("statistics")
     public   List<Statistics>  getStatistics(Integer cid) throws Exception{
         return  residenceService.findStatistics(cid);
     }
}
