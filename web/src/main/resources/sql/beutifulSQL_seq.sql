----


--프로그램관리
CREATE TABLE PRGR_MNG
(
	PRGR_ID            INT(11) NOT NULL AUTO_INCREMENT       COMMENT '프로그램_ID',
	PRGR_NM            VARCHAR(30) NULL                      COMMENT '프로그램_이름',
	PRGR_URL           VARCHAR(50) NULL                      COMMENT '프로그램URL',
	PRGR_CN            VARCHAR(200) NULL                     COMMENT '프로그램_내용',
	PRGR_VRIABL        VARCHAR(20) NULL                      COMMENT '프로그램_변수',
	REG_ID            VARCHAR(35) NULL                       COMMENT '등록_ID',
	REG_DE            DATETIME NULL                          COMMENT '등록_일자',
	UPDT_ID              VARCHAR(35) NULL                    COMMENT '수정_ID',
	UPDT_DE              DATETIME NULL                       COMMENT '수정_일자',
	PRIMARY KEY (PRGR_ID)
)COMMENT '프로그램_관리';

         
--ALTER TABLE PRGR_MNG
--ADD PRIMARY KEY (PRGR_ID);

--첨부파일
CREATE TABLE ATCHFL
(
	ATCHFL_ID          INT(11) NOT NULL AUTO_INCREMENT     COMMENT '첨부파일_ID',
	CRET_DE             DATETIME  NULL                     COMMENT '생성_일자',
	USE_YN               CHAR(1) NULL                      COMMENT '사용_여부',
	PRIMARY KEY (ATCHFL_ID)
)COMMENT '첨부파일';

         
--ALTER TABLE ATCHFL
--ADD PRIMARY KEY (ATCHFL_ID);


--첨부파일상세
CREATE TABLE ATCHFL_DTL
(
	ATCHFL_ID          INT(11) NOT NULL AUTO_INCREMENT     COMMENT '첨부파일_ID',
	FILE_SN              INTEGER NOT NULL                  COMMENT '파일_순번',
	FILE_STRE_COURS      VARCHAR(2000) NULL                COMMENT '파일_저장_경로',
	STRE_FILE_NM         VARCHAR(20) NULL                  COMMENT '저장_파일_명',
	ORIGNL_FILE_NM       VARCHAR(20) NULL                  COMMENT '본래_파일_명',
	FILE_EXTSN           VARCHAR(20) NULL                  COMMENT '파일_확장자',
	FILE_CN              VARCHAR(4000) NULL                COMMENT '파일_내용',
	FILE_MG              INTEGER NULL                      COMMENT '파일_크기',
	PRIMARY KEY (ATCHFL_ID,FILE_SN)
)COMMENT '첨부파일_상세';


--ALTER TABLE ATCHFL_DTL
--ADD PRIMARY KEY (ATCHFL_ID,FILE_SN);

ALTER TABLE ATCHFL_DTL
ADD CONSTRAINT RR_20 FOREIGN KEY (ATCHFL_ID) REFERENCES ATCHFL (ATCHFL_ID);


--권한관리
CREATE TABLE ATHR_MNG
(
	ATHR_ID            INT(11) NOT NULL AUTO_INCREMENT     COMMENT '권한_ID',
	ATHR_CD          VARCHAR(20) NULL                      COMMENT '권한_코드',
	ATHR_NM            VARCHAR(30) NULL                    COMMENT '권한_이름',
	ATHR_CN            VARCHAR(200) NULL                   COMMENT '권한_내용',
	ATHR_CL            CHAR(1) NULL                        COMMENT '권한_분류',
	REG_ID            VARCHAR(35) NULL                     COMMENT '등록_ID',
	REG_DE            DATETIME NULL                        COMMENT '등록_일자',
	UPDT_ID              VARCHAR(35) NULL                  COMMENT '수정_ID',
	UPDT_DE              DATETIME NULL                     COMMENT '수정_일자',
	PRIMARY KEY (ATHR_ID)
)COMMENT '권한_관리';
         
--ALTER TABLE ATHR_MNG
--ADD PRIMARY KEY (ATHR_ID);

