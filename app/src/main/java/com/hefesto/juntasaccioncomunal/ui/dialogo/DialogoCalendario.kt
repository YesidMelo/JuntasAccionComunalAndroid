package com.hefesto.juntasaccioncomunal.ui.dialogo

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.hefesto.juntasaccioncomunal.databinding.DialogfragmentCalendarioBinding
import com.hefesto.juntasaccioncomunal.ui.base.BaseActivity
import dagger.android.support.DaggerDialogFragment
import java.util.Date

class DialogoCalendario : DaggerDialogFragment() {

    //region variables
    private var accionAceptar : ((Date)->Unit)? = null
    private var accionCancelar : (()->Unit)? = null
    private lateinit var binding: DialogfragmentCalendarioBinding
    //endregion

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogfragmentCalendarioBinding.inflate(inflater)
        ponerEscuchadorBotones()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)
    }

    private fun ponerEscuchadorBotones() {
        ponerEscuchadorBotonAceptar()
        ponerEscuchadorBotonCancelar()
    }

    private fun ponerEscuchadorBotonCancelar() {
        binding.buttonCancelarDialogoFecha.setOnClickListener {
            dismiss()
            instancia = null
        }
    }

    private fun ponerEscuchadorBotonAceptar() {
        binding.buttonAceptarDialogoFecha.setOnClickListener {
            dismiss()
            instancia = null
        }
    }

    companion object {

        private var instancia : DialogoCalendario? = null

        fun mostrarDialogo(
            activity: BaseActivity<*>,
            accionAceptar : ((Date)->Unit)? = null,
            accionCancelar : (()->Unit)? = null,
        )
        {
            if(instancia != null) return
            instancia = DialogoCalendario()
            instancia?.accionAceptar = accionAceptar
            instancia?.accionCancelar = accionCancelar
            instancia?.show(activity.supportFragmentManager,"dialogoCalendario")
        }

    }

}