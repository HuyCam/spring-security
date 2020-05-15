# Spring security with database
How to tell spring security how your data is structured?
use DatasSource with @Autowired annotation

`
@Autowired
DataSource dataSource	
` 

When you add and database in your application, spring boot is smart enough to create a **datasource** for you.

Spring security have a default authority schema

