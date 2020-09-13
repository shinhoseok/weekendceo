DROP TABLE SEQ_MNG;

DROP TABLE MENU_STATS;

DROP TABLE MENU_ROLE_MAPNG;

DROP TABLE MENU_MNG;

DROP TABLE CNNT_LOG;

DROP TABLE ROLE_USER_MAPNG;

DROP TABLE USERS;

DROP TABLE ROLE_MNG;

DROP TABLE ATCHFL_DTL;

DROP TABLE ATCHFL;

DROP TABLE prgr_mng;

CREATE TABLE PRGR_MNG
(
	PRGR_ID            VARCHAR(20) NOT NULL COMMENT '프로그램_ID',
	PRGR_NM            VARCHAR(30) NULL     COMMENT '프로그램_이름',
	PRGR_URL           VARCHAR(50) NULL     COMMENT '프로그램URL',
	PRGR_DES            VARCHAR(200) NULL    COMMENT '프로그램_내용',
	PRGR_VRIABL        VARCHAR(20) NULL     COMMENT '프로그램_변수',
	REG_ID            VARCHAR(35) NULL      COMMENT '등록_ID',
	REG_DT            DATETIME NULL         COMMENT '등록_일자',
	MOD_ID              VARCHAR(35) NULL   COMMENT '수정_ID',
	MOD_DT              DATETIME NULL      COMMENT '수정_일자'
)COMMENT '프로그램_관리';

ALTER TABLE PRGR_MNG COLLATE='utf8mb4_general_ci', CONVERT TO CHARSET UTF8MB4;
         
ALTER TABLE PRGR_MNG
ADD PRIMARY KEY (PRGR_ID);




-- 첨부파일

CREATE TABLE ATCHFL
(
	ATCHFL_ID          VARCHAR(20) NOT NULL COMMENT '첨부파일_ID',
	CRET_DE             DATETIME  NULL      COMMENT '생성_일자',
	USE_YN               CHAR(1) NULL       COMMENT '사용_여부'
)COMMENT '첨부파일';

ALTER TABLE ATCHFL COLLATE='utf8mb4_general_ci', CONVERT TO CHARSET UTF8MB4;
         
ALTER TABLE ATCHFL
ADD PRIMARY KEY (ATCHFL_ID);



-- 첨부파일상세

CREATE TABLE ATCHFL_DTL
(
	ATCHFL_ID          VARCHAR(20) NOT NULL COMMENT '첨부파일_ID',
	FILE_SN              INTEGER NOT NULL   COMMENT '파일_순번',
	FILE_STRE_COURS      VARCHAR(2000) NULL COMMENT '파일_저장_경로',
	STRE_FILE_NM         VARCHAR(20) NULL   COMMENT '저장_파일_명',
	ORIGNL_FILE_NM       VARCHAR(20) NULL   COMMENT '본래_파일_명',
	FILE_EXTSN           VARCHAR(20) NULL   COMMENT '파일_확장자',
	FILE_DES              VARCHAR(4000) NULL COMMENT '파일_내용',
	FILE_MG              INTEGER NULL       COMMENT '파일_크기'
)COMMENT '첨부파일_상세';

ALTER TABLE ATCHFL_DTL COLLATE='utf8mb4_general_ci', CONVERT TO CHARSET UTF8MB4;

ALTER TABLE ATCHFL_DTL
ADD PRIMARY KEY (ATCHFL_ID,FILE_SN);

ALTER TABLE ATCHFL_DTL
ADD CONSTRAINT R_20 FOREIGN KEY (ATCHFL_ID) REFERENCES ATCHFL (ATCHFL_ID);



-- 권한관리

CREATE TABLE ROLE_MNG
(
	ROLE_ID            VARCHAR(20) NOT NULL COMMENT '권한_ID',
	ROLE_CD          VARCHAR(20) NULL       COMMENT '권한_코드',
	ROLE_NM            VARCHAR(30) NULL     COMMENT '권한_이름',
	ROLE_DES            VARCHAR(200) NULL    COMMENT '권한_내용',
	ROLE_CL            CHAR(1) NULL         COMMENT '권한_분류',
	REG_ID            VARCHAR(35) NULL      COMMENT '등록_ID',
	REG_DT            DATETIME NULL         COMMENT '등록_일자',
	MOD_ID              VARCHAR(35) NULL   COMMENT '수정_ID',
	MOD_DT              DATETIME NULL      COMMENT '수정_일자'
)COMMENT '권한_관리';
         
