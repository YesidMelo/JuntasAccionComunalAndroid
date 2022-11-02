package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.Afiliado.detalleAfiliadoActualizacion.subfragments.detalleEnJACAfiliado

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hefesto.juntasaccioncomunal.databinding.FragmentDetalleEnJacHomeBinding
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.Afiliado.detalleAfiliadoActualizacion.DetalleAfiliadoRegistroActualizacionFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.Afiliado.detalleAfiliadoActualizacion.helpers.HelperSpinnerComitesEnJacHome
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.Afiliado.detalleAfiliadoActualizacion.helpers.HelperSpinnerEstadosAfiliadoHome
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.Afiliado.detalleAfiliadoActualizacion.subfragments.ConfigurarInformacionParaCrearModeloRegistro
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.NodosNavegacionFragments
import com.hefesto.juntasaccioncomunal.logica.modelos.home.DatosBasicosAfiliadoActualizarRegistrarInformacionModel
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
        funcionSegura(funcion = {
            precargarSpinnerComitesJac()
            precargarSpinnerEstadosAfiliado()
            precargarAfiliadoLiveData()
        })
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

                ingresarDetalleUsuario()
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

                ingresarDetalleUsuario()
            }
    }

    private fun precargarAfiliadoLiveData() {
        traerViewModel()
            .cargarDetalleUsuarioEnJAC()
            .observe(viewLifecycleOwner){
                if (it == null) return@observe
                helperSpinnerEstadosAfiliadoHome.conEstadoSeleccionado(estadoSeleccionado = it.datosJACModel!!.estadoAfiliacion)
                helperSpinnerComitesEnJacHome.conComiteSeleccionado(comiteSeleccionado = it.datosJACModel?.comite)
            }
    }

    //endregion

    //region detalle usuario
    private fun ingresarDetalleUsuario() {
        val usuario = (arguments?.get(DetalleAfiliadoRegistroActualizacionFragment.DETALLE_AFILIADO_ACTUALIZACION) as? DatosBasicosAfiliadoActualizarRegistrarInformacionModel)?:return
        traerViewModel().conDatosBasicosAfiliadoActualizarRegistrarInformacionModel(datosBasicosAfiliadoActualizarRegistrarInformacionModel = usuario)
    }
    //endregion
    //endregion


}