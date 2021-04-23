package com.example.groceryshoppinglist;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(
        entities = {
                User.class,
                ListUser.class,
                ListClass.class,
                Item.class
        },
        version = 1,
        exportSchema = false
)
public abstract class GroceryDatabase extends RoomDatabase {
        private static volatile GroceryDatabase INSTANCE;

        // Setup abstract DAOs
        public abstract UserDAO userDAO();
        public abstract ListUserDAO listUserDAO();
        public abstract ListDAO listDAO();
        public abstract ItemDAO itemDAO();

        // Create database if it doesn't exist
        public static GroceryDatabase getDatabase(Context context) {
                if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                GroceryDatabase.class, "database")
                                .addCallback(sRoomDatabaseCallback)
                                .build();
                }
                return INSTANCE;
        }


        // Write data to table
        private static final int NUMBER_OF_THREADS = 4;
        static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

        private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);

                        databaseWriteExecutor.execute(() -> {
                                // Populate the database in the background.
                                // If you want to start with more words, just add them.
                                ItemDAO varItemDao = INSTANCE.itemDAO();

                                Item fish = new Item("Fish", "2x", 2, "Fresh");
                                varItemDao.insert(fish);
                        });
                }
        };

}
/*
@Database(entities = {Item.class}, version = 1, exportSchema = false)
public abstract class RoomDatabase extends androidx.room.RoomDatabase {

    public abstract ItemDAO itemDao();

    private static volatile RoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static RoomDatabase getDatabase(final Context context){
        if (INSTANCE == null) {
            synchronized (RoomDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RoomDatabase.class, "database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                ItemDAO dao = INSTANCE.itemDao();


                Item fish = new Item(1L, "Fish", "2x", 2, "Fresh");
                dao.insert(fish);

            });
        }
    };

}
*/