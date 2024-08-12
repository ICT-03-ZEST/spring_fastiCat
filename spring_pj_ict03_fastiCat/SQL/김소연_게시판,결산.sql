--============1.공연후기게시판 2. 자유게시판  1-1,2-1.댓글  3.하트  4.결산  테이블+데이터  /////  <sql문 총정리>   =================김소연 
-- ----------[자유/공연후기 게시판]-------- 
-- <1. 공연후기 게시판>
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
ORDER BY board_num DESC;
--DELETE FROM reviewBoard_tbl;

--반복추가  *회원 ID : user1 이 있어야함
DECLARE 
    i NUMBER:=1; 
BEGIN
    WHILE i<=30 LOOP
        INSERT INTO reviewBoard_tbl(board_num, board_title, board_content, board_thumnail, board_writer)
        VALUES ((SELECT NVL(MAX(board_num)+1, 1) FROM reviewBoard_tbl), '후기제목'||i, '후기내용'||i, '/ict03_fastiCat/resources/images/noimage.png', 'user1');
        i:=i+1;
    END LOOP;
END;
/
-- <1-1. 공연후기게시판 댓글>
DROP TABLE reviewComment_tbl;
CREATE TABLE reviewComment_tbl(  
    comment_num     NUMBER(7) PRIMARY KEY,      -- PK, 댓글 일련번호
    board_num       NUMBER(7) REFERENCES reviewBoard_tbl(board_num),                   -- FK, 게시글 번호
    board_category  VARCHAR2(30) DEFAULT 'review',
    userID          VARCHAR2(30),       -- 작성자
    content         CLOB  NOT NULL,              -- 글내용
    regDate         Date  DEFAULT sysdate       -- 등록일
);
SELECT * FROM reviewComment_tbl; 

-- <2. 자유 게시판>
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
--DELETE freeBoard_tbl;

--[데이터 - 반복추가]  *회원 ID : user1 이 있어야함
DECLARE 
    i NUMBER:=1; 
BEGIN
    WHILE i<=30 LOOP
        INSERT INTO freeBoard_tbl(board_num, board_title, board_content, board_thumnail, board_writer)
        VALUES ((SELECT NVL(MAX(board_num)+1, 1) FROM freeBoard_tbl), '자유제목'||i, '자유내용'||i, '/ict03_fastiCat/resources/images/noimage.png', 'user1');
        i:=i+1;
    END LOOP;
END;
/

-- <2-1 자유게시판 댓글 >
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

-- <3. 하트테이블 통합(후기+자유)>
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

--<4. 관리자 현황 - 결산차트>
--(1) 결산 > 방문자 수 테이블
DROP TABLE visit_tbl;
CREATE TABLE visit_tbl (
    visit_num NUMBER PRIMARY KEY,
    visit_date DATE
);
--<테스트 데이터 추가>
--[1. 방문자 수 데이터 - 반복추가] *i 값 변경 / 날짜 데이터 변경해서 추가
DECLARE 
    i NUMBER:=1; 
BEGIN
    WHILE i<=45 LOOP
        INSERT INTO visit_tbl
        VALUES((SELECT NVL(MAX(visit_num)+1, 1) FROM visit_tbl), '2024-07-17');
        i:=i+1;
    END LOOP;
    
    WHILE i<=30 LOOP
        INSERT INTO visit_tbl
        VALUES((SELECT NVL(MAX(visit_num)+1, 1) FROM visit_tbl), '2024-07-22');
        i:=i+1;
    END LOOP;
    
    WHILE i<=22 LOOP
        INSERT INTO visit_tbl
        VALUES((SELECT NVL(MAX(visit_num)+1, 1) FROM visit_tbl), '2024-07-19');
        i:=i+1;
    END LOOP;
END;
/

--[2. 예매 수량 - 반복추가] 테이블 : show_Reservation
DECLARE 
    i NUMBER:=1; 
BEGIN
    WHILE i<=15 LOOP
        INSERT INTO show_Reservation(show_ResId, showNum, userID, Reservation_dateNow)
        VALUES((SELECT NVL(MAX(show_ResId)+1,1) FROM show_Reservation), 2, 'user1', '2024-07-27');
        i:=i+1;
    END LOOP;
    
    WHILE i<=10 LOOP
        INSERT INTO show_Reservation(show_ResId, showNum, userID, Reservation_dateNow)
        VALUES((SELECT NVL(MAX(show_ResId)+1,1) FROM show_Reservation), 2, 'user1', '2024-07-29');
        i:=i+1;
    END LOOP;
    
    WHILE i<=28 LOOP
       INSERT INTO show_Reservation(show_ResId, showNum, userID, Reservation_dateNow)
       VALUES((SELECT NVL(MAX(show_ResId)+1,1) FROM show_Reservation), 2, 'user1', '2024-07-30');
        i:=i+1;
    END LOOP;
