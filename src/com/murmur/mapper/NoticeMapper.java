package com.murmur.mapper;

import com.murmur.po.NoticeDetail;
import com.murmur.po.Notice;

import java.util.List;

public interface NoticeMapper {
    public List<NoticeDetail> find(Notice notice);
    public int update(Notice notice);
    public int insert(Notice notice);
    public int delete(Notice notice);
}
