package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.registrarAfiliado.helper

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.registrarAfiliado.adapter.ListaAfiliadosRecyclerViewAdapter
import com.hefesto.juntasaccioncomunal.logica.modelos.home.DatosBasicosAfiliadoActualizarRegistrarInformacionModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HelperRecyclerViewListaAfiliadosRegistrarActualizar {

    //region variables
    private lateinit var recyclerView: RecyclerView
    private lateinit var listaAfiliados: List<DatosBasicosAfiliadoActualizarRegistrarInformacionModel>
    private lateinit var adapter: ListaAfiliadosRecyclerViewAdapter
    private lateinit var escuchadorItemSeleccionado: (DatosBasicosAfiliadoActualizarRegistrarInformacionModel)->Unit
    private var listaFiltrada = emptyList<DatosBasicosAfiliadoActualizarRegistrarInformacionModel>().toMutableList()
    private var filtrando = false
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

    fun filtrar(texto: String?, filtro: Filtro) {
        GlobalScope.launch {
            if (filtrando) return@launch
            filtrando = true
            if (texto.isNullOrEmpty()) {
                reiniciarListaFiltrada()
                filtrando = false
                recyclerView.post { recyclerView.adapter?.notifyDataSetChanged() }
                return@launch
            }
            when (filtro) {
                Filtro.NOMBRE -> filtrarPorNombre(texto = texto)
                Filtro.DOCUMENTO -> filtrarPorDocumento(texto = texto)
            }
            recyclerView.post { recyclerView.adapter?.notifyDataSetChanged() }
            filtrando = false
        }
    }

    fun cargarLista() {
        adapter = ListaAfiliadosRecyclerViewAdapter(listaAfiliados = listaFiltrada, escuchadorItemSeleccionado = escuchadorItemSeleccionado)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.hasFixedSize()
        reiniciarListaFiltrada()
    }

    //endregion

    //region metodos privados
    private fun reiniciarListaFiltrada() {
        listaFiltrada.clear()
        listaAfiliados.forEach {
            listaFiltrada.add(it)
        }
    }

    private fun filtrarPorNombre(texto: String) {
        val nuevaLista = listaAfiliados.filter { return@filter "${it.nombres?:""} ${it.apellidos?:""}".contains(texto) }.toMutableList()
        listaFiltrada.clear()
        nuevaLista.forEach {  listaFiltrada.add(it) }
    }

    private fun filtrarPorDocumento(texto: String) {
        val nuevaLista = listaAfiliados.filter { return@filter (it.documento?:"").contains(texto) }.toMutableList()
        listaFiltrada.clear()
        nuevaLista.forEach {  listaFiltrada.add(it) }
    }

    //endregion

    enum class Filtro {
        DOCUMENTO,
        NOMBRE,
    }
}