END;
/

--[3. 등록 컨텐츠 개수] 테이블 : show_tbl(공연) / show_tbl_fes(페스티벌)
--show_tbl(공연)
DECLARE 
    i NUMBER:=1; 
BEGIN
    WHILE i<=20 LOOP
       INSERT INTO show_tbl(showNum, showCategory, showName, showImage, showPlace, showTime, showDay, showAge, showPrice)
        VALUES(i, '트로트', '콘서트이름'||i, '/ict03_fastiCat/resources/images/contents/미스터트롯2 진선미.gif', '체조경기장', 120, '2024/07/07', '7세이상',  115000); 
        i:=i+1;
         INSERT INTO show_tbl(showNum, showCategory, showName, showImage, showPlace, showTime, showDay, showAge, showPrice)
         VALUES(i, '케이팝', '콘서트이름'||i, '/ict03_fastiCat/resources/images/contents/더보이즈.jpg', '체조경기장', 120, '2024/07/07', '15세이상',  115000); 
        i:=i+1; 
         INSERT INTO show_tbl(showNum, showCategory, showName, showImage, showPlace, showTime, showDay, showAge, showPrice)
          VALUES(i, '케이팝', '콘서트이름'||i, '/ict03_fastiCat/resources/images/contents/블랙핑크.jpg', '체조경기장', 120, '2024/07/07', '15세이상', 115000); 
        i:=i+1;
         INSERT INTO show_tbl(showNum, showCategory, showName, showImage, showPlace, showTime, showDay, showAge, showPrice)
          VALUES(i, '인디', '콘서트이름'||i, '/ict03_fastiCat/resources/images/contents/나상현씨밴드.jfif', '체조경기장', 120, '2024/07/07', '15세이상', 115000); 
        i:=i+1;
         INSERT INTO show_tbl(showNum, showCategory, showName, showImage, showPlace, showTime, showDay, showAge, showPrice)
          VALUES(i, '케이팝', '콘서트이름'||i, '/ict03_fastiCat/resources/images/contents/데이식스.jpg', '체조경기장', 120, '2024/07/07', '전체연령관람', 115000); 
        i:=i+1;
    END LOOP;
END;
/

--show_tbl_fes(페스티벌)
DECLARE 
    i NUMBER:=1; 
BEGIN
    WHILE i<=20 LOOP
       INSERT INTO show_tbl_fes(showNum, showCategory, showName, showImage, showPlace, showTime, showDay, showAge, showPrice)
        VALUES(i, 'festival', '콘서트이름'||i, '/ict03_fastiCat/resources/images/contents/미스터트롯2 진선미.gif', '체조경기장', 120, '2024/07/07', '15세이상',  115000); 
        i:=i+1;
         INSERT INTO show_tbl_fes(showNum, showCategory, showName, showImage, showPlace, showTime, showDay, showAge, showPrice)
         VALUES(i, 'festival', '콘서트이름'||i, '/ict03_fastiCat/resources/images/contents/더보이즈.jpg', '체조경기장', 120, '2024/07/07', '15세이상',  115000); 
        i:=i+1; 
         INSERT INTO show_tbl_fes(showNum, showCategory, showName, showImage, showPlace, showTime, showDay, showAge, showPrice)
          VALUES(i, 'festival', '콘서트이름'||i, '/ict03_fastiCat/resources/images/contents/블랙핑크.jpg', '체조경기장', 120, '2024/07/07', '15세이상', 115000); 
        i:=i+1;
         INSERT INTO show_tbl_fes(showNum, showCategory, showName, showImage, showPlace, showTime, showDay, showAge, showPrice)
          VALUES(i, 'festival', '콘서트이름'||i, '/ict03_fastiCat/resources/images/contents/나상현씨밴드.jfif', '체조경기장', 120, '2024/07/07', '15세이상', 115000); 
        i:=i+1;
         INSERT INTO show_tbl_fes(showNum, showCategory, showName, showImage, showPlace, showTime, showDay, showAge, showPrice)
          VALUES(i, 'festival', '콘서트이름'||i, '/ict03_fastiCat/resources/images/contents/데이식스.jpg', '체조경기장', 120, '2024/07/07', '15세이상', 115000); 
        i:=i+1;
    END LOOP;
