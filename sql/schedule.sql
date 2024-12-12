-- lv1 일정 테이블
create table SCHEDULES (
       schedule_id INT AUTO_INCREMENT,
       writer VARCHAR(4) NOT NULL,
       title VARCHAR(10) NOT NULL,
       contents VARCHAR(200) NOT NULL,
       created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
       updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
       PRIMARY KEY(schedule_id),
);