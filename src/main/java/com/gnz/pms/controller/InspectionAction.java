package com.gnz.pms.controller;
import com.gnz.pms.entities.Inspection;
import com.gnz.pms.service.IHomeInspectionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import java.util.List;
@Controller
@RequestMapping("/inspection/*")
public class InspectionAction  extends  BaseController {
    @Resource
    private IHomeInspectionService  inspectionService;
     /**
      *  添加验房记录
      * @param item
      * @return
      */
      @RequestMapping("add")
      @ResponseBody
      public  Inspection   addInspectionItem(Inspection  item) throws  Exception{
           if(inspectionService.createInspectionItem(item)) {
               System.out.println(item);
               return item;
           }
           return null;
      }
      /**
       * 查询验房记录
       * @param rid
         * @return
         */
      @RequestMapping("list")
      @ResponseBody
      public List<Inspection> getInspectionItems(Integer  rid) throws  Exception{
          return  (List)inspectionService.findInspectionItemsByRid(rid).get("list");
      }
    /**
     * 删除验房记录
     * @param id
     * @return
     */
    @RequestMapping("remove")
    @ResponseBody
      public  boolean  removeItemById(Integer  id)  throws  Exception{
          return  inspectionService.removeInspectionItemById(id);
      }

    @Override
    public String getDir() {
        return null;
    }
}
