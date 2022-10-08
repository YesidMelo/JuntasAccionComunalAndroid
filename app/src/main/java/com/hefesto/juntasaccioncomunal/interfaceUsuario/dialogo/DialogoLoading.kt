package com.hefesto.juntasaccioncomunal.interfaceUsuario.dialogo

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.hefesto.juntasaccioncomunal.databinding.DialogfragmentLoadingBinding
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseActivity
import dagger.android.support.DaggerDialogFragment

class DialogoLoading : DaggerDialogFragment() {

    private lateinit var binding: DialogfragmentLoadingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogfragmentLoadingBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)
    }


    companion object {

        private var instancia : DialogoLoading? = null

        fun mostrarProgress(activity: BaseActivity<*>) {
            if(instancia != null) return
            instancia = DialogoLoading()
            instancia?.show(activity.supportFragmentManager, "loadingdialog")
        }

        fun ocultarrProgress() {
            instancia?.dismiss()
            instancia = null
        }
    }

}