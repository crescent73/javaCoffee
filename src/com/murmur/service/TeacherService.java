package com.murmur.service;


import com.murmur.kit.ResultData;
import com.murmur.po.File;
import com.murmur.po.Notice;

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
	 * @return
	 */
	public ResultData addFile(File file);
	
	/**
	 * 删除文件
	 * @param id 文件id
	 * @return
	 */
	public ResultData deleteFile(Long id);

}
