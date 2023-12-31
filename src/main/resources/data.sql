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
values ('국어', '국어수업', now(), now(), 'C001');
insert ignore into course (course_name, course_brief, start_date_time, end_date_time, status_code)
values ('수학', '수학수업', now(), now(), 'C001');
insert ignore into course (course_name, course_brief, start_date_time, end_date_time, status_code)
values ('영어', '영어수업', now(), now(), 'C002');
insert ignore into course (course_name, course_brief, start_date_time, end_date_time, status_code)
values ('과학', '과학수업', now(), now(), 'C002');
insert ignore into course (course_name, course_brief, start_date_time, end_date_time, status_code)
values ('사회', '사회수업', now(), now(), 'C003');
insert ignore into course (course_name, course_brief, start_date_time, end_date_time, status_code)
values ('도덕', '도덕수업', now(), now(), 'C003');
insert ignore into course (course_name, course_brief, start_date_time, end_date_time, status_code)
values ('체육', '체육수업', now(), now(), 'C002');
insert ignore into course (course_name, course_brief, start_date_time, end_date_time, status_code)
values ('미술', '미술수업', now(), now(), 'C003');
insert ignore into course (course_name, course_brief, start_date_time, end_date_time, status_code)
values ('사회', '사회수업', now(), now(), 'C003');
insert ignore into course (course_name, course_brief, start_date_time, end_date_time, status_code)
values ('도덕', '도덕수업', now(), now(), 'C003');
insert ignore into course (course_name, course_brief, start_date_time, end_date_time, status_code)
values ('체육', '체육수업', now(), now(), 'C002');
insert ignore into course (course_name, course_brief, start_date_time, end_date_time, status_code)
values ('미술', '미술수업', now(), now(), 'C003');
insert ignore into course (course_name, course_brief, start_date_time, end_date_time, status_code)
values ('사회', '사회수업', now(), now(), 'C003');
insert ignore into course (course_name, course_brief, start_date_time, end_date_time, status_code)
values ('도덕', '도덕수업', now(), now(), 'C003');
insert ignore into course (course_name, course_brief, start_date_time, end_date_time, status_code)
values ('체육', '체육수업', now(), now(), 'C002');
insert ignore into course (course_name, course_brief, start_date_time, end_date_time, status_code)
values ('미술', '미술수업', now(), now(), 'C003');
insert ignore into course (course_name, course_brief, start_date_time, end_date_time, status_code)
values ('사회', '사회수업', now(), now(), 'C003');
insert ignore into course (course_name, course_brief, start_date_time, end_date_time, status_code)
values ('도덕', '도덕수업', now(), now(), 'C003');
insert ignore into course (course_name, course_brief, start_date_time, end_date_time, status_code)
values ('체육', '체육수업', now(), now(), 'C002');
insert ignore into course (course_name, course_brief, start_date_time, end_date_time, status_code)
values ('미술', '미술수업', now(), now(), 'C003');
       insert ignore into course (course_name, course_brief, start_date_time, end_date_time, status_code)
values ('국어', '국어수업', now(), now(), 'C001');
insert ignore into course (course_name, course_brief, start_date_time, end_date_time, status_code)
values ('수학', '수학수업', now(), now(), 'C001');
insert ignore into course (course_name, course_brief, start_date_time, end_date_time, status_code)
values ('영어', '영어수업', now(), now(), 'C002');
insert ignore into course (course_name, course_brief, start_date_time, end_date_time, status_code)
values ('과학', '과학수업', now(), now(), 'C002');
insert ignore into course (course_name, course_brief, start_date_time, end_date_time, status_code)
values ('사회', '사회수업', now(), now(), 'C003');
insert ignore into course (course_name, course_brief, start_date_time, end_date_time, status_code)
values ('도덕', '도덕수업', now(), now(), 'C003');
insert ignore into course (course_name, course_brief, start_date_time, end_date_time, status_code)
values ('체육', '체육수업', now(), now(), 'C002');
insert ignore into course (course_name, course_brief, start_date_time, end_date_time, status_code)
values ('미술', '미술수업', now(), now(), 'C003');
insert ignore into course (course_name, course_brief, start_date_time, end_date_time, status_code)
values ('사회', '사회수업', now(), now(), 'C003');
insert ignore into course (course_name, course_brief, start_date_time, end_date_time, status_code)
values ('도덕', '도덕수업', now(), now(), 'C003');
insert ignore into course (course_name, course_brief, start_date_time, end_date_time, status_code)
values ('체육', '체육수업', now(), now(), 'C002');
insert ignore into course (course_name, course_brief, start_date_time, end_date_time, status_code)
values ('미술', '미술수업', now(), now(), 'C003');
insert ignore into course (course_name, course_brief, start_date_time, end_date_time, status_code)
values ('사회', '사회수업', now(), now(), 'C003');
insert ignore into course (course_name, course_brief, start_date_time, end_date_time, status_code)
values ('도덕', '도덕수업', now(), now(), 'C003');
insert ignore into course (course_name, course_brief, start_date_time, end_date_time, status_code)
values ('체육', '체육수업', now(), now(), 'C002');
insert ignore into course (course_name, course_brief, start_date_time, end_date_time, status_code)
values ('미술', '미술수업', now(), now(), 'C003');
insert ignore into course (course_name, course_brief, start_date_time, end_date_time, status_code)
values ('사회', '사회수업', now(), now(), 'C003');
insert ignore into course (course_name, course_brief, start_date_time, end_date_time, status_code)
values ('도덕', '도덕수업', now(), now(), 'C003');
insert ignore into course (course_name, course_brief, start_date_time, end_date_time, status_code)
values ('체육', '체육수업', now(), now(), 'C002');
insert ignore into course (course_name, course_brief, start_date_time, end_date_time, status_code)
values ('미술', '미술수업', now(), now(), 'C003');

insert ignore into member (member_division_code, name_kor, email, username, password, password_change_date, password_change_required, password_error_count, last_login_date, register_date)
values (
        'M003',
        '홍길동',
        'testuser@email.com',
        'testuser',
        '$2a$10$9rkBzIxbQHWziANogd9Q..h5Oayqcf/aYM4qQrJ8gEJm5v/.5TUdS',
        now(),
        0,
        0,
        now(),
        now()
       );

insert ignore into member (member_division_code, name_kor, email, username, password, password_change_date, password_change_required, password_error_count, last_login_date, register_date)
values (
           'M001',
           '관리자',
           'admin@email.com',
           'admin',
           '$2a$10$9rkBzIxbQHWziANogd9Q..h5Oayqcf/aYM4qQrJ8gEJm5v/.5TUdS',
           now(),
           0,
           0,
           now(),
           now()
       );
