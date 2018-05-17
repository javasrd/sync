/*==============================================================*/
/* 更改所有表VARCHAR2默认单位为 'CHAR'                            */
/*==============================================================*/
ALTER SESSION SET NLS_LENGTH_SEMANTICS='CHAR';

/*==============================================================*/
/* TABLE: PIVAS_TASK任务表                             */
/*==============================================================*/
CREATE TABLE PIVAS_TASK
(
  TASK_ID         		NUMBER(20, 0) NOT NULL,
  TASK_NAME           	VARCHAR2(32)  NOT NULL,
  TASK_TYPE       		INTEGER NOT NULL,
  TASK_EXECUTE_MODE   	INTEGER,
  TASK_TASKPRIORITY     INTEGER,
  TASK_INTEVAL      	INTEGER,
  TASK_RETRYTIMES     	INTEGER,
  TASK_RETRYINTEVAL   	INTEGER,
  TASK_EXECUTE_TIME     DATE  NOT NULL,
  TASK_CREATE_TIME      DATE,
  TASK_UPDATE_TIME      DATE,
  TASK_CONTENT_TYPE     INTEGER NOT NULL,
  TASK_STATUS           INTEGER NOT NULL,
  TASK_REMARK           VARCHAR2(256),
  TASK_BEANCLASS      	VARCHAR2(256),
  RESERVE1          	VARCHAR2(32), 
  RESERVE2          	VARCHAR2(32), 
  RESERVE3          	VARCHAR2(32),
  CONSTRAINT PIVAS_TASK_PK PRIMARY KEY(TASK_ID) USING INDEX 
);

COMMENT ON TABLE PIVAS_TASK IS '任务表';

COMMENT ON COLUMN PIVAS_TASK.TASK_ID IS '主键标识，任务创建成功后由数据中间件返回写入';

COMMENT ON COLUMN PIVAS_TASK.TASK_NAME IS '任务名称，必须唯一';

COMMENT ON COLUMN PIVAS_TASK.TASK_TYPE IS '任务类型，0,定时任务 1,一次性任务 ';

COMMENT ON COLUMN PIVAS_TASK.TASK_EXECUTE_MODE IS '任务执行模式，仅选择定时任务时有效, 0,分钟 1,天';

COMMENT ON COLUMN PIVAS_TASK.TASK_TASKPRIORITY IS '任务优先级';

COMMENT ON COLUMN PIVAS_TASK.TASK_INTEVAL IS '任务执行间隔  仅选择定时任务时有效';

COMMENT ON COLUMN PIVAS_TASK.TASK_RETRYTIMES IS '重试次数';

COMMENT ON COLUMN PIVAS_TASK.TASK_RETRYINTEVAL IS '重试时间间隔';

COMMENT ON COLUMN PIVAS_TASK.TASK_EXECUTE_TIME IS '任务执行时间  YYYY-MM-DD HH:MM:SS';

COMMENT ON COLUMN PIVAS_TASK.TASK_CREATE_TIME IS '任务创建时间  YYYY-MM-DD HH:MM:SS';

COMMENT ON COLUMN PIVAS_TASK.TASK_UPDATE_TIME IS '任务更新时间  YYYY-MM-DD HH:MM:SS';

COMMENT ON COLUMN PIVAS_TASK.TASK_CONTENT_TYPE IS '任务执行内容类型  0病人、1医嘱、2药品字典、3病区信息、4医嘱频次、5用药途径、6药单执行记录、7员工';

COMMENT ON COLUMN PIVAS_TASK.TASK_STATUS IS '任务状态  0,激活  1,去激活';

COMMENT ON COLUMN PIVAS_TASK.TASK_REMARK IS '任务备注信息';

COMMENT ON COLUMN PIVAS_TASK.TASK_BEANCLASS IS '任务执行类';

COMMENT ON COLUMN PIVAS_TASK.RESERVE1 IS '备用字段1';

COMMENT ON COLUMN PIVAS_TASK.RESERVE2 IS '备用字段2';

COMMENT ON COLUMN PIVAS_TASK.RESERVE3 IS '备用字段3';


/*==============================================================*/
/* TABLE: PIVAS_TASK_RESULT任务执行结果表                            */
/*==============================================================*/
CREATE TABLE PIVAS_TASK_RESULT
(
  TASK_ID       		NUMBER(20, 0) NOT NULL,
  TASK_NAME         	VARCHAR2(32)  NOT NULL,
  TASK_TYPE     		INTEGER NOT NULL,
  TASK_RESULT       	INTEGER  NOT NULL,
  TASK_EXESTART_TIME   	DATE  NOT NULL, 
  TASK_EXESTOP_TIME   	DATE  NOT NULL, 
  TASK_CONTENT_TYPE   	INTEGER NOT NULL,
  RESERVE1        		VARCHAR2(32), 
  RESERVE2        		VARCHAR2(32), 
  RESERVE3        		VARCHAR2(32)
);

COMMENT ON TABLE PIVAS_TASK_RESULT IS '任务表';

COMMENT ON COLUMN PIVAS_TASK_RESULT.TASK_ID IS '任务唯一标识';

COMMENT ON COLUMN PIVAS_TASK_RESULT.TASK_NAME IS '任务名称';

COMMENT ON COLUMN PIVAS_TASK_RESULT.TASK_TYPE IS '任务类型，0,定时任务 1,一次性任务 ';

COMMENT ON COLUMN PIVAS_TASK_RESULT.TASK_RESULT IS '任务执行结果  0成功  1失败 ';

COMMENT ON COLUMN PIVAS_TASK_RESULT.TASK_EXESTART_TIME IS '任务开始执行时间';

COMMENT ON COLUMN PIVAS_TASK_RESULT.TASK_EXESTOP_TIME IS '任务执行结束时间';

COMMENT ON COLUMN PIVAS_TASK_RESULT.TASK_CONTENT_TYPE IS '任务执行内容类型 0,病人、1,医嘱、2,病区、3,用药方式、4,医嘱频次、5,药品';

COMMENT ON COLUMN PIVAS_TASK_RESULT.RESERVE1 IS '备用字段1';

COMMENT ON COLUMN PIVAS_TASK_RESULT.RESERVE2 IS '备用字段2';

COMMENT ON COLUMN PIVAS_TASK_RESULT.RESERVE3 IS '备用字段3';


-- 病区表
create table PIVAS_INPATIENTAREA
(
  gid      NUMBER(20, 0) not null,
  deptcode VARCHAR2(24) not null,
  deptname VARCHAR2(60) not null,
  reserve0 VARCHAR2(100),
  reserve1 VARCHAR2(100),
  reserve2 VARCHAR2(100)
) TABLESPACE PIVAS_TABLESPACE;
-- Add comments to the table 
comment on table PIVAS_INPATIENTAREA
  is '病区';
-- Add comments to the columns 
comment on column PIVAS_INPATIENTAREA.gid
  is '主键，自增长';
comment on column PIVAS_INPATIENTAREA.deptcode
  is '病区编码';
comment on column PIVAS_INPATIENTAREA.deptname
  is '病区名称';
comment on column PIVAS_INPATIENTAREA.reserve0
  is '预留字段0';
comment on column PIVAS_INPATIENTAREA.reserve1
  is '预留字段1';
comment on column PIVAS_INPATIENTAREA.reserve2
  is '预留字段2';
-- Create/Recreate primary, unique and foreign key constraints 
alter table PIVAS_INPATIENTAREA
  add constraint PIVAS_INPATIENTAREA_GID primary key (GID);
alter table PIVAS_INPATIENTAREA
  add constraint PIVAS_INPATIENTAREA_DEPTCODE unique (DEPTCODE);
  
alter table PIVAS_INPATIENTAREA add action number not null;
-- Add comments to the columns 
comment on column PIVAS_INPATIENTAREA.action
  is '0(新增)、1(变更)';


