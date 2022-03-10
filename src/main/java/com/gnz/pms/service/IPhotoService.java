package com.gnz.pms.service;

import com.gnz.pms.entities.FileInformation;

public interface IPhotoService {
    public boolean photoUpload(FileInformation fileInformation) throws Exception;
}
