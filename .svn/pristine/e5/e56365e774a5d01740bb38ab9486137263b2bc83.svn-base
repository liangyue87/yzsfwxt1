package com.ljx.hfgsjt.main.serviceImpl;

import com.ljx.hfgsjt.main.service.FileInfoService;
import com.ljx.hfgsjt.repository.FileInfoRepository;
import com.ljx.hfgsjt.util.bean.FileInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author luoliang
 * @date 2018/6/20
 */
@Service
public class FileInfoServiceImpl implements FileInfoService {
    @Resource
    private FileInfoRepository fileInfoRepository;

    @Override
    public FileInfo addFileInfo(FileInfo fileInfo) {
        return fileInfoRepository.save(fileInfo);
    }
}
