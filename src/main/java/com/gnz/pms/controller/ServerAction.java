package com.gnz.pms.controller;

import com.gnz.pms.entities.server.Server;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 服务器监控
 *
 * @author ruoyi
 */
@Controller
@RequestMapping("/monitor/server")
public class ServerAction extends BaseController
{
    private String prefix = "monitor/server";

    @GetMapping()
    public String server(ModelMap mmap) throws Exception
    {
        Server server = new Server();
        server.copyTo();
        mmap.put("server", server);
        return "server";
    }

    @Override
    public String getDir() {
        return null;
    }
}