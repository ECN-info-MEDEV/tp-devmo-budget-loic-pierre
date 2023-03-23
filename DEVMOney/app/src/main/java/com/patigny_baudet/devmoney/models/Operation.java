package com.patigny_baudet.devmoney.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

/**
 * Operation entity.
 */
@Entity(foreignKeys = {@ForeignKey(entity=Category.class, parentColumns="id", childColumns="category_id")})
public class Operation {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;
    private String name;
    private String description;
    private String date;
    private float amount;
    @ColumnInfo(name = "category_id", index=true)
    private long category_id;

    public Operation(String name, String description, String date, float amount, long category_id) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.amount = amount;
        this.category_id = category_id;
    }

    // --- GETTER ---
    public long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getDate() { return date; }
    public float getAmount() { return amount; }
    public long getCategory_id() {return category_id; }

    // --- SETTER ---
    public void setId(long id) { this.id = id; }
}
