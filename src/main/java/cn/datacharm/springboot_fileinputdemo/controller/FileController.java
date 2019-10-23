package cn.datacharm.springboot_fileinputdemo.controller;


import cn.datacharm.springboot_fileinputdemo.util.DateTimeUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import jdk.nashorn.internal.ir.debug.JSONWriter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
public class FileController {
    @RequestMapping("/")
    public String index(){
        return "index";
    }
    @RequestMapping("/upload")
    public String upload(){
        return "upload";
    }

    @RequestMapping("/show")
    public String show(Model model){
        String src = "img/pig.jpg";
        model.addAttribute("src",src);
        return "show";
    }

    @RequestMapping("/test")
    public String test(){

        return "test";
    }


    @RequestMapping("/uploadImg")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file,Model model) throws Exception {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }
        String fileName = file.getOriginalFilename();
        //上传的文件名加上时间戳后缀   DateTimeUtil.dateNumToStr();
        String newFileName = fileName.substring(0,fileName.lastIndexOf(".")) + " " + DateTimeUtil.dateNumToStr() + fileName.substring(fileName.lastIndexOf("."));

        File dest = null;
        String os = System.getProperty("os.name");
        System.out.println(os);
        if (os.toLowerCase().startsWith("win")) {
            String path = "G:"+File.separator+"img"+File.separator;
            dest= new File(path + fileName);
        }else {
            String path = "/webapps/img/";
            dest= new File(path + fileName);
        }
        if( !dest.getParentFile().exists()) {//若不存在G:/img 目录
            dest.getParentFile().mkdirs();
        }
        //获取图片的宽度高度  可保存到数据库  （可以加入硬盘路径、 URL访问路径、 图片大小、 MD5 验证、 创建时间 修改时间、）
        BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
        System.out.println("加时间戳后的文件名："+ newFileName);
        System.out.println("图片高度： " + bufferedImage.getWidth() +"像素");
        System.out.println("图片高度： " + bufferedImage.getHeight()+"像素");
        System.out.println("文件大小(MultipartFile)： " + file.getSize()   + "    ，另一个方法(File)：" + dest.length());

        try {
            file.transferTo(dest);
            System.out.println("MD5校验需要硬盘的绝对路径： " + DigestUtils.md5Hex(new FileInputStream("G:/img/"+fileName)));
            return JSON.toJSONString("上传成功！");
        } catch (IOException e) {
            return JSON.toJSONString("上传失败！");
        }

    }

}
