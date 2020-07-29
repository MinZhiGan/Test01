package cn.it.controller;


import cn.it.domain.User;

import cn.it.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class UserController {


     @Autowired
    private UserService userService;

     @GetMapping
     public  String  admin(){

         return "admin/login";
     }

   @GetMapping("/index")
    public  String  index(){
        return "admin/index";
    }



     @PostMapping("/login")
     public  String login(@RequestParam String username,@RequestParam String password,HttpSession session,RedirectAttributes attributes){
         User user=userService.findByUsernameAndPassword( username,password );

       if(user!=null){


           //登录成功
           session.setAttribute("user",user);

           return "admin/index";


       }else {
           //登录失败
           attributes.addAttribute("message","账号密码错误");

           return "redirect:/admin";


       }


     }




}
