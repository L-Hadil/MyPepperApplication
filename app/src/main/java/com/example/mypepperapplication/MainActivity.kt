package com.example.mypepperapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import com.aldebaran.qi.sdk.QiSDK
import com.aldebaran.qi.sdk.QiContext
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks
import com.aldebaran.qi.sdk.builder.SayBuilder

class MainActivity : ComponentActivity(), RobotLifecycleCallbacks {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Enregistrement pour recevoir le focus robot
        QiSDK.register(this, this)
        setContent {
            // Simple interface Compose
            Text(text = "Hello Pepper!")
        }
    }

    override fun onDestroy() {
        QiSDK.unregister(this, this)
        super.onDestroy()
    }

    override fun onRobotFocusGained(qiContext: QiContext?) {
        val say = SayBuilder.with(qiContext)
            .withText("Hello, I am Pepper!")
            .build()
        say.run()
    }


    override fun onRobotFocusLost() {
        // Optionnel : gérer la perte de focus si nécessaire
    }

    override fun onRobotFocusRefused(reason: String?) {
        // Optionnel : gérer le refus de focus si nécessaire
    }
}
