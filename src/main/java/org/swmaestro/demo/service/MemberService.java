package org.swmaestro.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.swmaestro.demo.mapper.MemberMapper;
import org.swmaestro.demo.model.Member;

import java.util.List;

/**
 * Member Service
 *
 * @since	2022-06-29
 * @author	ywkim
 */
@Service
@Slf4j
public class MemberService {

    private final MemberMapper memberMapper;

    @Autowired
    public MemberService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    public int create(Member member) {
        int createdCount = 0;

        try {
            createdCount = memberMapper.create(member);
        } catch(Exception e) {
            log.error(e.getMessage());
        }

        return createdCount;
    }

    public Member read(String id) {
        Member member = null;

        try {
            member = memberMapper.read(id);
        } catch(Exception e) {
            log.error(e.getMessage());
        }

        return member;
    }

    public List<Member> list(Member member) {
        List<Member> list = null;

        try {
            list = memberMapper.list(member);
        } catch(Exception e) {
            log.error(e.getMessage());
        }

        return list;
    }

    public int update(Member member) {
        int updatedCount = 0;

        try {
            updatedCount = memberMapper.update(member);
        } catch(Exception e) {
            log.error(e.getMessage());
        }

        return updatedCount;
    }

    public int delete(String id) {
        int deletedCount = 0;

        try {
            deletedCount = memberMapper.delete(id);
        } catch(Exception e) {
            log.error(e.getMessage());
        }

        return deletedCount;
    }

}