END;
/

--[4. 회원가입자 수]
DECLARE 
    i NUMBER:=1; 
BEGIN
    WHILE i<=10 LOOP
        INSERT INTO mvc_customer_tbl(userid, password, username, birthday, address, email)
        VALUES ('user'||i, '1234', '회원', '1990-04-03', '인천', 'qw@naver.com');
        i:=i+1;
    END LOOP;
END;
/

--=====================================테이블+데이터 끝==========================================

------------------<sql문 총정리>-----------------------(1)게시판 (2)조회,하트 (3)게시글 (4)댓글
--[ boardMapper ]
--(1). 게시판
--(1).A - 게시글 목록조회(카테고리별)

-- reviewBoard_tbl(후기)
SELECT *
  FROM (
        SELECT A.*
             , ROWNUM AS rn 
          FROM 
            (SELECT * FROM reviewBoard_tbl
              WHERE board_show = 'y'
              ORDER BY board_num DESC
             ) A
        )
WHERE rn BETWEEN 1 AND 10

-- freeBoard_tbl(자유)
SELECT *
  FROM (
        SELECT A.*
             , ROWNUM AS rn 
          FROM 
            (SELECT * FROM freeBoard_tbl
              WHERE board_show = 'y'
              ORDER BY board_num DESC
             ) A
        )
WHERE rn BETWEEN 1 AND 10

--(1).B - 게시글 개수

-- reviewBoard_tbl(후기)
SELECT COUNT(*) AS cnt
  FROM  reviewBoard_tbl
 WHERE board_show = 'y'
 
-- freeBoard_tbl(자유)
SELECT COUNT(*) AS cnt
  FROM  freeBoard_tbl
 WHERE board_show = 'y'

--(1).C - 게시글 상세페이지

-- reviewBoard_tbl(후기)
SELECT * 
  FROM  reviewBoard_tbl
 WHERE board_num = 30;
 
-- freeBoard_tbl(자유)
SELECT * 
  FROM  freeBoard_tbl
 WHERE board_num = 30;

--(2) 부가 기능 (하트, 게시글조회, 조회수 증가)

--(2).A 하트 조회 (하트를 누른 적이 있는지 조회)
SELECT COUNT(*) AS cnt
  FROM heart_tbl
 WHERE userID = 'user1'
   AND board_num = 30
   AND board_category = 'review'

--(2).B 회원 게시글 이력조회 (회원이 작성한 게시글인지 조회)

-- reviewBoard_tbl(후기)
SELECT COUNT(*) AS cnt
  FROM  reviewBoard_tbl
 WHERE board_writer = 'user1'
   AND board_num = 30
 
-- freeBoard_tbl(자유)
SELECT COUNT(*) AS cnt
  FROM  freeBoard_tbl
 WHERE board_writer = 'user1'
   AND board_num = 30

--(2).C 조회수 증가
-- reviewBoard_tbl(후기)
UPDATE reviewBoard_tbl
   SET board_views = board_views + 1
 WHERE board_num = 30

-- freeBoard_tbl(자유)
UPDATE freeBoard_tbl
   SET board_views = board_views + 1
 WHERE board_num = 30

--(2).D 게시판 테이블에서 하트 수정
-- reviewBoard_tbl(후기)
UPDATE reviewBoard_tbl
   SET board_heart = board_heart  (board_heart <= board_heart+1 OR board_heart-1)
 WHERE board_num = 30

-- freeBoard_tbl(자유)
UPDATE freeBoard_tbl
   SET board_heart = board_heart  (board_heart <= board_heart+1 OR board_heart-1)
 WHERE board_num = 30

