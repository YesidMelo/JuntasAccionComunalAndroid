package com.hefesto.juntasaccioncomunal.interfaceUsuario.utilidades

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.TextView

@Suppress("UNCHECKED_CAST")
class ArrayAdapterPersonalizado<T> constructor(
    private val contextLocal: Context,
    private val vistaFuenteId: Int,
    private val textViewAMostrarId: Int,
    private val listaElementos: List<T>,
    private val textoAMostrar: (T?) -> String,
    private val filtro: ((T, String?)-> Boolean)
) : ArrayAdapter<T>(contextLocal, vistaFuenteId, listaElementos) {

    //region variables
    private val todosLosElementos = emptyList<T>().toMutableList()
    private val sugeridos = emptyList<T>().toMutableList()
    //endregion

    init {
        todosLosElementos.addAll(listaElementos)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val v = traerVistaItem(convertView = convertView)
        val elemento = listaElementos[position]
        if (elemento != null) {
            val textview = v.findViewById<TextView>(textViewAMostrarId)
            textview.text = textoAMostrar.invoke(elemento)
        }
        return v
    }

    override fun getFilter(): Filter {
        return FiltroPersonalizado()
    }

    //region metodos privados
    private fun traerVistaItem(convertView: View?) : View {
        var v = convertView
        if (v == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            v = inflater.inflate(vistaFuenteId, null)
        }
        return v!!
    }
    //endregion

    private inner class FiltroPersonalizado : Filter(){

        override fun convertResultToString(resultValue: Any?): CharSequence {
            return textoAMostrar.invoke(resultValue as T)
        }

        override fun performFiltering(p0: CharSequence?): FilterResults {
            val textoParaFiltro = p0?: return FilterResults()
            sugeridos.clear()
            for (item in todosLosElementos) {
                if (filtro.invoke(item,textoParaFiltro.toString())) {
                    sugeridos.add(item)
                }
            }
            val filterResult = FilterResults()
            filterResult.values = sugeridos
            filterResult.count = sugeridos.size
            return filterResult
        }

        override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
            val listaFiltrada = p1?.values as? List<T>
            if(listaFiltrada != null && p1.count > 0) {
                clear()
                for(item in listaFiltrada) {
                    add(item)
                }
                notifyDataSetChanged()
            }
        }

    }

}