-- 病人表
create table PIVAS_PATIENT
(
  gid      NUMBER(20, 0) not null,
  inhospno VARCHAR2(24) not null,
  patname  VARCHAR2(60) not null,
  sex      INTEGER not null,
  wardcode VARCHAR2(24),
  state    VARCHAR2(60),
  bedno    VARCHAR2(24),
  reserve0 VARCHAR2(100),
  reserve1 VARCHAR2(100),
  reserve2 VARCHAR2(100),
  case_id  VARCHAR2(24),
  birthday DATE,
  age      VARCHAR2(24),
  ageunit  INTEGER,
  avdp     VARCHAR2(24)
) TABLESPACE PIVAS_TABLESPACE;
-- Add comments to the table
 
comment on table PIVAS_PATIENT
  is '病人表';
-- Add comments to the columns 
comment on column PIVAS_PATIENT.gid
  is '主键，自增长';
comment on column PIVAS_PATIENT.inhospno
  is '住院流水号，病人唯一标识';
comment on column PIVAS_PATIENT.patname
  is '患者姓名';
comment on column PIVAS_PATIENT.sex
  is '性别：0女，1男，默认0';
comment on column PIVAS_PATIENT.wardcode
  is '病人当前病区(科室）对应病区表PIVAS_INPATIENTAREA.deptCode';
comment on column PIVAS_PATIENT.state
  is '病人当前状态';
comment on column PIVAS_PATIENT.bedno
  is '患者住院期间，所住床位对应的编号';
comment on column PIVAS_PATIENT.reserve0
  is '预留字段0';
comment on column PIVAS_PATIENT.reserve1
  is '预留字段1';
comment on column PIVAS_PATIENT.reserve2
  is '预留字段2';
comment on column PIVAS_PATIENT.case_id
  is '病人唯一住院号';
comment on column PIVAS_PATIENT.birthday
  is '病人出生日期';
comment on column PIVAS_PATIENT.age
  is '病人年龄';
comment on column PIVAS_PATIENT.ageunit
  is '年龄单位，0天 1月 2年';
comment on column PIVAS_PATIENT.avdp
  is '病人体重';
-- Create/Recreate primary, unique and foreign key constraints 
alter table PIVAS_PATIENT
  add constraint PIVAS_PATIENT_GID primary key (GID);
alter table PIVAS_PATIENT
  add constraint IPIVAS_PATIENT_NHOSPNO unique (INHOSPNO);

alter table PIVAS_PATIENT add action number not null;
-- Add comments to the columns 
comment on column PIVAS_PATIENT.action
  is '0(新增)、1(变更)';

--用药途径表
create table PIVAS_DRUGWAY
(
  gid         NUMBER(20, 0) not null,
  drugwayid   VARCHAR2(24) not null,
  drugwaycode VARCHAR2(24) not null,
  drugwayname VARCHAR2(60) not null,
  reserve0    VARCHAR2(100),
  reserve1    VARCHAR2(100),
  reserve2    VARCHAR2(100)
) TABLESPACE PIVAS_TABLESPACE;
-- Add comments to the table 
comment on table PIVAS_DRUGWAY
  is '用药途径表';
-- Add comments to the columns 
comment on column PIVAS_DRUGWAY.gid
  is '主键，自增长';
comment on column PIVAS_DRUGWAY.drugwayid
  is '用药方法id';
comment on column PIVAS_DRUGWAY.drugwaycode
  is '用药方法编码';
comment on column PIVAS_DRUGWAY.drugwayname
  is '用药方法名字';
comment on column PIVAS_DRUGWAY.reserve0
  is '预留字段0';
comment on column PIVAS_DRUGWAY.reserve1
  is '预留字段1';
comment on column PIVAS_DRUGWAY.reserve2
  is '预留字段2';
-- Create/Recreate primary, unique and foreign key constraints 
alter table PIVAS_DRUGWAY
  add constraint PIVAS_DRUGWAY_GID primary key (GID) using index ;
  
alter table PIVAS_DRUGWAY add action number not null;
-- Add comments to the columns 
comment on column PIVAS_DRUGWAY.action
  is '0(新增)、1(变更)';

/*==============================================================*/
/* TABLE: "PIVAS_MEDICAMENTS_FREQUENCY"      用药频次表             */
/*==============================================================*/
CREATE TABLE PIVAS_MEDICAMENTS_FREQUENCY
(
	ID_ 			NUMBER(20, 0) NOT NULL, 
	CODE_ 			VARCHAR2(32 CHAR) NOT NULL, 
	NAME_ 			VARCHAR2(32 CHAR), 
	INTERVAL_ 		NUMBER(5, 0), 
	TIME_OF_DAY_ 	NUMBER(5, 0), 
	START_TIME_ 	VARCHAR2(32 CHAR), 
	END_TIME_ 		VARCHAR2(32 CHAR), 
	RESERVE1_ 		VARCHAR2(20 CHAR), 
	RESERVE2_ 		VARCHAR2(20 CHAR), 
	RESERVE3_ 		VARCHAR2(20 CHAR), 
	CONSTRAINT PIVAS_MEDICAMENTS_FREQUEN_PK PRIMARY KEY(ID_) USING INDEX tablespace pivas_index_tablespace 
) TABLESPACE PIVAS_TABLESPACE;

COMMENT ON TABLE PIVAS_MEDICAMENTS_FREQUENCY IS '用药频次表';

COMMENT ON COLUMN PIVAS_MEDICAMENTS_FREQUENCY.ID_ IS '主键';

COMMENT ON COLUMN PIVAS_MEDICAMENTS_FREQUENCY.CODE_ IS '用药频次编码';

COMMENT ON COLUMN PIVAS_MEDICAMENTS_FREQUENCY.NAME_ IS '用药频次名称';

COMMENT ON COLUMN PIVAS_MEDICAMENTS_FREQUENCY.INTERVAL_ IS '用药间隔时间(小时)';

COMMENT ON COLUMN PIVAS_MEDICAMENTS_FREQUENCY.TIME_OF_DAY_ IS '每天次数';

COMMENT ON COLUMN PIVAS_MEDICAMENTS_FREQUENCY.START_TIME_ IS '开始时间';

COMMENT ON COLUMN PIVAS_MEDICAMENTS_FREQUENCY.END_TIME_ IS '结束时间';

COMMENT ON COLUMN PIVAS_MEDICAMENTS_FREQUENCY.RESERVE1_ IS '预留字段1';

COMMENT ON COLUMN PIVAS_MEDICAMENTS_FREQUENCY.RESERVE2_ IS '预留字段2';

COMMENT ON COLUMN PIVAS_MEDICAMENTS_FREQUENCY.RESERVE3_ IS '预留字段3';

alter table PIVAS_MEDICAMENTS_FREQUENCY add action number not null;
-- Add comments to the columns 
comment on column PIVAS_MEDICAMENTS_FREQUENCY.action
  is '0(新增)、1(变更)';

