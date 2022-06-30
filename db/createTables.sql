-----------------------------
-- Local MariaDB에 테이블 생성
-----------------------------

CREATE DATABASE SwMaestro;

USE SwMaestro;

CREATE TABLE Member (
	id VARCHAR(16) NOT NULL COMMENT '회원 아이디',
    password VARCHAR(128) NOT NULL COMMENT '비밀번호 (SHA-512 암호화)',
    name VARCHAR(64) DEFAULT '' COMMENT '이름',
    used CHAR(1) DEFAULT 'Y' COMMENT '사용 여부',
    createdAt DATETIME DEFAULT CURRENT_TIMESTAMP() COMMENT '최초 등록 일시',
    updatedAt DATETIME DEFAULT CURRENT_TIMESTAMP() ON UPDATE CURRENT_TIMESTAMP() COMMENT '최종 수정 일시',
    PRIMARY KEY (id)
) COMMENT='회원 정보 M';
