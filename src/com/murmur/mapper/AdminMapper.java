package com.murmur.mapper;

import com.murmur.po.Admin;

import java.util.List;

public interface AdminMapper {
    /**
     * 组合查询，所有非空字段以AND连接 select * from table where table.a and table.b ...
     * @param admin 查询参数
     * @return 查询结果
     */
    public List<Admin> find(Admin admin);

    /**
     * 按照键值更新，若主键字段为空，不执行操作。
     * 参数中非空字段参与更新
     * @param admin 参数
     * @return 更新影响的行数
     */
    public int update(Admin admin);

    public int insert(Admin admin);

    /**
     * 按照主键删除，
     * @param admin 主键属性不能为空，为空不执行操作
     * @return 删除影响行数
     */
    public int delete(Admin admin);
}
