# DataBaseStructureWithApiCalling
        This is a base structure by that structure you do not need to code to create a database Class
        or Not need to write code for table and it’s operation like insert, update, delete, find. Find
        all etc. and not to write code for call API and get response just set API and get response.

### Create A Table.
        To Create a Table you Just inherite the BaseModle class just like the example and you need to
        create your class members like SubModle in example only one PK member you shoulde use in the class
        and Create all methods and Constractor like SubModle class use. you can use extra veriables and
        method but is you don't wan't to add members in table column you just use simple String and int.

### Add Tables in DataBase
        To add Table in DataBase Open DatabaseHandler class and add your Modles like SubModle class
        added in dbSchema just write line like this.

```sh
put(ModelName.getInstance().getTableName(),ModelName.getInstance().getAllDBFileds());
```

**Don't forget to create _instance and getInstance in your Model class like in SubModle class.**

# API Call
        For API calling you need you add your apis in Request Class you can add base API middle API and User
        Token for set API you have to create a object of ApiRequest Class and put required parameters in
        constructor like API URL if you already set base and middle url you can just add end part of URL
        (/login,/registration etc.) then put method for call (like GET,POST etc.) then true if you want to
        add Authuntication Token and you already set it. and so on the last perameter is a enum you have to
        set to identifie called URL.

### APIServiceAsyncTask

    APIServiceAsyncTask is a class you have to inherit this class and over right success and failer method
    and fill the perameters of constructor that is context, object of API request class which api you want
    to call and if you have post api then Hashmet of parameters.please check example for better understanding.