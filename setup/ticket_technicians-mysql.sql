`id` integer auto_increment primary key not null,
`ticket_id` integer not null,
`technician_id` integer not null,
unique(`ticket_id`, `technician_id`),
key(`ticket_id`,`technician_id`)
