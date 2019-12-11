package com.murmur.mapper;

import com.murmur.po.Attachment;

import java.util.List;

public interface AttachmentMapper {

    public int insert(Attachment attachment);

    public int update(Attachment attachment);

    public int delete(Attachment attachment);

    public List<Attachment> find(Attachment attachment);
}
