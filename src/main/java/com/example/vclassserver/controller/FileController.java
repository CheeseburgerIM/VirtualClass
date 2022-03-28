package com.example.vclassserver.controller;

import com.example.vclassserver.entity.vcfile;
import com.example.vclassserver.entity.vclassuser;
import com.example.vclassserver.entity.vcourse;
import com.example.vclassserver.repository.CourseRepository;
import com.example.vclassserver.repository.FileRepository;
import com.example.vclassserver.repository.UserRepository;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import com.example.vclassserver.utils.AsposeUtil;

import static com.example.vclassserver.session.SessionController.sessionMap;

@RestController
@RequestMapping(value = "/file/api")
public class FileController {

    @Autowired
    FileRepository fileRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CourseRepository courseRepository;

    @Resource
    private HttpServletResponse response;

    @Value("${root}")
    private String root;

    // type: img word ppt md txt excel pdf

    @PostMapping(value = "/uploadFile")
    private String uploadFile(HttpServletRequest request,
                              @RequestParam("username") String username,
                              @RequestParam("teacherUsername") String teacherUsername,
                              @RequestParam("cname") String cname,
                              @RequestParam("chname") String chname,
                              @RequestParam("fname") String fname,
                              @RequestParam("ftag") String ftag,
                              @RequestParam("chnum") int chnum,
                              @RequestParam("file") MultipartFile file) {
        HttpSession session=request.getSession();
        if(sessionMap.containsKey(session.getId())) { // 验证session是否有效
            if(session.getAttribute(username)!=null) { // 验证当前用户是否登录
                List<vclassuser> vclassuserList=userRepository.findUserByUsername(username);
                if(vclassuserList.isEmpty()) return "fail";
                vclassuser u=vclassuserList.get(0);
                String cid=teacherUsername+"-"+cname; // 通过老师账号+课程名获得课程id
                List<vcourse> vcourseList=courseRepository.findCourseById(cid);
                if(vcourseList.isEmpty()) return "fail";
                vcourse c=vcourseList.get(0);
                String fid="fileid";
                String chid=teacherUsername+"-"+cname+"-"+chname; // 通过老师账号+课程名+章节名获得章节id
                String ftype="filetype";
                if(!file.isEmpty()) {
                    // 获取文件名
                    String fileName=file.getOriginalFilename();
                    // 获取文件后缀名
                    String suffixName=fileName.substring(fileName.lastIndexOf("."));
                    ftype=getFileTypeBySuffix(suffixName); // 通过文件后缀名获得文件类型
                    if(ftype.equals("fail")) return "fail";
                    // 文件上传后保存在本地的路径
                    String filePath=new File(root).getAbsolutePath()+"//VClass//"+u.getStatus()+"//"+username+"//"+ftype+"//"+fname+getSuffixByFileType(ftype);
                    File chk=new File(filePath);
                    // 检测是否存在目录
                    if(!chk.getParentFile().exists()) {
                        chk.getParentFile().mkdirs();
                    }
                    try {
                        file.transferTo(chk);
                        Date date=new Date();
                        fid=username+"-"+ftype+"-"+fname; // 通过上传者账号+文件类型+文件名获得文件id
                        List<vcfile> vcfileList=fileRepository.getFileById(fid);
                        if(!vcfileList.isEmpty()) return "fail";
                        vcfile f=new vcfile(fid,cid,chid,username,fname,ftype,ftag,chnum); // 创建vcfile对象
                        fileRepository.save(f); // 保存在数据库中
                        return "success";
                    } catch (IOException e) {
                        e.printStackTrace();
                        return "fail";
                    }
                }
                return "fail";
            }
            return "fail";
        }
        return "fail";
    }

