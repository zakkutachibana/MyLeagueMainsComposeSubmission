package com.zak.myleaguemainscompose.data

import com.zak.myleaguemainscompose.model.ChampData
import com.zak.myleaguemainscompose.model.Champions
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ChampRepository {

    private val champions = mutableListOf<Champions>()

    init {
        if (champions.isEmpty()) {
            ChampData.champs.forEach {
                champions.add(Champions(it, 0))
            }
        }
    }


    fun getAllChampions(): Flow<List<Champions>> {
        return flowOf(champions)
    }

    fun getChampionById(id: Long): Champions {
        return champions.first {
            it.champ.id == id
        }
    }


    companion object {
        @Volatile
        private var instance: ChampRepository? = null

        fun getInstance(): ChampRepository =
            instance ?: synchronized(this) {
                ChampRepository().apply {
                    instance = this
                }
            }
    }
}