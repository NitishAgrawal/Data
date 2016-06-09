package com.app.android.db.dbhelper.DBDataTypes;


public class DBString extends BaseType{
      String fieldDb;

    public DBString() {
    }

    public DBString(String field, boolean pkValue) {
        this.fieldDb = field;
        this.pkValueDb = pkValue;
    }

    public DBString(boolean pkValue) {
        this.pkValueDb = pkValue;
    }

    public DBString(String field) {
        this.fieldDb = field;
    }

    public void setFieldDb(String fieldDb) {
        this.fieldDb = fieldDb;
    }

    @Override
    public Object getFieldDb() {
        return fieldDb;
    }

    @Override
    public String getFieldType(){
        return STRING_TY;
    }
}


/*

public class DBString extends BaseType{
    String field;

    public DBString() {
    }

    public DBString(String field, boolean pkValue) {
        this.field = field;
        this.pkValue = pkValue;
    }

    public DBString(boolean pkValue) {
        super(pkValue);
    }

    public void setField(String field) {
        this.field = field;
    }

    public DBString(String field) {
        this.field = field;
    }

    @Override
    public String getFieldType(){
        return "String";
    }
}
* */