    @RequestMapping(value = "/downloadFile")
    private String downloadFile(HttpServletRequest request,
                                @RequestParam("username") String username,
                                @RequestParam("uploaderUsername") String uploaderUsername,
                                @RequestParam("ftype") String ftype,
                                @RequestParam("fname") String fname) {
        HttpSession session=request.getSession();
        if(sessionMap.containsKey(session.getId())) {
            if(session.getAttribute(username)!=null) {
                List<vclassuser> vclassuserList=userRepository.findUserByUsername(username);
                if(vclassuserList.isEmpty()) return "fail";
                vclassuser u=vclassuserList.get(0); // 下载文件的用户
                vclassuserList.clear();
                vclassuserList=userRepository.findUserByUsername(uploaderUsername);
                if(vclassuserList.isEmpty()) return "fail";
                vclassuser uploader=vclassuserList.get(0); // 上传文件的用户
                String fid=uploaderUsername+"-"+ftype+"-"+fname;
                List<vcfile> vcfileList=fileRepository.getFileById(fid);
                if(vcfileList.isEmpty()) return "fail";
                String suffix=getSuffixByFileType(ftype);
                if(suffix.equals("fail")) return "fail";
                return "//VClass//"+uploader.getStatus()+"//"+uploaderUsername+"//"+ftype+"//"+fname+suffix;
            }
            return "fail";
        }
        return "fail";
    }

//    @RequestMapping(value = "/Aspose")
//    private String Aspose() {
//        // doc
//        String srcWord="C:\\Users\\16607\\Desktop\\wordtest.doc";
//        String destWord="C:\\Users\\16607\\Desktop\\wordpdf.pdf";
//        // docx
//        String srcWordx="C:\\Users\\16607\\Desktop\\wordxtest.docx";
//        String destWordx="C:\\Users\\16607\\Desktop\\wordxpdf.pdf";
//        // md
//        String srcMd="C:\\Users\\16607\\Desktop\\mdtest.md";
//        String destMd="C:\\Users\\16607\\Desktop\\mdpdf.pdf";
//        AsposeUtil.trans(srcMd,destMd,"word");
//        return "Aspose";
//    }

//    @RequestMapping(value = "/OnlineBrowsing")
//    private String OnlineBrowsing(@RequestParam("uploaderUsername") String uploaderUsername,
//                                  @RequestParam("ftype") String ftype,
//                                  @RequestParam("fname") String fname) {
//        List<vclassuser> vclassuserList=userRepository.findUserByUsername(uploaderUsername);
//        if(vclassuserList.isEmpty()) return "fail";
//        vclassuser u=vclassuserList.get(0);
//        String src=root+"//VClass//"+u.getStatus()+"//"+uploaderUsername+"//"+ftype+"//"+fname+getSuffixByFileType(ftype);
//        String dest=root+"//VClass//"+u.getStatus()+"//"+uploaderUsername+"//"+"OpenOffice//temp//"+ftype+"//"+fname+".pdf";
//        String destWithWatermark=root+"//VClass//"+u.getStatus()+"//"+uploaderUsername+"//"+"OpenOffice//"+ftype+"//"+fname+".pdf";
//        File srcFile=new File(src);
//        File destFile=new File(dest);
//        File destFileWithWatermark=new File(destWithWatermark);
//        if(!srcFile.getParentFile().exists()) {
//            srcFile.getParentFile().mkdirs();
//        }
//        if(!destFile.getParentFile().exists()) {
//            destFile.getParentFile().mkdirs();
//        }
//        if(!destFileWithWatermark.getParentFile().exists()) {
//            destFileWithWatermark.getParentFile().mkdirs();
//        }
//        return "OnlineBrowsing";
//    }

    @RequestMapping(value = "/getAllFile")
    private Object getAllFile(@RequestParam("teacherUsername") String teacherUsername,
                              @RequestParam("cname") String cname) {
        Map<String,String> temp=new HashMap<>();
        temp.put("result","fail");
        String cid=teacherUsername+"-"+cname;
        List<vclassuser> vclassuserList=userRepository.findUserByUsername(teacherUsername);
        if(vclassuserList.isEmpty()) return temp;
        vclassuser teacher=vclassuserList.get(0);
        if(!teacher.getStatus().equals("teacher")) return temp;
        List<vcourse> vcourseList=courseRepository.findCourseById(cid);
        if(vcourseList.isEmpty()) return temp;
        vcourse c=vcourseList.get(0);
        return fileRepository.getAllFileByCourseId(cid);
    }

