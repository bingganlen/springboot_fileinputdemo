# springboot_fileinputdemo
springboot+bootstrap fileinput插件实现多文件上传及显示本地图片
前台 thymeleaf

FileController改变  Windows下图片会上传到 G:/img 目录下

    //获取图片的宽度高度  可保存到数据库  （可以加入硬盘路径、 URL访问路径、 图片大小、 MD5 验证、 创建时间 修改时间、）
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            System.out.println("图片高度： " + bufferedImage.getWidth() +"像素");
            System.out.println("图片高度： " + bufferedImage.getHeight()+"像素");
            System.out.println("文件大小(MultipartFile)： " + file.getSize()   + "    ，另一个方法(File)：" + dest.length()); 
            System.out.println("MD5校验需要硬盘的绝对路径： " + DigestUtils.md5Hex(new FileInputStream("G:/img/"+fileName)));


#注意：  windows创建文件夹不区分大小写

![image](https://github.com/feihb123/images/blob/master/fileinput.png)

文件命名： //使用UUID给图片重命名，并去掉四个“-”
		String name = UUID.randomUUID().toString().replaceAll("-", "")
        //设置文件路径   String url = request.getSession().getServletContext().getRealPath("/upload");
