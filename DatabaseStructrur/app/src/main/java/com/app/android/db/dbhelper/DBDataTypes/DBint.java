package com.app.android.db.dbhelper.DBDataTypes;

/**
 * Created by rails-dev on 17/3/16.
 */
public class DBint extends BaseType{
      int fieldDb;

    public DBint() {}

    public DBint(int field, boolean pkValue) {
        this.fieldDb = field;
        this.pkValueDb = pkValue;
    }

    public DBint(boolean pkValue) {
        this.pkValueDb = pkValue;
    }

    public void setFieldDb(int fieldDb) {
        this.fieldDb = fieldDb;
    }

    @Override
    public Object getFieldDb() {
        return fieldDb;
    }

    public DBint(int field) {
        this.fieldDb = field;
    }

    @Override
    public String getFieldType(){
        return INT_TY;
    }
}
