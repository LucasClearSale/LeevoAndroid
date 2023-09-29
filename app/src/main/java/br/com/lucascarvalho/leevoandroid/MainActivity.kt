package br.com.lucascarvalho.leevoandroid

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import br.com.lucascarvalho.leevoandroid.ui.theme.LeevoAndroidTheme
import com.clear.studio.csliveness.core.CSLiveness
import com.clear.studio.csliveness.core.CSLivenessResult
import com.clear.studio.csliveness.view.CSLivenessActivity


class MainActivity : ComponentActivity() {

    private val mCSLiveness: CSLiveness? = null
    private val REQUEST_CODE = 40

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LeevoAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Button(onClick = {

                        //Coloque o clientId e clientSecrete
                        var mCSLiveness = CSLiveness("SEU CLIENT ID AQUI", "SEU CLIENT SECRET AQUI")
                        val mIntent = Intent(/* packageContext = */this, /* cls = */ CSLivenessActivity::class.java)
                        mIntent.putExtra(CSLiveness.PARAMETER_NAME, mCSLiveness)
                        startActivity(mIntent)
                        startActivityForResult(mIntent, REQUEST_CODE)
                    }) {
                        Text("Chamar SDK")
                    }
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                val mCSLivenessResult =
                    data.getSerializableExtra(CSLiveness.PARAMETER_NAME) as CSLivenessResult?
                mCSLivenessResult?.let { Log.d("Result", it.responseMessage) }
            } else {
                Log.d("Result", "UserCancel")
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}

