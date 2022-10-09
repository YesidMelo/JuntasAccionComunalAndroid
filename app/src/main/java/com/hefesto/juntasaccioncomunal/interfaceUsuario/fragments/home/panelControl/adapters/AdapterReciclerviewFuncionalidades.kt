package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.panelControl.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.hefesto.juntasaccioncomunal.R
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.FuncionesRolApp

class AdapterReciclerviewFuncionalidades constructor(
    var listaFunciones : List<FuncionesRolApp>,
    var onClick: (FuncionesRolApp)->Unit
) :  RecyclerView.Adapter<AdapterReciclerviewFuncionalidades.ViewHolder> (){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemlayout_panel_control, null, false)
        view.layoutParams = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT)
        return ViewHolder(view = view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder
            .llenar(listaFunciones[position])
            .escuchar(onClick)
    }

    override fun getItemCount(): Int = listaFunciones.size

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        private lateinit var funcionesRolApp: FuncionesRolApp

        fun llenar(funcionesRolApp: FuncionesRolApp) : ViewHolder {
            this.funcionesRolApp = funcionesRolApp
            llenarLottie()
            llenarTexto()
            return this
        }

        fun escuchar(onClick: (FuncionesRolApp)->Unit) : ViewHolder {
            view.setOnClickListener {
                onClick.invoke(this.funcionesRolApp)
            }
            return this
        }

        //region metodos privados
        private fun llenarLottie() {
            val lottie = view.findViewById<LottieAnimationView>(R.id.lottie_itemFuncionalidad)
            lottie.setAnimation(funcionesRolApp.traerLottieRes())
        }

        private fun llenarTexto() {
            val text = view.findViewById<TextView>(R.id.textView_nombreFuncionalidad)
            text.setText(funcionesRolApp.traerStringRes())
        }
        //endregion
    }
}