## application.properties

### maple_helper_discord_bot

```properties
discord.bot.token=""
```

### maple_helper_api

```properties
# MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/MYSQL_DATABASE?useSSL=false
spring.datasource.username=
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
# MySQL - JPA
spring.jpa.database=mysql
spring.jpa.hibernate.ddl-auto=update
# MySQL - Logging
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.jpa.show-sql=true
```

## db.config.env

```env
MYSQL_DATABASE=
MYSQL_USER=
MYSQL_PASSWORD=
MYSQL_ROOT_PASSWORD=
```
