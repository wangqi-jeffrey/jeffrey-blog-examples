package com.jeffrey.blog.examples;

import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Description:
 *
 * @author WQ
 * @date 2020/11/27 8:14 PM
 */
public interface UserMapper {

    User select();

    List<User> selectList();

    List<User> selectBySql();

    List<User> testTrim();

    List<User> testBind();

    @Update({"<script>",
            "update user",
            "  <set>",
            "    <if test='name != null'>name=#{name},</if>",
            "  </set>",
            "where id=#{id}",
            "</script>"})
    void update(User user);

    /**
     * Multi-db vendor support
     */
    void insertByMultiDB();
}