package com.coffee.mapper;

import com.coffee.po.NoticeDetail;
import com.coffee.po.Notice;

import java.util.List;

public interface NoticeMapper {
    public List<NoticeDetail> find(Notice notice);
    public int update(Notice notice);
    public int insert(Notice notice);
    public int delete(Notice notice);
}
