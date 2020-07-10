package com.walfud.walle.android

import android.content.ContentResolver
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import java.util.*

object DbUtils {
    const val TAG = "DbUtils"

    fun query(contentResolver: ContentResolver, uri: Uri,
              projection: Array<String>?, selection: String?,
              selectionArgs: Array<String>?, sortOrder: String?): List<Map<String, Any?>> {

        return contentResolver.query(uri, projection, selection, selectionArgs, sortOrder).use {
            query(it!!)
        }
    }

    fun query(database: SQLiteDatabase, table: String,
              projection: Array<String>? = null, selection: String? = null, selectionArgs: Array<String>? = null,
              groupBy: String? = null, having: String? = null, sortOrder: String? = null, limit: String? = null): List<Map<String, Any?>> {
        val cursor = database.query(table, projection, selection, selectionArgs, groupBy, having, sortOrder, limit)
        val result = query(cursor)
        cursor.close()
        return result
    }

    fun query(cursor: Cursor): List<Map<String, Any?>> {
        val result = ArrayList<Map<String, Any?>>()
        if (cursor.moveToFirst()) {
            do {
                val row = HashMap<String, Any?>()
                for (i in 0..cursor.columnCount - 1) {
                    val key = cursor.getColumnName(i)
                    val value: Any?
                    when (cursor.getType(i)) {
                        Cursor.FIELD_TYPE_INTEGER -> value = cursor.getLong(i)
                        Cursor.FIELD_TYPE_FLOAT -> value = cursor.getDouble(i)
                        Cursor.FIELD_TYPE_STRING -> value = cursor.getString(i)
                        Cursor.FIELD_TYPE_BLOB -> value = cursor.getBlob(i)
                        Cursor.FIELD_TYPE_NULL -> value = null
                        else -> throw RuntimeException(String.format("Unsupported type: %d", cursor.getType(i)))
                    }
                    row.put(key, value)
                }
                result.add(row)
            } while (cursor.moveToNext())
        }
        return result
    }
}
