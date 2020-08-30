
    create table `accounting_record` (
       `id` integer not null,
        `version` integer not null,
        `body` varchar(1024),
        `moment` datetime(6),
        `status` integer,
        `title` varchar(255),
        `bookkeeper_id` integer not null,
        `investment_round_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `administrator` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `anonymous` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `application` (
       `id` integer not null,
        `version` integer not null,
        `justification` varchar(1024),
        `moment` datetime(6),
        `offer_amount` double precision,
        `offer_currency` varchar(255),
        `statement` varchar(255),
        `status` integer,
        `ticker` varchar(255),
        `investment_round_id` integer not null,
        `investor_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `authenticated` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `bookkeeper` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `firm_name` varchar(255),
        `responsibility_statement` varchar(1024),
        primary key (`id`)
    ) engine=InnoDB;

    create table `challenge` (
       `id` integer not null,
        `version` integer not null,
        `deadline` datetime(6),
        `description` varchar(255),
        `goal_average` varchar(255),
        `goal_expert` varchar(255),
        `goal_rookie` varchar(255),
        `reward_average_amount` double precision,
        `reward_average_currency` varchar(255),
        `reward_expert_amount` double precision,
        `reward_expert_currency` varchar(255),
        `reward_rookie_amount` double precision,
        `reward_rookie_currency` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `consumer` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `company` varchar(255),
        `sector` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `customisation` (
       `id` integer not null,
        `version` integer not null,
        `activity_sectors` varchar(1024),
        `spamwords` varchar(1024),
        `threshold` double precision,
        primary key (`id`)
    ) engine=InnoDB;

    create table `discussion_forum` (
       `id` integer not null,
        `version` integer not null,
        `moment` datetime(6),
        `title` varchar(255),
        `users` varchar(1024),
        `author_id` integer not null,
        `investment_round_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `entrepreneur` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `activity_sector` varchar(255),
        `qualification_record` varchar(255),
        `skills_record` varchar(255),
        `startup_name` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `inquiry` (
       `id` integer not null,
        `version` integer not null,
        `deadline` datetime(6),
        `description` varchar(1024),
        `email` varchar(255),
        `max_amount` double precision,
        `max_currency` varchar(255),
        `min_amount` double precision,
        `min_currency` varchar(255),
        `moment` datetime(6),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `investment_round` (
       `id` integer not null,
        `version` integer not null,
        `amount_amount` double precision,
        `amount_currency` varchar(255),
        `description` varchar(255),
        `is_final_mode` bit,
        `kind` integer,
        `moment` datetime(6),
        `more_info` varchar(255),
        `ticker` varchar(255),
        `title` varchar(255),
        `entrepreneur_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `investor` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `activity_sector` varchar(255),
        `firm_name` varchar(255),
        `profile` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `message` (
       `id` integer not null,
        `version` integer not null,
        `body` varchar(1024),
        `moment` datetime(6),
        `tags` varchar(255),
        `title` varchar(255),
        `author_id` integer not null,
        `forum_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `molina_bulletin` (
       `id` integer not null,
        `version` integer not null,
        `author` varchar(255),
        `moment` datetime(6),
        `text` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `notice` (
       `id` integer not null,
        `version` integer not null,
        `body` varchar(255),
        `deadline` datetime(6),
        `header_picture` varchar(255),
        `link1` varchar(255),
        `link2` varchar(255),
        `moment` datetime(6),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `overture` (
       `id` integer not null,
        `version` integer not null,
        `deadline` datetime(6),
        `description` varchar(1024),
        `email` varchar(255),
        `max_amount` double precision,
        `max_currency` varchar(255),
        `min_amount` double precision,
        `min_currency` varchar(255),
        `moment` datetime(6),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `provider` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `company` varchar(255),
        `sector` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `storage` (
       `id` integer not null,
        `version` integer not null,
        `firm_name` varchar(255),
        `responsibility_statement` varchar(255),
        `status` integer,
        `authenticated_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `technology_record` (
       `id` integer not null,
        `version` integer not null,
        `activity_sector` varchar(255),
        `description` varchar(255),
        `email` varchar(255),
        `investor` varchar(255),
        `rate` integer,
        `source` integer,
        `title` varchar(255),
        `website` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `tool_record` (
       `id` integer not null,
        `version` integer not null,
        `activity_sector` varchar(255),
        `description` varchar(255),
        `email` varchar(255),
        `investor` varchar(255),
        `rate` integer,
        `source` integer,
        `title` varchar(255),
        `website` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `user_account` (
       `id` integer not null,
        `version` integer not null,
        `enabled` bit not null,
        `identity_email` varchar(255),
        `identity_name` varchar(255),
        `identity_surname` varchar(255),
        `password` varchar(255),
        `username` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `work_programme` (
       `id` integer not null,
        `version` integer not null,
        `budget_amount` double precision,
        `budget_currency` varchar(255),
        `deadline` datetime(6),
        `moment` datetime(6),
        `title` varchar(255),
        `investment_round_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `hibernate_sequence` (
       `next_val` bigint
    ) engine=InnoDB;

    insert into `hibernate_sequence` values ( 1 );

    alter table `user_account` 
       add constraint UK_castjbvpeeus0r8lbpehiu0e4 unique (`username`);

    alter table `accounting_record` 
       add constraint `FK41jm4vk7runvmg5tderffrele` 
       foreign key (`bookkeeper_id`) 
       references `bookkeeper` (`id`);

    alter table `accounting_record` 
       add constraint `FKk1pmfnppwk0kav7xloy8u71uq` 
       foreign key (`investment_round_id`) 
       references `investment_round` (`id`);

    alter table `administrator` 
       add constraint FK_2a5vcjo3stlfcwadosjfq49l1 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `anonymous` 
       add constraint FK_6lnbc6fo3om54vugoh8icg78m 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `application` 
       add constraint `FKk5ibe41quxsif8im882xv4afo` 
       foreign key (`investment_round_id`) 
       references `investment_round` (`id`);

    alter table `application` 
       add constraint `FKl4fp0cd8c008ma79n6w58xhk9` 
       foreign key (`investor_id`) 
       references `investor` (`id`);

    alter table `authenticated` 
       add constraint FK_h52w0f3wjoi68b63wv9vwon57 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `bookkeeper` 
       add constraint FK_krvjp9eaqyapewl2igugbo9o8 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `consumer` 
       add constraint FK_6cyha9f1wpj0dpbxrrjddrqed 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `discussion_forum` 
       add constraint `FKgkgyrl9xew6uucn4vldachbih` 
       foreign key (`author_id`) 
       references `authenticated` (`id`);

    alter table `discussion_forum` 
       add constraint `FKmcgrpw22g3baap51wq319v1bp` 
       foreign key (`investment_round_id`) 
       references `investment_round` (`id`);

    alter table `entrepreneur` 
       add constraint FK_r6tqltqvrlh1cyy8rsj5pev1q 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `investment_round` 
       add constraint `FKkj1l8c2ftn9c65y061me6t37j` 
       foreign key (`entrepreneur_id`) 
       references `entrepreneur` (`id`);

    alter table `investor` 
       add constraint FK_dcek5rr514s3rww0yy57vvnpq 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `message` 
       add constraint `FKe1edpykjs39o98sfkjafa0dtn` 
       foreign key (`author_id`) 
       references `authenticated` (`id`);

    alter table `message` 
       add constraint `FK7ju7uxmh5mdbjgrfwgoem3eqd` 
       foreign key (`forum_id`) 
       references `discussion_forum` (`id`);

    alter table `provider` 
       add constraint FK_b1gwnjqm6ggy9yuiqm0o4rlmd 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `storage` 
       add constraint `FKbak20tfheetwsi0t2ammfwip0` 
       foreign key (`authenticated_id`) 
       references `authenticated` (`id`);

    alter table `work_programme` 
       add constraint `FK3nxyaik1cnvfdg02p9a8ibiko` 
       foreign key (`investment_round_id`) 
       references `investment_round` (`id`);
