# Nuty-Bank

Proyect in development

### Versions

- java 17
- MySQL 8

### Setup Proyect

put your route and your db name in this line in file properties
```java
spring.datasource.url=jdbc:mysql://localhost:33060/db_nutybank_test
```

put your access credentials for database in file properites
```java
spring.datasource.username=root
spring.datasource.password=1234
```

run your application and it will create the database tables on its own, except for one table that you must create by inserting this SQL
```SQL
CREATE TABLE `db_nutybank_test`.`clients_roles` (
  `person_id` BIGINT NOT NULL,
  `role_id` BIGINT NOT NULL);
```
And add the foreign key 
```SQL
ALTER TABLE `db_nutybank_test`.`clients_roles` 
ADD CONSTRAINT `FK_clients`
  FOREIGN KEY (`person_id`)
  REFERENCES `db_nutybank_test`.`persons` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `FK_roles`
  FOREIGN KEY (`role_id`)
  REFERENCES `db_nutybank_test`.`roles` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
```

Next step insert the role names into table roles
```SQL
INSERT INTO `db_nutybank_test`.`roles` (`id`, `name`) VALUES ('1', 'ROLE_EMPLOYEE');
INSERT INTO `db_nutybank_test`.`roles` (`id`, `name`) VALUES ('2', 'ROLE_CLIENT');
INSERT INTO `db_nutybank_test`.`roles` (`id`, `name`) VALUES ('3', 'ROLE_MANAGER');
INSERT INTO `db_nutybank_test`.`roles` (`id`, `name`) VALUES ('4', 'ROLE_ADMIN');
```

Create a new User admin
```SQL
INSERT INTO `db_nutybank_test`.`persons` (`id`, `dni`, `email`, `lastname`, `name`, `othername`, `password`) VALUES ('1', '3245643E', 'admin@admin.es', 'Administrator', 'Admin', 'Administrator', 'Admin1234');
INSERT INTO `db_nutybank_test`.`employees` (`admin`, `client`, `enabled`, `manager`, `position`, `salary`, `id`) VALUES (true, false, true, false,'NutyBank Administrator', 1234.56, 1);
```

### IMPORTANT NOTE

remember to change all occurrences with the name ***db_nutybank_test*** to the name of your database
