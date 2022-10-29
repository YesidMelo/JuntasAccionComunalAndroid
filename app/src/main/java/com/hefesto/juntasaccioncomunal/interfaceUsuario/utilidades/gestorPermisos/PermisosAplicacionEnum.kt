package com.hefesto.juntasaccioncomunal.interfaceUsuario.utilidades.gestorPermisos

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE

enum class PermisosAplicacionEnum constructor(
    private var identificadorManifiest : String
) {
    LECTURA_DOCUMENTO(identificadorManifiest =  READ_EXTERNAL_STORAGE),
    ESCRITURA_DOCUMENTOS(identificadorManifiest =  WRITE_EXTERNAL_STORAGE),
    ;
    fun traerIdentificadorManifiest() = identificadorManifiest
}
