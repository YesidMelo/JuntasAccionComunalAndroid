package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.subfragments.datosBasicosAfiliado

import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseViewModel
import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.registroAfiliado.DatosBasicosAfiliadoUI
import com.hefesto.juntasaccioncomunal.logica.modelos.general.TipoDocumentoModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.registroAfiliado.DatosBasicosParaRegistrarModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.TipoDocumento
import com.hefesto.juntasaccioncomunal.logica.utilidades.extenciones.ManejarErrores
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class DatosRegistroActualizacionViewModel constructor(
    @JvmField @Inject var datosBasicosAfiliadoUI: DatosBasicosAfiliadoUI
): BaseViewModel(){

    //region variables
    private val tiposDocumentoLiveData = MutableLiveData<List<TipoDocumentoModel>?>()
    private var afiliadoId: Int? = null
    private var nombres : String? = null
    private var apellidos: String? = null
    private var fechaNacimiento: Date? = null
    private var tipoDocumento: TipoDocumento? = null
    private var numeroDocumento : String? = null
    private var credencialesSesionId: Int? = null
    //endregion

    override fun traerBaseUI(): BaseUI = datosBasicosAfiliadoUI

    fun conAfiliadoId(afiliadoId: Int?) : DatosRegistroActualizacionViewModel {
        this.afiliadoId = afiliadoId
        return this
    }

    fun conNombres(nombres: String) : DatosRegistroActualizacionViewModel {
        this.nombres = nombres
        return this
    }

    fun conApellidos(apellidos: String) : DatosRegistroActualizacionViewModel {
        this.apellidos = apellidos
        return this
    }

    fun conFechaNacimiento(fechaNacimiento: Date?) : DatosRegistroActualizacionViewModel {
        this.fechaNacimiento = fechaNacimiento
        return this
    }

    fun conTipoDocumento(tipoDocumento: TipoDocumento) : DatosRegistroActualizacionViewModel {
        this.tipoDocumento = tipoDocumento
        return this
    }

    fun conNumeroDocumento(numeroDocumento: String) : DatosRegistroActualizacionViewModel {
        this.numeroDocumento = numeroDocumento
        return this
    }

    fun conCredencialesSesionId(credencialesSesionId: Int?) : DatosRegistroActualizacionViewModel {
        this.credencialesSesionId = credencialesSesionId
        return this
    }

    fun crearModeloParaActualizarDatosBasicos(): DatosBasicosParaRegistrarModel {
        return DatosBasicosParaRegistrarModel(
            afiliadoId = this.afiliadoId,
            nombres = this.nombres,
            apellidos = this.apellidos,
            fechaNacimiento = this.fechaNacimiento,
            tipoDocumento = this.tipoDocumento,
            numeroDocumento = this.numeroDocumento
        )
    }

    fun traerTiposDocumento() : MutableLiveData<List<TipoDocumentoModel>?> {
        GlobalScope.launch {
            datosBasicosAfiliadoUI
                .traerListaTiposDocumento()
                .ManejarErrores(escuchadorErrores = datosBasicosAfiliadoUI.traerEscuchadorExcepciones())
                .collect{ tiposDocumentoLiveData.postValue(it)}
        }
        return tiposDocumentoLiveData
    }



}