ALTER TABLE ROLE_MNG COLLATE='utf8mb4_general_ci', CONVERT TO CHARSET UTF8MB4;

ALTER TABLE ROLE_MNG
ADD PRIMARY KEY (ROLE_ID);



-- 유저테이블

CREATE TABLE USERS
(
  USER_ID      VARCHAR(20)                 NOT NULL       COMMENT '사용자아이디',
  USER_PW      VARCHAR(100)                 NOT NULL      COMMENT '로그인비밀번호',
  USER_NM      VARCHAR(100)                NOT NULL       COMMENT '사용자이름',
  DEL_YN	CHAR(1)					 COMMENT '삭제여부',
  PNO         VARCHAR(50)                                COMMENT '전화번호',
  MBL_PNO     VARCHAR(50)                                COMMENT '핸드폰번호',
  ZIP_NO     VARCHAR(5)                                 COMMENT '우편번호',
  ADDR        VARCHAR(300)                               COMMENT '주소',
  EMAIL_ADDR  VARCHAR(100)                               COMMENT '이메일',
  REG_ID            VARCHAR(35) NULL        COMMENT '등록_ID',
  REG_DT            DATETIME NULL           COMMENT '등록_일자',
  MOD_ID      VARCHAR(20)                                COMMENT '수정_ID',
  MOD_DT      DATETIME                                   COMMENT '수정_일자'
)COMMENT '사용자테이블';

ALTER TABLE USERS COLLATE='utf8mb4_general_ci', CONVERT TO CHARSET UTF8MB4;

ALTER TABLE USERS
ADD PRIMARY KEY (USER_ID);


-- 권한사용자매핑

CREATE TABLE ROLE_USER_MAPNG
(
	ROLE_ID            VARCHAR(20) NOT NULL   COMMENT '권한_ID',
	MAPNG_ID             VARCHAR(20) NOT NULL COMMENT '매핑_ID',
	USER_ID              VARCHAR(35) NOT NULL COMMENT '사용자_ID',
	REG_ID            VARCHAR(35) NULL        COMMENT '등록_ID',
	REG_DT            DATETIME NULL           COMMENT '등록_일자',
	MOD_ID              VARCHAR(35) NULL     COMMENT '수정_ID',
	MOD_DT              DATETIME NULL        COMMENT '수정_일자'
)COMMENT '권한_사용자_매핑';

ALTER TABLE ROLE_USER_MAPNG COLLATE='utf8mb4_general_ci', CONVERT TO CHARSET UTF8MB4;

ALTER TABLE ROLE_USER_MAPNG
ADD PRIMARY KEY (MAPNG_ID);

ALTER TABLE ROLE_USER_MAPNG
ADD CONSTRAINT R_3 FOREIGN KEY (ROLE_ID) REFERENCES ROLE_MNG (ROLE_ID);

ALTER TABLE ROLE_USER_MAPNG
ADD CONSTRAINT R_4 FOREIGN KEY (USER_ID) REFERENCES USERS (USER_ID);




-- 접속로그

CREATE TABLE CNNT_LOG
(
	CNNT_LOG_ID        VARCHAR(20) NOT NULL   COMMENT '접속_로그_ID',
	USER_ID              VARCHAR(35) NOT NULL COMMENT '사용자_ID',
	CNNT_IP           VARCHAR(50) NULL       COMMENT '접속_IP',
	CNNT_DT            DATETIME NULL          COMMENT '접속_일시'
)COMMENT '접속_로그';

ALTER TABLE CNNT_LOG COLLATE='utf8mb4_general_ci', CONVERT TO CHARSET UTF8MB4;
         
ALTER TABLE CNNT_LOG
ADD PRIMARY KEY (USER_ID,CNNT_LOG_ID);

ALTER TABLE CNNT_LOG
ADD CONSTRAINT R_6 FOREIGN KEY (USER_ID) REFERENCES USERS (USER_ID);


-- 메뉴관리

