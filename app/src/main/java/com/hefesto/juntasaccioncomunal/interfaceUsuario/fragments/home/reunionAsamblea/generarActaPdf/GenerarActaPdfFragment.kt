package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.generarActaPdf

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hefesto.juntasaccioncomunal.databinding.FragmentGeneraractaPdfBinding
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.NodosNavegacionFragments
import javax.inject.Inject

class GenerarActaPdfFragment : BaseFragment<GenerarActaPdfViewModel>() {

    //region variables
    @Inject
    lateinit var generarActaPdfViewModel: GenerarActaPdfViewModel

    private lateinit var binding: FragmentGeneraractaPdfBinding
    //endregion

    override fun traerViewModel(): GenerarActaPdfViewModel = generarActaPdfViewModel

    override fun traerNodoNavegacion(): NodosNavegacionFragments = NodosNavegacionFragments.GENERAR_ACTA_PDF

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGeneraractaPdfBinding.inflate(inflater)
        precargarVista()
        return binding.root
    }

    //region metodos privados
    private fun precargarVista() {
        configuracionBotones()
        configurarLiveData()
        cargarInformacion()
    }

    //region botones

    private fun configuracionBotones() {
        configurarBotonAtras()
    }

    private fun configurarBotonAtras() {
        conEscuchadorAccionBotonAtras { navegarAtras() }
    }
    //endregion

    //region livedata
    private fun configurarLiveData() {
        precargarHaCargadoLiveData()
        precargarListaReunionesLiveData()
    }

    private fun precargarHaCargadoLiveData() {
        traerViewModel()
            .traerHaCargadoLiveData()
            .observe(viewLifecycleOwner) {
                if (!it) {
                    mostrarLoading()
                    return@observe
                }
                ocultarLoading()
            }
    }

    private fun precargarListaReunionesLiveData() {
        traerViewModel()
            .traerListaREunionesLiveData()
            .observe(viewLifecycleOwner) {
                Log.e("ERr", "Ha cargado")
            }
    }
    //endregion

    //region carga
    private fun cargarInformacion() {
        traerViewModel().cargarListaReuniones()
    }
    //endregion

    //endregion

}