--(2).F 하트 클릭 => 하트 insert
INSERT INTO heart_tbl(heart_num, board_num, board_category, userID)
VALUES ((SELECT NVL(MAX(heart_num)+1, 1) FROM heart_tbl), #{board_num}, #{board_category}, #{userID})

--(2).G 하트 취소 => 하트 delete 
DELETE heart_tbl
 WHERE userID = #{userID}
   AND board_num = #{board_num}
   AND board_category = #{board_category}

----(3) 게시글 CRUD

--(3).A 게시글 작성
-- reviewBoard_tbl(후기)
INSERT INTO reviewBoard_tbl(board_num, board_title, board_content, board_thumnail, board_image, board_writer) 
VALUES ((SELECT NVL(MAX(board_num)+1, 1) FROM reviewBoard_tbl), #{board_title}, #{board_content}, #{board_thumnail}, #{board_image}, #{board_writer})

-- freeBoard_tbl(자유)
INSERT INTO freeBoard_tbl(board_num, board_title, board_content, board_thumnail, board_image, board_writer) 
VALUES ((SELECT NVL(MAX(board_num)+1, 1) FROM freeBoard_tbl), #{board_title}, #{board_content}, #{board_thumnail}, #{board_image}, #{board_writer})

--(3).B 게시글 수정
-- reviewBoard_tbl(후기)
UPDATE reviewBoard_tbl 
   SET board_title = #{board_title}, board_content= #{board_content}, board_thumnail= #{board_thumnail}, board_image = #{board_image}
 WHERE board_num = #{board_num}
 
-- freeBoard_tbl(자유)
UPDATE freeBoard_tbl 
   SET board_title = #{board_title}, board_content= #{board_content}, board_thumnail= #{board_thumnail}, board_image = #{board_image}
 WHERE board_num = #{board_num}

--(3).C 게시글 삭제
-- reviewBoard_tbl(후기)
 UPDATE reviewBoard_tbl 
    SET board_show = 'n'
  WHERE board_num = 30
  
-- freeBoard_tbl(후기)
UPDATE freeBoard_tbl 
   SET board_show = 'n'
 WHERE board_num = 30
 
--(4) 댓글
--(4).A 댓글 목록조회
-- reviewComment_tbl(후기)
SELECT *
  FROM reviewComment_tbl
 WHERE board_num = 30
 ORDER BY comment_num DESC
  
-- freeComment_tbl(후기)
SELECT *
  FROM freeComment_tbl
 WHERE board_num = 30
 ORDER BY comment_num DESC

--(4).B 댓글 작성
-- reviewComment_tbl(후기)
INSERT INTO reviewComment_tbl(comment_num, board_num, userID, content) 
VALUES ((SELECT NVL(MAX(comment_num)+1, 1) FROM reviewComment_tbl), #{board_num}, #{userID}, #{content})
  
-- freeComment_tbl(후기)
INSERT INTO freeComment_tbl(comment_num, board_num, userID, content) 
VALUES ((SELECT NVL(MAX(comment_num)+1, 1) FROM freeComment_tbl), #{board_num}, #{userID}, #{content})

--(4).C 댓글 한건 조회
-- reviewComment_tbl(후기)
SELECT *
  FROM reviewComment_tbl
 WHERE comment_num = #{comment_num}
 
-- freeComment_tbl(후기)
SELECT *
  FROM freeComment_tbl
 WHERE comment_num = #{comment_num}

--(4).D 댓글 수정
-- reviewComment_tbl(후기)
UPDATE reviewComment_tbl 
   SET content = #{content}
 WHERE comment_num = #{comment_num}
 
-- freeComment_tbl(후기)
UPDATE freeComment_tbl 
   SET content = #{content}
 WHERE comment_num = #{comment_num}

--(4).F 댓글 삭제
-- reviewComment_tbl(후기)
DELETE FROM reviewComment_tbl 
 WHERE comment_num = #{comment_num}
 
-- freeComment_tbl(후기)
DELETE FROM freeComment_tbl 
 WHERE comment_num = #{comment_num}

--[ mainShowMapper ]
--(5) 컨텐츠 (공연/페스티벌)
--(5).A 공연개수
--show_tbl_fes(페스티벌)
SELECT COUNT(*) as cnt
 FROM show_tbl_fes

--show_tbl(공연)
SELECT COUNT(*) as cnt
 FROM show_tbl

--(5).B 공연 목록
--show_tbl_fes(페스티벌)
SELECT *
  FROM 
	(
	SELECT showNum, showName, showCategory, showIndate, showPlace, showPrice, showTime, showAge, showBene, curCapacity, maxCapacity, showDay, showImage, show, ROWNUM AS rn
	  FROM (
        SELECT showNum, showName, showCategory, showIndate, showPlace, showPrice, showTime, showAge, showBene, curCapacity, maxCapacity, showDay, showImage, show,
               ROW_NUMBER() OVER (PARTITION BY showName ORDER BY showNum DESC) as rn
         FROM show_tbl_fes
        WHERE show = 'y'
    )
    WHERE rn = 1
) 
WHERE rn BETWEEN TO_NUMBER(#{start}) AND TO_NUMBER(#{end})

--show_tbl(공연)
SELECT *
  FROM 
	(
	SELECT showNum, showName, showCategory, showIndate, showPlace, showPrice, showTime, showAge, showBene, curCapacity, maxCapacity, showDay, showImage, show, ROWNUM AS rn
	  FROM (
        SELECT showNum, showName, showCategory, showIndate, showPlace, showPrice, showTime, showAge, showBene, curCapacity, maxCapacity, showDay, showImage, show,
               ROW_NUMBER() OVER (PARTITION BY showName ORDER BY showNum DESC) as rn
         FROM show_tbl
        WHERE show = 'y'
    )
    WHERE rn = 1
) 
WHERE rn BETWEEN TO_NUMBER(#{start}) AND TO_NUMBER(#{end})

--(5).C 공연 상세페이지
--show_tbl_fes(페스티벌)
SELECT * 
  FROM show_tbl_fes
 WHERE showNum=#{showNum}
--show_tbl(공연)
SELECT * 
  FROM show_tbl
 WHERE showNum=#{showNum}

--[ chartMapper ]
--(6) 현황차트
--(6).A 전체 회원수 
SELECT COUNT(*) AS cnt FROM mvc_customer_tbl
 WHERE show='y'

--(6).B 등록된 공연 및 페스티벌 수
SELECT SUM(cnt)AS total 
  FROM (
		SELECT COUNT(*) AS cnt FROM show_tbl_fes
		 WHERE show='y'
		UNION ALL
		SELECT COUNT(*) AS cnt FROM show_tbl
		 WHERE show='y')

--(6).C 일주일 간 등록된 게시물 수
SELECT SUM(cnt) 
  FROM (
		SELECT COUNT(*) AS cnt FROM reviewBoard_tbl
		 WHERE board_show = 'y'
		   AND board_regDate BETWEEN SYSDATE - INTERVAL '7' DAY AND SYSDATE
	    UNION ALL
		SELECT COUNT(*) AS cnt FROM freeBoard_tbl
		 WHERE board_show = 'y'
		   AND board_regDate BETWEEN SYSDATE - INTERVAL '7' DAY AND SYSDATE)

--(6).D 일일 등록 게시글 수	
SELECT TRUNC(board_regDate) AS board_regDate, COUNT(board_regDate) AS board_count 
  FROM (
		SELECT * FROM reviewBoard_tbl
		WHERE board_show = 'y'
		UNION ALL
		SELECT *  FROM freeBoard_tbl
		WHERE board_show = 'y')
GROUP BY TRUNC(board_regDate)	

--(6).E 일주일 간 예매된 수량
SELECT COUNT(*) AS cnt FROM show_Reservation
 WHERE Reservation_dateNow BETWEEN SYSDATE - INTERVAL '7' DAY AND SYSDATE
--(6).F 일일  예매 수량
SELECT TRUNC(Reservation_dateNow) AS reservation_date,  
	   COUNT(Reservation_dateNow) AS reserv_count 
  FROM show_Reservation
 GROUP BY TRUNC(Reservation_dateNow)
 ORDER BY reservation_date ASC
--(6).G 인기 게시글 목록
SELECT * 
  FROM (
		SELECT A.*, rownum AS rn
		  FROM (
		        SELECT * FROM(
		        SELECT * FROM reviewBoard_tbl
		         WHERE board_show = 'y'
        		 UNION ALL
		        SELECT * FROM freeBoard_tbl
		         WHERE board_show = 'y')
		         ORDER BY board_views DESC
        		) A )
WHERE rn <= 5

--(6).H 일일 방문자수
SELECT TRUNC(visit_date) AS visit_date,  
	   COUNT(visit_date) AS visit_count 
  FROM visit_tbl
GROUP BY TRUNC(visit_date)
ORDER BY visit_date ASC

--(6).I 방문자 수 추가
INSERT INTO visit_tbl
VALUES((SELECT NVL(MAX(visit_num)+1, 1) FROM visit_tbl), sysdate)