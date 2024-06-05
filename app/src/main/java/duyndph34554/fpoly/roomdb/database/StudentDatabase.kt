package duyndph34554.fpoly.roomdb.database

import androidx.room.Database
import androidx.room.RoomDatabase
import duyndph34554.fpoly.roomdb.DAO.StudentDao
import duyndph34554.fpoly.roomdb.model.StudentModel

@Database(entities = arrayOf(StudentModel::class), version = 1)
abstract class StudentDatabase : RoomDatabase() {
    abstract fun studentDao(): StudentDao
}