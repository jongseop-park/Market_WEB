package com.pbboard.user.mapper;

import com.pbboard.user.domain.UserInfo;
import com.pbboard.user.domain.UserInfoDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Mapper
public interface UserMapper {
    Optional<UserInfo> findByMemberId(String id);
    public int save(UserInfoDTO InfoDTO);

}
