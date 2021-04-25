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
                                /*ItemDAO varItemDao = INSTANCE.itemDAO();

                                Item fish = new Item(1, "Fish", "2x", 2, "Fresh");
                                varItemDao.insert(fish);

                                ListDAO varListDao = INSTANCE.listDAO();
                                ListClass first = new ListClass("Sebastian");
                                varListDao.insert(first);*/
                        });
                }
        };

}
