package database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import entities.Contact;
import java.util.ArrayList;
import java.util.List;


public class ContactDB extends SQLiteOpenHelper {

    //static  String SELECT_ALL ="SELECT * FROM ";


    private static String dbName = "contactDB";
    private static String tableName = "contact";
    private static String idColumn = "id";
    private static String NameColum ="name";
    private static String PhoneColum ="phone";
    private Context context;

    public ContactDB(Context context){
        super(context, dbName,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + tableName + "("
                + idColumn + " INTEGER PRIMARY KEY," + NameColum + " TEXT,"
                + PhoneColum + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+tableName);
        onCreate(sqLiteDatabase);
    }

    public List<Contact> findAll(){
        try {
            List<Contact> contacts = new ArrayList<Contact>();
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + "contact", null);
            if(cursor.moveToFirst()){
                do {
                    Contact contact = new Contact();
                    contact.setId(cursor.getInt(0));
                    contact.setName(cursor.getString(1));
                    contact.setPhone(cursor.getString(2));
                    contacts.add(contact);

                } while (cursor.moveToNext());
            }
            sqLiteDatabase.close();
            return contacts;
        }catch (Exception e){
            return null;
        }
    }

    public boolean create(Contact contact){
        try {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(NameColum, contact.getName());
            contentValues.put(PhoneColum, contact.getPhone());
            long rows = sqLiteDatabase.insert(tableName, null, contentValues);

            sqLiteDatabase.close();
            return rows >0;
        }catch (Exception e){
            return false;
        }
    }

    public boolean delete(int id){
        try{
            SQLiteDatabase sqLiteDatabase= getWritableDatabase();
            int raws = sqLiteDatabase.delete(tableName,idColumn + "=?", new String[] {String.valueOf(id)});
            sqLiteDatabase.close();
            return raws > 0;
        }catch (Exception e) {
            return false;
        }
    }

    public Contact find(int id){
        try {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + tableName + " where " + idColumn +"=?", new String[]{String.valueOf(id)});
            Contact contact = null;
            if(cursor.moveToFirst()){
                contact = new Contact();
                contact.setId(cursor.getInt(0));
                contact.setName(cursor.getString(1));
                contact.setPhone(cursor.getString(2));
            }
            sqLiteDatabase.close();
            return contact;

        }catch (Exception e){
            return null;
        }
    }

    public boolean update(Contact contact){
        try{
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(NameColum, contact.getName());
            contentValues.put(PhoneColum, contact.getPhone());
            int rows = sqLiteDatabase.update(tableName, contentValues, idColumn +" = ?", new String[] {String.valueOf(contact.getId())});
            sqLiteDatabase.close();
            return rows >0 ;
        }catch (Exception e){
            return false;
        }
    }
}
