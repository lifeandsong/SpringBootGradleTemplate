package org.swmaestro.demo.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.swmaestro.demo.config.Auth;
import org.swmaestro.demo.model.Member;
import org.swmaestro.demo.service.MemberService;
import org.swmaestro.demo.util.Sha512Encryptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 회원 정보를 CRUD 하는 RESTful API를 제공하는 RestController
 *
 * @since   2022-06-29
 * @author  ywkim
 */
@RestController
@RequestMapping("/members")
//@RequiredArgsConstructor
@Slf4j
public class MemberRestController extends BaseRestController {

    private final MemberService memberService;

    @Autowired
    public MemberRestController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("")
    @ApiOperation(value = "신규 회원 등록", notes = "신규 회원을 등록한다.", httpMethod = "POST", response = ResponseEntity.class, consumes = "application/json", tags = {})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "member", value = "신규 회원 정보", dataType = "Member", paramType = "body")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "status_code = 0, message = ok / status_code = -1, message = error / status_code = -99, message = Not Exist Required Param"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")
    })
    public ResponseEntity<?> create(@RequestBody Member member) {

        // 1. Validation
        if (member == null) {
            log.warn("create: Fail to create a new member; member=null, createdCount=0");
            return ResponseEntity.badRequest().build();
        }

        log.info("create: member={}", member);
        if (! StringUtils.hasLength(member.getId())) {
            log.warn("Fail to create a new member; createdCount=0");
            return ResponseEntity.badRequest().build();
        }

        // 2. Business Logic
        Sha512Encryptor sha512 = new Sha512Encryptor();
        member.setPassword(sha512.encrypt(member.getPassword()));
        int createdCount = memberService.create(member);

        // 3. Make Response
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("createdCount", createdCount);
        log.info("resultMap={}", resultMap);
        return ResponseEntity.ok(resultMap);
    }

    @GetMapping("/{id}")
    @Auth
    @ApiOperation(value = "회원 정보 조회", notes = "회원 1명의 정보를 조회한다.", httpMethod = "GET", response = ResponseEntity.class, consumes = "application/json", tags = {})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "회원 아이디", required = true, dataType = "string", paramType = "path", defaultValue = "gdhong")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "status_code = 0, message = ok / status_code = -1, message = error / status_code = -99, message = Not Exist Required Param"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")
    })
    public ResponseEntity<?> read(@PathVariable String id) {
        log.info("read: id={}", id);

        // 1. Validation
        if (! StringUtils.hasLength(id)) {
            log.warn("Fail to read a member; member=null");
            return ResponseEntity.badRequest().build();
        }

        // 2. Business Logic
        Member member = memberService.read(id);
        if (member == null) {
            log.warn("Fail to read a member; member=null");
            return ResponseEntity.badRequest().build();
        }

        // 3. Make Response
        log.info("member={}", member);
        return ResponseEntity.ok(member);
    }

    @GetMapping("")
    @Auth
    @ApiOperation(value = "회원 목록 조회", notes = "회원 목록을 조회한다.", httpMethod = "GET", response = ResponseEntity.class, consumes = "application/json", tags = {})
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "member", value = "검색할 회원 정보", required = false, dataType = "Member", paramType = "body")
//    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "status_code = 0, message = ok / status_code = -1, message = error / status_code = -99, message = Not Exist Required Param"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")
    })
//    public ResponseEntity<?> list() {
    public ResponseEntity<?> list(@RequestParam(required = false) Map<String, Object> param) {
        if (param == null)
            param = new HashMap<String, Object>();

        log.info("list; param={}");

        // 1. Validation

        // 2. Business Logic
        Member member = new Member();
        if (param != null) {
            if (param.get("name") != null)
                member.setName(String.valueOf(param.get("name")));

            if (param.get("email") != null)
                member.setEmail(String.valueOf(param.get("email")));

            if (param.get("phone") != null)
                member.setPhone(String.valueOf(param.get("phone")));
        }

        List<Member> list = memberService.list(member);
        if (list == null)
            list = new ArrayList<Member>();

        // 3. Make Response
        log.info("list.size={}", list.size());
        return ResponseEntity.ok(list);
    }

    @PutMapping("/{id}")
    @Auth
    @ApiOperation(value = "회원 정보 업데이트", notes = "회원 정보를 업데이트 한다.", httpMethod = "PUT", response = ResponseEntity.class, consumes = "application/json", tags = {})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "member", value = "업데이트 할 회원 정보", dataType = "Member", paramType = "body")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "status_code = 0, message = ok / status_code = -1, message = error / status_code = -99, message = Not Exist Required Param"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")
    })
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody Member member) {
        if (member == null)
            member = new Member();

        log.info("update: id={}, member={}", id, member.toString());

        // 1. Validation
        if (! StringUtils.hasLength(id)) {
            log.warn("Fail to update a member; updatedCount=0");
            return ResponseEntity.badRequest().build();
        }

        // 2. Business Logic
        member.setId(id);
        int updatedCount = memberService.update(member);

        // 3. Make Response
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("updatedCount", updatedCount);
        log.info("resultMap={}", resultMap);
        return ResponseEntity.ok(resultMap);
    }

    @DeleteMapping("/{id}")
    @Auth
    @ApiOperation(value = "회원 정보 삭제", notes = "회원 정보를 삭제한다.", httpMethod = "DELETE", response = ResponseEntity.class, consumes = "application/json", tags = {})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "삭제할 회원의 아이디", required = true, dataType = "string", paramType = "path", defaultValue = "gdhong")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "status_code = 0, message = ok / status_code = -1, message = error / status_code = -99, message = Not Exist Required Param"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")
    })
    public ResponseEntity<?> delete(@PathVariable String id) {
        log.info("update: id={}", id);

        // 1. Validation
        if (! StringUtils.hasLength(id)) {
            log.warn("Fail to delete a member; deletedCount=0");
            return ResponseEntity.badRequest().build();
        }

        // 2. Business Logic
        int deletedCount = memberService.delete(id);

        // 3. Make Response
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("deletedCount", deletedCount);
        log.info("resultMap={}", resultMap);
        return ResponseEntity.ok(resultMap);
    }

}
