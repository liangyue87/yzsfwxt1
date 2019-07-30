package com.ljx.hfgsjt.main.controller;

import com.ljx.hfgsjt.main.service.ChunkService;
import com.ljx.hfgsjt.main.service.FileInfoService;
import com.ljx.hfgsjt.sjst.service.SjstService;
import com.ljx.hfgsjt.util.bean.Chunk;
import com.ljx.hfgsjt.util.bean.FileInfo;
import com.ljx.hfgsjt.util.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.ljx.hfgsjt.util.utils.FileUtils.generatePath;
import static com.ljx.hfgsjt.util.utils.FileUtils.merge;

/**
 * @author luoliang
 * @date 2018/6/19
 */
@RestController
@RequestMapping("/uploader")
@Slf4j
public class UploadController {
    @Value("${prop.upload-folder}")
    private String uploadFolder;
    @Resource
    private FileInfoService fileInfoService;
    @Resource
    private ChunkService chunkService;
    @Autowired
    private SjstService service;

    @Autowired
    private static final Logger log = LoggerFactory.getLogger(UploadController.class);

    @PostMapping("/chunk")
    @ResponseBody
    public String uploadChunk(Chunk chunk,HttpServletRequest request,HttpServletResponse response) {
//        Date currentTime = new Date();
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
//        String dateString = formatter.format(currentTime);
//        chunk.setFilename(chunk.getFilename()+dateString);
        MultipartFile file = chunk.getFile();
        log.debug("file originName: {}, chunkNumber: {}", file.getOriginalFilename(), chunk.getChunkNumber());
        List<Map<String,Object>> list = new ArrayList<>();
        try {
            byte[] bytes = file.getBytes();
            list = service.getxmList(chunk.getXmid());
            Path path = Paths.get(generatePath(StringUtil.getBasePath(), chunk,list.get(0).get("XMBH").toString(),"sjst"));
            //文件写入指定路径
            Files.write(path, bytes);
            log.debug("文件 {} 写入成功, uuid:{}", chunk.getFilename(), chunk.getIdentifier());
            chunkService.saveChunk(chunk);

            return "文件上传成功";
        } catch (IOException e) {
            e.printStackTrace();
            return "后端异常...";
        }
    }

    @GetMapping("/chunk")
    @ResponseBody
    public Object checkChunk(Chunk chunk, HttpServletRequest request,HttpServletResponse response) {
        if (chunkService.checkChunk(chunk.getIdentifier(), chunk.getChunkNumber(),chunk.getXmid())) {
            response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
        }

        return chunk;
    }

    @PostMapping("/mergeFile")
    @ResponseBody
    public String mergeFile(
            FileInfo fileInfo, HttpServletRequest request, HttpServletResponse response,String xmid) {
        String filename = fileInfo.getFilename();
        List<Map<String,Object>> list = new ArrayList<>();
        list = service.getxmList(xmid);
        String file = StringUtil.getBasePath() + "upload"+ File.separator+list.get(0).get("XMBH").toString()+File.separator+"sjst"+File.separator+fileInfo.getIdentifier() + File.separator + filename;
        String folder = StringUtil.getBasePath() + "upload"+ File.separator+list.get(0).get("XMBH").toString()+File.separator+"sjst"+File.separator+fileInfo.getIdentifier();

        merge(file, folder, filename);
        fileInfo.setLocation(file);
        fileInfoService.addFileInfo(fileInfo);

        return "upload"+ File.separator+list.get(0).get("XMBH").toString()+File.separator+"sjst"+File.separator+fileInfo.getIdentifier() + File.separator + filename;
    }
}
