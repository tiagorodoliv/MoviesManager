package br.ifsp.moviesmanager.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie (
    @PrimaryKey
    var nome:String,
    var releaseYear:String,
    var studio:String,
    var duration:String,
    var flag:Int,
    var note:String,
    var gender:String,
)