/*==============================================================*/
/* TABLE: "PIVAS_MEDICAMENTS"                                   */
/*==============================================================*/
CREATE TABLE "PIVAS_MEDICAMENTS" 
(
   "MEDICAMENTS_ID"              INTEGER              NOT NULL,
   "MEDICAMENTS_NAME"            VARCHAR2(256)         NOT NULL,
   "MEDICAMENTS_CODE"            VARCHAR2(32)         NOT NULL,
   "CATEGORY_ID"                 VARCHAR2(32),
   "MEDICAMENTS_SUCHAMA"         VARCHAR2(32),
   "MEDICAMENTS_SPECIFICATIONS"  VARCHAR2(32),
   "MEDICAMENTS_VOLUME"          VARCHAR2(32),
   "MEDICAMENTS_VOLUME_UNIT"     VARCHAR2(32),
   "MEDICAMENTS_DOSAGE"          VARCHAR2(32),
   "MEDICAMENTS_DOSAGE_UNIT"     VARCHAR2(32),
   "MEDICAMENTS_PACKING_UNIT"    VARCHAR2(400),
   "MEDICAMENTS_TEST_FLAG"       INTEGER  DEFAULT 1,
   "MEDICAMENTS_PLACE"           VARCHAR2(32),
   "MEDICAMENTS_PRICE"           VARCHAR2(32),
   "MEDICAMENTS_MENSTRUUM"       INTEGER,
   "MEDICAMENTS_ISACTIVE"        INTEGER DEFAULT 1,
   "MEDICAMENTS_ISMAINDRUG"      INTEGER,
   CONSTRAINT PK_PIVAS_MEDICAMENTS PRIMARY KEY ("MEDICAMENTS_ID") USING INDEX tablespace pivas_index_tablespace 
) TABLESPACE PIVAS_TABLESPACE;

COMMENT ON TABLE "PIVAS_MEDICAMENTS" IS
'药品表';

COMMENT ON COLUMN "PIVAS_MEDICAMENTS"."MEDICAMENTS_ID" IS
'主键标识，自增长';

COMMENT ON COLUMN "PIVAS_MEDICAMENTS"."MEDICAMENTS_NAME" IS
'药品名称';

COMMENT ON COLUMN "PIVAS_MEDICAMENTS"."MEDICAMENTS_CODE" IS
'药品编码';

COMMENT ON COLUMN "PIVAS_MEDICAMENTS"."CATEGORY_ID" IS
'药品分类ID';

COMMENT ON COLUMN "PIVAS_MEDICAMENTS"."MEDICAMENTS_SUCHAMA" IS
'药品速查码';

COMMENT ON COLUMN "PIVAS_MEDICAMENTS"."MEDICAMENTS_SPECIFICATIONS" IS
'药品规格';

COMMENT ON COLUMN "PIVAS_MEDICAMENTS"."MEDICAMENTS_VOLUME" IS
'药品体积';

COMMENT ON COLUMN "PIVAS_MEDICAMENTS"."MEDICAMENTS_VOLUME_UNIT" IS
'药品体积单位';

COMMENT ON COLUMN "PIVAS_MEDICAMENTS"."MEDICAMENTS_DOSAGE" IS
'药品使用次剂量';

COMMENT ON COLUMN "PIVAS_MEDICAMENTS"."MEDICAMENTS_DOSAGE_UNIT" IS
'药品使用次剂量单位';

COMMENT ON COLUMN "PIVAS_MEDICAMENTS"."MEDICAMENTS_PACKING_UNIT" IS
'包装单位';

COMMENT ON COLUMN "PIVAS_MEDICAMENTS"."MEDICAMENTS_TEST_FLAG" IS
'皮试标志 0不需要,1需要';

COMMENT ON COLUMN "PIVAS_MEDICAMENTS"."MEDICAMENTS_PLACE" IS
'药品产地';

COMMENT ON COLUMN "PIVAS_MEDICAMENTS"."MEDICAMENTS_PRICE" IS
'单价';

COMMENT ON COLUMN "PIVAS_MEDICAMENTS"."MEDICAMENTS_MENSTRUUM" IS
'是否溶媒 0不是溶媒,1是溶媒';

COMMENT ON COLUMN "PIVAS_MEDICAMENTS"."MEDICAMENTS_ISACTIVE" IS
'是否可用 0不可用,1可用';

COMMENT ON COLUMN "PIVAS_MEDICAMENTS"."MEDICAMENTS_ISMAINDRUG" IS
'是否做主药 0不是主药,1主药';

alter table PIVAS_MEDICAMENTS add action number not null;
-- Add comments to the columns 
comment on column PIVAS_MEDICAMENTS.action
  is '0(新增)、1(变更)';
  
-- Add/modify columns 
alter table PIVAS_MEDICAMENTS add medicaments_place_code VARCHAR2(32 CHAR);
-- Add comments to the columns 
comment on column PIVAS_MEDICAMENTS.medicaments_place_code
  is '药品产地编码';

alter table PIVAS_MEDICAMENTS add medicaments_Shelf_No VARCHAR2(32 CHAR);
-- Add comments to the columns 
comment on column PIVAS_MEDICAMENTS.medicaments_Shelf_No
  is '药品货架号';
  
-- Add/modify columns 
alter table PIVAS_MEDICAMENTS add medicaments_danger INTEGER default 1;
-- Add comments to the columns 
comment on column PIVAS_MEDICAMENTS.medicaments_danger
  is '高危药标志 1不是 0是';
  
-- Add/modify columns 
alter table PIVAS_MEDICAMENTS add effective_date VARCHAR2(32 CHAR);
alter table PIVAS_MEDICAMENTS add difficulty_degree VARCHAR2(32 CHAR);
-- Add comments to the columns 
comment on column PIVAS_MEDICAMENTS.effective_date
  is '药品有效期';
comment on column PIVAS_MEDICAMENTS.difficulty_degree
  is '配置难度系数';
  
-- Add/modify columns 
alter table PIVAS_MEDICAMENTS add phyfunctiy VARCHAR2(64 CHAR);
-- Add comments to the columns 
comment on column PIVAS_MEDICAMENTS.phyfunctiy
  is '药理作用';

-- 病区表
create table PIVAS_INPATAREA_SYN
(
  gid      NUMBER(20, 0) not null,
  deptcode VARCHAR2(24) not null,
  deptname VARCHAR2(60) not null,
  reserve0 VARCHAR2(100),
  reserve1 VARCHAR2(100),
  reserve2 VARCHAR2(100)
) TABLESPACE PIVAS_TABLESPACE;
-- Add comments to the table 
comment on table PIVAS_INPATAREA_SYN
  is '病区';
-- Add comments to the columns 
comment on column PIVAS_INPATAREA_SYN.gid
  is '主键，自增长';
comment on column PIVAS_INPATAREA_SYN.deptcode
  is '病区编码';
comment on column PIVAS_INPATAREA_SYN.deptname
  is '病区名称';
comment on column PIVAS_INPATAREA_SYN.reserve0
  is '预留字段0';
comment on column PIVAS_INPATAREA_SYN.reserve1
  is '预留字段1';
comment on column PIVAS_INPATAREA_SYN.reserve2
  is '预留字段2';
-- Create/Recreate primary, unique and foreign key constraints 
alter table PIVAS_INPATAREA_SYN
  add constraint PIVAS_INPATAREA_SYN_GID primary key (GID);
alter table PIVAS_INPATAREA_SYN add action number not null;
-- Add comments to the columns 
comment on column PIVAS_INPATAREA_SYN.action
  is '0(新增)、1(变更)';


-- 病人表
create table PIVAS_PATIENT_SYN
(
  gid      NUMBER(20, 0) not null,
  inhospno VARCHAR2(24) not null,
  patname  VARCHAR2(60) not null,
  sex      INTEGER not null,
  wardcode VARCHAR2(24),
  state    VARCHAR2(60),
  bedno    VARCHAR2(24),
  reserve0 VARCHAR2(100),
  reserve1 VARCHAR2(100),
  reserve2 VARCHAR2(100),
  case_id  VARCHAR2(24),
  birthday DATE,
  age      VARCHAR2(24),
  ageunit  INTEGER,
  avdp     VARCHAR2(24)
) TABLESPACE PIVAS_TABLESPACE;
-- Add comments to the table
 
comment on table PIVAS_PATIENT_SYN
  is '病人表';
-- Add comments to the columns 
comment on column PIVAS_PATIENT_SYN.gid
  is '主键，自增长';
