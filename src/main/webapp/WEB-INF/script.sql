create schema fitness;
use fitness;

create table user (
	id int auto_increment primary key,
    username varchar(50) not null,
    password varchar(50) not null,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    city varchar(50) not null,
    email varchar(50) not null,
   	role int not null,
    status boolean not null,
    token varchar(50)
);

create table category (
    id int auto_increment primary key,
    name varchar(50) not null,
    status boolean not null
);

create table attribute_category (
    id int auto_increment primary key,
    name varchar(50) not null,
    status boolean not null,
    category_id int not null,
     FOREIGN KEY (category_id) REFERENCES category(id)
    
);


CREATE TABLE advisor_message (
    id INT AUTO_INCREMENT PRIMARY KEY,
    date DATETIME NOT NULL,
    text TEXT NOT NULL,
    seen BOOLEAN NOT NULL,
    category_id INT NOT NULL,
    advisor_id INT NOT NULL,
    user_id INT NOT NULL,
    FOREIGN KEY (category_id) REFERENCES category(id),
    FOREIGN KEY (advisor_id) REFERENCES user(id),
    FOREIGN KEY (user_id) REFERENCES user(id)
);

CREATE TABLE statistic(
id INT AUTO_INCREMENT PRIMARY KEY,
date timestamp NOT NULL,
description varchar(255) not null
);

CREATE TABLE user_weight(
id INT AUTO_INCREMENT PRIMARY KEY,
weight double not null,
date DATETIME not null,
user_id INT NOT NULL,
FOREIGN KEY (user_id) REFERENCES user(id)
);

create table fitness_program(
id int auto_increment primary key,
name varchar(50) not null,
description varchar(255) not null,
price double not null,
duration int not null,
difficulty varchar(50) not null,
location varchar(50) not null,
image longblob not null,
instructor_name varchar(50) not null,
instructor_surname  varchar(50) not null,
instructor_contact  varchar(50) not null,
link_you_tube text,
category_id int not null,
user_id int not null,
FOREIGN KEY (category_id) REFERENCES category(id),
foreign key(user_id) references user(id)
);

create table comment_user(
id int auto_increment primary key,
text varchar(255) not null,
date datetime not null,
user_id INT NOT NULL,
fitness_program_id int not null,
FOREIGN KEY (user_id) REFERENCES user(id),
foreign key (fitness_program_id) references fitness_program(id)
);

create table fitness_program_users(
id int auto_increment primary key,
date_start datetime not null,
user_id INT NOT NULL,
fitness_program_id int not null,
FOREIGN KEY (user_id) REFERENCES user(id),
foreign key (fitness_program_id) references fitness_program(id)
);

create table message(
id int auto_increment primary key,
text_message text not null,
date datetime not null,
sender_id INT NOT NULL,
receiver_id int not null,
FOREIGN KEY (sender_id) REFERENCES user(id),
foreign key (receiver_id) references user(id)
);

CREATE TABLE activity(
id INT AUTO_INCREMENT PRIMARY KEY,
name varchar(50) not null,
weight double not null,
repetition int not null,
series int not null,
date date not null,
 user_id INT NOT NULL,
 FOREIGN KEY (user_id) REFERENCES user(id)

);



