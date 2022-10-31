package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.crearActa.subfragment

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.hefesto.juntasaccioncomunal.databinding.SubfragmentDetallepuntoreunionBinding
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.crearActa.CrearActaViewModel
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.crearActa.helper.HelperViewPagerPuntosCrearActa
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.NodosNavegacionFragments
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.crearActa.PuntoReunionParaCreacionActaModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.TipoReunion
import javax.inject.Inject

class DetallePuntoSubfragment : BaseFragment<CrearActaViewModel>() {

    //region variables
    lateinit var crearActaViewModel: CrearActaViewModel
    lateinit var puntoReunionParaCreacionActaModel: PuntoReunionParaCreacionActaModel
    var tipoReunion: TipoReunion? = null
    var puntoNo :Int = 0
    private lateinit var binding: SubfragmentDetallepuntoreunionBinding
    //endregion

    override fun traerViewModel(): CrearActaViewModel = crearActaViewModel

    override fun traerNodoNavegacion(): NodosNavegacionFragments = NodosNavegacionFragments.DETALLE_PUNTO_REUNION

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SubfragmentDetallepuntoreunionBinding.inflate(inflater)
        precargarVista()
        escuchadorEditTexts()
        return binding.root
    }

    //region metodos privados

    //region precarga
    private fun precargarVista() {
        ocultarFormularioVotos()
        cargarInformacionPunto()
    }

    private fun ocultarFormularioVotos() {
        val tipo = tipoReunion?:return
        if (tipo == TipoReunion.REUNION_DIRECTIVA_INFORMATIVA) return
        if (tipo == TipoReunion.ASAMBLEA_INFORMATIVA) return
        binding.switchCrearActaTienVotacion.visibility = View.VISIBLE
        binding.switchCrearActaTienVotacion.setOnCheckedChangeListener { _, check ->
            puntoReunionParaCreacionActaModel.tieneVotacion = check
            if (check) {
                binding.constraintLayoutCrearActaContadorVotos.visibility = View.VISIBLE
                return@setOnCheckedChangeListener
            }
            binding.constraintLayoutCrearActaContadorVotos.visibility = View.GONE
        }

    }

    //region ingresarInformacion
    private fun cargarInformacionPunto() {
        binding.textViewCrearActaTituloPunto.text = puntoReunionParaCreacionActaModel.tituloPunto
        binding.edittextCrearActaVotosAFavor.setText("${puntoReunionParaCreacionActaModel.votosAFavor?:0}")
        binding.edittextCrearActaVotosEnContra.setText("${puntoReunionParaCreacionActaModel.votosEnContra?:0}")
        binding.edittextCrearActaDetallePunto.setText(puntoReunionParaCreacionActaModel.detallePunto?:"")
    }
    //endregion

    //endregion

    //region escuchadores
    private fun escuchadorEditTexts() {
        escuchadorDetallePunto()
        escuchadorVotosAFavor()
        escuchadorVotosEnContra()
    }

    private fun escuchadorDetallePunto() {
        binding.edittextCrearActaDetallePunto.addTextChangedListener {
            edittext ->
            puntoReunionParaCreacionActaModel.detallePunto = edittext?.toString()
        }
    }

    private fun escuchadorVotosAFavor() {
        binding.edittextCrearActaVotosAFavor.addTextChangedListener {
                edittext ->
            puntoReunionParaCreacionActaModel.votosAFavor = edittext?.toString()?.toIntOrNull()
        }
    }

    private fun escuchadorVotosEnContra() {
        binding.edittextCrearActaVotosEnContra.addTextChangedListener {
                edittext ->
            puntoReunionParaCreacionActaModel.votosEnContra = edittext?.toString()?.toIntOrNull()
        }
    }
    //endregion

    //endregion
}