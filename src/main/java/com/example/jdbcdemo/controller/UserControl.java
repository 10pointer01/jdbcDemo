package com.example.jdbcdemo.controller;

import com.example.jdbcdemo.pojo.Manager;
import com.example.jdbcdemo.pojo.User;
import com.example.jdbcdemo.service.UserService;
import com.example.jdbcdemo.service.ManagerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * 控制层，导入service层
 */
@Controller
public class UserControl {
    @Resource
    private UserService userService;
    @Resource
    private ManagerService managerService;

    //管理员登录
    @RequestMapping("/login")
    public String login(Model model,HttpServletRequest req){
        model.addAttribute("Manager",new Manager());
        return "total_login";
    }

    //主页面
    @RequestMapping("/main")
    public String main( Model model){
        List<Manager> managers = managerService.findAll();
        model.addAttribute("managers",managers);
        return "mainPanel";
    }
    //测试菜单
    @RequestMapping("/menu")
    public String menu( Model model,HttpServletRequest request){
        List<User> users = userService.findAll();
        Integer id = (Integer) request.getSession().getAttribute("id");
        String username=managerService.get(id).getManagername();
        model.addAttribute("users",users);
        model.addAttribute("username",username);
        return "workerPanel";
    }

    //管理员校验
    @RequestMapping("/loginCheck")
    @ResponseBody
    public int check(@ModelAttribute(value = "Manager") Manager manager,HttpServletRequest req, HttpServletResponse resp,Model model){
        //允许任何域名访问
        resp.setHeader("Access-Control-Allow-Origin","*");
        resp.setHeader("Access-Control-Allow-Headers","content-type");
        //允许任何方法（post/get...)
        resp.setHeader("Access-Control-Allow-Methods","POST,GET,DELETE,OPTIONS,PATCH");
        Integer managerId = manager.getId();
        String password = manager.getPassword();
        String auto = req.getParameter("auto");
        System.out.println(managerId);
        if(managerService.get(managerId)!=null) {
            if (managerService.check(managerId, password) > 0) {
                Cookie cookie;
                if(auto=="") {
                    cookie = new Cookie("auto", null);
                    System.out.println("no");
                }else {
                    cookie = new Cookie("auto", managerId + "_" + password);
                    System.out.println("ok");
                }
                cookie.setMaxAge(60*60);//cookie有效时间
                cookie.setPath("path=/");//设置路径，这个路径即该工程下都可以访问该cookie，否则只有设置该cookie路径及其子路径可以访问
                resp.addCookie(cookie);// 将Cookie添加到Response中,使之生效
                req.getSession().setAttribute("id", managerId);
                return 1;
            } else {
                return 0;
            }
        }
        else{
            return -1;
        }
    }

    @RequestMapping("/test")
    public String test(Model model){
        model.addAttribute("names",managerService.test());
        return "test";
    }


    //查询全部
    @RequestMapping("/list")
    public String userList(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("users",users);
        return "index";
    }

    //新增数据
    @RequestMapping("/add")
    public String save(User user){
        userService.save(user);
        return "redirect:http://192.168.1.85:8088/list";
    }
    @RequestMapping("/useradd")
    public String add(Model model){
        //下四行向选项添加manager已有id，以防关联失败
        List<Manager> managers = managerService.findAll();
        int i=managers.size();
        model.addAttribute("size",i);
        model.addAttribute("managers",managers);
        return "add";
    }
    @RequestMapping("/managerAdd")
    public String managerAdd(Manager manager){
        managerService.save(manager);
        return "redirect:http://192.168.1.85:8088/main";
    }


    //删除数据
    @RequestMapping("/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable Integer id, HttpServletResponse servletResponse) throws IOException {
        int count =userService.delete(id);
        if(count==1) {
            servletResponse.sendRedirect("http://192.168.1.85:8088/list");
        }
            return "error";
    }
    @RequestMapping("/deleteManager/{id}")
    @ResponseBody
    public String deleteManager(@PathVariable Integer id, HttpServletResponse servletResponse) throws IOException {
        int count =managerService.delete(id);
        if(count==1) {
            servletResponse.sendRedirect("http://192.168.1.85:8088/main");
        }
        return "error";
    }

    //根据id查找
    @GetMapping("/updatePage/{id}")
    public String updatePage(@PathVariable Integer id,Model model){
        User users = userService.get(id);
        model.addAttribute("users",users);
        //下四行向选项添加manager已有id，以防关联失败
        List<Manager> managers = managerService.findAll();
        int i=managers.size();
        model.addAttribute("size",i);
        model.addAttribute("managers",managers);
        return "modifier";
    }
    @RequestMapping("/updateManagerPage")
    @ResponseBody
    public Manager updateManagerPage(HttpServletRequest request) {
        int id= Integer.parseInt(request.getParameter("managerId"));
        Manager manager = managerService.get(id);
        return manager;
    }
    @RequestMapping("/updateList")
    @ResponseBody
    public List updateList(HttpServletRequest request) {
        int id= Integer.parseInt(request.getParameter("followerId"));
        List<User> user = managerService.getList(id);
        return user;
    }
    @RequestMapping("/search")
    public String followerSearch(Model model)
    {
        List<Manager> managers = managerService.findAll();
        int i=managers.size();
        model.addAttribute("size",i);
        model.addAttribute("managers",managers);
        return "search";
    }

    //更新数据
    @PostMapping("/update")
    public String updateUser(User user,HttpServletRequest request){
        userService.update(user);
        return "redirect:http://192.168.1.85:8088/list";
    }
    @PostMapping("/updateManager")
    public String updateManager(Manager manager,HttpServletRequest request){
        managerService.update(manager);
        return "redirect:http://192.168.1.85:8088/main";
    }



}