comment on column PIVAS_PATIENT_SYN.inhospno
  is '住院流水号，病人唯一标识';
comment on column PIVAS_PATIENT_SYN.patname
  is '患者姓名';
comment on column PIVAS_PATIENT_SYN.sex
  is '性别：0女，1男，默认0';
comment on column PIVAS_PATIENT_SYN.wardcode
  is '病人当前病区(科室）对应病区表PIVAS_INPATAREA_SYN.deptCode';
comment on column PIVAS_PATIENT_SYN.state
  is '病人当前状态';
comment on column PIVAS_PATIENT_SYN.bedno
  is '患者住院期间，所住床位对应的编号';
comment on column PIVAS_PATIENT_SYN.reserve0
  is '预留字段0';
comment on column PIVAS_PATIENT_SYN.reserve1
  is '预留字段1';
comment on column PIVAS_PATIENT_SYN.reserve2
  is '预留字段2';
comment on column PIVAS_PATIENT_SYN.case_id
  is '病人唯一住院号';
comment on column PIVAS_PATIENT_SYN.birthday
  is '病人出生日期';
comment on column PIVAS_PATIENT_SYN.age
  is '病人年龄';
comment on column PIVAS_PATIENT_SYN.ageunit
  is '年龄单位，0天 1月 2年';
comment on column PIVAS_PATIENT_SYN.avdp
  is '病人体重';
-- Create/Recreate primary, unique and foreign key constraints 
alter table PIVAS_PATIENT_SYN
  add constraint PIVAS_PATIENT_SYN_GID primary key (GID);
alter table PIVAS_PATIENT_SYN add action number not null;
-- Add comments to the columns 
comment on column PIVAS_PATIENT_SYN.action
  is '0(新增)、1(变更)';
  
--用药途径表
create table PIVAS_DRUGWAY_SYN
(
  gid         NUMBER(20, 0) not null,
  drugwayid   VARCHAR2(24) not null,
  drugwaycode VARCHAR2(24) not null,
  drugwayname VARCHAR2(60) not null,
  reserve0    VARCHAR2(100),
  reserve1    VARCHAR2(100),
  reserve2    VARCHAR2(100)
) TABLESPACE PIVAS_TABLESPACE;
-- Add comments to the table 
comment on table PIVAS_DRUGWAY_SYN
  is '用药途径表';
-- Add comments to the columns 
comment on column PIVAS_DRUGWAY_SYN.gid
  is '主键，自增长';
comment on column PIVAS_DRUGWAY_SYN.drugwayid
  is '用药方法id';
comment on column PIVAS_DRUGWAY_SYN.drugwaycode
  is '用药方法编码';
comment on column PIVAS_DRUGWAY_SYN.drugwayname
  is '用药方法名字';
comment on column PIVAS_DRUGWAY_SYN.reserve0
  is '预留字段0';
comment on column PIVAS_DRUGWAY_SYN.reserve1
  is '预留字段1';
comment on column PIVAS_DRUGWAY_SYN.reserve2
  is '预留字段2';
-- Create/Recreate primary, unique and foreign key constraints 
alter table PIVAS_DRUGWAY_SYN
  add constraint PIVAS_DRUGWAY_SYN_GID primary key (GID) using index ;
alter table PIVAS_DRUGWAY_SYN add action number not null;
-- Add comments to the columns 
comment on column PIVAS_DRUGWAY_SYN.action
  is '0(新增)、1(变更)';

/*==============================================================*/
/* TABLE: "PIVAS_MEDFREQUENCY_SYN"      用药频次表             */
/*==============================================================*/
create table PIVAS_MEDFREQUENCY_SYN
(
  id_          NUMBER(20) not null,
  code_        VARCHAR2(32 CHAR) not null,
  name_        VARCHAR2(32 CHAR),
  interval_    NUMBER(5),
  time_of_day_ NUMBER(5),
  start_time_  VARCHAR2(32 CHAR),
  end_time_    VARCHAR2(32 CHAR),
  reserve1_    VARCHAR2(20 CHAR),
  reserve2_    VARCHAR2(20 CHAR),
  reserve3_    VARCHAR2(20 CHAR)
)
tablespace PIVAS_TABLESPACE
  pctfree 10
  initrans 1
  maxtrans 255;
-- Add comments to the table 
comment on table PIVAS_MEDFREQUENCY_SYN
  is '用药频次表';
-- Add comments to the columns 
comment on column PIVAS_MEDFREQUENCY_SYN.id_
  is '主键';
comment on column PIVAS_MEDFREQUENCY_SYN.code_
  is '用药频次编码';
comment on column PIVAS_MEDFREQUENCY_SYN.name_
  is '用药频次名称';
comment on column PIVAS_MEDFREQUENCY_SYN.interval_
  is '用药间隔时间(小时)';
comment on column PIVAS_MEDFREQUENCY_SYN.time_of_day_
  is '每天次数';
comment on column PIVAS_MEDFREQUENCY_SYN.start_time_
  is '开始时间';
comment on column PIVAS_MEDFREQUENCY_SYN.end_time_
  is '结束时间';
comment on column PIVAS_MEDFREQUENCY_SYN.reserve1_
  is '预留字段1';
comment on column PIVAS_MEDFREQUENCY_SYN.reserve2_
  is '预留字段2';
comment on column PIVAS_MEDFREQUENCY_SYN.reserve3_
  is '预留字段3';
-- Create/Recreate primary, unique and foreign key constraints 
alter table PIVAS_MEDFREQUENCY_SYN
  add constraint PIVAS_MEDFREQUENCY_SYN_PK primary key (ID_);

alter table PIVAS_MEDFREQUENCY_SYN add action number not null;
-- Add comments to the columns 
comment on column PIVAS_MEDFREQUENCY_SYN.action
  is '0(新增)、1(变更)';

/*==============================================================*/
/* TABLE: "PIVAS_MEDICAMENTS_SYN"                               */
/*==============================================================*/
CREATE TABLE "PIVAS_MEDICAMENTS_SYN" 
(
   "MEDICAMENTS_ID"              INTEGER              NOT NULL,
   "MEDICAMENTS_NAME"            VARCHAR2(256)         NOT NULL,
   "MEDICAMENTS_CODE"            VARCHAR2(32)         NOT NULL,
   "CATEGORY_ID"                 VARCHAR2(32),
   "MEDICAMENTS_SUCHAMA"         VARCHAR2(32),
   "MEDICAMENTS_SPECIFICATIONS"  VARCHAR2(32),
   "MEDICAMENTS_VOLUME"          VARCHAR2(32),
   "MEDICAMENTS_VOLUME_UNIT"     VARCHAR2(32),
   "MEDICAMENTS_DOSAGE"          VARCHAR2(32),
   "MEDICAMENTS_DOSAGE_UNIT"     VARCHAR2(32),
   "MEDICAMENTS_PACKING_UNIT"    VARCHAR2(400),
   "MEDICAMENTS_TEST_FLAG"       INTEGER  DEFAULT 1,
   "MEDICAMENTS_PLACE"           VARCHAR2(32),
   "MEDICAMENTS_PRICE"           VARCHAR2(32),
   "MEDICAMENTS_MENSTRUUM"       INTEGER,
   "MEDICAMENTS_ISACTIVE"        INTEGER DEFAULT 1,
   "MEDICAMENTS_ISMAINDRUG"      INTEGER DEFAULT 1,
   CONSTRAINT PK_PIVAS_MEDICAMENTS_SYN PRIMARY KEY ("MEDICAMENTS_ID") USING INDEX tablespace pivas_index_tablespace 
) TABLESPACE PIVAS_TABLESPACE;

COMMENT ON TABLE "PIVAS_MEDICAMENTS_SYN" IS
'药品表';

