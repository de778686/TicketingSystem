`id` integer primary key not null,
`actor_id` integer not null,
`movie_id` integer not null,
unique(`movie_id`, `actor_id`)
