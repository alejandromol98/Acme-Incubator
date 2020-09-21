
    alter table `accounting_record` 
       drop 
       foreign key `FK41jm4vk7runvmg5tderffrele`;

    alter table `accounting_record` 
       drop 
       foreign key `FKk1pmfnppwk0kav7xloy8u71uq`;

    alter table `administrator` 
       drop 
       foreign key FK_2a5vcjo3stlfcwadosjfq49l1;

    alter table `anonymous` 
       drop 
       foreign key FK_6lnbc6fo3om54vugoh8icg78m;

    alter table `application` 
       drop 
       foreign key `FKk5ibe41quxsif8im882xv4afo`;

    alter table `application` 
       drop 
       foreign key `FKl4fp0cd8c008ma79n6w58xhk9`;

    alter table `authenticated` 
       drop 
       foreign key FK_h52w0f3wjoi68b63wv9vwon57;

    alter table `bookkeeper` 
       drop 
       foreign key FK_krvjp9eaqyapewl2igugbo9o8;

    alter table `discussion_forum` 
       drop 
       foreign key `FKgkgyrl9xew6uucn4vldachbih`;

    alter table `discussion_forum` 
       drop 
       foreign key `FKmcgrpw22g3baap51wq319v1bp`;

    alter table `entrepreneur` 
       drop 
       foreign key FK_r6tqltqvrlh1cyy8rsj5pev1q;

    alter table `investment_round` 
       drop 
       foreign key `FKkj1l8c2ftn9c65y061me6t37j`;

    alter table `investor` 
       drop 
       foreign key FK_dcek5rr514s3rww0yy57vvnpq;

    alter table `message` 
       drop 
       foreign key `FKe1edpykjs39o98sfkjafa0dtn`;

    alter table `message` 
       drop 
       foreign key `FK7ju7uxmh5mdbjgrfwgoem3eqd`;

    alter table `monema` 
       drop 
       foreign key `FKcujvi89uqkav60w3e6rkvuiy6`;

    alter table `storage` 
       drop 
       foreign key `FKbak20tfheetwsi0t2ammfwip0`;

    alter table `work_programme` 
       drop 
       foreign key `FK3nxyaik1cnvfdg02p9a8ibiko`;

    drop table if exists `accounting_record`;

    drop table if exists `administrator`;

    drop table if exists `anonymous`;

    drop table if exists `application`;

    drop table if exists `authenticated`;

    drop table if exists `bookkeeper`;

    drop table if exists `challenge`;

    drop table if exists `customisation`;

    drop table if exists `dashboard`;

    drop table if exists `discussion_forum`;

    drop table if exists `entrepreneur`;

    drop table if exists `inquiry`;

    drop table if exists `investment_round`;

    drop table if exists `investor`;

    drop table if exists `message`;

    drop table if exists `molina_bulletin`;

    drop table if exists `monema`;

    drop table if exists `notice`;

    drop table if exists `overture`;

    drop table if exists `storage`;

    drop table if exists `technology_record`;

    drop table if exists `tool_record`;

    drop table if exists `user_account`;

    drop table if exists `work_programme`;

    drop table if exists `hibernate_sequence`;
