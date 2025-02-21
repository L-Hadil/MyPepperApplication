package com.example.mypepperapplication

import android.os.Bundle
import com.aldebaran.qi.sdk.QiContext
import com.aldebaran.qi.sdk.QiSDK
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks
import com.aldebaran.qi.sdk.`object`.conversation.Say
import com.aldebaran.qi.sdk.builder.SayBuilder
import com.aldebaran.qi.sdk.design.activity.RobotActivity
import kotlinx.coroutines.*
import kotlin.Exception

class MainActivity : RobotActivity(), RobotLifecycleCallbacks {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        QiSDK.register(this, this)
    }

    override fun onDestroy() {
        QiSDK.unregister(this, this)
        super.onDestroy()
    }

    override fun onRobotFocusGained(qiContext: QiContext) {
        // Lancer l’histoire interactive (sans animations)
        CoroutineScope(Dispatchers.IO).launch {
            interactiveStory(qiContext)
        }
    }

    override fun onRobotFocusLost() {
        // À implémenter si besoin
    }

    override fun onRobotFocusRefused(reason: String) {
        // Gestion en cas de refus du focus
    }

    private suspend fun interactiveStory(qiContext: QiContext) = withContext(Dispatchers.IO) {
        val storyLines = listOf(
            "Hello kids! Welcome to our amazing adventure! Are you ready?",
            "Once upon a time, in a magical forest, there was a brave little robot.",
            "This robot had one dream: to become a great explorer!",
            "So one day, he packed his backpack and started his journey.",
            "During his adventure, he found a big mysterious cave. Do you think he should go inside?",
            "Inside the cave, he discovered a treasure chest! What do you think was inside?",
            "Suddenly, a friendly dragon appeared and asked for help!",
            "The robot decided to help the dragon and they became best friends.",
            "And so, they continued their adventure together, discovering new lands and making more friends.",
            "The moral of the story is: Always be curious and never be afraid to explore new things!",
            "Thank you for listening to my story! Did you enjoy it?"
        )

        for (text in storyLines) {
            try {
                // Création du texte à dire
                val say: Say = SayBuilder.with(qiContext)
                    .withText(text)
                    .build()

                // Pepper prononce le texte
                say.run()

                // Petite pause après chaque phrase
                delay(2000)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