    @RequestMapping(value = "/deleteFile")
    private String deleteFile(HttpServletRequest request,
                              @RequestParam("teacherUsername") String teacherUsername,
                              @RequestParam("uploaderUsername") String uploaderUsername,
                              @RequestParam("ftype") String ftype,
                              @RequestParam("fname") String fname) {
        HttpSession session=request.getSession();
        if(sessionMap.containsKey(session.getId())) {
            if(session.getAttribute(teacherUsername)!=null) {
                if(userRepository.findUserByUsername(teacherUsername).isEmpty()) return "fail";
                vclassuser u=userRepository.findUserByUsername(teacherUsername).get(0);
                if(!u.getStatus().equals("teacher")) return "fail";
                String fid=uploaderUsername+"-"+ftype+"-"+fname;
                List<vcfile> vcfileList=fileRepository.getFileById(fid);
                if(vcfileList.isEmpty()) return "fail";
                vcfile f=vcfileList.get(0);
                List<vclassuser> vclassuserList=userRepository.findUserByUsername(uploaderUsername);
                if(vclassuserList.isEmpty()) return "fail";
                vclassuser uploader=vclassuserList.get(0);
                String filePath=root+"//VClass//"+uploader.getStatus()+"//"+uploaderUsername+"//"+ftype+"//"+fname+getSuffixByFileType(ftype);
                File file=new File(filePath);
                String pdfPath=root+"//VClass//"+uploader.getStatus()+"//"+uploaderUsername+"//OpenOffice//temp//"+ftype+"//"+fname+".pdf";
                File pdfFile=new File(pdfPath);
                String pdfWithWatermarkPath=root+"//VClass//"+uploader.getStatus()+"//"+uploaderUsername+"//OpenOffice//"+ftype+"//"+fname+".pdf";
                if(!file.exists()) return "fail";
                file.delete();
                fileRepository.deleteFileById(fid);
                if(pdfFile.exists()) pdfFile.delete();
                if(new File(pdfWithWatermarkPath).exists()) new File(pdfWithWatermarkPath).delete();
                return "success";
            }
            return "fail";
        }
        return "fail";
    }

    private String getFileTypeBySuffix(String suffix) {
        switch (suffix) {
            case ".jpg": return "img";
            case ".png": return "img";
            case ".doc": return "word";
            case ".docx": return "word";
            case ".ppt": return "ppt";
            case ".pptx": return "ppt";
            case ".md": return "md";
            case ".txt": return "txt";
            case ".pdf": return "pdf";
            case ".xls": return "excel";
            case ".xlsx": return "excel";
            default: return "fail";
        }
    }

    private String getSuffixByFileType(String ftype) {
        switch (ftype) {
            case "img": return ".jpg";
            case "word": return ".docx";
            case "ppt": return ".pptx";
            case "excel": return ".xlsx";
            case "md": return ".md";
            case "txt": return ".txt";
            case "pdf": return ".pdf";
            default: return "fail";
        }
    }

    private void addWaterMark(File srcFile,File destFile,File destFileWithWatermark) {
        // 加水印
        try {
            PdfReader reader=new PdfReader(new FileInputStream(destFile.getPath()));
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(destFileWithWatermark.getPath()));
            BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
            PdfGState gs = new PdfGState();
            int pageCount = reader.getNumberOfPages() + 1;
            PdfContentByte content;
            Rectangle pageRect=null;
            for (int i = 1; i < pageCount; i++)
            {
                content = stamper.getOverContent(i);
                gs.setFillOpacity(0.5f);
                content.setGState(gs);
                content.beginText();
                content.setColorFill(BaseColor.LIGHT_GRAY);
                content.setFontAndSize(baseFont, 45);
                pageRect=stamper.getReader().getPageSizeWithRotation(i);
                float x = pageRect.getWidth() / 2;
                float y = pageRect.getHeight() / 2;
                content.showTextAligned(Element.ALIGN_CENTER, "天津大学智能与计算学部", x, 3*y/2, 45);
                content.showTextAligned(Element.ALIGN_CENTER, "天津大学智能与计算学部", x, y/2, 45);
                content.endText();
            }
            stamper.close();
            reader.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}