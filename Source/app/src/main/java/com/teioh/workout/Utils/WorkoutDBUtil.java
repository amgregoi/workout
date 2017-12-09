package com.teioh.workout.Utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.teioh.workout.Models.DaoMaster;
import com.teioh.workout.Models.DaoSession;
import com.teioh.workout.Models.Exercise;
import com.teioh.workout.Models.ExerciseDao;
import com.teioh.workout.WorkoutApplication;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Locale;

//import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class WorkoutDBUtil extends SQLiteOpenHelper
{
    public final static String TAG = WorkoutDBUtil.class.getSimpleName();

    public final static int DATABASE_VERSION = 1;
    private final static String DB_PATH = "/data/data/com.teioh.workout/databases/";
    private final static String DB_NAME = "Workout.db";
    private static WorkoutDBUtil aInstance;
    private Context myContext;
    private DaoSession mDaoSession;

    public WorkoutDBUtil(Context context)
    {
        super(context, DB_NAME, null, DATABASE_VERSION);
        myContext = context;
    }

    public static synchronized WorkoutDBUtil getInstance()
    {
        if (aInstance == null)
        {
            aInstance = new WorkoutDBUtil(WorkoutApplication.getInstance());
        }
        return aInstance;
    }

    public void onCreate(SQLiteDatabase db)
    {
//        cupboard().withDatabase(db).createTables();
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
//        cupboard().withDatabase(db).upgradeTables();
    }

    public void createDatabase()
    {
        createDB();
        mDaoSession = new DaoMaster(getWritableDatabase()).newSession();
    }

    private void createDB()
    {
        boolean dbExist = DBExists();
        if (!dbExist)
        {
            this.getReadableDatabase();
            copyDBFromResource();
        }
    }

    private boolean DBExists()
    {
        SQLiteDatabase db = null;

        try
        {
            File database = myContext.getDatabasePath(DB_NAME);
            if (database.exists())
            {
                db = SQLiteDatabase.openDatabase(database.getPath(), null, SQLiteDatabase.OPEN_READWRITE);
                db.setLocale(Locale.getDefault());
                db.setVersion(1);
            }
        }
        catch (Exception e)
        {
            Log.e("SqlHelper", "database not found");
        }

        if (db != null)
        {
            db.close();
        }
        return db != null;
    }

    private void copyDBFromResource()
    {
        String dbFilePath = DB_PATH + DB_NAME;
        try
        {
            InputStream inputStream = WorkoutApplication.getInstance().getAssets().open(DB_NAME);
            OutputStream outStream = new FileOutputStream(dbFilePath);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0)
            {
                outStream.write(buffer, 0, length);
            }

            outStream.flush();
            outStream.close();
            inputStream.close();
        }
        catch (IOException e)
        {
            throw new Error("Problem copying database from resource file.: " + e.getMessage());
        }
    }

    /**
     * Helper functions
     */

    public List<Exercise> getExerciseListType(String aType)
    {
        ExerciseDao exerciseDao = mDaoSession.getExerciseDao();
        return exerciseDao.queryRaw("WHERE exerciseType = ?", aType);
    }

//    public List<String> getSessionList()
//    {
//        SessionDao lSessionDao = mDaoSession.getSessionDao();
//        return lSessionDao.loadAll();
//    }
//
//    public void addSession(Session aSession)
//    {
//        SessionDao lSessionDao = mDaoSession.getSessionDao();
//        lSessionDao.insert(aSession);
//    }
//
//    public Session getSession(long aID)
//    {
//        SessionDao lSessionDao = mDaoSession.getSessionDao();
//        return lSessionDao.load(aID);
//    }
//
//    public void updateSession(Session aSession)
//    {
//        SessionDao lSessionDao = mDaoSession.getSessionDao();
//        lSessionDao.update(aSession);
//    }
}