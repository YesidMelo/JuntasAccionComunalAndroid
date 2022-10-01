package com.hefesto.juntasaccioncomunal.fuentesDatos.cache

import androidx.lifecycle.MutableLiveData

interface MemoriaCache {
    fun AdicionarActualizarElemento(par : Pair<ElementoEnCache, Any?>)
    fun EliminarElemento(llave : ElementoEnCache)
    fun TraerLiveDataDelCache() : MutableLiveData<MutableMap<ElementoEnCache, Any?>>
    fun <T>traerObjeto(llave: ElementoEnCache): T?
}

interface ElementoEnCache

class MemoriaCacheImpl private constructor(): MemoriaCache {

    private var elementosEnMemoria : MutableLiveData<MutableMap<ElementoEnCache, Any?>> = MutableLiveData()

    init {
        elementosEnMemoria.value = emptyMap<ElementoEnCache, Any?>().toMutableMap()
    }

    override fun AdicionarActualizarElemento(par : Pair<ElementoEnCache, Any?>) {
        if (elementosEnMemoria.value == null) throw SinMapaEnLivedataException()
        if (elementosEnMemoria.value!!.containsKey(par.first)) return
        elementosEnMemoria.value!![par.first] = par.second
    }

    override fun EliminarElemento(llave : ElementoEnCache) {
        if (elementosEnMemoria.value == null) throw SinMapaEnLivedataException()
        if (!elementosEnMemoria.value!!.containsKey(llave)) return
        elementosEnMemoria.value!!.remove(llave)
    }

    override fun TraerLiveDataDelCache(): MutableLiveData<MutableMap<ElementoEnCache, Any?>> = elementosEnMemoria

    override fun <T> traerObjeto(llave: ElementoEnCache): T? {
        if (elementosEnMemoria.value == null) return null
        if(!elementosEnMemoria.value!!.containsKey(llave)) return null
        return elementosEnMemoria.value!![llave] as T
    }

    companion object {
        private var instance : MemoriaCache? = null

        fun traerInstancia() : MemoriaCache{
            if (instance == null) instance = MemoriaCacheImpl()
            return instance!!
        }
    }
}