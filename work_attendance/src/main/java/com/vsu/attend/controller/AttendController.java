package com.vsu.attend.controller;

import com.vsu.attend.entity.Attend;
import com.vsu.attend.service.AttendService;
import com.vsu.attend.vo.QueryCondition;
import com.vsu.common.page.PageQueryBean;
import com.vsu.user.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by vsu on 2018/03/15.
 */
@Controller
@RequestMapping("attend")
public class AttendController {

    @Autowired
    private AttendService attendService;

    @RequestMapping
    public String toAttend(){
        return "attend";
    }

    /**
     * @Author: vsu
     * @Descriptioon: 签到
     * @Date: 2018/03/15
     */
    @RequestMapping("/sign")
    @ResponseBody
    public String signAttend(@RequestBody  Attend attend, HttpSession session){

        attendService.signAttend(attend);

        return "succ";
    }


    /**
     * @Author: vsu
     * @Descriptioon: 分页数据查询
     * @Date: 2018/03/17
     */
    @RequiresPermissions("attend:attendList")//权限
    @RequestMapping("/attendList")
    @ResponseBody
    public PageQueryBean listAttend(QueryCondition condition, HttpSession session){
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("userInfo");
//        User user = (User) session.getAttribute("userInfo");
        String [] rangeDate = condition.getRangeDate().split("/");
        condition.setStartDate(rangeDate[0]);
        condition.setEndDate(rangeDate[1]);
        condition.setUserId(user.getId());
        PageQueryBean result = attendService.listAttend(condition);
        return result;
    }

}
