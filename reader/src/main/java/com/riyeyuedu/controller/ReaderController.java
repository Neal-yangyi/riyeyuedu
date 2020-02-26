package com.riyeyuedu.controller;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectResult;
import com.riyeyuedu.entity.ReaderEntity;
import com.riyeyuedu.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ReaderController {
    private ReaderService readerService;

    @Autowired
    public void setReaderService(ReaderService readerService) {
        this.readerService = readerService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public String registerReader(ReaderEntity reader, HttpSession session) {
        ReaderEntity readerEntity = readerService.getReader(reader);

        if (readerEntity != null) {
            return "0";
        } else {
            reader.setPortrait("http://nealcaffrey.oss-cn-beijing.aliyuncs.com/avatar/default.jpg");
            readerService.addReader(reader);
            session.setAttribute("reader", reader);
            return "1";
        }
    }

    @RequestMapping(value = "/toRegister", method = RequestMethod.GET)
    public String toRegister() {
        return "register";
    }

    @RequestMapping(value = "/user/toEditPassword", method = RequestMethod.GET)
    public String toEditPassword() {

        return "editPassword";
    }

    @RequestMapping(value = "/user/editPassword")
    public ModelAndView editPassword(@RequestParam("oldPassword") String oldPasswor, @RequestParam("newPassword1") String newPassword1, @RequestParam("newPassword2") String newPassword2, HttpSession session) {
        ModelAndView mv = new ModelAndView("home");
        ReaderEntity reader = (ReaderEntity) session.getAttribute("reader");
        reader.setPassword(newPassword1);
        readerService.changePassword(reader);
        session.setAttribute("reader", reader);
        mv.addObject("reader", reader);

        return mv;
    }

    @RequestMapping(value = "/user/home")
    public ModelAndView myHome(HttpSession session) {
        ModelAndView mv = new ModelAndView("home");
        ReaderEntity reader = (ReaderEntity) session.getAttribute("reader");
        mv.addObject("reader", reader);

        return mv;
    }

    @RequestMapping(value = "/user/toEditAvatar")
    public ModelAndView toEditAvatar(HttpSession session) {
        ModelAndView mv = new ModelAndView("avatar");
        ReaderEntity reader = (ReaderEntity) session.getAttribute("reader");
        mv.addObject("reader", reader);

        return mv;
    }

    @RequestMapping(value = "/user/editAvatar")
    public ModelAndView editAvatar(HttpSession session, @RequestParam("file") MultipartFile file) throws IOException {
        ModelAndView mv = new ModelAndView("home");
        ReaderEntity reader = (ReaderEntity) session.getAttribute("reader");

        Date day=new Date();

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);

        String avatar = df.format(day) + "." + suffix;

        String oldAvatar = reader.getPortrait().substring(reader.getPortrait().lastIndexOf("/") + 1);
        try {
            ClientConfiguration config = new ClientConfiguration();
            config.setConnectionTimeout(1000);
            config.setMaxErrorRetry(1);

            // endpoint以杭州为例，其它region请按实际情况填写
            String endpoint = "http://oss-cn-beijing.aliyuncs.com";
            // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建
            String accessKeyId = "******";
            String accessKeySecret = "******";
            // 创建OSSClient实例
            OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
            // 上传文件流
            InputStream inputStream = file.getInputStream();

            ossClient.putObject("nealcaffrey", "avatar/" + avatar, inputStream);
            // 删除Object
            ossClient.deleteObject("nealcaffrey", "avatar/" + oldAvatar);
            ossClient.shutdown();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        // 关闭client


        avatar = "http://nealcaffrey.oss-cn-beijing.aliyuncs.com/avatar/" + avatar;

        reader.setPortrait(avatar);

        readerService.updateAvatar(reader);
        mv.addObject("reader", reader);
        return mv;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(ReaderEntity reader, HttpSession session) {
        ReaderEntity readerEntity = readerService.getReader(reader);


        if (readerEntity != null) {
            session.setAttribute("reader", readerEntity);
            return "1";
        } else {
            return "0";
        }
    }

    /*@RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("reader_name") String readerName, @RequestParam("password") String password, HttpSession session) {
        ReaderEntity readerEntity = new ReaderEntity();
        readerEntity.setReaderName(readerName);
        readerEntity.setPassword(password);

        ReaderEntity reader = readerService.getReader(readerEntity);

        if (reader != null) {
            session.setAttribute("reader", reader);
            System.out.println("reader != null");
        } else {
            System.out.println("reader = null");
        }

        return "riye";
    }*/

    @RequestMapping(value = "/user/logout")
    @ResponseBody
    public void logout(HttpSession session) {
        ReaderEntity reader = (ReaderEntity) session.getAttribute("reader");

        if (reader != null) {
            session.removeAttribute("reader");
        }
    }
}
