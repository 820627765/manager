package com.znb.web.controller;

import com.znb.pojo.Employee;
import com.znb.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;

/**
 * @Auther zhangningbo
 * @Date:2019/11/28
 * @Description:com.znb.web.SpringMVCHandler
 * @version:1.0
 */
@Controller
public class SpringMVCHandler {
    @Autowired
    private EmployeeService employeeService;

    public SpringMVCHandler() {
        System.out.println("新建springMVCHandler实例......");
    }

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }

    /**
     * REST GET
     */
    @RequestMapping(value="/order/{id}",method = RequestMethod.GET)
    public String testRestGet(@PathVariable("id") Integer id){
        System.out.println("REST GET 查询 " + id);
        return "success";
    }

    /**
     * REST DELETE
     */
    @RequestMapping(value="/order/{id}",method = RequestMethod.DELETE)
    public String testRestDelete(@PathVariable("id") Integer id){
        System.out.println("REST DELETE 删除 " + id);
        return "success";
    }

    /**
     * REST POST
     */
    @RequestMapping(value="/order",method = RequestMethod.POST)
    public String testRestPOST(){
        System.out.println("REST POST 新增 ");
        return "success";
    }

    /**
     * REST PUT
     */
    @RequestMapping(value="/order",method = RequestMethod.PUT)
    public String testRestPut(HttpServletResponse response){
        response.setContentType("text/html;charset=utf-8");
        System.out.println("REST PUT 修改 ");
        return "success";
    }

    /**
     * @RequestParam 用于映射请求参数到请求处理方法的形参
     *    1，如果请求参数名与形参名一样，则可以省略@RequestParam的指定。建议无论是否一样，都指定。
     *    2，@RequestParam 注解标准的形参必须要赋值，也就是请求参数必须要有对应的请求参数。
     *       可以使用 required 来设置不是必须的,如 @RequestParam(value="age",required=false)
     *    3，可以使用defaultValue来指定一个默认值取代默认的null值（如果不是引用类型，就会报错）。如
     *       @RequestParam(value="age",required=false,defaultValue="0")
     * 客户端的请求：testRequestParam?username=Tom&age=22
     */
    @RequestMapping("/testRequestParam")
    public String testRequestParam(@RequestParam("username") String username,
                                   @RequestParam(value="age",required = false,defaultValue = "0") int age){
        return "success";
    }

    /**
     * @RequestHeader 映射请求头信息到请求处理方法的形参中
     *    1，@RequestHeader 注解标准的形参必须要赋值，也就是请求头重必须要有对应的请求头名称对应。
     *       可以使用 required 来设置不是必须的,如 @RequestHeader(value="Accept-Language",required=false)
     *    2，可以使用defaultValue来指定一个默认值取代默认的null值（如果不是引用类型，就会报错）。如
     *       @RequestHeader(value="Accept-Language",required=false,defaultValue="0")
     */
    @RequestMapping("/testRequestHeader")
    public String testRequestHeader(
            @RequestHeader(value = "Accept-Language",required = false,defaultValue = "0") String acceptLanguage){
        System.out.println(acceptLanguage);
        return "success";
    }

    /**
     * @CookieValue 映射cookie信息到请求处理方法的形参中
     *    1，@CookieValue 注解标准的形参必须要赋值，也就是请求的cookie信息必须要有对应的名称对应。
     *       可以使用 required 来设置不是必须的,如 @CookieValue(value="JSESSIONID",required=false)
     *    2，可以使用defaultValue来指定一个默认值取代默认的null值（如果不是引用类型，就会报错）。如
     *       @CookieValue(value="JSESSIONID",required=false,defaultValue="0")
     */
    @RequestMapping("/testCookieValue")
    public String testCookieValue(
            @CookieValue(value = "JSESSIONID",required = false,defaultValue = "0") String sessionId){
        System.out.println(sessionId);
        return "success";
    }

    /**
     * ModelAndView
     * springMVC 会把ModeAndView中的模型数据存储到request请求域中
     */
    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView(){
        //模型数据需要为：username=Admin
        ModelAndView mv = new ModelAndView();
        //添加模型数据
        mv.addObject("username","Admin");
        //设置视图信息
        mv.setViewName("success");
        return mv;
    }

    /**
     * Map or Model
     * SpringMVC 会把Map中的数据存储到request 请求域中。
     * SpringMVC 在调用完请求处理方法后，不管处理器返回类型是什么，都会封装为一个ModelAndView 对象。
     *
     */
    @RequestMapping("/testMap")
    public String testMap(Map<String,Object> map){
        //模型数据为:password=123456
        map.put("password","123456");
        return "success";
    }

    /**
     * Model
     */
    @RequestMapping("/testModel")
    public String testModel(Model model){
        //模型数据为:loginMsg=success
        model.addAttribute("loginMsg","success");
        return "success";
    }

    @RequestMapping("/testRedirectView")
    public String testRedirectView(){
        //return "redirect:/ok.jsp";
        return "forward:/ok.jsp";
    }

    /**
     * springMVC 返回JSON
     * @return
     */
    @ResponseBody
    @RequestMapping("/testJson")
    public Collection<Employee> testJson(){
        return employeeService.getAll();
    }

    /**
     * 使用 HttpMessageConvert 完成下载功能：
     *   首先HttpMessageConvert 支持：
     *      @RequestBody
     *      @ResponseBody
     *      HttpEntity
     *      ResponseEntity 用于将要下载的文件数据，以响应信息封装到ResponseEntity对象中，浏览器通过解析
     *                     发送回去的响应数据，就可以进行一个下载操作。
     *  下载的原理：将服务器端的文件以流的形式写到客户端。
     *
     */
    @RequestMapping("/download")
    public ResponseEntity<byte[]> testDownload(HttpSession session)throws Exception{
        //将要下载的文件读取为一个字节数组
        byte[] imgs;
        ServletContext servletContext = session.getServletContext();
        InputStream inputStream = servletContext.getResourceAsStream("static/images/favicon.png");
        imgs = new byte[inputStream.available()];
        inputStream.read(imgs);//将流中的数据读取到字节数组中
        //将响应数据，以及一些响应头信息封装到ResponseEntity中
        /**
         *  new ResponseEntity<byte[]>(imgs,headers,statusCode);
         *      参数1：发给浏览器端的数据
         *      参数2：设置响应头
         *      参数3：设置响应码
         *  相当于，我自己给浏览器发送数据，不是服务器主动发。那我自己发，就得
         *  告诉浏览器我发送得是什么数据，浏览器需要做什么操作。这就需要通过响应头信息
         *  来告诉浏览器需要做什么操作。
         */
        HttpHeaders httpHeaders = new HttpHeaders();
        //通过响应头，告诉浏览器当前是下载操作。Content-Disposition 是一个消息头名称
        //attachment;filename=favicon.png 中的attachment表示是一个附件，filename表示附件的名字，可以随意写
        //下载的时候提示的下载文件名就是通过filename 指定。
        httpHeaders.add("Content-Disposition","attachment;filename=favicon.png");

        HttpStatus httpStatus = HttpStatus.OK; // OK 就是 200，表示正常
        ResponseEntity<byte[]> re = new ResponseEntity<byte[]>(imgs,httpHeaders,httpStatus);

        return re;
    }


}
