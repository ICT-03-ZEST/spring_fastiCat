--============1.공연후기게시판 2. 자유게시판  1-1,2-1.댓글  3.하트  4.결산  테이블+데이터  /////  <sql문 총정리>   =================김소연 
---------<< 예매테이블 [show_tbl] >>----------- 
DROP SEQUENCE show_tbl_seq;
DROP TABLE show_tbl CASCADE CONSTRAINTS;

CREATE TABLE show_tbl (
    showNum         NUMBER(6)      PRIMARY KEY,                                 -- 공연번호
    showName        VARCHAR2(150)   NOT NULL,                                   -- 공연명
    showPlace       VARCHAR2(150)   NOT NULL,                                   -- 공연장소
    showPrice       NUMBER(20, 2),                                               -- 1매당 가격 (소수점 2자리까지)
    showTime        NUMBER(5),                                                   -- 공연시간
    showAge         NUMBER(3),                                                   -- 관람연령
    showBene        VARCHAR2(150),                                               -- 혜택
    curCapacity     NUMBER(20)     DEFAULT 0,                                    -- 현수용인원
    maxCapacity     NUMBER(20)     DEFAULT 50,                                    -- 최대수용인원
    showDay         DATE           NOT NULL,                                    -- 공연날짜
    showImage       VARCHAR2(150),                                               -- 이미지 이름
    show      CHAR(1)        DEFAULT 'y' CHECK (show IN ('y', 'n')),            -- 공연기간 활성화 유무
    showCategory    VARCHAR2(150)   NOT NULL,                                   -- 공연카테고리 (트로트, 케이팝, 인디 등)
    showIndate      DATE           DEFAULT SYSDATE                               -- 등록일자
);

CREATE SEQUENCE show_tbl_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;  
--더미 데이터 입력-----------------------------------------------------
DECLARE
    start_date DATE := TO_DATE('2024-06-01', 'YYYY-MM-DD');
    end_date DATE := TO_DATE('2024-10-31', 'YYYY-MM-DD');
    current_day DATE;
    show_names SYS.ODCIVARCHAR2LIST := SYS.ODCIVARCHAR2LIST(
        '뮤지컬 라이온킹', '콘서트 BTS', '연극 햄릿', '뮤지컬 캣츠',
        '오페라 라 트라비아타', '발레 백조의 호수', '뮤지컬 맘마미아',
        '콘서트 아이유', '연극 리어왕', '오페라 토스카'
    );
    show_places SYS.ODCIVARCHAR2LIST := SYS.ODCIVARCHAR2LIST(
        '잠실실내체육관', '올림픽공원', '세종문화회관', '코엑스', '서울예술의전당'
    );
    show_benes SYS.ODCIVARCHAR2LIST := SYS.ODCIVARCHAR2LIST(
        '무이자할부', '조조할인', '회원할인'
    );
    show_categories SYS.ODCIVARCHAR2LIST := SYS.ODCIVARCHAR2LIST(
        '콘서트', '뮤지컬', '연극', '오페라', '발레'
    );
    j PLS_INTEGER;
    selected_show_name VARCHAR2(150);
    max_capacity CONSTANT PLS_INTEGER := 350;

