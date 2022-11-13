// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import di.koinInitialize
import entities.toText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject
import viewmodel.QuadEqViewModel

@Composable
@Preview
fun App() {

    val viewModel by inject<QuadEqViewModel>(QuadEqViewModel::class.java)
    val coroutineScope = rememberCoroutineScope()

    MaterialTheme {
        Row(Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.weight(2f))
            Box(modifier = Modifier.weight(3f)) {
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(modifier = Modifier.padding(10.dp), text = viewModel.textOfEquation)

                    inputFields(viewModel)

                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                        Button(
                            modifier = Modifier.padding(30.dp),
                            //onClick = { viewModel.solveEquation() }
                            onClick = {
                                coroutineScope.launch(Dispatchers.Main) {
                                    viewModel.solveEquation()
                                }
                            }
                        ) {
                            Text("Solve")
                        }
                    }

                    if (viewModel.result.value != null) {
                        printResult(viewModel)
                    }

                    if (viewModel.isError){
                        Text(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 30.dp),
                            text = viewModel.errorMessage.value
                        )
                    }
                }
            }
            Box(modifier = Modifier.weight(2f))
        }
    }
}

@Composable
fun inputFields(vm: QuadEqViewModel) {
    Row(modifier = Modifier.padding(start = 30.dp, top = 30.dp, end = 30.dp, bottom = 5.dp).fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Text("a =", modifier = Modifier.padding(end = 15.dp))
        OutlinedTextField(
            value = vm.a.value?.toString() ?: "0.0",
            onValueChange = { vm.strToDouble(it)?.let { x -> vm.a.value = x } },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }

    Row(modifier = Modifier.padding(start = 30.dp, top = 5.dp, end = 30.dp, bottom = 5.dp).fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Text("b =", modifier = Modifier.padding(end = 15.dp))
        OutlinedTextField(
            value = vm.b.value?.toString() ?: "0.0",
            onValueChange = { vm.strToDouble(it)?.let { x -> vm.b.value = x } },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }

    Row(modifier = Modifier.padding(start = 30.dp, top = 5.dp, end = 30.dp, bottom = 30.dp).fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Text("c =", modifier = Modifier.padding(end = 15.dp))
        OutlinedTextField(
            value = vm.c.value?.toString() ?: "0.0",
            onValueChange = { vm.strToDouble(it)?.let { x -> vm.c.value = x } },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }
}

@Composable
fun printResult(vm: QuadEqViewModel) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
        Text("Result:")
    }

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
        Text("x1 =", modifier = Modifier.padding(end = 10.dp))
        Text(vm.result.value!!.first.toText(), modifier = Modifier.padding(vertical = 10.dp))

        Text(";", modifier = Modifier.padding(vertical = 10.dp))
    }

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
        Text("x2 =", modifier = Modifier.padding(end = 10.dp))
        Text(vm.result.value!!.second.toText(), modifier = Modifier.padding(vertical = 10.dp))

        Text(";", modifier = Modifier.padding(vertical = 10.dp))
    }
}

fun main() = application {

    koinInitialize()

    Window(title = "Quadratic equation", onCloseRequest = ::exitApplication) {
        App()
    }
}
