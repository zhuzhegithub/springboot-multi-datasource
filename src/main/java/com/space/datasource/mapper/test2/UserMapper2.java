package com.space.datasource.mapper.test2;

import com.space.datasource.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhuzhe
 * @date 2018/6/22 14:24
 * @email zhe.zhu1@outlook.com
 */
@Component
@Mapper
public interface UserMapper2 {

    List<User> getAll();
}
