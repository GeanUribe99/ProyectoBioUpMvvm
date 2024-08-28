package pe.idat.appbasicmvvm.home.view

import android.content.Context
import androidx.compose.foundation.Image
import pe.idat.appbasicmvvm.R
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.MedicalInformation
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Pageview
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import pe.idat.appbasicmvvm.core.routes.AppRoutes
import pe.idat.appbasicmvvm.core.utils.MenuItem
import pe.idat.appbasicmvvm.home.ViewModel.AlbunesViewModel
import pe.idat.appbasicmvvm.home.ViewModel.HomeViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun homeScreen(homeViewModel: HomeViewModel,navPrincipal: NavController, usuario: String){

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val navController = rememberNavController()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            drawerContent(items = opcionesMenu(), onItemClick = { item ->
                coroutineScope.launch {
                    drawerState.close()
                }
                when (item.titulo) {
                    "Informacion de Usuario" -> navController.navigate(AppRoutes.infouserScreen.path)
                    "Historial Clinico" -> navController.navigate(AppRoutes.historiaclinicaScreen.path)
                    "Historias Diarias" -> navController.navigate(AppRoutes.historiadiariaScreen.path)
                    //"Mapas" -> navController.navigate(AppRoutes.mapasScreen.path)
                    /*"Citas Médicas" -> navController.navigate(AppRoutes.historiaclinicaScreen.path)*/

                }
            }, usuario)
        },
        content = {
            Column {
                TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.LightGray,
                    titleContentColor = Color.Black
                ),
                    title = { Text(text = "BioUp") },
                    navigationIcon = {
                        IconButton(onClick = {
                            coroutineScope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }, actions = {
                        IconButton(onClick = {
                            navPrincipal.navigate(AppRoutes.loginScreen.path){
                                popUpTo(navController.graph.startDestinationId)
                                { inclusive = true}
                            }
                        }) {
                            Icon(imageVector = Icons.AutoMirrored.Filled.ExitToApp, contentDescription = null,
                                tint = Color.Black)
                        }
                    })
                NavHost(navController = navController,
                    startDestination = AppRoutes.infouserScreen.path) {
                    composable(AppRoutes.infouserScreen.path){ infouserScreen(homeViewModel,usuario)}
                    composable(AppRoutes.historiaclinicaScreen.path){ AlbumesScreen(homeViewModel)}
                    composable(AppRoutes.historiadiariaScreen.path){ historiadiariaScreen(homeViewModel)}
                    //composable(AppRoutes.citasScreen.path){ citasScreen(it.context)}
                    //composable(AppRoutes.mapasScreen.path){ mapasScreen()}

                }
            }
        }
    )
}

@Composable
fun drawerContent(
    items: List<MenuItem>,
    onItemClick: (MenuItem) -> Unit,
    usuario: String
) {
    Column(
        Modifier
            .fillMaxHeight().fillMaxWidth(0.87f)
            .background(Color.White)
            .systemBarsPadding()
    ) {
        cabeceraMenu(usuario)
        Spacer(modifier = Modifier.height(8.dp))
        items.forEach { item ->
            drawerMenuItem(item, onItemClick)
        }
    }
}

@Composable
fun drawerMenuItem(item: MenuItem, onItemClick: (MenuItem) -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable { onItemClick(item) }
        .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Icon(imageVector = item.icon, contentDescription = "")
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = item.titulo)
    }
}

@Composable
fun cabeceraMenu(usuario: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        /*Image(
            painter = painterResource(id = R.drawable.imgperfil),
            contentDescription = "logo",
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
        )*/
        Spacer(modifier = Modifier.width(16.dp))
        Column {
                Text(text = usuario, fontWeight = FontWeight.Bold)
            }

        }
}

fun opcionesMenu(): List<MenuItem> {
    return listOf(
        MenuItem(Icons.Default.Person, "Informacion de Usuario"),
        MenuItem(Icons.Default.MedicalInformation, "Historial Clinico"),
        MenuItem(Icons.Default.Pageview, "Historias Diarias"),
        MenuItem(Icons.Default.Map, "Mapas"),
        MenuItem(Icons.Default.DateRange, "Citas Médicas")
    )
}