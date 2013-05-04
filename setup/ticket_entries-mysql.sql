`id` integer auto_increment primary key not null,
`ticket_id` integer not null,
`entry_id` integer not null,
unique(`ticket_id`, `entry_id`),
key(`ticket_id`,`entry_id`)