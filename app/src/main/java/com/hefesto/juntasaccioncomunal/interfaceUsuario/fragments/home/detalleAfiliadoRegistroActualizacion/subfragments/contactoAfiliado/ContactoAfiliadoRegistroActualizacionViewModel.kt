package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.subfragments.contactoAfiliado

import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseViewModel
import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.registroAfiliado.ContactoAfiliadoUI
import com.hefesto.juntasaccioncomunal.logica.modelos.general.TipoTelefonoModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.registroAfiliado.ContactoParaRegistrarModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.TipoTelefono
import com.hefesto.juntasaccioncomunal.logica.utilidades.extenciones.ManejarErrores
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class ContactoAfiliadoRegistroActualizacionViewModel constructor(
    @JvmField @Inject var contactoAfiliadoUI: ContactoAfiliadoUI
) : BaseViewModel() {

    //region variables
    private var correoId : Int? = null
    private var correo: String? = null
    private var direccionId: Int? = null
    private var direccion: String? = null
    private var tipoTelefono: TipoTelefono? = null
    private var telefonoId: Int? = null
    private var telefono: String? = null
    private val tiposTelefonoLiveData = MutableLiveData<List<TipoTelefonoModel>?>()
    //endregion

    override fun traerBaseUI(): BaseUI = contactoAfiliadoUI

    fun conCorreo(correo: String?): ContactoAfiliadoRegistroActualizacionViewModel {
        this.correo = correo
        return this
    }

    fun conDireccion(direccion : String?): ContactoAfiliadoRegistroActualizacionViewModel {
        this.direccion = direccion
        return this
    }

    fun conTipoTelefono(tipoTelefono: TipoTelefono?): ContactoAfiliadoRegistroActualizacionViewModel {
        this.tipoTelefono = tipoTelefono
        return this
    }

    fun conTelefono(telefono: String?) : ContactoAfiliadoRegistroActualizacionViewModel {
        this.telefono = telefono
        return this
    }

    fun traerObjetoArmado() : ContactoParaRegistrarModel {
        return ContactoParaRegistrarModel(
            correoElectronicoId = correoId,
            correo = correo,
            direccionId = direccionId,
            direccion = direccion,
            telefonoId = telefonoId,
            telefono = telefono,
            tipoTelefono = tipoTelefono
        )
    }

    fun traerListaTiposTelefono() : MutableLiveData<List<TipoTelefonoModel>?> {
        GlobalScope.launch {
            contactoAfiliadoUI
                .traerListaTiposTelefonos()
                .ManejarErrores(escuchadorErrores = contactoAfiliadoUI.traerEscuchadorExcepciones())
                .collect{ tiposTelefonoLiveData.postValue(it) }
        }
        return tiposTelefonoLiveData
    }
}