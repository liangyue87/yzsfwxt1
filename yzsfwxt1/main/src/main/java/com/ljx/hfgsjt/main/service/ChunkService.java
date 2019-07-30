package com.ljx.hfgsjt.main.service;

import com.ljx.hfgsjt.util.bean.Chunk;

/**
 * @author luoliang
 * @date 2018/6/21
 */
public interface ChunkService {
    /**
     * 保存文件块
     *
     * @param chunk
     */
    void saveChunk(Chunk chunk);

    /**
     * 检查文件块是否存在
     *
     * @param identifier
     * @param chunkNumber
     * @return
     */
    boolean checkChunk(String identifier, Integer chunkNumber,String xmid);
}
