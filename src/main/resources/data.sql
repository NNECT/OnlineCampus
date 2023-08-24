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

insert ignore into course (course_seq, course_brief, course_name, end_date_time, start_date_time, status_code)
values ('1','강의1 내용','강의1','2023-08-12','2023-08-24', 'C002');
insert ignore into course (course_seq, course_brief, course_name, end_date_time, start_date_time, status_code)
values ('2','강의2 내용','강의2','2023-08-14','2023-08-28', 'C002');
insert ignore into course (course_seq, course_brief, course_name, end_date_time, start_date_time, status_code)
values ('3','강의3 내용','강의3','2023-08-18','2023-08-30', 'C002');
insert ignore into course (course_name, course_brief, start_date_time, end_date_time, status_code)
values ('테스트', '테스트', now(), now(), 'C001');