--뷰티풀CEO 유저테이블
CREATE TABLE BEUTY_USER
(
  USER_ID      INT(11)            NOT NULL    AUTO_INCREMENT            COMMENT '사용자아이디',
  USER_PW      VARCHAR(100)                 NOT NULL                    COMMENT '로그인비밀번호',
  USER_NM      VARCHAR(100)                NOT NULL                     COMMENT '사용자이름',
  USER_ENG_NM  VARCHAR(100)                                             COMMENT '사용자영문이름',
  DEL_YN    CHAR(1)                                                     COMMENT '삭제여부',
  PNO         VARCHAR(50)                                               COMMENT '전화번호',
  MBL_PNO     VARCHAR(50)                                               COMMENT '핸드폰번호',
  FAX_NO      VARCHAR(50)                                               COMMENT '팩스번호',
  ZIP_NO     VARCHAR(5)                                                 COMMENT '우편번호',
  ADDR        VARCHAR(300)                                              COMMENT '주소',
  EMAIL_ADDR  VARCHAR(100)                                              COMMENT '이메일',
  JOIN_DT     DATETIME                                                  COMMENT '등록_일자',
  MOD_ID      VARCHAR(20)                                               COMMENT '수정_ID',
  MOD_DT      DATETIME                                                  COMMENT '수정_일자',
  PRIMARY KEY (USER_ID)
)COMMENT '사용자테이블';

--ALTER TABLE BEUTY_USER
--ADD PRIMARY KEY (USER_ID);


--권한사용자매핑
CREATE TABLE ATHR_USER_MAPNG
(
	MAPNG_ID             INT(11) NOT NULL AUTO_INCREMENT    COMMENT '매핑_ID',
	ATHR_ID            INT(11) NOT NULL                     COMMENT '권한_ID',
	USER_ID              INT(11) NOT NULL                   COMMENT '사용자_ID',
	REG_ID            VARCHAR(35) NULL                      COMMENT '등록_ID',
	REG_DE            DATETIME NULL                         COMMENT '등록_일자',
	UPDT_ID              VARCHAR(35) NULL                   COMMENT '수정_ID',
	UPDT_DE              DATETIME NULL                      COMMENT '수정_일자',
	PRIMARY KEY (MAPNG_ID,ATHR_ID,USER_ID)
)COMMENT '권한_사용자_매핑';



--ALTER TABLE ATHR_USER_MAPNG
--ADD PRIMARY KEY (ATHR_ID,USER_ID,MAPNG_ID);

ALTER TABLE ATHR_USER_MAPNG
ADD CONSTRAINT RR_3 FOREIGN KEY (ATHR_ID) REFERENCES ATHR_MNG (ATHR_ID);

ALTER TABLE ATHR_USER_MAPNG
ADD CONSTRAINT RR_4 FOREIGN KEY (USER_ID) REFERENCES BEUTY_USER (USER_ID);


--접속로그
CREATE TABLE CNNT_LOG
(
	CNNT_LOG_ID        INT(11) NOT NULL AUTO_INCREMENT     COMMENT '접속_로그_ID',
	USER_ID              INT(11) NOT NULL                  COMMENT '사용자_ID',
	CNNT_IP           VARCHAR(50) NULL                     COMMENT '접속_IP',
	CNNT_DT            DATETIME NULL                       COMMENT '접속_일시',
	PRIMARY KEY (CNNT_LOG_ID, USER_ID)
)COMMENT '접속_로그';
         
--ALTER TABLE CNNT_LOG
--ADD PRIMARY KEY (USER_ID,CNNT_LOG_ID);

ALTER TABLE CNNT_LOG
ADD CONSTRAINT RR_6 FOREIGN KEY (USER_ID) REFERENCES BEUTY_USER (USER_ID);

