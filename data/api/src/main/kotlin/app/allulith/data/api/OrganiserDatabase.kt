package app.allulith.data.api

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import app.allulith.data.api.dao.TaskDao
import app.allulith.data.api.dao.UserDao
import app.allulith.data.api.entity.Task
import app.allulith.data.api.entity.User

@Database(
    entities = [User::class, Task::class],
    version = 1,
)
abstract class OrganiserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile
        private var INSTANCE: OrganiserDatabase? = null
        private const val DATABASE_NAME = "organiser-database"

        fun getInstance(context: Context): OrganiserDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    OrganiserDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
