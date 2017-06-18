package com.vcooline.li.videoplayer.databases;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.vcooline.li.videoplayer.bean.SketchBean;

import java.util.ArrayList;

/**
 * Created by Trace on 2014/8/21.
 */
public class SketchDBManager {
    private DBHelper helper;
    private SQLiteDatabase db;
    private Context context;

    public SketchDBManager(Context mcontext){
        this.context = mcontext;
        //因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0, mFactory);
        //所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里

    }

    public void saveSketch(SketchBean bean){
        try{
        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
        db.beginTransaction();  //开始事务

            db.execSQL("INSERT INTO sketch VALUES(null,?,?,?, ?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?)",
                    new Object[]{bean.getId(),bean.getMaterialName(),bean.getMaterialPath(),bean.getMaterialKey(),bean.getMaterialType(),
                    bean.getMaterialClasses(),bean.getMaterialSubClasses(),bean.isMediaImage(),bean.getThumbnailName(),bean.getThumbnailPath(),
                    bean.getFinishedName(),bean.getFinishedPath(),bean.getFinishedHdPath(),bean.getSortId(),bean.getClassSort(),
                            bean.getUploadedAt(),bean.getCreateAt(),bean.getUpdateAt(),bean.getStatus()});

            db.setTransactionSuccessful();  //设置事务成功完成

            Log.v("lijing","save db success");
            db.endTransaction();    //结束事务
            helper.close();
            db.close();
        }catch(Exception e){
            helper.close();
            if(db!=null) {
                db.close();
            }
        }
    }

    public ArrayList<SketchBean> getSketchBean(){

        ArrayList<SketchBean> list = new ArrayList<SketchBean>();
        try{
         helper = new DBHelper(context);
        db = helper.getWritableDatabase();
//        db.beginTransaction();;
            Cursor localCursor = db.rawQuery("select * from sketch",null);
            while (localCursor.moveToNext())
            {
                SketchBean temp=new SketchBean();
                temp.setId(localCursor.getString(localCursor.getColumnIndex("id")));
                temp.setMaterialName(localCursor.getString(localCursor.getColumnIndex("materialName")));
                temp.setMaterialPath(localCursor.getString(localCursor.getColumnIndex("materialPath")));
                temp.setMaterialKey(localCursor.getString(localCursor.getColumnIndex("materialKey")));
                temp.setMaterialType(localCursor.getString(localCursor.getColumnIndex("materialType")));
                temp.setMaterialClasses(localCursor.getString(localCursor.getColumnIndex("materialClasses")));
                temp.setMaterialSubClasses(localCursor.getString(localCursor.getColumnIndex("materialSubClasses")));
                temp.setMediaImage(localCursor.getString(localCursor.getColumnIndex("isMediaImage")));
                temp.setThumbnailName(localCursor.getString(localCursor.getColumnIndex("thumbnailName")));
                temp.setThumbnailPath(localCursor.getString(localCursor.getColumnIndex("thumbnailPath")));
                temp.setFinishedName(localCursor.getString(localCursor.getColumnIndex("finishedName")));
                temp.setFinishedPath(localCursor.getString(localCursor.getColumnIndex("finishedPath")));
                temp.setFinishedHdName(localCursor.getString(localCursor.getColumnIndex("finishedHdName")));
                temp.setFinishedHdPath(localCursor.getString(localCursor.getColumnIndex("finishedHdPath")));
                temp.setSortId(localCursor.getString(localCursor.getColumnIndex("sortId")));
                temp.setClassSort(localCursor.getString(localCursor.getColumnIndex("classSort")));
                temp.setUploadedAt(localCursor.getString(localCursor.getColumnIndex("uploadedAt")));
                temp.setCreateAt(localCursor.getString(localCursor.getColumnIndex("createAt")));
                temp.setUpdateAt(localCursor.getString(localCursor.getColumnIndex("updateAt")));
                temp.setStatus(localCursor.getString(localCursor.getColumnIndex("status")));
                list.add(temp);
            }

            localCursor.close();
//            db.endTransaction();    //结束事务
//            helper.close();
            db.close();
        }catch(Exception e){
//            helper.close();
            if(db!=null) {
                db.close();
            }
        }

        return list;
    }


    public SketchBean getSketchBeanById(String id){

        ArrayList<SketchBean> list = new ArrayList<SketchBean>();
        try{
            helper = new DBHelper(context);
            db = helper.getWritableDatabase();
//        db.beginTransaction();;

            Cursor localCursor = db.rawQuery("select * from sketch WHERE id = ?",new String[]{id});

            while (localCursor.moveToNext())
            {
                SketchBean temp=new SketchBean();
                temp.setId(localCursor.getString(localCursor.getColumnIndex("id")));
                temp.setMaterialName(localCursor.getString(localCursor.getColumnIndex("materialName")));
                temp.setMaterialPath(localCursor.getString(localCursor.getColumnIndex("materialPath")));
                temp.setMaterialKey(localCursor.getString(localCursor.getColumnIndex("materialKey")));
                temp.setMaterialType(localCursor.getString(localCursor.getColumnIndex("materialType")));
                temp.setMaterialClasses(localCursor.getString(localCursor.getColumnIndex("materialClasses")));
                temp.setMaterialSubClasses(localCursor.getString(localCursor.getColumnIndex("materialSubClasses")));
                temp.setMediaImage(localCursor.getString(localCursor.getColumnIndex("isMediaImage")));
                temp.setThumbnailName(localCursor.getString(localCursor.getColumnIndex("thumbnailName")));
                temp.setThumbnailPath(localCursor.getString(localCursor.getColumnIndex("thumbnailPath")));
                temp.setFinishedName(localCursor.getString(localCursor.getColumnIndex("finishedName")));
                temp.setFinishedPath(localCursor.getString(localCursor.getColumnIndex("finishedPath")));
                temp.setFinishedHdName(localCursor.getString(localCursor.getColumnIndex("finishedHdName")));
                temp.setFinishedHdPath(localCursor.getString(localCursor.getColumnIndex("finishedHdPath")));
                temp.setSortId(localCursor.getString(localCursor.getColumnIndex("sortId")));
                temp.setClassSort(localCursor.getString(localCursor.getColumnIndex("classSort")));
                temp.setUploadedAt(localCursor.getString(localCursor.getColumnIndex("uploadedAt")));
                temp.setCreateAt(localCursor.getString(localCursor.getColumnIndex("createAt")));
                temp.setUpdateAt(localCursor.getString(localCursor.getColumnIndex("updateAt")));
                temp.setStatus(localCursor.getString(localCursor.getColumnIndex("status")));
                list.add(temp);
            }

            localCursor.close();
//            db.endTransaction();    //结束事务
//            helper.close();
            db.close();
        }catch(Exception e){
//            helper.close();
            if(db!=null) {
                db.close();
            }
        }

        if(list.size()>0){
            return list.get(0);
        }else{
            return null;
        }

    }

//    public void updateAge(Person person) {
//        ContentValues cv = new ContentValues();
//        cv.put("age", person.age);
//        db.update("person", cv, "name = ?", new String[]{person.name});
//    }
}
