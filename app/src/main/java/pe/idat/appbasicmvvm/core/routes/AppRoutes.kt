package pe.idat.appbasicmvvm.core.routes

sealed class AppRoutes(val path: String) {
    object loginScreen: AppRoutes("loginScreen")
    object homeScreen: AppRoutes("homeScreen/{usuario}"){
        fun paramHome(usuario: String) = "homeScreen/$usuario"
    }

    object infouserScreen: AppRoutes("infouserScreen/{usuario}")
    /*{
        fun paramInfouser(usuario: String) = "infouserScreen/$usuario"
    }*/
    object historiaclinicaScreen: AppRoutes("AlbumesScreen")
    /*{
        fun paramHistoriaclinica(usuario: String) = "historiaclinicaScreen/$usuario"
    }*/
    object historiadiariaScreen: AppRoutes("historiadiariaScreen/{usuario}")
    /*{
        fun paramHistoriadiaria(usuario: String) = "historiadiariaScreen/$usuario"
    }*/
    //object mapasScreen: AppRoutes("mapasScreen")

    object citasScreen: AppRoutes("citasScreen/{usuario}")
    /*{
        fun paramCitas(usuario: String) = "citasScreen/$usuario"
    }*/



}