COMMENT ON COLUMN "PIVAS_MEDICAMENTS_SYN"."MEDICAMENTS_ID" IS
'主键标识，自增长';

COMMENT ON COLUMN "PIVAS_MEDICAMENTS_SYN"."MEDICAMENTS_NAME" IS
'药品名称';

COMMENT ON COLUMN "PIVAS_MEDICAMENTS_SYN"."MEDICAMENTS_CODE" IS
'药品编码';

COMMENT ON COLUMN "PIVAS_MEDICAMENTS_SYN"."CATEGORY_ID" IS
'药品分类ID';

COMMENT ON COLUMN "PIVAS_MEDICAMENTS_SYN"."MEDICAMENTS_SUCHAMA" IS
'药品速查码';

COMMENT ON COLUMN "PIVAS_MEDICAMENTS_SYN"."MEDICAMENTS_SPECIFICATIONS" IS
'药品规格';

COMMENT ON COLUMN "PIVAS_MEDICAMENTS_SYN"."MEDICAMENTS_VOLUME" IS
'药品体积';

COMMENT ON COLUMN "PIVAS_MEDICAMENTS_SYN"."MEDICAMENTS_VOLUME_UNIT" IS
'药品体积单位';

COMMENT ON COLUMN "PIVAS_MEDICAMENTS_SYN"."MEDICAMENTS_DOSAGE" IS
'药品使用次剂量';

COMMENT ON COLUMN "PIVAS_MEDICAMENTS_SYN"."MEDICAMENTS_DOSAGE_UNIT" IS
'药品使用次剂量单位';

COMMENT ON COLUMN "PIVAS_MEDICAMENTS_SYN"."MEDICAMENTS_PACKING_UNIT" IS
'包装单位';

COMMENT ON COLUMN "PIVAS_MEDICAMENTS_SYN"."MEDICAMENTS_TEST_FLAG" IS
'皮试标志 0不需要,1需要';

COMMENT ON COLUMN "PIVAS_MEDICAMENTS_SYN"."MEDICAMENTS_PLACE" IS
'药品产地';

COMMENT ON COLUMN "PIVAS_MEDICAMENTS_SYN"."MEDICAMENTS_PRICE" IS
'单价';

COMMENT ON COLUMN "PIVAS_MEDICAMENTS_SYN"."MEDICAMENTS_MENSTRUUM" IS
'是否溶媒 0不是溶媒,1是溶媒';

COMMENT ON COLUMN "PIVAS_MEDICAMENTS_SYN"."MEDICAMENTS_ISACTIVE" IS
'是否可用 0不可用,1可用';

COMMENT ON COLUMN "PIVAS_MEDICAMENTS_SYN"."MEDICAMENTS_ISMAINDRUG" IS
'是否做主药 0不是主药,1主药';

alter table PIVAS_MEDICAMENTS_SYN add action number not null;
-- Add comments to the columns 
comment on column PIVAS_MEDICAMENTS_SYN.action
  is '0(新增)、1(变更)';
  
-- Add/modify columns 
alter table PIVAS_MEDICAMENTS_SYN add medicaments_danger INTEGER default 1;
-- Add comments to the columns 
comment on column PIVAS_MEDICAMENTS_SYN.medicaments_danger
  is '高危药标志 1不是 0是';
  
  
-- Add/modify columns 
alter table PIVAS_MEDICAMENTS_SYN add medicaments_place_code VARCHAR2(32 CHAR);
-- Add comments to the columns 
comment on column PIVAS_MEDICAMENTS_SYN.medicaments_place_code
  is '药品产地编码';
  
alter table PIVAS_MEDICAMENTS_SYN add medicaments_Shelf_No VARCHAR2(32 CHAR);
-- Add comments to the columns 
comment on column PIVAS_MEDICAMENTS_SYN.medicaments_Shelf_No
  is '药品货架号';
  
 -- Add/modify columns 
alter table PIVAS_MEDICAMENTS_SYN add effective_date VARCHAR2(32 CHAR);
alter table PIVAS_MEDICAMENTS_SYN add difficulty_degree VARCHAR2(32 CHAR);
-- Add comments to the columns 
comment on column PIVAS_MEDICAMENTS_SYN.effective_date
  is '药品有效期';
comment on column PIVAS_MEDICAMENTS_SYN.difficulty_degree
  is '配置难度系数'; 
  
-- Add/modify columns 
alter table PIVAS_MEDICAMENTS_syn add phyfunctiy VARCHAR2(64 CHAR);
-- Add comments to the columns 
comment on column PIVAS_MEDICAMENTS_syn.phyfunctiy
  is '药理作用';
  


/*==============================================================*/
/* TABLE: PIVAS_TASK_RESULT_SYN任务执行结果表                            */
/*==============================================================*/
CREATE TABLE PIVAS_TASK_RESULT_SYN
(
  TASK_ID       		NUMBER(20, 0) NOT NULL,
  TASK_NAME         	VARCHAR2(32)  NOT NULL,
  TASK_TYPE     		INTEGER NOT NULL,
  TASK_RESULT       	INTEGER  NOT NULL,
  TASK_EXESTART_TIME   	DATE  NOT NULL, 
  TASK_EXESTOP_TIME   	DATE  NOT NULL, 
  TASK_CONTENT_TYPE   	INTEGER NOT NULL,
  RESERVE1        		VARCHAR2(32), 
  RESERVE2        		VARCHAR2(32), 
  RESERVE3        		VARCHAR2(32)
);

COMMENT ON TABLE PIVAS_TASK_RESULT_SYN IS '任务表';

COMMENT ON COLUMN PIVAS_TASK_RESULT_SYN.TASK_ID IS '任务唯一标识';

COMMENT ON COLUMN PIVAS_TASK_RESULT_SYN.TASK_NAME IS '任务名称';

COMMENT ON COLUMN PIVAS_TASK_RESULT_SYN.TASK_TYPE IS '任务类型，0,定时任务 1,一次性任务 ';

COMMENT ON COLUMN PIVAS_TASK_RESULT_SYN.TASK_RESULT IS '任务执行结果  0成功  1失败 ';

COMMENT ON COLUMN PIVAS_TASK_RESULT_SYN.TASK_EXESTART_TIME IS '任务开始执行时间';

COMMENT ON COLUMN PIVAS_TASK_RESULT_SYN.TASK_EXESTOP_TIME IS '任务执行结束时间';

COMMENT ON COLUMN PIVAS_TASK_RESULT_SYN.TASK_CONTENT_TYPE IS '任务执行内容类型 0,病人、1,医嘱、2,病区、3,用药方式、4,医嘱频次、5,药品';

COMMENT ON COLUMN PIVAS_TASK_RESULT_SYN.RESERVE1 IS '备用字段1';

COMMENT ON COLUMN PIVAS_TASK_RESULT_SYN.RESERVE2 IS '备用字段2';

