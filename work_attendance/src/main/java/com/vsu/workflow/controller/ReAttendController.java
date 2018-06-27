package com.vsu.workflow.controller;

import com.vsu.user.entity.User;
import com.vsu.workflow.entity.ReAttend;
import com.vsu.workflow.service.ReAttendService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by vsu on 2018/03/17.
 */
@Controller
@RequestMapping("reAttend")
public class ReAttendController {

    @Autowired
    private ReAttendService reAttendService;


    /**
     * @Author: vsu
     * @Descriptioon: 补签数据页面
     * @Date: 2018/03/17
     */
    @RequestMapping
    public String toReAttend(Model model, HttpSession session){

        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("userInfo");
//        User user = (User) session.getAttribute("userInfo");
        List<ReAttend> reAttendList = reAttendService.listReAttend(user.getUsername());
        model.addAttribute("reAttendList",reAttendList);
        return "reAttend";
    }

    @RequestMapping("/start")
    public void startReAttendFlow(@RequestBody ReAttend reAttend, HttpSession session){
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("userInfo");
//        User user = (User) session.getAttribute("userinfo");
        reAttend.setReAttendStarter(user.getRealName());
        reAttendService.startReAttendFlow(reAttend);
    }

    //    @RequiresRoles("leader")
    @RequiresPermissions("reAttend:list")
    @RequestMapping("/list")
    public String listReAttendFlow(Model model,HttpSession session){
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("userInfo");
//        User user = (User) session.getAttribute("userInfo");
        String userName = user.getUsername();
        List<ReAttend> tasks = reAttendService.listTasks(userName);
        model.addAttribute("tasks",tasks);
        return  "reAttendApprove";
    }

    @RequestMapping("/approve")
    public void approveReAttendFlow(@RequestBody ReAttend reAttend){
        reAttendService.approve(reAttend);
    }

}
