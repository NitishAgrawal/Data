package com.app.android.db.dbhelper;

import android.database.Cursor;

public class SubModel extends BaseModel {

     //Every Database column should be db field like DBint, DBString etc. and other use normal ;
        public DBPKAutoint id;
        public DBString url;
        public DBString category_language;
        public DBboolean is_deleted;
        public DBString created_at;
        public DBString updated_at;
        public DBString category_name;
        public DBString sequence_no;

        public SubModel() {
                this.id= makePKAutoInt();
                this.url = makeStr();
                this.category_language = makeStr();
                this.is_deleted = makeBoolean();
                this.created_at = makeStr();
                this.updated_at = makeStr();
                this.category_name = makeStr();
                this.sequence_no = makeStr();
        }

        public SubModel(int id, String url, String category_language, boolean isDeleted, String createdAt, String updatedAt,
                        String subCategoryName, String seuenceNo) {
                this.id= makePKAutoInt(id);
                this.url = makeStr(url);
                this.category_language = makeStr(category_language);
                this.is_deleted = makeBoolean(isDeleted);
                this.created_at = makeStr(createdAt);
                this.updated_at = makeStr(updatedAt);
                this.category_name = makeStr(subCategoryName);
                this.sequence_no = makeStr(seuenceNo);
        }

        public SubModel(Cursor cursor) {
                super(cursor);
        }

    @Override
    protected BaseModel getInstanse(Cursor cursor) {
        return new SubModel(cursor);
    }

    public static SubModel _instance = new SubModel();

        public static SubModel getInstance(){
                return _instance;
        }
}
