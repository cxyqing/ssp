package com.ex.controller;

import com.ex.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;

@SessionAttributes(value = {"user"},types = {String.class})
@RequestMapping("/mvc")
@Controller
public class MvcController {

    @Autowired
    private HttpServletRequest httpRequest;

    @Autowired
    private HttpSession httpSession;

//    @ModelAttribute
//    public void getModel(@RequestParam(value = "id",required = false) Integer id,Model model){
//        if(id != null){
//            User user = new User(id,"James","123456","james@qq.com",22);
//            System.out.println("getModel: " + user.hashCode());
//            model.addAttribute("user",user);
//        }
//    }
        @GetMapping("testRedirect")
        public String testRedirect(){
            System.out.println("testRedirect");
            return "redirect:/home.jsp";
        }

        @GetMapping("testBeanNameView")
        public String testBeanNameView(){
            System.out.println("helloView");
            return "helloView";
        }



    @GetMapping("testViewAndViewResolver")
    public String testViewAndViewResolver(){
        System.out.println("testViewAndViewResolver");
        return "success";
    }

    @PostMapping("testModelAttribute")
    public String testModelAttribute(User user){
        System.out.println(user.hashCode());
        return "success";
    }


    @GetMapping("testSessionAttributes")
    public String testSessionAttributes(Model model){
        User user = new User();
        user.setEmail("aa@qq.com");
        model.addAttribute("user",user);
        model.addAttribute("name","zhang3");
        return "success";
    }


    @GetMapping("testModelAndView")
    public ModelAndView testModelAndView(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("success");
        mv.addObject("date",new Date());
        System.out.println(mv.getView());
        return mv;
    }

    @GetMapping("testServletAPI")
    public void testServletAPI(HttpServletRequest request, HttpSession session, Writer writer) throws IOException {
        System.out.println(httpRequest.getClass().getName());
        System.out.println(request.getParameter("user"));
        System.out.println(request);
        System.out.println(session);
        writer.append("succss page ooooo");
        writer.append("hello world");
//        return "success";
    }


    @PostMapping("testPojo")
    public String testPojo(User user){
        System.out.println("user: " + user);
        return "success";
    }

    @GetMapping("testCookieValue")
    public String testCookieValue(@CookieValue("Webstorm-7b06f7f8") String cookieValue,@RequestHeader("Cookie") String header){
        System.out.println("Webstorm-7b06f7f8 : " + cookieValue + ",Cookie: " + header);
        return "success";
    }

    @GetMapping("/testRequestHeader")
    public String testRequestHeader(@RequestHeader("User-Agent") String value){
        System.out.println(value);
        return "success";
    }

    @GetMapping("testRequestParam")
    public String testRequestParam(@RequestParam("username") String name,Integer age){
        System.out.println("username: " + name + ",age: " + age);
        return "success";
    }


    @PutMapping("/testRest/{id}")
    @ResponseBody
    public String testRestPut(@PathVariable("id") Long id){
        System.out.println("testRestPut  " + id);
        return "success";
    }
    @DeleteMapping("/testRest/{id}")
    public String testRestDelete(@PathVariable("id") Long id){
        System.out.println("testRestDelete  " + id);
        return "success";
    }

    @GetMapping("/testAntPath/*/abc")
    public String testAntPath(){
        System.out.println("testAntPath");
        return "success";
    }

    @GetMapping(value = "/testParamsAndHeaders",params = {"username","age!=10"},headers = {"Accept-Language=zh-CN,zh;q=0.9"})
    public String testParamsAndHeaders(){
        System.out.println("testParamsAndHeaders");
        return "success";
    }
}
