package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.subfragments.contactoAfiliado

import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseViewModel
import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.registroAfiliado.ContactoAfiliadoUI
import javax.inject.Inject

class ContactoAfiliadoRegistroActualizacionViewModel constructor(
    @JvmField @Inject var contactoAfiliadoUI: ContactoAfiliadoUI
) : BaseViewModel() {

    override fun traerBaseUI(): BaseUI = contactoAfiliadoUI
}