COMMENT ON COLUMN PIVAS_TASK_RESULT_SYN.RESERVE3 IS '备用字段3';

  
--医嘱
create table PIVAS_YZ
(
  id                       NUMBER(20) not null,
  orderno                  VARCHAR2(32 CHAR) not null,
  ordergroupno             VARCHAR2(32 CHAR) not null,
  orderopendeptcode        VARCHAR2(32 CHAR),
  orderopendeptname        VARCHAR2(32 CHAR),
  bedno                    VARCHAR2(32 CHAR),
  inhospno                 VARCHAR2(32 CHAR),
  inhospindexno            VARCHAR2(32 CHAR),
  orderopendrcode          VARCHAR2(32 CHAR),
  orderopendrname          VARCHAR2(256 CHAR),
  recorddrcode             VARCHAR2(32 CHAR),
  recorddrname             VARCHAR2(400 CHAR),
  orderfrequencycode       VARCHAR2(32 CHAR),
  dosewaycode              VARCHAR2(32 CHAR),
  drugcode                 VARCHAR2(32 CHAR),
  drugname                 VARCHAR2(400 CHAR),
  specifications           VARCHAR2(32 CHAR),
  druguseonedosage         VARCHAR2(32 CHAR),
  druguseonedosageunit     VARCHAR2(32 CHAR),
  drugamount               VARCHAR2(32 CHAR),
  orderorderdate           DATE,
  orderstopdate            DATE,
  note                     VARCHAR2(800 CHAR),
  selfdrugflag             INTEGER default 0,
  nutritionliquidflag      INTEGER,
  orderexecutestatus       INTEGER,
  drugplacecode            VARCHAR2(32 CHAR),
  action                   NUMBER not null,
  zxrq                     DATE,
  zxsj                     DATE,
  yzlx                     INTEGER,
  medicaments_packing_unit VARCHAR2(32 CHAR),
  syndata                  VARCHAR2(32 CHAR),
  patname                  VARCHAR2(32 CHAR),
  sex                      VARCHAR2(8 CHAR),
  birth                    DATE,
  age                      VARCHAR2(8 CHAR),
  ageunit                  INTEGER,
  avdp                     VARCHAR2(32 CHAR),
  dropspeed                VARCHAR2(32 CHAR),
  confirm_date             VARCHAR2(32 CHAR),
  firstusecount            INTEGER,
  check_date               VARCHAR2(32 CHAR)
)
tablespace PIVAS_TABLESPACE;

comment on table PIVAS_YZ
  is '医嘱';
-- Add comments to the columns 
comment on column PIVAS_YZ.id
  is '主键标识，自增长';
comment on column PIVAS_YZ.orderno
  is '医嘱的唯一性标志';
comment on column PIVAS_YZ.ordergroupno
  is '成组医嘱的唯一性标志';
comment on column PIVAS_YZ.orderopendeptcode
  is '开立医嘱的科室在医疗机构的科室代码';
comment on column PIVAS_YZ.orderopendeptname
  is '开立医嘱的科室在医疗机构的科室名称';
comment on column PIVAS_YZ.bedno
  is '患者住院期间，所住床位对应的编号';
comment on column PIVAS_YZ.inhospno
  is '住院流水号';
comment on column PIVAS_YZ.inhospindexno
  is '患者住院期间，住院对应的索引号，每次就诊相同，类似于就诊卡号';
comment on column PIVAS_YZ.orderopendrcode
  is '医嘱开立医生工号';
comment on column PIVAS_YZ.orderopendrname
  is '医嘱开立医生姓名';
comment on column PIVAS_YZ.recorddrcode
  is '录入医生工号';
comment on column PIVAS_YZ.recorddrname
  is '录入医生姓名';
comment on column PIVAS_YZ.orderfrequencycode
  is '医嘱频次代码,对应医嘱频次信息';
comment on column PIVAS_YZ.dosewaycode
  is '用药途径代码,对应用药途径';
comment on column PIVAS_YZ.drugcode
  is '药品代码,对应药品字典';
comment on column PIVAS_YZ.drugname
  is '药品名称';
comment on column PIVAS_YZ.specifications
  is '规格,如0.25kg';
comment on column PIVAS_YZ.druguseonedosage
  is '药品使用次剂量';
comment on column PIVAS_YZ.druguseonedosageunit
  is '药品使用次剂量单位';
comment on column PIVAS_YZ.drugamount
  is '药品数量';
comment on column PIVAS_YZ.orderorderdate
  is '医嘱开立时间';
comment on column PIVAS_YZ.orderstopdate
  is '医嘱停止时间';
comment on column PIVAS_YZ.note
  is '备注';
comment on column PIVAS_YZ.selfdrugflag
  is '自备药标志,0是，1不是, 默认不是';
comment on column PIVAS_YZ.nutritionliquidflag
  is '营养液标志,0是，1不是, 默认不是';
comment on column PIVAS_YZ.orderexecutestatus
  is '医嘱执行状态,0：执行 1：停止 2：撤销';
comment on column PIVAS_YZ.drugplacecode
  is '药品产地代码';
comment on column PIVAS_YZ.action
  is '0(新增)、1(变更)';
comment on column PIVAS_YZ.zxrq
  is '执行日期';
comment on column PIVAS_YZ.zxsj
  is '执行时间';
comment on column PIVAS_YZ.yzlx
  is '医嘱类型 0:长期 1:短期';
comment on column PIVAS_YZ.medicaments_packing_unit
  is '包装单位';
comment on column PIVAS_YZ.syndata
  is '同步时间';
comment on column PIVAS_YZ.patname
  is '患者姓名';
comment on column PIVAS_YZ.sex
  is '生理性别代码0:女 1：男';
comment on column PIVAS_YZ.birth
  is 'yyyy/mm/dd出生日期，HIS过来的格式是yyyymmdd';
comment on column PIVAS_YZ.age
  is '年龄';
comment on column PIVAS_YZ.ageunit
  is '年龄单位，0代表天，1代表月，2代表年';
comment on column PIVAS_YZ.avdp
  is '病人体重';
comment on column PIVAS_YZ.dropspeed
  is '医嘱滴速';
comment on column PIVAS_YZ.confirm_date
  is '医生医嘱确认时间';
comment on column PIVAS_YZ.firstusecount
  is '首日用药次数';
comment on column PIVAS_YZ.check_date
  is '护士医嘱审核时间';
-- Create/Recreate indexes 
create index PIVAS_YZ_CONPARE on PIVAS_YZ (ORDERNO, ORDERGROUPNO, ORDERORDERDATE) tablespace PIVAS_TABLESPACE;
create index PIVAS_YZ_ORDERGROUPNO on PIVAS_YZ (ORDERGROUPNO) tablespace PIVAS_TABLESPACE;
create index PIVAS_YZ_ORDERNO on PIVAS_YZ (ORDERNO) tablespace PIVAS_TABLESPACE;
create index PIVAS_YZ_ORDERORDERDATE on PIVAS_YZ (ORDERORDERDATE) tablespace PIVAS_TABLESPACE;
alter table PIVAS_YZ add constraint PIVAS_YZ_ID_PK primary key (ID)  using index  tablespace PIVAS_TABLESPACE;
-- Add/modify columns 
alter table PIVAS_YZ add adddate date default sysdate;

--医嘱同步
-- Create table
create table PIVAS_YZ_SYN
(
  id                       NUMBER(20) not null,
  orderno                  VARCHAR2(32 CHAR) not null,
  ordergroupno             VARCHAR2(32 CHAR) not null,
  orderopendeptcode        VARCHAR2(32 CHAR),
  orderopendeptname        VARCHAR2(32 CHAR),
  bedno                    VARCHAR2(32 CHAR),
  inhospno                 VARCHAR2(32 CHAR),
  inhospindexno            VARCHAR2(32 CHAR),
  orderopendrcode          VARCHAR2(32 CHAR),
  orderopendrname          VARCHAR2(256 CHAR),
  recorddrcode             VARCHAR2(32 CHAR),
  recorddrname             VARCHAR2(400 CHAR),
  orderfrequencycode       VARCHAR2(32 CHAR),
  dosewaycode              VARCHAR2(32 CHAR),
  drugcode                 VARCHAR2(32 CHAR),
  drugname                 VARCHAR2(400 CHAR),
  specifications           VARCHAR2(32 CHAR),
  druguseonedosage         VARCHAR2(32 CHAR),
  druguseonedosageunit     VARCHAR2(32 CHAR),
  drugamount               VARCHAR2(32 CHAR),
  orderorderdate           DATE,
  orderstopdate            DATE,
  note                     VARCHAR2(800 CHAR),
  selfdrugflag             INTEGER default 0,
  nutritionliquidflag      INTEGER,
  orderexecutestatus       INTEGER,
  drugplacecode            VARCHAR2(32 CHAR),
  action                   NUMBER not null,
  zxrq                     VARCHAR2(32 CHAR),
  zxsj                     VARCHAR2(32 CHAR),
  yzlx                     INTEGER,
  medicaments_packing_unit VARCHAR2(32 CHAR),
  syndata                  VARCHAR2(32 CHAR),
  patname                  VARCHAR2(32 CHAR),
  sex                      VARCHAR2(8 CHAR),
  birth                    DATE,
  age                      VARCHAR2(8 CHAR),
  ageunit                  INTEGER,
  avdp                     VARCHAR2(32 CHAR),
  dropspeed                VARCHAR2(32 CHAR),
  confirm_date             VARCHAR2(32 CHAR),
  firstusecount            INTEGER,
  check_date               VARCHAR2(32 CHAR)
)
tablespace PIVAS_TABLESPACE;
-- Add comments to the table 
comment on table PIVAS_YZ_SYN
  is '医嘱';
