package com.xing.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.UUID;

public class fileServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //判断上传的表单是普通表单还是带文件表单
        if(!ServletFileUpload.isMultipartContent(req)){
            //如果不是带文件表单，终止方法运行
            return;
        }

        //创建上传文件的保存路径，建议保存在web-inf路径下，安全，用户无法直接上传的文件；
        String uploadpath = this.getServletContext().getRealPath("/WEB-INF/upload");
        File uploadFile = new File(uploadpath);
        if(!uploadFile.exists()){
            uploadFile.mkdir();
        }

        //缓存，临时文件，加入文件超过了我们规定的代销，我们把他放到一个临时文件中，过几天自动删除
        String tmpPath = this.getServletContext().getRealPath("/WEB-INF/tmp");
        File tmpFile = new File(tmpPath);
        if(!tmpFile.exists()){
            tmpFile.mkdir();
        }

        try {
            //处理上传的文件，我们一般通过流获取，原生态的文件上传流获取，十分麻烦
            //此处建议使用apache的common-fileupload
            //1， 创建DiskFileItemFactory对象，处理文件上传路径或者限制大小限制的
            DiskFileItemFactory factory = getDiskFileItemFactory(tmpFile);
            //2. 获取ServletFileUpload
            ServletFileUpload upload = getServletFileUpload(factory);

            //3. 处理上传的文件
            String msg = uploadParseRequest(upload, req, uploadpath);

            req.setAttribute("msg", msg);
            req.getRequestDispatcher("info.jsp").forward(req,resp);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
    }

    public static DiskFileItemFactory getDiskFileItemFactory(File file){
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //通过这个工厂设置一个缓冲区，当上传的文件大于这个缓冲区的时候，把他放到临时文件中
        factory.setSizeThreshold(1024*1024);
        factory.setRepository(file);
        return factory;
    }

    public static ServletFileUpload getServletFileUpload(DiskFileItemFactory factory){
        ServletFileUpload upload = new ServletFileUpload(factory);

        //监听文件上传进度
        upload.setProgressListener(new ProgressListener() {
            public void update(long pBytesRead, long pContentLength, int pItems) {
                System.out.println("总大小：" + pContentLength + "已上传:" + pBytesRead);
            }
        });
        //处理乱码问题
        upload.setHeaderEncoding("UTF-8");
        //设置单个文件的最大值
        upload.setFileSizeMax(1024*1024*10);
        //设置总共能够上传文件的大小
        //10M
        upload.setSizeMax(1024*1024*10);
        return upload;
    }

    public String uploadParseRequest(ServletFileUpload upload, HttpServletRequest req, String uploadpath) throws FileUploadException, IOException {
        String msg = "";
        List<FileItem> fileItems = upload.parseRequest(req);
        //fileItem 每一个表单对象
        for (FileItem fileItem: fileItems){
            if(fileItem.isFormField()){
                //getFiledName指的是前端表单控件的name；
                String name = fileItem.getFieldName();
                String value = fileItem.getString("UTF-8");
                System.out.println(name + ":" + value);
            }else {
                //文件
                // ==================================文件=======================================
                String uploadFileName = fileItem.getName();
                if(uploadFileName.trim().equals("") || uploadFileName == null){
                    continue;
                }
                //获得上传的文件名
                String fileName = uploadFileName.substring(uploadFileName.lastIndexOf("/") + 1);
                String fileExtName = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1);
                String uuidPath = UUID.randomUUID().toString();

                // ==================================存放地址======================================
                String realPath = uploadpath + "/" + uuidPath;
                //给每个文件创建一个对应的文件夹
                File realPathFile = new File(realPath);
                if(!realPathFile.exists()){
                    realPathFile.mkdir();
                }
                // ==================================文件传输======================================
                //获得文件上传的流
                InputStream inputStream = fileItem.getInputStream();

                //创建一个文件输出流
                //realPath = 真实的文件夹;
                //差了一个文件；加上输出文件的名字+"/"+uuidFileName
                FileOutputStream fos = new FileOutputStream(realPath + "/" + fileName);

                //创建一个缓冲区
                byte[] buffer = new byte[1024 * 1024];

                //判断是否读取完毕
                int len = 0;
                //如果大于0说明还存在数据:
                while ((len=inputStream.read(buffer)) > 0){
                    fos.write(buffer, 0, len);
                }

                //关闭流
                fos.close();
                inputStream.close();

                msg = "文件上传成功！";
                fileItem.delete();
            }
        }
        return msg;
    }
}
