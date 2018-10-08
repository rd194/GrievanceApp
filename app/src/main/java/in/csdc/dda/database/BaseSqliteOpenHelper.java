package in.csdc.dda.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import in.csdc.dda.application.BaseApplication;

/**
 * @author Rajdeep Yadav
 * Date : 08-Oct-18
 * Time : 12:42 PM
 */
public class BaseSqliteOpenHelper extends SQLiteOpenHelper {

    private static final String TAG = "BaseSqliteOpenHelper";

    private final Context myContext;
    private static BaseSqliteOpenHelper mInstance;
    private static SQLiteDatabase myWritableDb;

    /**
     * Common constants for database and for all tables
     */
    public final static String DB_NAME = "db_echallan";
    public final static int DB_VERSION = 1;

    public final static String DB_COLUMN_ID = "_id";
    public final static String DB_user_ID = "_id";
    public final static String DB_user_firstname = "firstName";
    public final static String DB_user_lastname = "lastName";

    public static final int DB_OPERATION_FAILURE = -1;
    public static final int DB_OPERATION_SUCCESS = 1;


    public final static String[] DB_SQL_DROP_TABLE_QUERIES = new String[]
            {

                    "DROP TABLE IF EXISTS AssignedOfficers",
                    "DROP TABLE IF EXISTS ChallanEntry",
                    "DROP TABLE IF EXISTS ChallanEntryCharge",
                    "DROP TABLE IF EXISTS ChallanEntryDocument",
                    "DROP TABLE IF EXISTS ChallanEntryPeople",
                    "DROP TABLE IF EXISTS ChallanEntryVehicle",
                    "DROP TABLE IF EXISTS ChallanTransaction",
                    "DROP TABLE IF EXISTS Comment",

            };


    public final static String[] DB_SQL_TRUNCATE_TABLE_QUERIES = new String[]
            {

                    "DELETE FROM  AssignedOfficers",
                    "DELETE FROM ChallanEntry",
                    "DELETE FROM ChallanEntryCharge",
                    "DELETE FROM ChallanEntryDocument",
                    "DELETE FROM ChallanEntryPeople",
                    "DELETE FROM ChallanEntryVehicle",
                    "DELETE FROM ChallanTransaction",
                    "DELETE FROM Comment",

            };

    public BaseSqliteOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.myContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
          /*  Log.i( TAG, "Tables creation start.");
            int size = DB_SQL_CREATE_TABLE_QUERIES.length;
            for (int i = 0; i < size; i++ ) {
                db.execSQL(DB_SQL_CREATE_TABLE_QUERIES[i]);
            }
            Log.i( TAG, "Tables creation end.");
*/

