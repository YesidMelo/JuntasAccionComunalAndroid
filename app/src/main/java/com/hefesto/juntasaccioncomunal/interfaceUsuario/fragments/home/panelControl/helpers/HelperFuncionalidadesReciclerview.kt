package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.panelControl.helpers

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.panelControl.adapters.AdapterReciclerviewFuncionalidades
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.FuncionesRolApp

class HelperFuncionalidadesReciclerview {
    //region variables
    private lateinit var recyclerView : RecyclerView
    private lateinit var listaFuncionalidades : List<FuncionesRolApp>
    private lateinit var itemSeleccionado : ((FuncionesRolApp)->Unit)
    private var adapter : AdapterReciclerviewFuncionalidades? = null
    //endregion

    fun conItemSeleccionado(itemSeleccionado : ((FuncionesRolApp)->Unit)) : HelperFuncionalidadesReciclerview {
        this.itemSeleccionado = itemSeleccionado
        return this
    }

    fun conRecyclerView(recyclerView : RecyclerView) : HelperFuncionalidadesReciclerview {
        this.recyclerView = recyclerView
        return this
    }

    fun conListaFuncionalidades(listaFuncionalidades : List<FuncionesRolApp>) : HelperFuncionalidadesReciclerview{
        this.listaFuncionalidades = listaFuncionalidades
        return  this
    }

    fun cargar() {
        if (adapter == null) {
            adapter = AdapterReciclerviewFuncionalidades(
                listaFunciones = listaFuncionalidades,
                onClick = itemSeleccionado
            )
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(recyclerView.context, 2)
        recyclerView.hasFixedSize()
        adapter?.notifyDataSetChanged()
    }

}
