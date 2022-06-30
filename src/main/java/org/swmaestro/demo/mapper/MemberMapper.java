package org.swmaestro.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.swmaestro.demo.model.Member;

import java.util.List;

/**
 * Member Mapper
 *
 * @since	2022-06-29
 * @author	ywkim
 */
@Mapper
public interface MemberMapper {

    int create(Member member);
    Member read(String id);
    List<Member> list();
    int update(Member member);
    int delete(String id);

}
