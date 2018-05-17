CREATE OR REPLACE PROCEDURE backup_data_prc AS
BEGIN
      insert into pivas_yz_his  select * from pivas_yz where to_date(syndata, 'yyyy-mm-dd hh24:mi:ss') < sysdate - 60 and ORDEREXECUTESTATUS <> 0 and ORDERGROUPNO not in (select groupno from PIVAS_YDRECORD group by groupno);
      
      insert into PIVAS_YDRECORD_his   select * from PIVAS_YDRECORD t where to_date(t.occdt, 'yyyy-MM-DD hh24:mi:ss') <  sysdate -1;

      delete pivas_yz where to_date(syndata, 'yyyy-mm-dd hh24:mi:ss') <  sysdate - 60  and ORDEREXECUTESTATUS <> 0;

      delete PIVAS_TASK_RESULT where task_exestop_time  < ADD_MONTHS(sysdate, -1) ;
      
      delete  PIVAS_YDRECORD t where to_date(t.occdt, 'yyyy-MM-DD hh24:mi:ss') < sysdate - 3;

      commit;
END;
/
