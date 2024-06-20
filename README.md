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

### IMPORTANT NOTE

remember to change all occurrences with the name ***db_nutybank_test*** to the name of your database