--메뉴관리
CREATE TABLE MENU_MNG
(
	MENU_ID              INT(11)  NOT NULL AUTO_INCREMENT    COMMENT '메뉴_ID',
	PRGR_ID            INT(11)     NULL                      COMMENT '프로그램_ID',
	MENU_SE              CHAR(1) NULL                        COMMENT '메뉴_구분',
	MENU_NM              VARCHAR(30) NULL                    COMMENT '메뉴_이름',
	MENU_CN              VARCHAR(200) NULL                   COMMENT '메뉴_내용',
	UPPER_MENU_ID        INT(11) NULL                        COMMENT '상위_메뉴_ID',
	MENU_SORT            INTEGER NULL                        COMMENT '메뉴_정렬',
	EXTRL_URL            VARCHAR(50) NULL                    COMMENT '외부URL',
	USE_YN               CHAR(1) NULL                        COMMENT '사용_여부',
	MENU_LV           INTEGER NULL                           COMMENT '메뉴_레벨',
	REG_ID            VARCHAR(35) NULL                       COMMENT '등록_ID',
	REG_DE            DATETIME NULL                          COMMENT '등록_일자',
	UPDT_ID              VARCHAR(35) NULL                    COMMENT '수정_ID',
	UPDT_DE              DATETIME NULL                       COMMENT '수정_일자',
	PRIMARY KEY (MENU_ID)
)COMMENT '메뉴_관리';
         
--ALTER TABLE MENU_MNG
--ADD PRIMARY KEY (MENU_ID);

ALTER TABLE MENU_MNG
ADD CONSTRAINT RR_7 FOREIGN KEY (PRGR_ID) REFERENCES PRGR_MNG (PRGR_ID);


--메뉴권한매핑
CREATE TABLE MENU_ATHR_MAPNG
(
	MAPNG_ID             INT(11) NOT NULL AUTO_INCREMENT     COMMENT '매핑_ID',
	MENU_ID              INT(11) NOT NULL                    COMMENT '메뉴_ID',
	ATHR_ID            INT(11) NOT NULL                      COMMENT '권한_ID',
	ATHR_SE            CHAR(1) NULL                          COMMENT '권한_구분',
	REG_ID            VARCHAR(35) NULL                       COMMENT '등록_ID',
	REG_DE            DATETIME NULL                          COMMENT '등록_일자',
	UPDT_ID              VARCHAR(35) NULL                    COMMENT '수정_ID',
	UPDT_DE              DATETIME NULL                       COMMENT '수정_일자',
	PRIMARY KEY (MAPNG_ID,MENU_ID,ATHR_ID)
)COMMENT '메뉴_권한_매핑';

         
--ALTER TABLE MENU_ATHR_MAPNG
--ADD PRIMARY KEY (MENU_ID,ATHR_ID,MAPNG_ID);

ALTER TABLE MENU_ATHR_MAPNG
ADD CONSTRAINT RR_1 FOREIGN KEY (MENU_ID) REFERENCES MENU_MNG (MENU_ID);

ALTER TABLE MENU_ATHR_MAPNG
ADD CONSTRAINT RR_2 FOREIGN KEY (ATHR_ID) REFERENCES ATHR_MNG (ATHR_ID);


--메뉴통계
CREATE TABLE MENU_STATS
(
	STATS_ID           INT(11)     NOT NULL  AUTO_INCREMENT  COMMENT '통계_ID',
	MENU_ID              INT(11)  NOT NULL                   COMMENT '메뉴_ID',
	CNNT_URL           VARCHAR(50) NULL                      COMMENT '접속_URL',
	CNNT_IP            VARCHAR(50) NULL                      COMMENT '접속_IP',
	USER_ID              VARCHAR(35) NULL                    COMMENT '사용자_ID',
	VIST_DE             DATETIME NULL                        COMMENT '방문_일자',
	PRIMARY KEY (STATS_ID,MENU_ID)
)COMMENT '메뉴_통계';

         
--ALTER TABLE MENU_STATS
--ADD PRIMARY KEY (MENU_ID,STATS_ID);

ALTER TABLE MENU_STATS
ADD CONSTRAINT RR_5 FOREIGN KEY (MENU_ID) REFERENCES MENU_MNG (MENU_ID);


--
-- SN_MANAGE  (시퀀스관리) 
--
--CREATE TABLE SN_MANAGE
--(
--  TABLE_NAME  VARCHAR(20)                 NOT NULL COMMENT '테이블_이름',
--  NEXT_ID     INTEGER                              COMMENT '다음_ID'
--)COMMENT '일련번호_관리';

