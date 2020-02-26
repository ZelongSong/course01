package com.itcodai.course01.dao;

import com.itcodai.course01.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper {

    //@Select("select * from user where id = #{id}")
    User getUserByName(String username);

    @Select("select * from user where id = #{id} and user_name=#{name}")
    User getUserByIdAndName(@Param("id") Long id, @Param("name") String username);

    /**
     * 有个问题需要注意一下，一般我们在设计表字段后，都会根据自动生成工具生成实体类，
     * 这样的话，基本上实体类是能和表字段对应上的，最起码也是驼峰对应的，
     * 由于在上面配置文件中开启了驼峰的配置，所以字段都是能对的上的。
     * 但是，万一有对不上的呢？我们也有解决办法，使用 @Results 注解来解决。
     * @param id
     * @return
     */
    @Select("select * from user where id = #{id}")
    @Results({
            @Result(property = "username", column = "user_name"),
            @Result(property = "password", column = "password")
    })
    User getUser(Long id);

    @Select("select * from user where id = #{id}")
    @ResultMap("BaseResultMap")
    User getUserById(Long id);

    @Insert("insert into user (user_name, password) values (#{username}, #{password})")
    Integer insertUser(User user);
}
