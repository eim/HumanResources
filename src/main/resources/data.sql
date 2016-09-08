SET SCHEMA DUDES;

insert into CANDIDATE (NAME, DATE, DELETED)
  select 'Cand 1', CURRENT_TIMESTAMP(), FALSE from dual
  WHERE NOT EXISTS
  (select 1 from CANDIDATE where NAME = 'Cand 1');

insert into CANDIDATE (NAME, DATE, DELETED)
  select 'Cand 2', CURRENT_TIMESTAMP(), FALSE from dual
  WHERE NOT EXISTS
  (select 1 from CANDIDATE where NAME = 'Cand 2');

insert into CANDIDATE (NAME, DATE, DELETED)
  select 'Cand 3', CURRENT_TIMESTAMP(), FALSE from dual
  WHERE NOT EXISTS
  (select 1 from CANDIDATE where NAME = 'Cand 3');

insert into CANDIDATE (NAME, DATE, DELETED)
  select 'Cand 4', CURRENT_TIMESTAMP(), FALSE from dual
  WHERE NOT EXISTS
  (select 1 from CANDIDATE where NAME = 'Cand 4');

insert into CANDIDATE (NAME, DATE, DELETED)
  select 'Cand 5', CURRENT_TIMESTAMP(), FALSE from dual
  WHERE NOT EXISTS
  (select 1 from CANDIDATE where NAME = 'Cand 5');

insert into CANDIDATE (NAME, DATE, DELETED)
  select 'Cand 6', CURRENT_TIMESTAMP(), FALSE from dual
  WHERE NOT EXISTS
  (select 1 from CANDIDATE where NAME = 'Cand 6');

insert into CANDIDATE (NAME, DATE, DELETED)
  select 'Cand 7', CURRENT_TIMESTAMP(), FALSE from dual
  WHERE NOT EXISTS
  (select 1 from CANDIDATE where NAME = 'Cand 7');

commit;