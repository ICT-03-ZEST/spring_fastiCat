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