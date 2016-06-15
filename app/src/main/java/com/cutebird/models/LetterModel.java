package com.cutebird.models;

import com.cutebird.database.SoundDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.List;

/**
 * Created by TCIG_PC_54 on 6/15/2016.
 */
@Table(database = SoundDatabase.class)
public class LetterModel extends BaseModel {
    private static LetterModel instance;
    @PrimaryKey(autoincrement = true)
    private int id;
    @Column
    private String name;
    @Column
    private String language;
    @Column
    private String description;

    public static LetterModel getInstance() {
        if (instance == null) {
            instance = new LetterModel();
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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<LetterModel> getLetterModels() {
        List<LetterModel> models = SQLite.select().from(LetterModel.class).queryList();
        return models;
    }
}
