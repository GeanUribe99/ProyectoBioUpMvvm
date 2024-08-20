package pe.idat.appbasicmvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import pe.idat.appbasicmvvm.auth.AuthViewModel
import pe.idat.appbasicmvvm.auth.authScreen
import pe.idat.appbasicmvvm.home.homeScreen
import pe.idat.appbasicmvvm.routes.AppRoutes
import pe.idat.appbasicmvvm.ui.theme.AppbasicmvvmTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppbasicmvvmTheme {
                val navigation = rememberNavController()
                NavHost(navController = navigation,
                    startDestination = AppRoutes.loginScreen.path,
                    builder = {
                        composable(AppRoutes.loginScreen.path){
                            authScreen(AuthViewModel(), navigation)
                        }
                        composable(AppRoutes.homeScreen.path,
                            arguments = listOf(navArgument("id")
                            {type = NavType.IntType} )
                        ){
                            params ->
                            homeScreen(params.arguments?.getInt("id") ?: 0)
                        }
                    })
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppbasicmvvmTheme {

    }
}