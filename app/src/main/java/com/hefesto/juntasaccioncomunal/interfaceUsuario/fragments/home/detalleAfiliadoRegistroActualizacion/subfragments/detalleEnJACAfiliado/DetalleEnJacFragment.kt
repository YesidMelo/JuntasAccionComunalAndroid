package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.subfragments.detalleEnJACAfiliado

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hefesto.juntasaccioncomunal.databinding.FragmentDetalleEnJacHomeBinding
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.helpers.HelperSpinnerComitesEnJacHome
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.helpers.HelperSpinnerEstadosAfiliadoHome
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.subfragments.ConfigurarInformacionParaCrearModeloRegistro
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.NodosNavegacionFragments
import com.hefesto.juntasaccioncomunal.logica.modelos.home.registroAfiliado.DetalleEnJACParaRegistroModel
import javax.inject.Inject

class DetalleEnJacFragment :
    BaseFragment<DetalleEnJacViewModel>(),
    ConfigurarInformacionParaCrearModeloRegistro<DetalleEnJACParaRegistroModel>
{

    //region variables
    @Inject lateinit var detalleEnJacViewModel: DetalleEnJacViewModel
    @Inject lateinit var helperSpinnerComitesEnJacHome : HelperSpinnerComitesEnJacHome
    @Inject lateinit var helperSpinnerEstadosAfiliadoHome: HelperSpinnerEstadosAfiliadoHome

    private lateinit var binding : FragmentDetalleEnJacHomeBinding
    //endregion

    override fun traerViewModel(): DetalleEnJacViewModel = detalleEnJacViewModel

    override fun traerNodoNavegacion(): NodosNavegacionFragments = NodosNavegacionFragments.DETALLE_JAC_AFILIADO_HOME

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetalleEnJacHomeBinding.inflate(inflater)
        precargarVista()
        return binding.root
    }

    override fun armarModelo(): DetalleEnJACParaRegistroModel {
        return traerViewModel()
            .conComiteSeleccionado(comitesEnJAC = helperSpinnerComitesEnJacHome.traerComiteSeleccionado())
            .conEstadoAfiliacion(estadoAfiliacion = helperSpinnerEstadosAfiliadoHome.traerEstadoAfiliado())
            .traerDetalleEnJACParaRegistroModel()
    }

    //region metodos privados
    //region precarga
    private fun precargarVista() {
        precargarSpinnerComitesJac()
        precargarSpinnerEstadosAfiliado()
    }

    private fun precargarSpinnerComitesJac() {
        traerViewModel()
            .cargarComitesEnJac()
            .observe(viewLifecycleOwner) {
                if (it == null) return@observe
                helperSpinnerComitesEnJacHome
                    .conSpinner(spinner = binding.spinnerDetalleAfiliadoEnJACComite)
                    .conListaComites(listaComites = it)
                    .cargarSpinner()
            }
    }

    private fun precargarSpinnerEstadosAfiliado() {
        traerViewModel()
            .cargarEstadosAfiliacion()
            .observe(viewLifecycleOwner) {
                if(it == null) return@observe
                helperSpinnerEstadosAfiliadoHome
                    .conSpinner(spinner = binding.spinnerDetalleAfiliadoEnJACEstadoAfiliadoEnJAc)
                    .conListaEstados(listaEstados = it)
                    .cargarSpinner()
            }
    }
    //endregion
    //endregion


}