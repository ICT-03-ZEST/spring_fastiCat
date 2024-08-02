


--============배너, 공연 &예매, 페스티벌 ====================
------------[배너, 공연 & 예매, 페스티벌 테이블]-------- 김가연
-- <배너 테이블>
DROP TABLE mvc_ad_banner_tbl CASCADE CONSTRAINTS;
 CREATE TABLE mvc_ad_banner_tbl(
    bannerNo        NUMBER(7)  PRIMARY KEY,            -- 배너번호
    bannerArea      VARCHAR2(50)  NOT NULL UNIQUE,     -- 배너영역(메인1,2,3,4,5)
    bannerImg       VARCHAR2(100) NOT NULL,            -- 배너 이미지
    bannerStatus    VARCHAR2(20) NOT NULL,             -- 배너 상태코드(사용함,안함)
    bannerIndate    DATE  DEFAULT sysdate,             -- 등록일
    show            CHAR(1) DEFAULT 'y'     
 );
SELECT * FROM mvc_ad_banner_tbl;

-- <공연&예매 통합 테이블>
DROP TABLE show_tbl CASCADE CONSTRAINTS;
CREATE TABLE show_tbl(                                       
    showNum         NUMBER(6)      PRIMARY KEY,              -- 공연번호
    showName        VARCHAR2(150)  NOT NULL,                 -- 공연명
    showCategory    VARCHAR2(50)   NOT NULL,                 -- 공연카테고리**(트로트,케이팝,인디)
    showIndate      DATE           DEFAULT sysdate,          -- 공연등록일**(sysdate)
    showPlace       VARCHAR2(150)  NOT NULL,                 -- 공연장소                                     
    showPrice       NUMBER(20)     ,                         -- 1매당 가격
    showTime        NUMBER(5)      ,                         -- 공연시간
    showAge         VARCHAR2(50)   ,                         -- 관람연령**VARCHAR2
    showBene        VARCHAR2(150)  ,                         -- 혜택
    curCapacity     NUMBER(20)     DEFAULT 0,                -- 현수용인원
    maxCapacity     NUMBER(20)     DEFAULT 50,               -- 최대수용인원
    showDay         Date           NOT NULL,                 -- 공연날짜
    showImage       VARCHAR2(150)  ,                         -- 이미지 이름
    show            CHAR(1)        DEFAULT 'y'               -- 삭제**
);
SELECT * FROM show_tbl;

 INSERT INTO show_tbl(showNum, showName, showCategory, showIndate, showPlace, showPrice, showTime, showAge, showBene,  showDay, showImage)
 VALUES((SELECT NVL(MAX(showNum)+1, 1) FROM show_tbl), '콘서트이름1', '케이팝', sysdate, '체조경기장', 154000, 120,  '전체관람가', '혜택', '2024-01-01', '/ict03_fastiCat/resources/upload/박혜신.jpg'); 
COMMIT;

-- show_tbl 시퀀스 생성
CREATE SEQUENCE SHOW_TBL_SEQ
START WITH 1
INCREMENT BY 1
NOCACHE;

-- show_tbl 트리거 생성
CREATE OR REPLACE TRIGGER show_tbl_bi
BEFORE INSERT ON show_tbl
FOR EACH ROW
BEGIN
    :NEW.showNum := SHOW_TBL_SEQ.NEXTVAL;
END;
/ 

-- <페스티벌 테이블>
DROP TABLE show_tbl_fes CASCADE CONSTRAINTS;
CREATE TABLE show_tbl_fes(                                       
    showNum         NUMBER(6)      PRIMARY KEY,              -- 공연번호
    showName        VARCHAR2(150)  NOT NULL,                 -- 공연명
    showCategory    VARCHAR2(50)   NOT NULL,                 -- 공연카테고리**(페스티벌)
    showIndate      DATE           DEFAULT sysdate,          -- 공연등록일**(sysdate)
    showPlace       VARCHAR2(150)  NOT NULL,                 -- 공연장소                                     
    showPrice       NUMBER(20)     ,                         -- 1매당 가격
    showTime        NUMBER(5)      ,                         -- 공연시간
    showAge         VARCHAR2(50)   ,                         -- 관람연령**VARCHAR2
    showBene        VARCHAR2(150)  ,                         -- 혜택
    curCapacity     NUMBER(20)     DEFAULT 0,                -- 현수용인원
    maxCapacity     NUMBER(20)     DEFAULT 50,               -- 최대수용인원
    showDay         Date           NOT NULL,                 -- 공연날짜
    showImage       VARCHAR2(150)  ,                         -- 이미지 이름
    show            CHAR(1)        DEFAULT 'y'               -- 삭제**
);
SELECT * FROM show_tbl_fes;

 INSERT INTO show_tbl_fes(showNum, showName, showCategory, showIndate, showPlace, showPrice, showTime, showAge, showBene,  showDay, showImage)
 VALUES((SELECT NVL(MAX(showNum)+1, 1) FROM show_tbl_fes), '콘서트이름1', '케이팝', sysdate, '체조경기장', 154000, 120,  '전체관람가', '혜택', '2024-01-01', '/ict03_fastiCat/resources/upload/박혜신.jpg'); 
COMMIT;

-- show_tbl_fes 시퀀스 생성
CREATE SEQUENCE SHOW_TBL_FES_SEQ
START WITH 1
INCREMENT BY 1
NOCACHE;

-- show_tbl_fes 트리거 생성
CREATE OR REPLACE TRIGGER show_tbl_fes_bi
BEFORE INSERT ON show_tbl_fes
FOR EACH ROW
BEGIN
    :NEW.showNum := SHOW_TBL_FES_SEQ.NEXTVAL;
END;
/