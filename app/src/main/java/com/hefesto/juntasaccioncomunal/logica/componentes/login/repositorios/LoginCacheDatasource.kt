package com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios

import com.hefesto.juntasaccioncomunal.logica.componentes.base.repositorios.BaseCacheDatasource
import javax.inject.Inject

interface LoginCacheDatasource {}

class LoginCacheDatasourceImpl constructor(
    @JvmField @Inject var baseCacheDatasource: BaseCacheDatasource
) : LoginCacheDatasource {

}