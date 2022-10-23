package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.listaReunionesCreacionActa.helpers

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.listaReunionesCreacionActa.adapters.ListaReunionesCreacionActasAdapter
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.crearActa.ReunionAsambleaCreacionActaModel

class HelperRecyclerViewListaReunionesCrearActa {

    //region variables
    private lateinit var recyclerView: RecyclerView
    private lateinit var listaReuniones: List<ReunionAsambleaCreacionActaModel>
    private lateinit var escuchadorReunionSeleccionada : (ReunionAsambleaCreacionActaModel) -> Unit
    private lateinit var adapter: ListaReunionesCreacionActasAdapter
    //endregion

    fun conRecycler(recyclerView: RecyclerView) : HelperRecyclerViewListaReunionesCrearActa {
        this.recyclerView = recyclerView
        return this
    }

    fun conListaReuniones(listaReuniones: List<ReunionAsambleaCreacionActaModel>) : HelperRecyclerViewListaReunionesCrearActa {
        this.listaReuniones = listaReuniones
        return this
    }

    fun conEscuchadorReunionSeleccionada(escuchadorReunionSeleccionada : (ReunionAsambleaCreacionActaModel) -> Unit) : HelperRecyclerViewListaReunionesCrearActa {
        this.escuchadorReunionSeleccionada = escuchadorReunionSeleccionada
        return this
    }

   fun llenarRecycler() {
       adapter = ListaReunionesCreacionActasAdapter(listaReuniones = listaReuniones, reunionSeleccionada = escuchadorReunionSeleccionada)
       recyclerView.adapter = adapter
       recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
       recyclerView.setHasFixedSize(true)
   }
}