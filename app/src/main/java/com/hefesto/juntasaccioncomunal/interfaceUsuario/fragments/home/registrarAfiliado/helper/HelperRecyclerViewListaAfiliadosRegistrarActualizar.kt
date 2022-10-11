package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.registrarAfiliado.helper

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.registrarAfiliado.adapter.ListaAfiliadosRecyclerViewAdapter
import com.hefesto.juntasaccioncomunal.logica.modelos.home.DatosBasicosAfiliadoActualizarRegistrarInformacionModel

class HelperRecyclerViewListaAfiliadosRegistrarActualizar {

    //region variables
    private lateinit var recyclerView: RecyclerView
    private lateinit var listaAfiliados: List<DatosBasicosAfiliadoActualizarRegistrarInformacionModel>
    private lateinit var adapter: ListaAfiliadosRecyclerViewAdapter
    private lateinit var escuchadorItemSeleccionado: (DatosBasicosAfiliadoActualizarRegistrarInformacionModel)->Unit
    private var listaFiltrada = emptyList<DatosBasicosAfiliadoActualizarRegistrarInformacionModel>().toMutableList()
    //endregion

    //region metodos publicois
    fun conRecyclerView(recyclerView: RecyclerView) : HelperRecyclerViewListaAfiliadosRegistrarActualizar {
        this.recyclerView = recyclerView
        return this
    }

    fun conListaAfiliados(listaAfiliados: List<DatosBasicosAfiliadoActualizarRegistrarInformacionModel>) : HelperRecyclerViewListaAfiliadosRegistrarActualizar {
        this.listaAfiliados = listaAfiliados
        return this
    }

    fun conEscuchadorItemSeleccionado(escuchadorItemSeleccionado: (DatosBasicosAfiliadoActualizarRegistrarInformacionModel)->Unit) : HelperRecyclerViewListaAfiliadosRegistrarActualizar {
        this.escuchadorItemSeleccionado = escuchadorItemSeleccionado
        return this
    }

    fun filtrar(filtro: String) {

    }

    fun cargarLista() {
        adapter = ListaAfiliadosRecyclerViewAdapter(listaAfiliados = listaAfiliados, escuchadorItemSeleccionado = escuchadorItemSeleccionado)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.hasFixedSize()
        reiniciarListaFiltraada()
    }

    //endregion

    //region metodos privados
    private fun reiniciarListaFiltraada() {
        listaFiltrada.clear()
        listaAfiliados.forEach {
            listaFiltrada.add(it)
        }
    }
    //endregion
}