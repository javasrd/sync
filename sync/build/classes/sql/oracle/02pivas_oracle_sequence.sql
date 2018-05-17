create sequence sq_dem_task
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
nocache;

-- 病人
create sequence SQ_dem_PATIENT_syn
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
nocache;


-- 病区
create sequence SQ_dem_INPATAREA_SYN
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
nocache;

-- 用药途径
create sequence SQ_dem_drugway_syn
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
nocache;

-- 用药频次
create sequence SQ_dem_MEDFREQUENCY_SYN
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
nocache;

--药品信息
create sequence SQ_dem_MEDICAMENTS_SYN
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
nocache;

--医嘱
create sequence SQ_dem_YZ_SYN
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
nocache;

create sequence SQ_dem_YZ
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
nocache;

--员工信息
create sequence SQ_DEM_EMPLOYEEINFO
minvalue 1
maxvalue 999999999999999999999999999
start with 4169
increment by 1
nocache;

--药单执行记录
create sequence SQ_DEM_YDRecord
minvalue 1
maxvalue 999999999999999999999999999
start with 2000
increment by 1
nocache;