CREATE TABLE MENU_MNG
(
	MENU_ID              VARCHAR(20) NOT NULL COMMENT '메뉴_ID',
	PRGR_ID            VARCHAR(20) NULL       COMMENT '프로그램_ID',
	MENU_SE              CHAR(1) NULL         COMMENT '메뉴_구분',
	MENU_NM              VARCHAR(30) NULL     COMMENT '메뉴_이름',
	MENU_DES              VARCHAR(200) NULL    COMMENT '메뉴_내용',
	UPPER_MENU_ID        VARCHAR(20) NULL     COMMENT '상위_메뉴_ID',
	MENU_SORT            INTEGER NULL         COMMENT '메뉴_정렬',
	EXTRL_URL            VARCHAR(50) NULL     COMMENT '외부URL',
	USE_YN               CHAR(1) NULL         COMMENT '사용_여부',
	MENU_LV           INTEGER NULL            COMMENT '메뉴_레벨',
	REG_ID            VARCHAR(35) NULL        COMMENT '등록_ID',
	REG_DT            DATETIME NULL           COMMENT '등록_일자',
	MOD_ID              VARCHAR(35) NULL     COMMENT '수정_ID',
	MOD_DT              DATETIME NULL        COMMENT '수정_일자'
)COMMENT '메뉴_관리';

ALTER TABLE MENU_MNG COLLATE='utf8mb4_general_ci', CONVERT TO CHARSET UTF8MB4;
         
ALTER TABLE MENU_MNG
ADD PRIMARY KEY (MENU_ID);

ALTER TABLE MENU_MNG
ADD CONSTRAINT R_7 FOREIGN KEY (PRGR_ID) REFERENCES PRGR_MNG (PRGR_ID);




-- 메뉴권한매핑

CREATE TABLE MENU_ROLE_MAPNG
(
	MENU_ID              VARCHAR(20) NOT NULL COMMENT '메뉴_ID',
	ROLE_ID            VARCHAR(20) NOT NULL   COMMENT '권한_ID',
	MAPNG_ID             VARCHAR(20) NOT NULL COMMENT '매핑_ID',
	ROLE_SE            CHAR(1) NULL           COMMENT '권한_구분',
	REG_ID            VARCHAR(35) NULL        COMMENT '등록_ID',
	REG_DT            DATETIME NULL           COMMENT '등록_일자',
	MOD_ID              VARCHAR(35) NULL     COMMENT '수정_ID',
	MOD_DT              DATETIME NULL        COMMENT '수정_일자'
)COMMENT '메뉴_권한_매핑';

ALTER TABLE MENU_ROLE_MAPNG COLLATE='utf8mb4_general_ci', CONVERT TO CHARSET UTF8MB4;

ALTER TABLE MENU_ROLE_MAPNG
ADD PRIMARY KEY (MAPNG_ID);

ALTER TABLE MENU_ROLE_MAPNG
ADD CONSTRAINT R_1 FOREIGN KEY (MENU_ID) REFERENCES MENU_MNG (MENU_ID);

ALTER TABLE MENU_ROLE_MAPNG
ADD CONSTRAINT R_2 FOREIGN KEY (ROLE_ID) REFERENCES ROLE_MNG (ROLE_ID);



-- 메뉴통계

CREATE TABLE MENU_STATS
(
	MENU_ID              VARCHAR(20) NOT NULL COMMENT '메뉴_ID',
	STATS_ID             VARCHAR(50) NOT NULL COMMENT '통계_ID',
	CNNT_URL           VARCHAR(50) NULL       COMMENT '접속_URL',
	CNNT_IP            VARCHAR(50) NULL       COMMENT '접속_IP',
	USER_ID              VARCHAR(35) NULL     COMMENT '사용자_ID',
	VIST_DE             DATETIME NULL         COMMENT '방문_일자'
)COMMENT '메뉴_통계';

ALTER TABLE MENU_STATS COLLATE='utf8mb4_general_ci', CONVERT TO CHARSET UTF8MB4;
         
ALTER TABLE MENU_STATS
ADD PRIMARY KEY (MENU_ID,STATS_ID);

ALTER TABLE MENU_STATS
ADD CONSTRAINT R_5 FOREIGN KEY (MENU_ID) REFERENCES MENU_MNG (MENU_ID);



--
-- SEQ_MNG  (시퀀스관리) 
--

