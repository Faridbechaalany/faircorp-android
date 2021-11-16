package com.faircorp

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.faircorp.model.ApiServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

const val WINDOW_NAME_PARAM = "com.faircorp.windowname.attribute"

class WindowActivity : BasicActivity() {

    var id: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_window)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        id = intent.getLongExtra(WINDOW_NAME_PARAM, 0)

        lifecycleScope.launch(context = Dispatchers.IO) {
            runCatching { ApiServices().windowsApiService.findById(id).execute() }
                .onSuccess {
                    withContext(context = Dispatchers.Main) {
                        findViewById<TextView>(R.id.txt_window_name).text = it.body()?.name
                        findViewById<TextView>(R.id.txt_room_name).text = it.body()?.roomName
                        findViewById<TextView>(R.id.txt_status).text =
                            it.body()?.windowStatus.toString()
                    }
                }
                .onFailure {
                }
        }
    }
        fun switchstatus(view: View) {
            lifecycleScope.launch(context = Dispatchers.IO) {
                runCatching { ApiServices().windowsApiService.switchStatus(id).execute() }
            }
        }
}
