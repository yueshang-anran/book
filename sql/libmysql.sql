/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2019/7/6 ������ 11:30:50                        */
/*==============================================================*/


drop table if exists books;

drop table if exists borrows;

drop table if exists users;

/*==============================================================*/
/* Table: books                                                 */
/*==============================================================*/
create table books
(
   book_id              int(10) not null auto_increment,
   book_name            char(20),
   book_publisher       char(20),
   book_classify        char(20),
   book_status          int(1),
   primary key (book_id)
);

/*==============================================================*/
/* Table: borrows                                               */
/*==============================================================*/
create table borrows
(
   borrow_id            int(10) not null auto_increment,
   user_id              char(10),
   book_id              int(10),
   borrow_date          char(50),
   return_date          char(50),
   primary key (borrow_id)
);

/*==============================================================*/
/* Table: users                                                 */
/*==============================================================*/
create table users
(
   user_id              char(10) not null,
   user_name            char(20),
   user_pass            char(32),
   user_depart          char(20),
   user_class           char(20),
   primary key (user_id)
);

alter table borrows add constraint FK_Reference_1 foreign key (user_id)
      references users (user_id) on delete restrict on update restrict;

alter table borrows add constraint FK_Reference_2 foreign key (book_id)
      references books (book_id) on delete restrict on update restrict;
