package com.gnz.pms.controller;

import com.gnz.pms.dao.IPhotoDAO;
import com.gnz.pms.entities.FileInformation;
import com.gnz.pms.service.IPhotoService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
@Controller
@RequestMapping("/sysuser/*")
public class PhotoUploadController {

    @Resource
    private IPhotoService photoService;

    @RequestMapping("uploadPre")
    public String uploadPre(){
        return "upload";
    }

    @RequestMapping("photoupload")
    public String photoupload(FileInformation fileInformation, MultipartFile photo, Model model) throws Exception{
        System.out.println("文件名称："+photo.getName());
        System.out.println("文件的原名称："+photo.getOriginalFilename());
        System.out.println("文件大小："+photo.getSize());
        System.out.println("文件类型："+photo.getContentType());

        String filename = photo.getOriginalFilename();
        //赋值
        fileInformation.setUpload_date("2021-11-8");
        fileInformation.setImage_name(photo.getName());
        fileInformation.setTitle(photo.getContentType());
        fileInformation.setFile_name(photo.getOriginalFilename());
        try {
            photo.transferTo(new File("D:"+File.separator+"Software"+File.separator+"ideaProject"+File.separator+"pms"+File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"static"+File.separator+"img"+File.separator+
                    filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        model.addAttribute("handle",photoService.photoUpload(fileInformation));
        model.addAttribute("photo",fileInformation);
        return "basic_gallery";
    }
}
