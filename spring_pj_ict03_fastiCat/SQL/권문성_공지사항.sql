--============ 공지사항 ====================
-- 권문성 -------------------------------------------------------------------------------
-- 게시판  테이블
DROP TABLE mvc_Notice_TBL  CASCADE CONSTRAINTS;
CREATE TABLE mvc_Notice_TBL(  
  N_Board_Num   NUMBER(7)  PRIMARY KEY,      -- 글번호
   N_Title       VARCHAR2(50)  NOT NULL,      -- 글제목
   N_Content     CLOB  NOT NULL,              -- 글내용
   N_Writer      VARCHAR2(30)  NOT NULL,       -- 작성자
   N_readCnt     NUMBER(6)   DEFAULT 0,      -- 조회수
   N_WriteDate     DATE  DEFAULT sysdate,       -- 작성일
   show         VARCHAR2(10) DEFAULT 'y'
);

--공지사항 입력
INSERT INTO mvc_Notice_TBL(N_Board_Num, N_Title, N_Content, N_Writer, N_WriteDate)
 VALUES((SELECT NVL(MAX(N_Board_Num)+1,1) FROM mvc_Notice_TBL), '서버점검안내' , '2024년 07월 15일 저녁시간 22시~24시까지 서버 점검이 이루어질 예정입니다', '매니저23', sysdate);
--공지사항 상세페이지
SELECT * FROM mvc_Notice_TBL
 WHERE N_Board_Num=1;
--공지사항 수정
UPDATE mvc_Notice_TBL
   SET N_Title = '예매 관련하여 공지 올려드립니다.'
      ,N_Content ='저희는 예매일정을 알려드리는 사이트입니다. 해당 공연의 예매는 예매사이트로 접속하셔서 예매하셔야합니다.'
  WHERE N_Board_Num= 2;
--공지사항 삭제 
UPDATE mvc_Notice_TBL
   SET show ='n'
 WHERE N_Board_Num = 3;
 
--공지사항 목록조회
SELECT * 
  FROM(
      SELECT A.*,
             N_Board_Num AS rn 
        FROM 
             (SELECT *
                 FROM mvc_Notice_TBL
                WHERE show = 'y'
               ORDER BY N_Board_Num DESC
                ) A    
      ) 
 WHERE rn BETWEEN 1 AND 10;
 
 --공지사항 조회수 증가
 UPDATE mvc_Notice_TBL
    SET N_readCnt = N_readCnt+1
  WHERE N_Board_Num= 2;  
  
 --공지사항 갯수 구하기
 SELECT COUNT(*) AS cnt
 FROM mvc_Notice_TBL;