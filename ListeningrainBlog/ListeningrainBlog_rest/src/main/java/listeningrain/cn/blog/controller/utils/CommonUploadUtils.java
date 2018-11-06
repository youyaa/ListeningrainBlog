package listeningrain.cn.blog.controller.utils;

import listeningrain.cn.blog.output.ReturnMessage;
import listeningrain.cn.blog.utils.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * User:        sunqingfeng6
 * Date:        2018/11/6 15:28
 * Description: 公共的文件上传方法
 */
@Controller
@RequestMapping("/common")
public class CommonUploadUtils {

    private StorageService storageService;

    @Autowired
    public CommonUploadUtils(StorageService storageService) {
        this.storageService = storageService;
    }

    @RequestMapping(path = "/uploadImg", method = RequestMethod.POST)
    @ResponseBody
    public ReturnMessage<String[]> saveExcel(HttpServletRequest request, MultipartFile file) {
        if (file.isEmpty()) {

        }
        String imgUrl = "/upload-dir/" + file.getOriginalFilename();
        storageService.store(file);

        //构建返回对象
        ReturnMessage<String[]> returnMessage = new ReturnMessage<>();
        returnMessage.setErrno(0);
        returnMessage.setData(new String[]{"http://localhost:8000"+imgUrl});
        return returnMessage;
    }
}
