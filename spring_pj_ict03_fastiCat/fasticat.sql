--============게시판, 댓글, 하트 =================
-- ----------[자유/공연후기 게시판]-------- 김소연 
-- <공연후기 게시판>
DROP TABLE reviewBoard_tbl;
CREATE TABLE reviewBoard_tbl(  
     board_num         NUMBER(7) PRIMARY KEY,           -- 글번호
     board_category    VARCHAR2(30) DEFAULT 'review',  -- 카테고리 
     board_title       VARCHAR2(50) NOT NULL,          -- 글제목
     board_content     CLOB  NOT NULL,                  -- 글내용
     board_thumnail    VARCHAR2(100),                   -- default 썸네일은 목록조회시 생성됨
     board_image       VARCHAR2(100),                   -- 이미지파일
     board_writer      VARCHAR2(30) REFERENCES mvc_customer_tbl(userid),-- 작성자 
     board_regDate     DATE  DEFAULT sysdate,           -- 작성일
     board_views       NUMBER(6)   DEFAULT 0,           -- 조회수
     board_heart       NUMBER(6)   DEFAULT 0,           -- 좋아요 
     board_show        CHAR(1) DEFAULT 'y'
);
SELECT * FROM reviewBoard_tbl
WHERE board_writer ='user2'
ORDER BY board_num DESC;
SELECT * FROM tab;
--반복추가
DECLARE 
    i NUMBER:=1; 
BEGIN
    WHILE i<=30 LOOP
        INSERT INTO reviewBoard_tbl(board_num, board_title, board_content, board_thumnail, board_writer)
        VALUES ((SELECT NVL(MAX(board_num)+1, 1) FROM reviewBoard_tbl), '후기제목'||i, '후기내용'||i, '/ict03_fastiCat/resources/upload/free.jfif', 'user1');
        i:=i+1;
    END LOOP;
END;
/
-- 공연후기게시판 댓글
DROP TABLE reviewComment_tbl;
CREATE TABLE reviewComment_tbl(  
    comment_num     NUMBER(7) PRIMARY KEY,      -- PK, 댓글 일련번호
    board_num       NUMBER(7) REFERENCES reviewBoard_tbl(board_num),                   -- FK, 게시글 번호
    board_category  VARCHAR2(30) DEFAULT 'review',
    userID          VARCHAR2(30),       -- 작성자
    content         CLOB  NOT NULL,              -- 글내용
    regDate         Date  DEFAULT sysdate       -- 등록일
);
-- <자유 게시판>
DROP TABLE freeBoard_tbl;
CREATE TABLE freeBoard_tbl(  
     board_num         NUMBER(7) PRIMARY KEY,           -- 글번호
     board_category    VARCHAR2(30) DEFAULT 'free',  -- 카테고리 
     board_title       VARCHAR2(50)  NOT NULL,          -- 글제목
     board_content     CLOB  NOT NULL,                  -- 글내용
     board_thumnail    VARCHAR2(100),                   -- default 썸네일은 목록조회시 생성됨
     board_image       VARCHAR2(100),                   -- 이미지파일
     board_writer      VARCHAR2(30) REFERENCES mvc_customer_tbl(userid),-- 작성자 
     board_regDate     DATE  DEFAULT sysdate,           -- 작성일
     board_views       NUMBER(6)   DEFAULT 0,           -- 조회수
     board_heart       NUMBER(6)   DEFAULT 0,           -- 좋아요 
     board_show        CHAR(1) DEFAULT 'y'
);
SELECT * FROM freeBoard_tbl 
ORDER BY board_num DESC;
DELETE freeBoard_tbl;
--반복추가
DECLARE 
    i NUMBER:=1; 
BEGIN
    WHILE i<=30 LOOP
        INSERT INTO freeBoard_tbl(board_num, board_title, board_content, board_thumnail, board_writer)
        VALUES ((SELECT NVL(MAX(board_num)+1, 1) FROM freeBoard_tbl), '자유제목'||i, '자유내용'||i, '/ict03_fastiCat/resources/upload/default.jpg', 'user1');
        i:=i+1;
    END LOOP;
END;
/

-- 자유게시판 댓글
DROP TABLE freeComment_tbl;
CREATE TABLE freeComment_tbl(  
    comment_num     NUMBER(7)  PRIMARY KEY,      -- PK, 댓글 일련번호
    board_num       NUMBER(7) REFERENCES freeBoard_tbl(board_num),                   -- FK, 게시글 번호
    board_category  VARCHAR2(30) DEFAULT 'free',
    userID          VARCHAR2(30) REFERENCES mvc_customer_tbl(userid),       -- 작성자
    content         CLOB  NOT NULL,              -- 글내용
    regDate         Date  DEFAULT sysdate       -- 등록일
);
SELECT * FROM freeComment_tbl;

-- 하트테이블 통합(후기+자유)
DROP TABLE heart_tbl;
CREATE TABLE heart_tbl(  
     heart_num          NUMBER(6) PRIMARY KEY,
     board_num          NUMBER(7) NOT NULL,    -- 글번호
     board_category     VARCHAR2(30) NOT NULL,   -- 카테고리
     userID             VARCHAR2(30) REFERENCES mvc_customer_tbl(userid),   -- 작성자 
     heart              CHAR(1)   DEFAULT 1,
     FOREIGN KEY(board_num) REFERENCES reviewBoard_tbl(board_num)ON DELETE CASCADE,
     FOREIGN KEY(board_num) REFERENCES freeBoard_tbl(board_num)ON DELETE CASCADE
);
--<관리자 현황 - 차트>
--결산 > 방문자 수
DROP TABLE visit_tbl;
CREATE TABLE visit_tbl (
    visit_num NUMBER PRIMARY KEY,
    visit_date DATE
);
--방문자수 데이터
DECLARE 
    i NUMBER:=1; 
BEGIN
    WHILE i<=55 LOOP
        INSERT INTO visit_tbl
        VALUES((SELECT NVL(MAX(visit_num)+1, 1) FROM visit_tbl), '2024-07-25');
        i:=i+1;
    END LOOP;
END;
/


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