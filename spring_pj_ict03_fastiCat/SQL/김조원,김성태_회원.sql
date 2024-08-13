-------------[ 회원 정보 테이블 ]----------------------
DROP TABLE mvc_customer_tbl CASCADE CONSTRAINTS;
CREATE TABLE mvc_customer_tbl(
    userid    VARCHAR2(20) PRIMARY KEY,
    password  VARCHAR2(100) NOT NULL,  -- 암호화된 비밀번호를 저장해야 하므로 사이즈 크게 
    username  VARCHAR2(20) NOT NULL,   
    birthday  DATE         NOT NULL,
    address   VARCHAR2(50) NOT NULL,
    hp        VARCHAR2(13),
    email     VARCHAR2(50) NOT NULL,
    regDate  TIMESTAMP DEFAULT sysdate,   -- 가입일
    show CHAR(1) DEFAULT 'y',
    -- 시큐리티를 위한 추가
    key      VARCHAR2(100), -- 회원가입시 이메일 key 추가
    authority VARCHAR2(30) DEFAULT 'ROLE_USER', -- 권한 : ROLE_USER:customer, ROLE_ADMIN:관리자
    enabled   CHAR(1)    DEFAULT 0    -- 계정사용 가능여부(1:사용가능, 0:사용불가) : 이메일인증시 1로 update
);


SELECT  * FROM mvc_customer_tbl;

-- 관리자 권한 업데이트
UPDATE mvc_customer_tbl
SET authority='ROLE_ADMIN'
WHERE userid='admin';
COMMIT;