--ALTER TABLE SN_MANAGE
--ADD PRIMARY KEY (TABLE_NAME);

-- menu_mng
-- menu_id는 시퀀스, upper_menu_id 사용자관리메뉴 의 2, 3 해당 부모의 menu_id
INSERT INTO menu_mng (menu_se, menu_nm, menu_cn, upper_menu_id, menu_sort, prgr_id, extrl_url, use_yn, menu_lv, reg_id, reg_de, updt_id, updt_de) VALUES ('1', '사용자메뉴', '사용자메뉴들의최상위메뉴', NULL, NULL, NULL, NULL, NULL, 1, 'USR-000001', now(), NULL, NULL);
INSERT INTO menu_mng (menu_se, menu_nm, menu_cn, upper_menu_id, menu_sort, prgr_id, extrl_url, use_yn, menu_lv, reg_id, reg_de, updt_id, updt_de) VALUES ('2', '관리자메뉴', '관리자메뉴의 최상위 메뉴', NULL, NULL, NULL, NULL, NULL, 1, 'USR-000001', now(), NULL, NULL);
INSERT INTO menu_mng (menu_se, menu_nm, menu_cn, upper_menu_id, menu_sort, prgr_id, extrl_url, use_yn, menu_lv, reg_id, reg_de, updt_id, updt_de) VALUES ('2', '사용자관리', '사용자관리메뉴', 2, 1, NULL, NULL, 'Y', 2, 'USR-000001', now(), NULL, NULL);
INSERT INTO menu_mng (menu_se, menu_nm, menu_cn, upper_menu_id, menu_sort, prgr_id, extrl_url, use_yn, menu_lv, reg_id, reg_de, updt_id, updt_de) VALUES ('2', '사용자관리', '사용자관리', 3, 1, NULL, NULL, 'Y', 3, 'USR-000001', now(), NULL, NULL);
--Insert into menu_mng (menu_se, menu_nm, menu_cn, upper_menu_id, menu_sort, prgr_id, extrl_url, use_yn, menu_lv, reg_id, reg_de, updt_id, updt_de) Values ('1', '사이트맵', '사이트맵', 1, 1, NULL, NULL, 'Y', 3, 'USR-000001', now(), NULL, NULL);

