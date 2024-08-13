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


-- show_tbl 시퀀스 생성
DROP SEQUENCE SHOW_TBL_SEQ;
CREATE SEQUENCE SHOW_TBL_SEQ
START WITH 1
INCREMENT BY 1
NOCACHE;

-- show_tbl 트리거 생성 
DROP TRIGGER show_tbl_bi;
CREATE OR REPLACE TRIGGER show_tbl_bi
BEFORE INSERT ON show_tbl
FOR EACH ROW
BEGIN
    IF :NEW.showNum IS NULL THEN
        :NEW.showNum := SHOW_TBL_SEQ.NEXTVAL;
    END IF;
END;
/
COMMIT;


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



-- show_tbl_fes 시퀀스 생성
DROP SEQUENCE SHOW_TBL_FES_SEQ;
CREATE SEQUENCE SHOW_TBL_FES_SEQ
START WITH 1
INCREMENT BY 1
NOCACHE;

-- show_tbl_fes 트리거 생성
DROP TRIGGER show_tbl_fes_bi;
CREATE OR REPLACE TRIGGER show_tbl_fes_bi
BEFORE INSERT ON show_tbl_fes
FOR EACH ROW
BEGIN
    IF :NEW.showNum IS NULL THEN
        :NEW.showNum := SHOW_TBL_FES_SEQ.NEXTVAL;
    END IF;
END;
/
COMMIT;

--============ show_tbl 더미데이터 (예매 캘린더 & 국내공연 공통) ================
DECLARE
    start_date DATE := TO_DATE('2024-07-01', 'YYYY-MM-DD'); 
    end_date DATE := TO_DATE('2024-09-30', 'YYYY-MM-DD');   
    current_day DATE := start_date;
    show_names SYS.ODCIVARCHAR2LIST := SYS.ODCIVARCHAR2LIST(
        '나상현씨밴드 콘서트', '엔믹스콘서트', '볼빨간사춘기콘서트', 
        '아이유콘서트', '데이식스콘서트', '송가인콘서트', '오마이걸콘서트',
        '세븐틴콘서트', '잔나비콘서트'
    );
    show_places SYS.ODCIVARCHAR2LIST := SYS.ODCIVARCHAR2LIST(
        '잠실실내체육관', '올림픽공원', '체조경기장', '코엑스'
    );
    show_benes SYS.ODCIVARCHAR2LIST := SYS.ODCIVARCHAR2LIST(
        '무이자할부', '조조할인', '회원할인'
    );
    show_categories SYS.ODCIVARCHAR2LIST := SYS.ODCIVARCHAR2LIST(
        '콘서트', '트로트', '인디'
    );
    selected_show_name VARCHAR2(150);
    selected_show_image VARCHAR2(150);
    
    row_count NUMBER := 0;  -- 총 삽입된 레코드 수 추적

BEGIN
    WHILE current_day <= end_date LOOP
        IF row_count < 150 THEN  -- 최대 150개의 레코드만 삽입
            FOR i IN 1..TRUNC(DBMS_RANDOM.VALUE(1, 4)) LOOP
                selected_show_name := show_names(TRUNC(DBMS_RANDOM.VALUE(1, show_names.COUNT + 1)));
                
                selected_show_image := CASE 
                    WHEN selected_show_name = '나상현씨밴드 콘서트' THEN '/ict03_fastiCat/resources/images/contents/나상현씨밴드.jfif'
                    WHEN selected_show_name = '엔믹스콘서트' THEN '/ict03_fastiCat/resources/upload/엔믹스.jpg'
                    WHEN selected_show_name = '볼빨간사춘기콘서트 ' THEN '/ict03_fastiCat/resources/images/contents/볼빨간사춘기_l.jpg'
                    WHEN selected_show_name = '아이유콘서트' THEN '/ict03_fastiCat/resources/images/contents/아이유포스터.jpg'
                    WHEN selected_show_name = '데이식스콘서트' THEN '/ict03_fastiCat/resources/images/contents/데이식스.jpg'
                    WHEN selected_show_name = '송가인콘서트' THEN '/ict03_fastiCat/resources/images/contents/송가인.jpg'
                    WHEN selected_show_name = '오마이걸콘서트' THEN '/ict03_fastiCat/resources/images/contents/오마이걸.jpg'
                    WHEN selected_show_name = '세븐틴콘서트' THEN '/ict03_fastiCat/resources/images/contents/세븐틴.jpg'
                    WHEN selected_show_name = '잔나비콘서트' THEN '/ict03_fastiCat/resources/images/contents/잔나비.webp'
                    ELSE '/ict03_fastiCat/resources/upload/정아로.gif'
                END;

                INSERT INTO show_tbl(
                    showNum, showName, showPlace, showPrice, curCapacity, maxCapacity, showDay, show, showBene, showAge, showTime, showImage, showCategory
                )
                VALUES(
                    show_tbl_seq.NEXTVAL,
                    selected_show_name,
                    show_places(TRUNC(DBMS_RANDOM.VALUE(1, show_places.COUNT + 1))),
                    200000 + TRUNC(DBMS_RANDOM.VALUE(1, 6)) * 10000,  
                    CASE
                        WHEN EXTRACT(MONTH FROM current_day) = 7 THEN 150 
                        WHEN EXTRACT(MONTH FROM current_day) = 8 THEN 0   
                        WHEN EXTRACT(MONTH FROM current_day) = 9 THEN 350 
                    END,
                    35000,
                    current_day,  -- 현재 날짜로 showDay 설정
                    'y',
                    show_benes(TRUNC(DBMS_RANDOM.VALUE(1, show_benes.COUNT + 1))),
                    전체관람가,             
                    120,
                    selected_show_image,  
                    show_categories(TRUNC(DBMS_RANDOM.VALUE(1, show_categories.COUNT + 1)))
                );

                row_count := row_count + 1;

                EXIT WHEN row_count >= 150;
            END LOOP;
        END IF;

        current_day := current_day + 1;
    END LOOP;
END;
/
COMMIT;

SELECT * FROM show_tbl
ORDER BY showNum;
