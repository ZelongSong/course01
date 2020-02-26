package com.itcodai.course01.controller;

import com.itcodai.course01.common.MicroServiceUrl;
import com.itcodai.course01.entity.User;
import com.itcodai.course01.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RestController
@RequestMapping("/test")
@Slf4j
@Api(value = "Swagger2 在线接口文档")
public class TestController {

    //private final static Logger logger = LoggerFactory.getLogger(TestController.class);

    @Resource
    private IUserService userService;

    @RequestMapping("/getUserByName/{name}")
    public User getUserByName(@PathVariable String name) {
        return userService.getUserByName(name);
    }

    @PostMapping("/adduser")
    public String addUser(@RequestBody User user) throws Exception {
        if (null != user) {
            userService.insertUser(user);
            return "success";
        } else {
            return "false";
        }
    }

    /**
     * 首先通过 contextRefreshedEvent 来获取 application 上下文，再通过 application 上下文来获取 UserService 这个 bean，
     * 项目中可以根据实际业务场景，也可以获取其他的 bean，然后再调用自己的业务代码获取相应的数据，
     * 最后存储到 application 域中，这样前端在请求相应数据的时候，
     * 我们就可以直接从 application 域中获取信息，减少数据库的压力
     * @param request
     * @return
     */
    @GetMapping("/listener/user")
    public User getUser(HttpServletRequest request) {
        ServletContext application = request.getServletContext();
        return (User) application.getAttribute("user");
    }

    /**
     * 获取当前在线人数，该方法有bug
     * @param request
     * @return
     */
    @GetMapping("/listener/total")
    public String getTotalUser(HttpServletRequest request) {
        Integer count = (Integer) request.getSession().getServletContext().getAttribute("count");
        return "当前在线人数：" + count;
    }


    /**
     * 该处理逻辑是让服务器记得原来那个 session，即把原来的 sessionId 记录在浏览器中，
     * 下次再打开时，把这个 sessionId 传过去，这样服务器就不会重新再创建了。
     * 重启一下服务器，在浏览器中再次测试一下，即可避免上面的问题
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/total2")
    public String getTotalUser(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie;
        try {
            // 把sessionId记录在浏览器中
            cookie = new Cookie("JSESSIONID", URLEncoder.encode(request.getSession().getId(), "utf-8"));
            cookie.setPath("/");
            //设置cookie有效期为2天，设置长一点
            cookie.setMaxAge( 48*60 * 60);
            response.addCookie(cookie);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Integer count = (Integer) request.getSession().getServletContext().getAttribute("count");
        return "当前在线人数：" + count;
    }

    @RequestMapping("/log")
    public String testLog() {
        log.debug("=====测试日志debug级别打印====");
        log.info("======测试日志info级别打印=====");
        log.error("=====测试日志error级别打印====");
        log.warn("======测试日志warn级别打印=====");

        // 可以使用占位符打印出一些参数信息
        String str1 = "blog.itcodai.com";
        String str2 = "blog.csdn.net/eson_15";
        log.info("======倪升武的个人博客：{}；倪升武的CSDN博客：{}", str1, str2);

        return "success";
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String testGet() {
        return "success";
    }

    @GetMapping("/user/{id}")
    @ApiOperation(value = "根据用户唯一标识获取用户信息")
    public String testPathVariable(@PathVariable  @ApiParam(value = "用户唯一标识") Integer id) {
        System.out.println("获取到的id为：" + id);
        return "success";
    }

    /**
     * 如果想要 url 中占位符中的 id 值直接赋值到参数 id 中，需要保证 url 中的参数和方法接收参数一致，否则就无法接收。
     * 如果不一致的话，其实也可以解决，需要用 @PathVariable 中的 value 属性来指定对应关系
     * @param id
     * @return
     */
    @RequestMapping("/user/{idd}")
    public String testPathVariable2(@PathVariable(value = "idd") Integer id) {
        System.out.println("获取到的id为：" + id);
        return "success";
    }

    @GetMapping("/user/{idd}/{name}")
    public String testPathVariable3(@PathVariable(value = "idd") Integer id, @PathVariable String name) {
        System.out.println("获取到的id为：" + id);
        System.out.println("获取到的name为：" + name);
        return "success";
    }

    @GetMapping("/user")
    public String testRequestParam(@RequestParam Integer id) {
        System.out.println("获取到的id为：" + id);
        return "success";
    }

    @RequestMapping("/user")
    public String testRequestParam2(@RequestParam(value = "idd", required = false) Integer id) {
        System.out.println("获取到的id为：" + id);
        return "success";
    }

    /**
     * 接受表单数据
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/form1")
    public String testForm(@RequestParam String username, @RequestParam String password) {
        System.out.println("获取到的username为：" + username);
        System.out.println("获取到的password为：" + password);
        return "success";
    }

    @PostMapping("/form2")
    public String testForm(User user) {
        System.out.println("获取到的username为：" + user.getUsername());
        System.out.println("获取到的password为：" + user.getPassword());
        return "success";
    }

    /**
     * 用于接收前端传来的实体，接收参数也是对应的实体，比如前端通过 json 提交传来两个参数 username 和 password，
     * 此时我们需要在后端封装一个实体来接收。在传递的参数比较多的情况下，使用 @RequestBody 接收会非常方便。
     * @param user
     * @return
     */
    @PostMapping("/user")
    public String testRequestBody(@RequestBody User user) {
        System.out.println("获取到的username为：" + user.getUsername());
        System.out.println("获取到的password为：" + user.getPassword());
        return "success";
    }
}
