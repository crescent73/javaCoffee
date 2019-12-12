package com.coffee.service;


import com.coffee.kit.ResultData;
import com.coffee.po.File;
import com.coffee.po.Notice;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TeacherService {
	/**
	 * 添加公告
	 * @param notice 公告信息
	 * @return
	 */
	public ResultData addNotice(Notice notice);
	
	/**
	 * 删除公告
	 * @param id 公告id
	 * @return
	 */
	public ResultData deleteNotice(Long id);
	
	/**
	 * 修改公告
	 * @param notice 公告信息
	 * @return
	 */
	public ResultData modifyNotice(Notice notice);
	
	/**
	 * 添加文件
	 * @param file 文件信息
	 * @param dirPath 附件寸尺地址
	 * @param attachments 附件列表
	 * @return
	 */
	public ResultData addFile(File file, String dirPath, List<MultipartFile> attachments);
	
	/**
	 * 删除文件
	 * @param id 文件id
	 * @return
	 */
	public ResultData deleteFile(Long id);

}
