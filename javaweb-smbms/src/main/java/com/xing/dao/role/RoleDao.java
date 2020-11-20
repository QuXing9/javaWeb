package com.xing.dao.role;

import com.xing.pojo.Role;

import java.sql.Connection;
import java.util.List;

public interface RoleDao {
    public List<Role> getRoleList(Connection connection) throws Exception;
}
