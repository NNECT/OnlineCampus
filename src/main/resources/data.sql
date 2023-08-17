use campusdb;

insert ignore into common_code_division (division_code, division_name, in_use, order_number, note)
values ('M', '멤버구분', true, 0, null);
insert ignore into common_code_division (division_code, division_name, in_use, order_number, note)
values ('G', '성별구분', true, 0, null);
insert ignore into common_code_division (division_code, division_name, in_use, order_number, note)
values ('C', '강좌상태구분', true, 0, null);
insert ignore into common_code_division (division_code, division_name, in_use, order_number, note)
values ('Y', '콘텐츠상태구분', true, 0, null);

insert ignore into common_code (code, division_code, name, in_use, order_number, note)
values ('M001', 'M', '관리자', true, 0, null);
insert ignore into common_code (code, division_code, name, in_use, order_number, note)
values ('M002', 'M', '강사회원', true, 0, null);
insert ignore into common_code (code, division_code, name, in_use, order_number, note)
values ('M003', 'M', '일반회원', true, 0, null);

insert ignore into common_code (code, division_code, name, in_use, order_number, note)
values ('G001', 'G', '남자', true, 0, null);
insert ignore into common_code (code, division_code, name, in_use, order_number, note)
values ('G002', 'G', '여자', true, 0, null);

insert ignore into common_code (code, division_code, name, in_use, order_number, note)
values ('C001', 'C', '대기', true, 0, null);
insert ignore into common_code (code, division_code, name, in_use, order_number, note)
values ('C002', 'C', '진행', true, 0, null);
insert ignore into common_code (code, division_code, name, in_use, order_number, note)
values ('C003', 'C', '종료', true, 0, null);

insert ignore into common_code (code, division_code, name, in_use, order_number, note)
values ('Y001', 'Y', '대기', true, 0, null);
insert ignore into common_code (code, division_code, name, in_use, order_number, note)
values ('Y002', 'Y', '업로드진행중', true, 0, null);
insert ignore into common_code (code, division_code, name, in_use, order_number, note)
values ('Y003', 'Y', '업로드완료', true, 0, null);
insert ignore into common_code (code, division_code, name, in_use, order_number, note)
values ('Y004', 'Y', '업로드실패', true, 0, null);
