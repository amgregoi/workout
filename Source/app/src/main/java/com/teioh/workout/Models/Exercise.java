package com.teioh.workout.Models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Exercise
{
    //Database ID
    @Id
    private Long _id;

    //Name of exercise
    @Property( nameInDb = "name" )
    public String name;

    //Abbreviate of exercise (up to 3 characters)
    @Property( nameInDb = "shortName" )
    public String shortName;

    @Property( nameInDb = "exerciseType" )
    public String exerciseType;

    @Generated(hash = 1827516563)
    public Exercise(Long _id, String name, String shortName, String exerciseType) {
        this._id = _id;
        this.name = name;
        this.shortName = shortName;
        this.exerciseType = exerciseType;
    }

    @Generated(hash = 1537691247)
    public Exercise() {
    }

    public Long get_id() {
        return this._id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return this.shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getExerciseType() {
        return this.exerciseType;
    }

    public void setExerciseType(String exerciseType) {
        this.exerciseType = exerciseType;
    }

}