-- Add comments to the columns 
comment on column PIVAS_YZ_SYN.id
  is '主键标识，自增长';
comment on column PIVAS_YZ_SYN.orderno
  is '医嘱的唯一性标志';
comment on column PIVAS_YZ_SYN.ordergroupno
  is '成组医嘱的唯一性标志';
comment on column PIVAS_YZ_SYN.orderopendeptcode
  is '开立医嘱的科室在医疗机构的科室代码';
comment on column PIVAS_YZ_SYN.orderopendeptname
  is '开立医嘱的科室在医疗机构的科室名称';
comment on column PIVAS_YZ_SYN.bedno
  is '患者住院期间，所住床位对应的编号';
comment on column PIVAS_YZ_SYN.inhospno
  is '住院流水号';
comment on column PIVAS_YZ_SYN.inhospindexno
  is '患者住院期间，住院对应的索引号，每次就诊相同，类似于就诊卡号';
comment on column PIVAS_YZ_SYN.orderopendrcode
  is '医嘱开立医生工号';
comment on column PIVAS_YZ_SYN.orderopendrname
  is '医嘱开立医生姓名';
comment on column PIVAS_YZ_SYN.recorddrcode
  is '录入医生工号';
comment on column PIVAS_YZ_SYN.recorddrname
  is '录入医生姓名';
comment on column PIVAS_YZ_SYN.orderfrequencycode
  is '医嘱频次代码,对应医嘱频次信息';
comment on column PIVAS_YZ_SYN.dosewaycode
  is '用药途径代码,对应用药途径';
comment on column PIVAS_YZ_SYN.drugcode
  is '药品代码,对应药品字典';
comment on column PIVAS_YZ_SYN.drugname
  is '药品名称';
comment on column PIVAS_YZ_SYN.specifications
  is '规格,如0.25kg';
comment on column PIVAS_YZ_SYN.druguseonedosage
  is '药品使用次剂量';
comment on column PIVAS_YZ_SYN.druguseonedosageunit
  is '药品使用次剂量单位';
comment on column PIVAS_YZ_SYN.drugamount
  is '药品数量';
comment on column PIVAS_YZ_SYN.orderorderdate
  is '医嘱开立时间';
comment on column PIVAS_YZ_SYN.orderstopdate
  is '医嘱停止时间';
comment on column PIVAS_YZ_SYN.note
  is '备注';
comment on column PIVAS_YZ_SYN.selfdrugflag
  is '自备药标志,0是，1不是, 默认不是';
comment on column PIVAS_YZ_SYN.nutritionliquidflag
  is '营养液标志,0是，1不是, 默认不是';
comment on column PIVAS_YZ_SYN.orderexecutestatus
  is '医嘱执行状态,0：执行 1：停止 2：撤销';
comment on column PIVAS_YZ_SYN.drugplacecode
  is '药品产地代码';
comment on column PIVAS_YZ_SYN.action
  is '0(新增)、1(变更)';
comment on column PIVAS_YZ_SYN.zxrq
  is '执行日期';
comment on column PIVAS_YZ_SYN.zxsj
  is '执行时间';
comment on column PIVAS_YZ_SYN.yzlx
  is '医嘱类型 0:长期 1:短期';
comment on column PIVAS_YZ_SYN.medicaments_packing_unit
  is '包装单位';
comment on column PIVAS_YZ_SYN.syndata
  is '同步时间';
comment on column PIVAS_YZ_SYN.patname
  is '患者姓名';
comment on column PIVAS_YZ_SYN.sex
  is '生理性别代码0:女 1：男';
comment on column PIVAS_YZ_SYN.birth
  is 'yyyy/mm/dd出生日期，HIS过来的格式是yyyymmdd';
comment on column PIVAS_YZ_SYN.age
  is '年龄';
comment on column PIVAS_YZ_SYN.ageunit
  is '年龄单位，0代表天，1代表月，2代表年';
comment on column PIVAS_YZ_SYN.avdp
  is '病人体重';
comment on column PIVAS_YZ_SYN.dropspeed
  is '医嘱滴速';
comment on column PIVAS_YZ_SYN.confirm_date
  is '医生医嘱确认时间';
comment on column PIVAS_YZ_SYN.firstusecount
  is '首日用药次数';
comment on column PIVAS_YZ_SYN.check_date
  is '护士医嘱审核时间';
alter table PIVAS_YZ_SYN add constraint PIVAS_YZ_SYN_ID_PK primary key (ID) using index  tablespace PIVAS_TABLESPACE;


--员工信息
create table PIVAS_EMPLOYEEINFO
(
  gid        VARCHAR2(12) not null,
  staff_code VARCHAR2(24),
  staff_name VARCHAR2(64),
  action     NUMBER not null
)
tablespace PIVAS_TABLESPACE;
-- Add comments to the table 
comment on table PIVAS_EMPLOYEEINFO
  is '员工表';
-- Add comments to the columns 
comment on column PIVAS_EMPLOYEEINFO.gid
  is '主键ID，自增长';
comment on column PIVAS_EMPLOYEEINFO.staff_code
  is '员工工号';
comment on column PIVAS_EMPLOYEEINFO.staff_name
  is '员工姓名';
comment on column PIVAS_EMPLOYEEINFO.action
  is '0(新增)、1(变更)';
-- Create/Recreate primary, unique and foreign key constraints 
alter table PIVAS_EMPLOYEEINFO
  add constraint PIVAS_EMPLOYEEINFO_GID primary key (GID);
  
-- 员工信息同步
create table PIVAS_EMPLOYEEINFO_SYN
(
  gid        VARCHAR2(12) not null,
  staff_code VARCHAR2(24),
  staff_name VARCHAR2(64),
  action     NUMBER not null
)
tablespace PIVAS_TABLESPACE;
-- Add comments to the table 
comment on table PIVAS_EMPLOYEEINFO_SYN
  is '员工表';
-- Add comments to the columns 
comment on column PIVAS_EMPLOYEEINFO_SYN.gid
  is '主键ID，自增长';
comment on column PIVAS_EMPLOYEEINFO_SYN.staff_code
  is '员工工号';
comment on column PIVAS_EMPLOYEEINFO_SYN.staff_name
  is '员工姓名';
comment on column PIVAS_EMPLOYEEINFO_SYN.action
  is '0(新增)、1(变更)';
-- Create/Recreate primary, unique and foreign key constraints 
alter table PIVAS_EMPLOYEEINFO_SYN
  add constraint PIVAS_EMPLOYEEINFO_SYN_GID primary key (GID);

-- 病人信息增加预出院
alter table PIVAS_PATIENT add hosouttime VARCHAR2(24 CHAR);
comment on column PIVAS_PATIENT.hosouttime
  is '预出院时间';
  
