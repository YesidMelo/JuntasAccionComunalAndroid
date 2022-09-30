package com.hefesto.juntasaccioncomunal.ui.dialogo

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.RawRes
import androidx.annotation.StringRes
import com.hefesto.juntasaccioncomunal.R
import com.hefesto.juntasaccioncomunal.databinding.DialogfragmentDialogoGenericoBinding
import com.hefesto.juntasaccioncomunal.ui.base.BaseActivity
import dagger.android.support.DaggerDialogFragment
import org.jetbrains.annotations.NotNull

class DialogoInformativo : DaggerDialogFragment() {

    //region variables
    private var tipoDialogo : TipoDialogo = TipoDialogo.ERROR_USUARIO
    @StringRes private var titulo : Int? = null
    @StringRes private var mensaje : Int? = null
    private var accionAceptar: (()->Unit)? = null
    private var accionCancelar: (()->Unit)? = null

    private lateinit var binding: DialogfragmentDialogoGenericoBinding
    //endregion

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogfragmentDialogoGenericoBinding.inflate(inflater)
        configurarDialogo()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)
    }

    //region metodos privados
    private fun configurarDialogo() {
        configurarLottie()
        configurarTitulo()
        configuracionMensaje()
        configuracionEscuchadorBotonAceptar()
        configuracionEscuchadorBotonCancelar()
    }

    private fun configurarLottie() {
        @RawRes val lottie = when(tipoDialogo) {
            TipoDialogo.INFORMATIVO -> R.raw.lottie_success
            TipoDialogo.ADVERTENCIA -> R.raw.lottie_warning
            else -> R.raw.lottie_error
        }
        binding.lottieIconodialogo.setAnimation(lottie)
    }

    private fun configurarTitulo(){
        if (titulo == null) {
            binding.textViewTitulo.visibility = View.GONE
            return
        }
        binding.textViewTitulo.text = context?.getText(titulo!!)
    }

    private fun configuracionMensaje() {
        if(mensaje == null) {
            binding.textViewMensaje.visibility = View.GONE
            return
        }
        binding.textViewMensaje.text = context?.getText(mensaje!!)
    }

    private fun configuracionEscuchadorBotonAceptar() {
        binding.buttonAceptar.setOnClickListener {
            dismiss()
            accionAceptar?.invoke()
        }
    }

    private fun configuracionEscuchadorBotonCancelar() {
        binding.buttonCancelar.setOnClickListener {
            dismiss()
            accionCancelar?.invoke()
        }
    }

    //endregion


    enum class TipoDialogo { ERROR_USUARIO, ERROR_SISTEMA, INFORMATIVO, ADVERTENCIA }

    companion object {
        fun mostrarDialogo(
            tipoDialogo: TipoDialogo,
            activity: BaseActivity<*>,
            @StringRes @NotNull titulo: Int,
            @StringRes @NotNull mensaje: Int,
            accionAceptar: (()->Unit)? = null,
            accionCancelar: (()->Unit)? = null,
        ) {
            val dialogo = DialogoInformativo()
            dialogo.tipoDialogo = tipoDialogo
            dialogo.titulo = titulo
            dialogo.mensaje = mensaje
            dialogo.accionAceptar = accionAceptar
            dialogo.accionCancelar = accionCancelar
            dialogo.show(activity.supportFragmentManager, "dialogo_informativo")
        }
    }
}