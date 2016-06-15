package com.cutebird;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by islam on 3/29/2016.
 */

/**
 * Database config class
 */
@Database(name = SoundDatabase.NAME, version = SoundDatabase.VERSION)
public class SoundDatabase {
    //database name without .db or .sqlite
    public static final String NAME = "SoundDatabase";
    //database version number
    public static final int VERSION = 1;

}
