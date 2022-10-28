package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.generarActaPdf.helpers

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.generarActaPdf.adapters.ListaActasParaPDFAdapter
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.actasParaPDF.ReunionParaGenerarPDFModel

class HelperRecyclerListaActasReunionesParaPDF {

    //region variables
    private lateinit var recyclerView: RecyclerView
    private lateinit var listaActas : List<ReunionParaGenerarPDFModel>
    private lateinit var adapter: ListaActasParaPDFAdapter
    private lateinit var escuchadorItemSeleccionado : (ReunionParaGenerarPDFModel)->Unit
    //endregion

    fun conRecyclerView(recyclerView: RecyclerView) : HelperRecyclerListaActasReunionesParaPDF {
        this.recyclerView = recyclerView
        return this
    }

    fun conListaActas(listaActas : List<ReunionParaGenerarPDFModel>) : HelperRecyclerListaActasReunionesParaPDF {
        this.listaActas = listaActas
        return this
    }

    fun conEscuchadorItemSeleccionado(escuchadorItemSeleccionado : (ReunionParaGenerarPDFModel)->Unit) : HelperRecyclerListaActasReunionesParaPDF {
        this.escuchadorItemSeleccionado = escuchadorItemSeleccionado
        return this
    }

    fun cargarRecycler() {
        adapter = ListaActasParaPDFAdapter(
            reunionSeleccionado = escuchadorItemSeleccionado,
            listaActasParaPDF = listaActas
        )

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.adapter = adapter
    }

}