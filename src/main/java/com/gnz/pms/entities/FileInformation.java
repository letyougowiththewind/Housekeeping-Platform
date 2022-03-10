package com.gnz.pms.entities;

import lombok.Data;

@Data
public class FileInformation {

    private Integer id;

    private String title;

    private String upload_date;

    private String image_name;

    private String file_name;
}

