`id` integer auto_increment primary key not null,
`ticket_id` integer not null,
`client_id` integer not null,
unique(`ticket_id`, `client_id`),
key(`ticket_id`,`client_id`)
