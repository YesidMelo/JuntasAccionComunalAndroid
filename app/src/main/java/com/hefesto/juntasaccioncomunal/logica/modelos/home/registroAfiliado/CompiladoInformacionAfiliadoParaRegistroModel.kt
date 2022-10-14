package com.hefesto.juntasaccioncomunal.logica.modelos.home.registroAfiliado

import com.hefesto.juntasaccioncomunal.logica.modelos.BaseModel

data class CompiladoInformacionAfiliadoParaRegistroModel(
    var datosBasicosParaRegistrarModel: DatosBasicosParaRegistrarModel? = null,
    var contactoParaRegistrarModel: ContactoParaRegistrarModel? = null,
    var detalleEnJACParaRegistroModel: DetalleEnJACParaRegistroModel? = null,
    var seguridadParaRegistroModel: SeguridadParaRegistroModel? = null
) : BaseModel()