CREATE TABLE SEQ_MNG
(
  TABLE_NAME  VARCHAR(20)                 NOT NULL COMMENT '테이블_이름',
  NEXT_ID     INTEGER                              COMMENT '다음_ID'
)COMMENT '일련번호_관리';

ALTER TABLE SEQ_MNG COLLATE='utf8mb4_general_ci', CONVERT TO CHARSET UTF8MB4;

ALTER TABLE SEQ_MNG
ADD PRIMARY KEY (TABLE_NAME);

-- 큐브리드참고 사용자 첫번째메뉴였음













INSERT INTO menu_mng (menu_id, menu_se, menu_nm, menu_des, upper_menu_id, menu_sort, prgr_id, extrl_url, use_yn, menu_lv, reg_id, REG_DT, MOD_ID, MOD_DT) VALUES ('MENU-00001', '1', '사용자메뉴', '사용자메뉴들의최상위메뉴', NULL, NULL, NULL, NULL, NULL, 1, 'USR-000001', now(), NULL, NULL);
INSERT INTO menu_mng (menu_id, menu_se, menu_nm, menu_des, upper_menu_id, menu_sort, prgr_id, extrl_url, use_yn, menu_lv, reg_id, REG_DT, MOD_ID, MOD_DT) VALUES ('MENU-00002', '2', '관리자메뉴', '관리자메뉴의 최상위 메뉴', NULL, NULL, NULL, NULL, NULL, 1, 'USR-000001', now(), NULL, NULL);
INSERT INTO menu_mng (menu_id, menu_se, menu_nm, menu_des, upper_menu_id, menu_sort, prgr_id, extrl_url, use_yn, menu_lv, reg_id, REG_DT, MOD_ID, MOD_DT) VALUES ('MENU-00003', '2', '사용자관리', '사용자관리메뉴', 'MENU-00002', 1, NULL, NULL, 'Y', 2, 'USR-000001', now(), NULL, NULL);
INSERT INTO menu_mng (menu_id, menu_se, menu_nm, menu_des, upper_menu_id, menu_sort, prgr_id, extrl_url, use_yn, menu_lv, reg_id, REG_DT, MOD_ID, MOD_DT) VALUES ('MENU-00004', '2', '사용자관리', '사용자관리', 'MENU-00003', 1, NULL, NULL, 'Y', 3, 'USR-000001', now(), NULL, now());

commit;


Insert into ROLE_MNG
   (ROLE_ID, ROLE_CD, ROLE_NM, ROLE_DES, ROLE_CL, 
    REG_ID, REG_DT, MOD_ID, MOD_DT)
 Values
   ('ROL-000001', 'authenticated', '인증된 사용자권한', '가입되어 승인된 일반권한', 'U', 
    'USR-000001', now(), NULL, NULL);
    
Insert into ROLE_MNG
   (ROLE_ID, ROLE_CD, ROLE_NM, ROLE_DES, ROLE_CL, 
    REG_ID, REG_DT, MOD_ID, MOD_DT)
 Values
   ('ROL-000002', 'admin', '관리자권한', '관리자권한', 'A', 
    'USR-000001', now(), NULL, NULL);
    
Insert into ROLE_MNG
   (ROLE_ID, ROLE_CD, ROLE_NM, ROLE_DES, ROLE_CL, 
    REG_ID, REG_DT, MOD_ID, MOD_DT)
 Values
   ('ROL-000003', 'unauthenticated', '인증되지 않은 사용자', '불량회원', 'X', 
    'USR-000001', now(), NULL, NULL);

 COMMIT;



INSERT INTO USERS ( USER_ID, USER_NM, USER_PW, MBL_PNO, ADDR, EMAIL_ADDR, DEL_YN, REG_ID, REG_DT)
VALUES ( 'USR-000001', '신호석', 'tlsghtjr1!', '010-1234-1234', '서울시 송파구 장지동 776', 'myojae1112@gmail.com', 'N', 'USR-000001', now());

COMMIT;

INSERT INTO ROLE_USER_MAPNG 
(MAPNG_ID, ROLE_ID, USER_ID, REG_ID, REG_DT)
VALUES
('RUM-000000', 'ROL-000002', 'USR-000001', 'USR-000001', now());

COMMIT;