package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.agendarReunion.helpers

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.agendarReunion.adapters.ListaPuntosReunionAgendarAdapter
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.PuntoReunionAgendarReunionAsambleaModel

class HelperRecyclerViewAgendarReunionListaPuntos {
    //region variables
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ListaPuntosReunionAgendarAdapter
    private var listaPuntosReunion = emptyList<PuntoReunionAgendarReunionAsambleaModel>().toMutableList()
    //endregion

    fun conRecyclerView(recyclerView: RecyclerView) : HelperRecyclerViewAgendarReunionListaPuntos {
        this.recyclerView = recyclerView
        return this
    }

    fun adicionarPunto(punto: PuntoReunionAgendarReunionAsambleaModel) {
        listaPuntosReunion.add(punto)
        cargarRecycler()
    }

    fun cargarRecycler() {
        adapter = ListaPuntosReunionAgendarAdapter(listaPuntos = listaPuntosReunion, eliminarItem = ::eliminarPunto)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.setHasFixedSize(true)
    }

    fun traerListaPuntos() = listaPuntosReunion

    //region metodos privados
    private fun eliminarPunto(punto: PuntoReunionAgendarReunionAsambleaModel) {
        listaPuntosReunion.remove(punto)
        cargarRecycler()
    }
    //endregion
}