BEGIN
    current_day := start_date;

    WHILE current_day <= end_date LOOP
        FOR j IN 1..5 LOOP
            selected_show_name := show_names(TRUNC(DBMS_RANDOM.VALUE(1, show_names.COUNT + 1)));

            -- 첫날 curCapacity 0
            INSERT INTO show_tbl(
                showNum, showName, showPlace, showPrice, curCapacity, maxCapacity, showDay, show, showBene, showAge, showTime, showImage, showCategory
            ) VALUES (
                show_tbl_seq.NEXTVAL,
                selected_show_name,
                show_places(TRUNC(DBMS_RANDOM.VALUE(1, show_places.COUNT + 1))),
                200000 + TRUNC(DBMS_RANDOM.VALUE(1, 6)) * 10000,
                0,
                max_capacity,
                current_day,
                'y',
                show_benes(TRUNC(DBMS_RANDOM.VALUE(1, show_benes.COUNT + 1))),
                7,
                120,
                '이문세',  -- showImage 값은 '이문세'로 통일
                show_categories(TRUNC(DBMS_RANDOM.VALUE(1, show_categories.COUNT + 1)))
            );

            -- 둘째날 curCapacity 중간값
            INSERT INTO show_tbl(
                showNum, showName, showPlace, showPrice, curCapacity, maxCapacity, showDay, show, showBene, showAge, showTime, showImage, showCategory
            ) VALUES (
                show_tbl_seq.NEXTVAL,
                selected_show_name,
                show_places(TRUNC(DBMS_RANDOM.VALUE(1, show_places.COUNT + 1))),
                200000 + TRUNC(DBMS_RANDOM.VALUE(1, 6)) * 10000,
                max_capacity / 2,
                max_capacity,
                current_day + 1,
                'y',
                show_benes(TRUNC(DBMS_RANDOM.VALUE(1, show_benes.COUNT + 1))),
                7,
                120,
                '이문세',  -- showImage 값은 '이문세'로 통일
                show_categories(TRUNC(DBMS_RANDOM.VALUE(1, show_categories.COUNT + 1)))
            );

            -- 마지막 날 curCapacity와 maxCapacity 동일
            INSERT INTO show_tbl(
                showNum, showName, showPlace, showPrice, curCapacity, maxCapacity, showDay, show, showBene, showAge, showTime, showImage, showCategory
            ) VALUES (
                show_tbl_seq.NEXTVAL,
                selected_show_name,
                show_places(TRUNC(DBMS_RANDOM.VALUE(1, show_places.COUNT + 1))),
                200000 + TRUNC(DBMS_RANDOM.VALUE(1, 6)) * 10000,
                max_capacity,
                max_capacity,
                current_day + 2,
                'y',
                show_benes(TRUNC(DBMS_RANDOM.VALUE(1, show_benes.COUNT + 1))),
                7,
                120,
                '이문세',  -- showImage 값은 '이문세'로 통일
                show_categories(TRUNC(DBMS_RANDOM.VALUE(1, show_categories.COUNT + 1)))
            );
        END LOOP;
        current_day := current_day + 3;  -- 매 공연은 3일 동안 진행되므로 날짜를 3일씩 증가시킴
    END LOOP;
END;
/



---------------------------------------------
SELECT * FROM show_tbl ORDER BY SHOWNUM;
---------------------------------------------
-- 메인화면 달력에 표기될 리스트(공연명)
--      공연번호,공연명    ,현수용인원   ,최대수용인원  , 공연일 ,활성화체크
SELECT showNum, showName, curCapacity, maxCapacity, showDay, show 
FROM show_tbl 
WHERE LPAD(TO_CHAR(showDay, 'MM'), 2, '0') = LPAD(7, 2, '0') 
AND EXTRACT(YEAR FROM showDay) = EXTRACT(YEAR FROM SYSDATE) 
AND SHOW = 'y'  
ORDER BY showNum; 

commit;
---------------------------------------------
-- 상세페이지에 표기될 리스트(show_tbl)
--      공연번호,공연명    ,공연장소   ,1매당가격 ,공연시간,관람연령,혜택        ,현수용인원   ,최대수용인원  , 공연일 ,이미지 이름,활성화체크
SELECT showNum, showName,showPlace, showPrice,showTime,showAge,showBene,curCapacity, maxCapacity, showDay,showImage ,show, showCategory 
FROM show_tbl 
WHERE SHOW LIKE 'y'
AND showNum = 648 
ORDER BY showNum;

SELECT * 
FROM show_tbl 
WHERE showName LIKE '공연명187'  
AND SHOWCHK LIKE 'y'
ORDER BY SHOWNUM;

commit;

SELECT * FROM show_tbl ORDER BY SHOWNUM;

---------<< 예약 테이블 [show_Reservation] >>---------- 
DROP SEQUENCE show_Reservation_seq;
DROP TABLE show_Reservation CASCADE CONSTRAINTS;
-- 공연에 대한 예매정보 저장하는 테이블
CREATE TABLE show_Reservation (
    show_ResId          NUMBER(6)           PRIMARY KEY,
    showNum             NUMBER(6)           REFERENCES show_tbl(showNum),
    userID              VARCHAR2(20)        REFERENCES mvc_customer_tbl(userid),
    totalPrice          NUMBER(20),
    Reservation_date    date,
    Reservation_check   CHAR(1)             DEFAULT 'y',
    Reservation_dateNow date                DEFAULT sysdate
);
-- show_seat_grade 테이블을 위한 시퀀스
CREATE SEQUENCE show_Reservation_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;
    
