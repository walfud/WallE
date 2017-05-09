package com.walfud.walle.android;

import android.content.ContentResolver;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbUtils {
    public static final String TAG = "DbUtils";

    public static List<Map<String, Object>> get(ContentResolver contentResolver, @RequiresPermission.Read @NonNull Uri uri,
                                                @Nullable String[] projection, @Nullable String selection,
                                                @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor = contentResolver.query(uri, projection, selection, selectionArgs, sortOrder);
        List<Map<String, Object>> result = get(cursor);
        cursor.close();
        return result;
    }

    public static List<Map<String, Object>> get(SQLiteDatabase database, String table,
                                                @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs,
                                                @Nullable String groupBy, @Nullable String having, @Nullable String sortOrder, @Nullable String limit) {
        Cursor cursor = database.query(table, projection, selection, selectionArgs, groupBy, having, sortOrder, limit);
        List<Map<String, Object>> result = get(cursor);
        cursor.close();
        return result;
    }

    public static List<Map<String, Object>> get(Cursor cursor) {
        List<Map<String, Object>> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Map<String, Object> row = new HashMap<>();
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    String key = cursor.getColumnName(i);
                    Object value;
                    switch (cursor.getType(i)) {
                        case Cursor.FIELD_TYPE_INTEGER:
                            value = cursor.getLong(i);
                            break;
                        case Cursor.FIELD_TYPE_FLOAT:
                            value = cursor.getDouble(i);
                            break;
                        case Cursor.FIELD_TYPE_STRING:
                            value = cursor.getString(i);
                            break;
                        case Cursor.FIELD_TYPE_BLOB:
                            value = cursor.getBlob(i);
                            break;
                        case Cursor.FIELD_TYPE_NULL:
                            value = null;
                            break;
                        default:
                            throw new RuntimeException(String.format("Unsupported type: %d", cursor.getType(i)));
                    }
                    row.put(key, value);
                }
                result.add(row);
            } while (cursor.moveToNext());
        }
        return result;
    }
}
