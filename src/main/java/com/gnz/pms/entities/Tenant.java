package com.gnz.pms.entities;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
public class Tenant  implements Serializable {
    private Integer id;//租户编号
    private String companyname;  //公司名称
    private String phone;  //负责人的电话
    private String managername; //负责人的名字
    private String identity; //身份证号码
    private String identitypositive; //身认证正面照片名称
    private String identitynegative;//身认证反面照片名称
    private String licensepath; //营业执照名称
}
