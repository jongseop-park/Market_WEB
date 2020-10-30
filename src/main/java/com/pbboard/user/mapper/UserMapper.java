package com.pbboard.user.mapper;

import com.pbboard.user.domain.UserInfo;
import com.pbboard.user.domain.UserInfoDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Repository
@Mapper
public interface UserMapper {
    /* 사용자 정보 찾기 */
    UserInfo findByMemberId(String id);

    /* 회원가입 */
    int insertUser(UserInfoDTO InfoDTO);

    /* 로그인 실패 시 실패횟수 추가 */
    void countFailure(String id);

    /* 로그인 실패 횟수 */
    int checkFailureCount(String id);

    /* 계정 잠금 처리 */
    void disabledUsername(String id);

    /* 로그인 실패횟수 초기화 */
    void resetLoginFailureCount(String id);

    /* 아이디 중복 조회 */
    UserInfo checkId(String id);

    /* 권한 부여 */
    void authorizationUser(String id);

    void insertIdAuthentication(UserInfoDTO userInfoDTO);
}
