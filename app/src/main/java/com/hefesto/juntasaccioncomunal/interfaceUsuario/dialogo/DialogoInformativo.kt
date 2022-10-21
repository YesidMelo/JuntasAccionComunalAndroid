package com.hefesto.juntasaccioncomunal.interfaceUsuario.dialogo

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
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseActivity
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
        isCancelable = false
        configurarBotones()
        configurarLottie()
        configurarTitulo()
        configuracionMensaje()
        configuracionEscuchadorBotonAceptar()
        configuracionEscuchadorBotonCancelar()
    }

    private fun configurarBotones() {
        if(tipoDialogo == TipoDialogo.ADVERTENCIA) return
        binding.buttonCancelar.visibility = View.GONE
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
            instancia = null
            accionAceptar?.invoke()
        }
    }

    private fun configuracionEscuchadorBotonCancelar() {
        binding.buttonCancelar.setOnClickListener {
            dismiss()
            instancia = null
            accionCancelar?.invoke()
        }
    }

    //endregion


    enum class TipoDialogo { ERROR_USUARIO, ERROR_SISTEMA, INFORMATIVO, ADVERTENCIA }

    companion object {

        private var instancia : DialogoInformativo? = null

        fun mostrarDialogo(
            tipoDialogo: TipoDialogo,
            activity: BaseActivity<*>,
            @StringRes @NotNull titulo: Int,
            @StringRes @NotNull mensaje: Int,
            accionAceptar: (()->Unit)? = null,
            accionCancelar: (()->Unit)? = null,
        ) {
            if (instancia != null) return
            instancia = DialogoInformativo()
            instancia?.tipoDialogo = tipoDialogo
            instancia?.titulo = titulo
            instancia?.mensaje = mensaje
            instancia?.accionAceptar = accionAceptar
            instancia?.accionCancelar = accionCancelar
            instancia?.show(activity.supportFragmentManager, "dialogo_informativo")
        }
    }
}