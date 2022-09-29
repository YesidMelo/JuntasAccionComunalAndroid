package com.hefesto.juntasaccioncomunal.fuentesDatos.cache

import androidx.lifecycle.MutableLiveData

interface MemoriaCache {
    fun AdicionarActualizarElemento(par : Pair<String, Any?>)
    fun EliminarElemento(llave : String)
    fun TraerLiveDataDelCache() : MutableLiveData<MutableMap<String, Any?>>
}

class MemoriaCacheImpl private constructor(): MemoriaCache {

    private var elementosEnMemoria : MutableLiveData<MutableMap<String, Any?>> = MutableLiveData()

    init {
        elementosEnMemoria.value = emptyMap<String, Any?>().toMutableMap()
    }

    override fun AdicionarActualizarElemento(par : Pair<String, Any?>) {
        if (elementosEnMemoria.value == null) throw SinMapaEnLivedataException()
        if (elementosEnMemoria.value!!.containsKey(par.first)) return
        elementosEnMemoria.value!![par.first] = par.second
    }

    override fun EliminarElemento(llave : String) {
        if (elementosEnMemoria.value == null) throw SinMapaEnLivedataException()
        if (!elementosEnMemoria.value!!.containsKey(llave)) return
        elementosEnMemoria.value!!.remove(llave)
    }

    override fun TraerLiveDataDelCache(): MutableLiveData<MutableMap<String, Any?>> = elementosEnMemoria

    companion object {
        private var instance : MemoriaCache? = null

        fun traerInstancia() : MemoriaCache{
            if (instance == null) instance = MemoriaCacheImpl()
            return instance!!
        }
    }
}