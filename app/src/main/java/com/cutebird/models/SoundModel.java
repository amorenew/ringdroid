package com.cutebird.models;

import com.cutebird.database.SoundDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.List;

/**
 * Created by islam on 3/29/2016.
 */
@Table(database = SoundDatabase.class)
public class SoundModel extends BaseModel {

    private static SoundModel instance;
    @PrimaryKey(autoincrement = true)
    private int id;
    @Column
    private String name;
    @Column
    private String fileName;

    public static SoundModel getInstance() {
        if (instance == null) {
            instance = new SoundModel();
        }
        return instance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<SoundModel> getSoundModels() {
        List<SoundModel> models = SQLite.select().from(SoundModel.class).queryList();
        return models;
    }


}
