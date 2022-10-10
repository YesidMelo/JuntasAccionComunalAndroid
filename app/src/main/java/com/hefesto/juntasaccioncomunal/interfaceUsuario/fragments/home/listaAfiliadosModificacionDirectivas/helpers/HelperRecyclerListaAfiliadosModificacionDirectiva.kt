package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.listaAfiliadosModificacionDirectivas.helpers

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.listaAfiliadosModificacionDirectivas.adapters.ListaAfiliadosModificacionDirectivaAdapter
import com.hefesto.juntasaccioncomunal.logica.modelos.home.AfiliadoParaModificacionDirectivaModel

class HelperRecyclerListaAfiliadosModificacionDirectiva {

    enum class Filtro {
        NOMBRES,
        DOCUMENTO
    }

    //region variables
    private var recyclerView: RecyclerView? =null
    private lateinit var listaAfiliados: List<AfiliadoParaModificacionDirectivaModel>
    private lateinit var escuchadorItemSeleccionado : (AfiliadoParaModificacionDirectivaModel)->Unit
    private lateinit var adaperReciclerView :  ListaAfiliadosModificacionDirectivaAdapter

    private var listaAfiliadosFiltrada = emptyList<AfiliadoParaModificacionDirectivaModel>().toMutableList()
    private var filtrando = false
    //endregion

    fun conRecyclerView(recyclerView: RecyclerView) : HelperRecyclerListaAfiliadosModificacionDirectiva {
        this.recyclerView = recyclerView
        return this
    }

    fun conListaAfiliados(listaAfiliados: List<AfiliadoParaModificacionDirectivaModel>) : HelperRecyclerListaAfiliadosModificacionDirectiva {
        this.listaAfiliados = listaAfiliados
        restaurarListaFiltrada()
        return this
    }

    fun conEscuchadorItemSeleccionado(escuchadorItemSeleccionado : (AfiliadoParaModificacionDirectivaModel)->Unit) : HelperRecyclerListaAfiliadosModificacionDirectiva {
        this.escuchadorItemSeleccionado = escuchadorItemSeleccionado
        return this
    }

    fun cargarGenerarAdapters() : HelperRecyclerListaAfiliadosModificacionDirectiva {
        generarAdapterReciclerView()
        return this
    }

    fun restaurarListaFiltrada() {
        if (filtrando) return
        filtrando = true
        listaAfiliadosFiltrada.clear()
        listaAfiliados.forEach {
            this.listaAfiliadosFiltrada.add(it)
        }
        recyclerView?.adapter?.notifyDataSetChanged()
        filtrando = false
    }

    fun buscar(texto: String, filtro: Filtro ) {
        val myRecyclerView = recyclerView?:return
        if (filtrando) return
        filtrando = true
        restaurarListaFiltrada()
        when(filtro) {
            Filtro.NOMBRES -> filtrarPorNombre(texto = texto)
            Filtro.DOCUMENTO -> filtrarPorDocumento(texto = texto)
        }
        myRecyclerView.adapter?.notifyDataSetChanged()
        filtrando = false
    }

    //region metodos privados
    private fun generarAdapterReciclerView() {
        val myRecyclerView = recyclerView?:return
        adaperReciclerView = ListaAfiliadosModificacionDirectivaAdapter(
            listaAfiliados = listaAfiliadosFiltrada,
            escuchadorSeleccionado = escuchadorItemSeleccionado
        )
        myRecyclerView.adapter = adaperReciclerView
        myRecyclerView.layoutManager = LinearLayoutManager(myRecyclerView.context)
        myRecyclerView.hasFixedSize()
    }

    private fun filtrarPorNombre(texto: String) {
        val listaAEliminar = listaAfiliadosFiltrada.filter {
            return@filter !"${it.nombres?:""} ${it.apellidos?:""}".contains(texto)
        }
        listaAfiliadosFiltrada.removeAll(listaAEliminar)
    }

    private fun filtrarPorDocumento(texto: String) {
        val listaAEliminar = listaAfiliadosFiltrada.filter {
            return@filter !(it.documento?:"").contains(texto)
        }
        listaAfiliadosFiltrada.removeAll(listaAEliminar)
    }
    //endregion

}