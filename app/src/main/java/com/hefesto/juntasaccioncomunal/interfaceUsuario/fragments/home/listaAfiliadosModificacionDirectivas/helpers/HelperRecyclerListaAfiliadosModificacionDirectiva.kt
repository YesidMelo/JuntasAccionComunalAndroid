package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.listaAfiliadosModificacionDirectivas.helpers

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.listaAfiliadosModificacionDirectivas.adapters.ListaAfiliadosModificacionDirectivaAdapter
import com.hefesto.juntasaccioncomunal.logica.modelos.home.AfiliadoModificacionDirectivaModel

class HelperRecyclerListaAfiliadosModificacionDirectiva {

    //region variables
    private lateinit var recyclerView: RecyclerView
    private lateinit var listaAfiliados: List<AfiliadoModificacionDirectivaModel>
    private lateinit var escuchadorItemSeleccionado : (AfiliadoModificacionDirectivaModel)->Unit
    //endregion

    fun conRecyclerView(recyclerView: RecyclerView) : HelperRecyclerListaAfiliadosModificacionDirectiva {
        this.recyclerView = recyclerView
        return this
    }

    fun conListaAfiliados(listaAfiliados: List<AfiliadoModificacionDirectivaModel>) : HelperRecyclerListaAfiliadosModificacionDirectiva {
        this.listaAfiliados = listaAfiliados
        return this
    }

    fun conEscuchadorItemSeleccionado(escuchadorItemSeleccionado : (AfiliadoModificacionDirectivaModel)->Unit) : HelperRecyclerListaAfiliadosModificacionDirectiva {
        this.escuchadorItemSeleccionado = escuchadorItemSeleccionado
        return this
    }

    fun cargarLista() {
        val adapter = ListaAfiliadosModificacionDirectivaAdapter(
            listaAfiliados = listaAfiliados,
            escuchadorSeleccionado = escuchadorItemSeleccionado
        )
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.hasFixedSize()
    }
}