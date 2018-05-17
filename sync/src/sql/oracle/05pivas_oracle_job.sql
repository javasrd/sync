--数据备份定时job 凌晨一点执行
declare
  job number;
begin
  sys.dbms_job.submit(job,
                      'backup_data_prc;',
                      sysdate,
                      'TRUNC(SYSDATE)+1+1/24');
commit;
end;
/