CREATE OR REPLACE TRIGGER "dem_task_trg"
BEFORE INSERT ON PIVAS_TASK
FOR EACH ROW
BEGIN
IF INSERTING AND :new.TASK_ID IS NULL THEN
SELECT sq_dem_task.NEXTVAL INTO :new.TASK_ID FROM DUAL;
END IF;
END;
/

CREATE OR REPLACE TRIGGER "dem_PATIENT_syn_trg"
BEFORE INSERT ON PIVAS_PATIENT_syn
FOR EACH ROW
BEGIN
IF INSERTING AND :new.gid IS NULL THEN
SELECT sq_dem_PATIENT_syn.NEXTVAL INTO :new.gid FROM DUAL;
END IF;
END;
/

-- 病区
CREATE OR REPLACE TRIGGER "dem_INPATAREA_SYN_TRG"
BEFORE INSERT ON PIVAS_INPATAREA_SYN
FOR EACH ROW
BEGIN
IF INSERTING AND :new.gid IS NULL THEN
SELECT sq_dem_INPATAREA_SYN.NEXTVAL INTO :new.gid FROM DUAL;
END IF;
END;
/

-- 医嘱
CREATE OR REPLACE TRIGGER "dem_yz_syn_TRG"
BEFORE INSERT ON pivas_yz_syn
FOR EACH ROW
BEGIN
IF INSERTING AND :new.id IS NULL THEN
SELECT sq_dem_yz_syn.NEXTVAL INTO :new.id FROM DUAL;
END IF;
END;
/

CREATE OR REPLACE TRIGGER "dem_yz_TRG"
BEFORE INSERT ON pivas_yz
FOR EACH ROW
BEGIN
IF INSERTING AND :new.id IS NULL THEN
SELECT sq_dem_yz.NEXTVAL INTO :new.id FROM DUAL;
END IF;
END;
/

-- 用药途径
CREATE OR REPLACE TRIGGER "dem_drugway_syn_TRG"
BEFORE INSERT ON pivas_drugway_syn
FOR EACH ROW
BEGIN
IF INSERTING AND :new.gid IS NULL THEN
SELECT sq_dem_drugway_syn.NEXTVAL INTO :new.gid FROM DUAL;
END IF;
END;
/

--用药频次
CREATE OR REPLACE TRIGGER "dem_MEDFREQUENCY_SYN_TRG"
BEFORE INSERT ON PIVAS_MEDFREQUENCY_SYN
FOR EACH ROW
BEGIN
IF INSERTING AND :new.id_ IS NULL THEN
SELECT sq_dem_MEDFREQUENCY_SYN.NEXTVAL INTO :new.id_ FROM DUAL;
END IF;
END;
/

--药品信息
CREATE OR REPLACE TRIGGER "dem_MEDICAMENTS_SYN_TRG"
BEFORE INSERT ON PIVAS_MEDICAMENTS_SYN
FOR EACH ROW
BEGIN
IF INSERTING AND :new.MEDICAMENTS_ID IS NULL THEN
SELECT sq_dem_MEDICAMENTS_SYN.NEXTVAL INTO :new.MEDICAMENTS_ID FROM DUAL;
END IF;
END;
/

--同步结果
CREATE OR REPLACE TRIGGER SYN_TASK_RESULT_TRG

  AFTER INSERT ON PIVAS_TASK_RESULT

  FOR EACH ROW

BEGIN

  INSERT INTO PIVAS_TASK_RESULT_SYN
  
    (TASK_ID,
     TASK_NAME,
     TASK_TYPE,
     TASK_RESULT,
     TASK_EXESTART_TIME,
     TASK_EXESTOP_TIME,
     TASK_CONTENT_TYPE,
     RESERVE1,
     RESERVE2,
     RESERVE3)
  
  VALUES
    (:NEW.TASK_ID,
     :NEW.TASK_NAME,
     :NEW.TASK_TYPE,
     :NEW.TASK_RESULT,
     :NEW.TASK_EXESTART_TIME,
     :NEW.TASK_EXESTOP_TIME,
     :NEW.TASK_CONTENT_TYPE,
     :NEW.RESERVE1,
     :NEW.RESERVE2,
     :NEW.RESERVE3);
end;
/