SELECT * FROM show_Reservation ORDER BY show_ResId;


INSERT INTO show_Reservation (show_ResId, showNum, userID, totalPrice, Reservation_date) 
VALUES (show_Reservation_seq.NEXTVAL, '304', 'hong1234', '220000', '24/08/02');

--INSERT INTO show_Reservation (show_ResId, showNum, userID, totalPrice, Reservation_date) 
--VALUES (show_Reservation_seq.NEXTVAL, #{showNum, jdbcType=INTEGER}, #{userID, jdbcType=VARCHAR}, #{totalPrice, jdbcType=INTEGER}, #{sendShowDay, jdbcType=DATE});

select * from show_Reservation;
CREATE TABLE mvc_customer_tbl(
   userid     VARCHAR2(20)    PRIMARY KEY,    -- ID
   password   VARCHAR2(20)    NOT NULL,          -- 비밀번호
   username   VARCHAR2(20)    NOT NULL,          -- 이름
   birthday   DATE            NOT NULL,          -- 생년월일    
   address    VARCHAR2(50)    NOT NULL,          -- 주소
   hp         VARCHAR2(13),                      -- 핸드폰      
   email      VARCHAR2(50)    NOT NULL,          -- 이메일
   regDate    TIMESTAMP       DEFAULT sysdate,    -- 가입일
   show            CHAR(1) DEFAULT 'y'
); 


select * from mvc_customer_tbl;
select * from show_tbl;

SELECT userid, password FROM mvc_customer_tbl WHERE userid='hong123';

INSERT INTO mvc_customer_tbl(userid, password, username, birthday, address, hp, email)
VALUES('hong', 'h1234', '홍길동', '2000/01/01','서울시 강남구 대치동', '010-1111-2222','hong@gmail.com');

UPDATE show_tbl 
SET curCapacity = curCapacity+2 
WHERE shownum = 1;
    
COMMIT;
rollback;

SELECT * FROM show_Reservation;
SELECT * FROM show_Reservation order by show_resid;

SELECT * FROM show_Reservation 
where showNum=340
ORDER BY show_ResId;

-- ALTER TABLE show_Reservation
--   ADD Reservation_date date;
--ALTER TABLE show_Reservation
--ADD Reservation_check CHAR(1) DEFAULT 'y';

CREATE TABLE mvc_customer_tbl(
   userid     VARCHAR2(20)    PRIMARY KEY,    -- ID
   password   VARCHAR2(20)    NOT NULL,          -- 비밀번호
   username   VARCHAR2(20)    NOT NULL,          -- 이름
   birthday   DATE            NOT NULL,          -- 생년월일    
   address    VARCHAR2(50)    NOT NULL,          -- 주소
   hp         VARCHAR2(13),                      -- 핸드폰      
   email      VARCHAR2(50)    NOT NULL,          -- 이메일
   regDate    TIMESTAMP       DEFAULT sysdate,    -- 가입일
   show            CHAR(1) DEFAULT 'y'
); 

INSERT INTO mvc_customer_tbl(userid, password, username, birthday, address, hp, email)
VALUES('hong', 'h1234', '홍길동', '2000/01/01','서울시 강남구 대치동', '010-1111-2222','hong@gmail.com');
INSERT INTO mvc_customer_tbl(userid, password, username, birthday, address, hp, email)
VALUES('hong123', 'h1234', '홍길동2', '2000/02/01','서울시 강남구', '010-1111-2222','hong2@gmail.com');
INSERT INTO mvc_customer_tbl(userid, password, username, birthday, address, hp, email)
VALUES('hong1234', 'h1234', '홍길동3', '2000/03/01','서울시 강서구', '010-1111-2222','hong3@gmail.com');
commit;

SELECT userid, password 
FROM mvc_customer_tbl  
WHERE userid='hong1234';

SELECT * FROM mvc_customer_tbl;