alter table PIVAS_PATIENT_SYN add hosouttime VARCHAR2(24 CHAR);
comment on column PIVAS_PATIENT_SYN.hosouttime
  is '预出院时间';
  
-- 药单执行记录
create table PIVAS_YDRECORD
(
  id           VARCHAR2(32) not null,
  recipeid     VARCHAR2(32),
  groupno      VARCHAR2(32),
  druglistid   VARCHAR2(32),
  drugfreq     VARCHAR2(32),
  drugcode     VARCHAR2(32),
  drugname     VARCHAR2(512),
  quantity     VARCHAR2(32),
  quantityunit VARCHAR2(32),
  schedule     VARCHAR2(32),
  occdt        VARCHAR2(32),
  chargedt     VARCHAR2(32),
  infusiondate VARCHAR2(32),
  labelno      VARCHAR2(32),
  begindt      VARCHAR2(32),
  enddt        VARCHAR2(32),
  syndate      DATE default sysdate not null,
  amount       VARCHAR2(3),
  state        VARCHAR2(3) default 0
)
tablespace PIVAS_TABLESPACE;
-- Add comments to the table 
comment on table PIVAS_YDRECORD
  is '药单执行记录';
-- Add comments to the columns 
comment on column PIVAS_YDRECORD.recipeid
  is '医嘱编码，HIS产生的药单中的执行医嘱的编码';
comment on column PIVAS_YDRECORD.groupno
  is '组号';
comment on column PIVAS_YDRECORD.druglistid
  is '药单号，医嘱执行单的编号';
comment on column PIVAS_YDRECORD.drugfreq
  is '用药频次';
comment on column PIVAS_YDRECORD.drugcode
  is '药品编码';
comment on column PIVAS_YDRECORD.drugname
  is '药品名称';
comment on column PIVAS_YDRECORD.quantity
  is '药品数量';
comment on column PIVAS_YDRECORD.quantityunit
  is '数量单位';
comment on column PIVAS_YDRECORD.schedule
  is '执行序号，例如一个BID的医嘱，药单要生成两个，这个序号为1和2。 如HIS无此值，可为空。 或选用HIS 药单唯一标示编码 ';
comment on column PIVAS_YDRECORD.occdt
  is '用药时间，可仅筛选出今明两日的。';
comment on column PIVAS_YDRECORD.chargedt
  is '收费时间';
comment on column PIVAS_YDRECORD.infusiondate
  is '输液时间';
comment on column PIVAS_YDRECORD.labelno
  is '瓶签号/或药单唯一号';
comment on column PIVAS_YDRECORD.begindt
  is '药单起始时间';
comment on column PIVAS_YDRECORD.enddt
  is '药单终止时间';
comment on column PIVAS_YDRECORD.syndate
  is '同步时间';
comment on column PIVAS_YDRECORD.amount
  is '一天用药次数';
comment on column PIVAS_YDRECORD.state
  is '药单执行记录状态 0：正常  1：停止 2:退费';
-- Add/modify columns 
alter table PIVAS_YDRECORD add drugeddate VARCHAR2(32 CHAR);
-- Add comments to the columns 
comment on column PIVAS_YDRECORD.drugeddate
  is '摆药时间';
-- Create/Recreate indexes 
create index PK_YDRECORD_DRUGLISTID on PIVAS_YDRECORD (DRUGLISTID) tablespace PIVAS_TABLESPACE;
create index PK_YDRECORD_GROUPNO on PIVAS_YDRECORD (GROUPNO) tablespace PIVAS_TABLESPACE;
alter table PIVAS_YDRECORD add constraint PK_YDRECORD_ID primary key (ID) using index tablespace PIVAS_TABLESPACE;
  -- Add/modify columns 
alter table PIVAS_YDRECORD add bedno VARCHAR2(32 CHAR);
alter table PIVAS_YDRECORD add patname VARCHAR2(32 CHAR);
-- Add comments to the columns 
comment on column PIVAS_YDRECORD.bedno
  is '床号';
comment on column PIVAS_YDRECORD.patname
  is '病人姓名';  
  
--药单执行记录同步任务
create table PIVAS_YDRECORD_SYN
(
  id           VARCHAR2(32) not null,
  recipeid     VARCHAR2(32),
  groupno      VARCHAR2(32),
  druglistid   VARCHAR2(32),
  drugfreq     VARCHAR2(32),
  drugcode     VARCHAR2(32),
  drugname     VARCHAR2(512),
  quantity     VARCHAR2(32),
  quantityunit VARCHAR2(32),
  schedule     VARCHAR2(32),
  occdt        VARCHAR2(32),
  chargedt     VARCHAR2(32),
  infusiondate VARCHAR2(32),
  labelno      VARCHAR2(32),
  begindt      VARCHAR2(32),
  enddt        VARCHAR2(32),
  syndate      DATE,
  amount       VARCHAR2(3),
  state        VARCHAR2(3)
)
tablespace PIVAS_TABLESPACE;
-- Add comments to the table 
comment on table PIVAS_YDRECORD_SYN
  is '药单执行记录';
-- Add comments to the columns 
comment on column PIVAS_YDRECORD_SYN.recipeid
  is '医嘱编码，HIS产生的药单中的执行医嘱的编码';
comment on column PIVAS_YDRECORD_SYN.groupno
  is '组号';
comment on column PIVAS_YDRECORD_SYN.druglistid
  is '药单号，医嘱执行单的编号';
comment on column PIVAS_YDRECORD_SYN.drugfreq
  is '用药频次';
comment on column PIVAS_YDRECORD_SYN.drugcode
  is '药品编码';
comment on column PIVAS_YDRECORD_SYN.drugname
  is '药品名称';
comment on column PIVAS_YDRECORD_SYN.quantity
  is '药品数量';
comment on column PIVAS_YDRECORD_SYN.quantityunit
  is '数量单位';
comment on column PIVAS_YDRECORD_SYN.schedule
  is '执行序号，例如一个BID的医嘱，药单要生成两个，这个序号为1和2。如HIS无此值，可为空。或选用HIS 药单唯一标示编码';
comment on column PIVAS_YDRECORD_SYN.occdt
  is '用药时间，可仅筛选出今明两日的。';
comment on column PIVAS_YDRECORD_SYN.chargedt
  is '收费时间';
comment on column PIVAS_YDRECORD_SYN.infusiondate
  is '输液时间';
comment on column PIVAS_YDRECORD_SYN.labelno
  is '瓶签号/或药单唯一号';
comment on column PIVAS_YDRECORD_SYN.begindt
  is '药单起始时间';
comment on column PIVAS_YDRECORD_SYN.enddt
  is '药单终止时间';
comment on column PIVAS_YDRECORD_SYN.syndate
  is '同步时间';
comment on column PIVAS_YDRECORD_SYN.amount
  is '全天用药次数';
comment on column PIVAS_YDRECORD_SYN.state
  is '药单执行记录状态 0：正常  1：停止 2:退费';
-- Add/modify columns 
alter table PIVAS_YDRECORD_SYN add drugeddate VARCHAR2(32 CHAR);
-- Add comments to the columns 
comment on column PIVAS_YDRECORD_SYN.drugeddate
  is '摆药时间';
  -- Add/modify columns 
alter table PIVAS_YDRECORD_syn add bedno VARCHAR2(32 CHAR);
alter table PIVAS_YDRECORD_syn add patname VARCHAR2(32 CHAR);
-- Add comments to the columns 
comment on column PIVAS_YDRECORD_syn.bedno
  is '床号';
comment on column PIVAS_YDRECORD_syn.patname
  is '病人姓名';
  
--备份医嘱表
create table pivas_yz_his as select * from pivas_yz where 1=2;
--备份药单记录表
create table PIVAS_YDRECORD_his as select * from PIVAS_YDRECORD where 1=2;
  