--病区同步
CREATE OR REPLACE TRIGGER SYN_INPATIENTAREA_TRG

  AFTER INSERT OR UPDATE ON PIVAS_INPATIENTAREA

  FOR EACH ROW

BEGIN

  INSERT INTO PIVAS_INPATAREA_SYN
  
    (deptcode, deptname, RESERVE1, RESERVE2, reserve0, ACTION)
  
  VALUES
    (:NEW.deptcode,
     :NEW.deptname,
     :NEW.RESERVE1,
     :NEW.RESERVE2,
     :NEW.reserve0,
     :NEW.ACTION);

end;

/

--用药途径同步
CREATE OR REPLACE TRIGGER SYN_drugway_TRG

  AFTER INSERT OR UPDATE ON pivas_drugway

  FOR EACH ROW

BEGIN

  INSERT INTO pivas_drugway_syn
  
    (drugwayid,
     drugwayname,
     drugwaycode,
     RESERVE1,
     RESERVE2,
     reserve0,
     ACTION)
  
  VALUES
    (:NEW.drugwayid,
     :NEW.drugwayname,
     :NEW.drugwaycode,
     :NEW.RESERVE1,
     :NEW.RESERVE2,
     :NEW.reserve0,
     :NEW.ACTION);

end;
/


--用药频次
CREATE OR REPLACE TRIGGER SYN_MEDFREQUENCY_TRG

  AFTER INSERT OR UPDATE ON PIVAS_MEDICAMENTS_FREQUENCY

  FOR EACH ROW

BEGIN

  INSERT INTO PIVAS_MEDFREQUENCY_SYN
  
    (code_,
     name_,
     interval_,
     time_of_day_,
     start_time_,
     end_time_,
     reserve1_,
     reserve2_,
     reserve3_,
     ACTION)
  
  VALUES
    (:NEW.code_,
     :NEW.name_,
     :NEW.interval_,
     :NEW.time_of_day_,
     :NEW.start_time_,
     :NEW.end_time_,
     :NEW.reserve1_,
     :NEW.reserve2_,
     :NEW.reserve3_,
     :NEW.ACTION);

end;
/

-- 药品同步
CREATE OR REPLACE TRIGGER SYN_MEDICAMENTS_TRG

  AFTER INSERT OR UPDATE ON PIVAS_MEDICAMENTS

  FOR EACH ROW

BEGIN

  INSERT INTO PIVAS_MEDICAMENTS_SYN

    (medicaments_name,
     medicaments_code,
     category_id,
     medicaments_suchama,
     medicaments_specifications,
     medicaments_volume,
     medicaments_volume_unit,
     medicaments_dosage,
     medicaments_dosage_unit,
     medicaments_packing_unit,
     medicaments_test_flag,
     medicaments_place,
     medicaments_price,
     medicaments_menstruum,
     medicaments_isactive,
     medicaments_ismaindrug,
     medicaments_danger,
     medicaments_place_code,
     medicaments_shelf_no,
     EFFECTIVE_DATE,
     difficulty_degree ,
     phyfunctiy ,
     ACTION)
  VALUES
    (:NEW.medicaments_name,
     :NEW.medicaments_code,
     :NEW.category_id,
     :NEW.medicaments_suchama,
     :NEW.medicaments_specifications,
     :NEW.medicaments_volume,
     :NEW.medicaments_volume_unit,
     :NEW.medicaments_dosage,
     :NEW.medicaments_dosage_unit,
     :NEW.medicaments_packing_unit,
     :NEW.medicaments_test_flag,
     :NEW.medicaments_place,
     :NEW.medicaments_price,
     :NEW.medicaments_menstruum,
     :NEW.medicaments_isactive,
     :NEW.medicaments_ismaindrug,
     :NEW.medicaments_danger,
     :NEW.medicaments_place_code,
     :NEW.medicaments_shelf_no,
     :new.Effective_Date,
     :new.Difficulty_Degree,
     :new.phyfunctiy ,
     :NEW.ACTION);
end;
/

--病人同步
CREATE OR REPLACE TRIGGER SYN_PATIENT_TRG

  AFTER INSERT OR UPDATE ON PIVAS_PATIENT

  FOR EACH ROW