            //insert data in tables
         /*   int sizeInsertTables = All_INSERT_Query.length;
            for (int j = 0; j < sizeInsertTables; j++ ) {
                int sizeInsertRows = All_INSERT_Query[j].length;
                Log.i( TAG, "inserting into this table "+All_INSERT_Query[j]);
                for (int k = 0; k < sizeInsertRows; k++ ) {
                    Log.i( TAG, "inserting this row "+All_INSERT_Query[j][k]);
                    db.execSQL(All_INSERT_Query[j][k]);
                }
            }*/
            Log.i(TAG, "insertion creation end.");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        Log.i(TAG, "DB upgrade.");
        int size = DB_SQL_DROP_TABLE_QUERIES.length;
        for (int i = 0; i < size; i++) {
            db.execSQL(DB_SQL_DROP_TABLE_QUERIES[i]);
        }
        onCreate(db);
    }

    public static BaseSqliteOpenHelper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new BaseSqliteOpenHelper(context);
        }
        return mInstance;
    }

    /**
     * Returns a writable database instance in order not to open and close many
     * SQLiteDatabase objects simultaneously
     *
     * @return a writable instance to SQLiteDatabase
     */
    public SQLiteDatabase getMyWritableDatabase() {
        if ((myWritableDb == null) || (!myWritableDb.isOpen())) {
            myWritableDb = this.getWritableDatabase();
        }

        return myWritableDb;
    }

    @Override
    public void close() {
        super.close();
        if (myWritableDb != null) {
            myWritableDb.close();
            myWritableDb = null;


        }
    }


    public static Cursor excuteSelectQuery(String query, String[] arg) {
        Log.d(TAG, "excuteSelectQuery() called with: query = [" + query + "], arg = [" + arg + "]");
        SQLiteDatabase database = BaseApplication.getDbHelper().getMyWritableDatabase();

        Cursor cursor = null;
        try {
            if (database != null) {
                cursor = database.rawQuery(query, arg);
                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    return cursor;
                }

            }
        } catch (SQLiteException se) {
            Log.e(TAG, "excuteSelectQuery: Could not create or Open the database", se);
        } finally {
            if (database != null) {
                database.close();
            }
        }

        return cursor;
    }


    //TODO : info
    public static Cursor excuteSelectNewQuery() {

        SQLiteDatabase database = BaseApplication.getDbHelper().getMyWritableDatabase();

        Cursor cursor = null;
        try {
            if (database != null) {


                //database.query()
                cursor.moveToFirst();
            }
        } catch (SQLiteException se) {
            Log.e(TAG, "excuteSelectQuery: Could not create or Open the database", se);
        } finally {
            if (database != null) {
                database.close();
            }
        }

        return cursor;

    }


    public static int excuteUpdateQuery(String tableName, ContentValues values, String whereClause, String[] whereArgs) {
        Log.d(TAG, "excuteUpdateQuery() called with: tableName = [" + tableName + "], values = [" + values + "]");
        SQLiteDatabase database = BaseApplication.getDbHelper().getMyWritableDatabase();

        int rowId = 0;
        try {
            if (database != null) {
                rowId = database.update(tableName, values, whereClause, whereArgs);
            }
        } catch (SQLiteException se) {
            Log.e(TAG, "excuteSelectQuery: Could not create or Open the database", se);
        } finally {
            if (database != null) {
                database.close();
            }
        }

        return rowId;
    }


    public static Long excuteInsertQuery(String tableName, ContentValues values, String nullColumnHack) {
        Log.d(TAG, "excuteUpdateQuery() called with: tableName = [" + tableName + "], values = [" + values + "], nullColumnHack = [" + nullColumnHack + "]");
        SQLiteDatabase database = BaseApplication.getDbHelper().getMyWritableDatabase();

        Long rowId = null;
        try {
            if (database != null) {
                rowId = database.insert(tableName, nullColumnHack, values);

            }
        } catch (SQLiteException se) {
            Log.e(TAG, "excuteUpdateQuery: Could not create or Open the database", se);
        } finally {
            if (database != null) {
                database.close();
            }
        }

        return rowId;
    }


    public static int excuteDeleteQuery(String tableName, String whereClause, String[] whereArgs) {
        Log.d(TAG, "excuteUpdateQuery() called with: tableName = [" + tableName + "]");
        SQLiteDatabase database = BaseApplication.getDbHelper().getMyWritableDatabase();

        int rowId = 0;
        try {
            if (database != null) {
                rowId = database.delete(tableName, whereClause, whereArgs);
            }
        } catch (SQLiteException se) {
            Log.e(TAG, "excuteDeleteQuery: Could not create or Open the database", se);
        } finally {
            if (database != null) {
                database.close();
            }
        }

        return rowId;
    }


    public static void cleanDatabaseTables() {
        SQLiteDatabase database = BaseApplication.getDbHelper().getMyWritableDatabase();
        int size = DB_SQL_TRUNCATE_TABLE_QUERIES.length;
        for (int i = 0; i < size; i++) {
            database.execSQL(DB_SQL_TRUNCATE_TABLE_QUERIES[i]);
        }
    }


    public static void rawQuery(String query) {
        Log.d(TAG, "rawQuery() called with: query = [" + query + "]");

        SQLiteDatabase database = BaseApplication.getDbHelper().getMyWritableDatabase();

        int rowId = 0;
        try {
            if (database != null) {
                database.execSQL(query);

            }
        } catch (SQLiteException se) {
            Log.e(TAG, "excuteDeleteQuery: Could not create or Open the database", se);
        } finally {
            if (database != null) {
                database.close();
            }
        }
    }
}




