package com.immimg.immimg.dao;

import com.immimg.immimg.model.entity.Admin;
import com.immimg.immimg.model.entity.User;
import org.apache.ibatis.annotations.Param;


public interface LoginDao {
    User getUserByMobile(@Param("mobile") String mobile);
    Admin getAdminByMobile(@Param("mobile") String mobile);
}