BEGIN

  INSERT INTO PIVAS_PATIENT_syn
  
    (inhospno,
     patname,
     sex,
     wardcode,
     state,
     bedno,
     case_id,
     birthday,
     age,
     ageunit,
     avdp,
     reserve0,
     reserve1,
     reserve2,
     hosOutTime,
     ACTION)
  
  VALUES
    (:NEW.inhospno,
     :NEW.patname,
     :NEW.sex,
     :NEW.wardcode,
     :NEW.state,
     :NEW.bedno,
     :NEW.case_id,
     :NEW.birthday,
     :NEW.age,
     :NEW.ageunit,
     :NEW.avdp,
     :NEW.RESERVE1,
     :NEW.RESERVE2,
     :NEW.reserve0,
     :NEW.hosOutTime,
     :NEW.ACTION);

end;
/

--医嘱同步
CREATE OR REPLACE TRIGGER SYN_YZ_TRG

  AFTER INSERT OR UPDATE ON pivas_yz

  FOR EACH ROW

BEGIN

  INSERT INTO pivas_yz_syn

    (orderno ,
     ordergroupno ,
     orderopendeptcode ,
     orderopendeptname ,
     bedno ,
     inhospno ,
     inhospindexno ,
     orderopendrcode ,
     orderopendrname ,
     recorddrcode ,
     recorddrname ,
     orderfrequencycode ,
     dosewaycode ,
     drugcode ,
     drugname ,
     specifications ,
     druguseonedosage ,
     druguseonedosageunit ,
     drugamount ,
     orderorderdate ,
     orderstopdate ,
     note ,
     selfdrugflag ,
     nutritionliquidflag ,
     orderexecutestatus ,
     drugplacecode ,
     zxrq,
     zxsj,
     yzlx,
     medicaments_packing_unit,
     syndata,
     patname,
     sex,
     birth,
     age,
     ageunit,
     avdp,
     dropspeed,
     confirm_date,
     check_date,
     FIRSTUSECOUNT,
     ACTION)
  VALUES
    (:NEW.orderno,
     :NEW.ordergroupno ,
     :NEW.orderopendeptcode ,
     :NEW.orderopendeptname ,
     :NEW.bedno ,
     :NEW.inhospno ,
     :NEW.inhospindexno ,
     :NEW.orderopendrcode ,
     :NEW.orderopendrname ,
     :NEW.recorddrcode ,
     :NEW.recorddrname ,
     :NEW.orderfrequencycode ,
     :NEW.dosewaycode ,
     :NEW.drugcode ,
     :NEW.drugname ,
     :NEW.specifications ,
     :NEW.druguseonedosage ,
     :NEW.druguseonedosageunit ,
     :NEW.drugamount ,
     :NEW.orderorderdate ,
     :NEW.orderstopdate ,
     :NEW.note ,
     :NEW.selfdrugflag ,
     :NEW.nutritionliquidflag ,
     :NEW.orderexecutestatus ,
     :NEW.drugplacecode ,
     :NEW.zxrq,
     :NEW.zxsj,
     :NEW.yzlx,
     :NEW.medicaments_packing_unit,
     :NEW.syndata,
     :NEW.patname,
     :NEW.sex,
     :NEW.birth,
     :NEW.age,
     :NEW.ageunit,
     :NEW.avdp,
     :NEW.dropspeed,
     :NEW.confirm_date,
     :NEW.check_date,
     :NEW.FIRSTUSECOUNT,
     :NEW.ACTION);
end;
/


-- 病人
CREATE OR REPLACE TRIGGER "dem_PATIENT_trg"
BEFORE INSERT ON PIVAS_PATIENT
FOR EACH ROW
BEGIN
IF INSERTING AND :new.gid IS NULL THEN
SELECT sq_dem_PATIENT_SYN.NEXTVAL INTO :new.gid FROM DUAL;
END IF;
END;
/


--用药途径表
CREATE OR REPLACE TRIGGER "dem_DRUGWAY_trg"
BEFORE INSERT ON PIVAS_DRUGWAY
FOR EACH ROW
BEGIN
IF INSERTING AND :new.gid IS NULL THEN
SELECT sq_dem_DRUGWAY_SYN.NEXTVAL INTO :new.gid FROM DUAL;
END IF;
END;
/


