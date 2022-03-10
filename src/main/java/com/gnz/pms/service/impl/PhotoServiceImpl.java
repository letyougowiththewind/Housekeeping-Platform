package com.gnz.pms.service.impl;
import com.gnz.pms.dao.IPhotoDAO;
import com.gnz.pms.entities.FileInformation;
import com.gnz.pms.service.IPhotoService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
@Service
public class PhotoServiceImpl implements IPhotoService{

    @Resource
    IPhotoDAO iPhotoDAO;

    @Override
    public boolean photoUpload(FileInformation fileInformation) throws Exception {
        return iPhotoDAO.insert(fileInformation)>0;
    }
}