--BEUTY_USER 사용자
INSERT INTO BEUTY_USER (USER_NM, USER_PW, MBL_PNO, ADDR, EMAIL_ADDR, JOIN_DT) VALUES ('김복이', 'superman1!', '010-1234-1234', '서울시 송파구 장지동 776', 'beuty1_admin@beuty_admin.com', now());
INSERT INTO BEUTY_USER (USER_NM, USER_PW, MBL_PNO, ADDR, EMAIL_ADDR, JOIN_DT) VALUES ('이복이', 'superman1!', '010-1234-1234', '서울시 송파구 장지동 776', 'beuty2_admin@beuty_admin.com', now());
INSERT INTO BEUTY_USER (USER_NM, USER_PW, MBL_PNO, ADDR, EMAIL_ADDR, JOIN_DT) VALUES ('신복이', 'superman1!', '010-1234-1234', '서울시 송파구 장지동 776', 'beuty3_admin@beuty_admin.com', now());
INSERT INTO BEUTY_USER (USER_NM, USER_PW, MBL_PNO, ADDR, EMAIL_ADDR, JOIN_DT) VALUES ('박복이', 'superman1!', '010-1234-1234', '서울시 송파구 장지동 776', 'beuty4_admin@beuty_admin.com', now());
INSERT INTO BEUTY_USER (USER_NM, USER_PW, MBL_PNO, ADDR, EMAIL_ADDR, JOIN_DT) VALUES ('기복이', 'superman1!', '010-1234-1234', '서울시 송파구 장지동 776', 'beuty5_admin@beuty_admin.com', now());
INSERT INTO BEUTY_USER (USER_NM, USER_PW, MBL_PNO, ADDR, EMAIL_ADDR, JOIN_DT) VALUES ('나복이', 'superman1!', '010-1234-1234', '서울시 송파구 장지동 776', 'beuty6_admin@beuty_admin.com', now());
INSERT INTO BEUTY_USER (USER_NM, USER_PW, MBL_PNO, ADDR, EMAIL_ADDR, JOIN_DT) VALUES ('홍복이', 'superman1!', '010-1234-1234', '서울시 송파구 장지동 776', 'beuty7_admin@beuty_admin.com', now());
INSERT INTO BEUTY_USER (USER_NM, USER_PW, MBL_PNO, ADDR, EMAIL_ADDR, JOIN_DT) VALUES ('호복이', 'superman1!', '010-1234-1234', '서울시 송파구 장지동 776', 'beuty8_admin@beuty_admin.com', now());
INSERT INTO BEUTY_USER (USER_NM, USER_PW, MBL_PNO, ADDR, EMAIL_ADDR, JOIN_DT) VALUES ('구복이', 'superman1!', '010-1234-1234', '서울시 송파구 장지동 776', 'beuty9_admin@beuty_admin.com', now());
INSERT INTO BEUTY_USER (USER_NM, USER_PW, MBL_PNO, ADDR, EMAIL_ADDR, JOIN_DT) VALUES ('자복이', 'superman1!', '010-1234-1234', '서울시 송파구 장지동 776', 'beuty10_admin@beuty_admin.com', now());
INSERT INTO BEUTY_USER (USER_NM, USER_PW, MBL_PNO, ADDR, EMAIL_ADDR, JOIN_DT) VALUES ('장복이', 'superman1!', '010-1234-1234', '서울시 송파구 장지동 776', 'beuty11_admin@beuty_admin.com', now());
INSERT INTO BEUTY_USER (USER_NM, USER_PW, MBL_PNO, ADDR, EMAIL_ADDR, JOIN_DT) VALUES ('종복이', 'superman1!', '010-1234-1234', '서울시 송파구 장지동 776', 'beuty12_admin@beuty_admin.com', now());

-- prgr_mng 프로그램 관리
INSERT INTO prgr_mng (PRGR_NM, PRGR_URL, PRGR_CN, PRGR_VRIABL, REG_ID, REG_DE) VALUES ('사용자관리', '/usermgr/z/m/selectUserList.do', '사용자 등록 수정 삭제', 'usermgr','beuty1_admin@beuty_admin.com', now());
INSERT INTO prgr_mng (PRGR_NM, PRGR_URL, PRGR_CN, PRGR_VRIABL, REG_ID, REG_DE) VALUES ('프로그램관리', '/program/z/m/selectProgramList.do', '프로그램 등록/수정/삭제, 메뉴의 어머니', 'program','beuty1_admin@beuty_admin.com', now());







Insert into ATHR_MNG
   (ATHR_CD, ATHR_NM, ATHR_CN, ATHR_CL, 
    REG_ID, REG_DE, UPDT_ID, UPDT_DE)
 Values
   ('authenticated', '인증된 사용자권한', '가입되어 승인된 일반권한', 'U', 
    'USR-000001', now(), NULL, NULL);
    
Insert into ATHR_MNG
   (ATHR_ID, ATHR_CD, ATHR_NM, ATHR_CN, ATHR_CL, 
    REG_ID, REG_DE, UPDT_ID, UPDT_DE)
 Values
   ('ROL-000002', 'USR-000001', '관리자권한', '관리자권한', 'A', 
    'USR-000001', now(), NULL, NULL);

 COMMIT;



INSERT INTO ATHR_USER_MAPNG 
(MAPNG_ID, ATHR_ID, USER_ID, REG_ID, REG_DE)
VALUES
('RUM-000000', 'ROL-000002', 'USR-000001', 'USR-000001', now());
          

--삭제시
drop table sn_manage;
drop table menu_stats;
drop table menu_athr_mapng;
drop table cnnt_log;
drop table athr_user_mapng;
drop table athr_mng;
drop table atchfl_dtl;
drop table menu_mng;
drop table prgr_mng;
drop table beuty_user;
drop table atchfl;

