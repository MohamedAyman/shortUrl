 CREATE TABLE `urls`
 (
   `short_url` varchar(6) not null,
   `long_url` varchar(400) NOT NULL,
   `number_of_hits` int not null default 0,
    unique key (`short_url`)
)ENGINE = InnoDB
 DEFAULT CHARACTER SET = utf8
 COLLATE = utf8_unicode_ci;