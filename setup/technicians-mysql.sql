`id` integer auto_increment primary key not null,
`username` varchar(255) unique not null,
`password` varchar(255) not null,
`name` varchar(255) not null,
`level` integer not null