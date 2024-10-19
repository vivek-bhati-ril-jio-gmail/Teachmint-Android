package com.vivek.githubassignment.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vivek.githubassignment.data.model.Repo
import androidx.lifecycle.LiveData

@Dao
interface RepoDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertAll(repos: List<Repo>)
//
//    @Query("SELECT * FROM repos LIMIT 15")
//    fun getAllReposLive(): LiveData<List<Repo>>
}