--用药频次
CREATE OR REPLACE TRIGGER "dem_medical_freq_trg"
BEFORE INSERT ON pivas_medicaments_frequency
FOR EACH ROW
BEGIN
IF INSERTING AND :new.id_ IS NULL THEN
SELECT sq_dem_MEDFREQUENCY_SYN.NEXTVAL INTO :new.id_ FROM DUAL;
END IF;
END;
/

--药品
CREATE OR REPLACE TRIGGER "dem_medicaments_trg"
BEFORE INSERT ON pivas_medicaments
FOR EACH ROW
BEGIN
IF INSERTING AND :new.MEDICAMENTS_ID IS NULL THEN
SELECT sq_dem_medicaments_SYN.NEXTVAL INTO :new.MEDICAMENTS_ID FROM DUAL;
END IF;
END;
/

-- 病区
CREATE OR REPLACE TRIGGER "dem_INPATIENTAREA_TRG"
BEFORE INSERT ON PIVAS_INPATIENTAREA
FOR EACH ROW
BEGIN
IF INSERTING AND :new.gid IS NULL THEN
SELECT sq_dem_INPATAREA_SYN.NEXTVAL INTO :new.gid FROM DUAL;
END IF;
END;
/

--员工信息
CREATE OR REPLACE TRIGGER "dem_EMPLOYEEINFO_trg"
BEFORE INSERT ON PIVAS_EMPLOYEEINFO
FOR EACH ROW
BEGIN
IF INSERTING AND :new.gid IS NULL THEN
SELECT SQ_DEM_EMPLOYEEINFO.NEXTVAL INTO :new.gid FROM DUAL;
END IF;
END;
/


--员工信息同步
CREATE OR REPLACE TRIGGER "dem_EMPLOYEEINFO_syn_trg"
BEFORE INSERT ON PIVAS_EMPLOYEEINFO_syn
FOR EACH ROW
BEGIN
IF INSERTING AND :new.gid IS NULL THEN
SELECT SQ_DEM_EMPLOYEEINFO.NEXTVAL INTO :new.gid FROM DUAL;
END IF;
END;
/

--员工信息同步触发
CREATE OR REPLACE TRIGGER SYN_EMPLOYEEINFO_TRG

  AFTER INSERT OR UPDATE ON PIVAS_EMPLOYEEINFO

  FOR EACH ROW

BEGIN

  INSERT INTO PIVAS_EMPLOYEEINFO_SYN
  
    (Staff_Code, Staff_Name, ACTION)
  
  VALUES
    (:NEW.Staff_Code,
     :NEW.Staff_Name,
     :NEW.ACTION);

end;
/

CREATE OR REPLACE TRIGGER "dem_YDRecord_TRG"
BEFORE INSERT ON PIVAS_YDRecord
FOR EACH ROW
BEGIN
IF INSERTING AND :new.id IS NULL THEN
SELECT SQ_DEM_YDRecord.NEXTVAL INTO :new.id FROM DUAL;
END IF;
END;
/

CREATE OR REPLACE TRIGGER SYN_YDRecord_TRG

  AFTER INSERT OR UPDATE ON PIVAS_YDRecord

  FOR EACH ROW

BEGIN

  INSERT INTO PIVAS_YDRecord_SYN

    (id,
     recipeid,
     groupno,
     druglistid,
     drugfreq,
     drugcode,
     drugname,
     quantity,
     quantityunit,
     schedule,
     occdt,
     chargedt,
     infusiondate,
     labelno,
     begindt,
     enddt,
     amount,
     state,
     drugeddate,
     bedno,
     patname,
     syndate
     )

  VALUES
    (:NEW.id,
     :NEW.recipeid,
     :NEW.groupno,
     :NEW.druglistid,
     :NEW.drugfreq,
     :NEW.drugcode,
     :NEW.drugname,
     :NEW.quantity,
     :NEW.quantityunit,
     :NEW.schedule,
     :NEW.occdt,
     :NEW.chargedt,
     :NEW.infusiondate,
     :NEW.labelno,
     :NEW.begindt,
     :NEW.enddt,
     :NEW.amount,
     :NEW.state,
     :NEW.drugeddate,
     :new.bedno,
     :new.patname,
     :NEW.syndate);

end;
/