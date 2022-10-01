package com.hefesto.juntasaccioncomunal.logica.componentes.base.ui

import com.hefesto.juntasaccioncomunal.logica.componentes.base.casosUso.CargarEscuchadorExcepcionesCasoUso
import javax.inject.Inject

abstract class BaseUI constructor(
    var cargarEscuchadorExcepcionesCasoUso: CargarEscuchadorExcepcionesCasoUso
)