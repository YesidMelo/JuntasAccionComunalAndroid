package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.listaConvocatoriasReuniones.helpers

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.listaConvocatoriasReuniones.adapters.ListaConvocatoriasParaPDFAdapter
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.reunionParaConvocatoriaPDF.ReunionParaGenerarConvocatoriaPDFModel

class HelperRecyclerListaReunionesParaConvocatoria {

    //region variables
    private lateinit var recyclerView: RecyclerView
    private lateinit var listaConvocatoriasParaPDFAdapter: ListaConvocatoriasParaPDFAdapter
    private lateinit var listaReuniones: List<ReunionParaGenerarConvocatoriaPDFModel>
    private lateinit var itemSeleccionado: (ReunionParaGenerarConvocatoriaPDFModel) -> Unit
    //endregion

    fun conRecyclerView(recyclerView: RecyclerView) : HelperRecyclerListaReunionesParaConvocatoria {
        this.recyclerView = recyclerView
        return this
    }

    fun conListaReuniones(listaReuniones: List<ReunionParaGenerarConvocatoriaPDFModel>) : HelperRecyclerListaReunionesParaConvocatoria {
        this.listaReuniones = listaReuniones
        return this
    }

    fun conItemSeleccionado(itemSeleccionado: (ReunionParaGenerarConvocatoriaPDFModel) -> Unit) : HelperRecyclerListaReunionesParaConvocatoria {
        this.itemSeleccionado = itemSeleccionado
        return this
    }

    fun cargarRecycler() {
        listaConvocatoriasParaPDFAdapter = ListaConvocatoriasParaPDFAdapter(
            listaConvocatorias = listaReuniones,
            itemSeleccionado = itemSeleccionado
        )

        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = listaConvocatoriasParaPDFAdapter
    }
}