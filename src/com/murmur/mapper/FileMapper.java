package com.murmur.mapper;

import com.murmur.po.File;
import com.murmur.po.FileDetail;

import java.util.List;

public interface FileMapper {
    public List<FileDetail> find(File file);
    public int update(File file);
    public int insert(File file);
    public int delete(File file);
}

