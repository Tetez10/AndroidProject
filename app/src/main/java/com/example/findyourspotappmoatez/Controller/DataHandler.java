package com.example.findyourspotappmoatez.Controller;

import static com.example.findyourspotappmoatez.Controller.Utils.TABLE_NAME;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.findyourspotappmoatez.model.Spot;

import java.util.ArrayList;
import java.util.List;

public class DataHandler extends SQLiteOpenHelper {
    public DataHandler(@Nullable Context context) {
        super(context, Utils.DATABASE_NAME, null, Utils.DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PLACES_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                Utils.KEY_ID + " INTEGER PRIMARY KEY," +
                Utils.LATITUDE + " TEXT," +
                Utils.LONGITUDE + " TEXT," +
                Utils.TITLE + " TEXT)" ;

        db.execSQL(CREATE_PLACES_TABLE);

}

    @Override
    public void onUpgrade(SQLiteDatabase db, int Oldversion, int Newversion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
   public Cursor GetAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }
    public void addPlace(Spot place){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Utils.LATITUDE , place.getPlatitude());
        contentValues.put(Utils.LONGITUDE , place.getPlongitude());
        contentValues.put(Utils.TITLE , place.getTitle());
        database.insert(TABLE_NAME,null,contentValues);
        database.close();
    }
    public  List<Spot> getAllPlaces(){
        SQLiteDatabase database = this.getReadableDatabase();
        List<Spot> placeList = new ArrayList<>();
        String getAll = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = database.rawQuery(getAll,null);
        if(cursor.moveToFirst()){
            do{
                Spot place = new Spot();
                place.setId(cursor.getInt(0));
                place.setPlatitude(cursor.getString(1));
                place.setPlongitude(cursor.getString(2));
                place.setTitle(cursor.getString(3));

                placeList.add(place);
            }while (cursor.moveToNext());
        }
        return  placeList;
    }

}
