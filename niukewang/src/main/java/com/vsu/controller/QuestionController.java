package com.vsu.controller;

import com.vsu.model.*;
import com.vsu.service.CommentService;
import com.vsu.service.LikeService;
import com.vsu.service.QuestionService;
import com.vsu.service.UserService;
import com.vsu.util.NKWUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by vsu on 2018/02/12.
 */
@Controller
public class QuestionController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private QuestionService questionService;

    @Autowired
    CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private LikeService likeService;

    @RequestMapping(path = "/question/add",method = RequestMethod.POST)
    @ResponseBody
    public String addQuetion(@RequestParam("title") String title,
                             @RequestParam("content") String content){
        try{
            Question question = new Question();
            question.setContent(content);
            question.setCreatedDate(new Date());
            question.setTitle(title);
            question.setCommentCount(0);
            if (hostHolder.getUser() == null){
                question.setUserId(NKWUtils.ANONYMOUS_USERID);
//                return NKWUtils.getJSONString(999);
            }else {
                question.setUserId(hostHolder.getUser().getId());
            }
            if (questionService.addQuestion(question) > 0){
                return NKWUtils.getJSONString(0);
            }
        }catch (Exception e){
            logger.error("增加题目失败" + e.getMessage());
        }

        return NKWUtils.getJSONString(1, "失败");

    }


    @RequestMapping(value = "/question/{qid}", method = {RequestMethod.GET})
    public String questionDetail(Model model, @PathVariable("qid") int qid) {
        Question question = questionService.getById(qid);
        model.addAttribute("question", question);
//        model.addAttribute("user", userService.getUser(question.getUserId()));
        List<Comment> commentList = commentService.getCommentsByEntity(qid, EntityType.ENTITY_QUESTION);
        List<ViewObject> vos = new ArrayList<>();
        for (Comment comment : commentList) {
            ViewObject vo = new ViewObject();
            vo.set("comment", comment);
//            vo.set("user", userService.getUser(comment.getUserId()));
            if (hostHolder.getUser() == null) {
                vo.set("liked", 0);
            } else {
                vo.set("liked", likeService.getLikeStatus(hostHolder.getUser().getId(), EntityType.ENTITY_COMMENT, comment.getId()));
            }

            vo.set("likeCount", likeService.getLikeCount(EntityType.ENTITY_COMMENT, comment.getId()));
            vo.set("user", userService.getUser(comment.getUserId()));

            vos.add(vo);
        }
        model.addAttribute("comments", vos);

        return "detail";
    }


}
