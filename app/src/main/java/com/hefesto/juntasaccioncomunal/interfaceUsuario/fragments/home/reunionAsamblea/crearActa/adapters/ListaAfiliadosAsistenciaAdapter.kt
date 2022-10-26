package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.crearActa.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hefesto.juntasaccioncomunal.R
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.crearActa.AfiliadoActaAsistenciaModel

class ListaAfiliadosAsistenciaAdapter constructor(
    private val listaAfiliados: List<AfiliadoActaAsistenciaModel>,
    private val afiliadoSeleccionado :  (AfiliadoActaAsistenciaModel) -> Unit
) : RecyclerView.Adapter<ListaAfiliadosAsistenciaAdapter.Item>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Item {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_crearacta_listaasistencia, null, false)
        view.layoutParams = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT)
        return Item(view = view)
    }

    override fun onBindViewHolder(holder: Item, position: Int) {
        holder
            .llenar(elemento = listaAfiliados[position])
            .ponerEscuchador()
    }

    override fun getItemCount(): Int = listaAfiliados.size

    inner class Item(val view: View): RecyclerView.ViewHolder(view) {

        private lateinit var afiliadoActaAsistenciaModel: AfiliadoActaAsistenciaModel

        fun llenar(elemento : AfiliadoActaAsistenciaModel) : Item {
            this.afiliadoActaAsistenciaModel = elemento
            llenarElementos()
            return this
        }

        fun ponerEscuchador() {
            val eliminar = view.findViewById<ImageView>(R.id.imageView_crearActa_quitarAfiliado)
            eliminar.setOnClickListener {
                afiliadoSeleccionado.invoke(afiliadoActaAsistenciaModel)
            }
        }

        //region metodos privados
        private fun llenarElementos() {
            val nombres = view.findViewById<TextView>(R.id.textView_crearActa_nombreAfiliado)
            val documentos = view.findViewById<TextView>(R.id.textView_crearActa_numeroDocumento)

            nombres.text = "${afiliadoActaAsistenciaModel.nombres?:""} ${afiliadoActaAsistenciaModel.apellidos?:""}"
            documentos.text = afiliadoActaAsistenciaModel.numeroDocumento?:""
        }
        //endregion

    }
}