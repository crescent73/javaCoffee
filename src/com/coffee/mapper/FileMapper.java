package com.coffee.mapper;

import com.coffee.po.File;
import com.coffee.po.FileDetail;

import java.util.List;

public interface FileMapper {
    public List<FileDetail> find(File file);
    public int update(File file);
    public int insert(File file);
    public int delete(File file);
}

