# DataBaseStructureWithApiCalling

        This is a base structure by that structure you do not need to code to create a database Class or Not need to write code for table and it’s operation like insert, update, delete, find. Find all etc. and not to write code for call API and get response just set API and get response.

 ### Create A Table.
        To Create a Table you Just inherit the BaseModle class just like the example and you need to create your class members like SubModle in example only one PK member you should use in the class and Create all methods and Constructor like SubModle class use. you can use extra variables and method but is you don't wan't to add members in table column you just use simple String and int.

### Add Tables in DataBase
        To add Table in DataBase Open DatabaseHandler class and add your Modles like SubModle class added in dbSchema just write line like this.

```sh
put(ModelName.getInstance().getTableName(),ModelName.getInstance().getAllDBFileds());
```

**Don't forget to create _instance and getInstance in your Model class like in SubModle class.**