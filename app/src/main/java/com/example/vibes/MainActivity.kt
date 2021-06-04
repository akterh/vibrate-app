package com.example.vibes
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
 import com.example.vibes.hasVibrator


class MainActivity : AppCompatActivity() {

    public  var clickedBtn =123
    lateinit var root_layout:ConstraintLayout
    lateinit var button:Button
    lateinit var button2:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        root_layout = findViewById(R.id.root_layout)
        button = findViewById(R.id.button)
        button2 = findViewById(R.id.button2)

        // Set the root layout background color
        root_layout.setBackgroundColor(if (hasVibrator) Color.GREEN else Color.RED)

        // Button click listener
         button.setOnClickListener{
           // Vibrate the phone programmatically
           vibrate()
            button.visibility = View.GONE
            button2.visibility = View.VISIBLE

       }
        button2.setOnClickListener{
            // Vibrate the phone programmatically
            button.visibility = View.VISIBLE
            button2.visibility = View.GONE
            vibrateCancel()

        }

    }
}


/*
    *** documentation source developer.android.com ***

    VibrationEffect
        A VibrationEffect describes a haptic effect to be performed by a Vibrator. These effects
        may be any number of things, from single shot vibrations to complex waveforms.


        VibrationEffect createOneShot (long milliseconds, int amplitude)
            Create a one shot vibration. One shot vibrations will vibrate constantly
            for the specified period of time at the specified amplitude, and then stop.

        Parameters
            milliseconds long : The number of milliseconds to vibrate. This must be a positive number.
            amplitude int : The strength of the vibration. This must be a value between 1 and 255, or DEFAULT_AMPLITUDE.

        Returns
            VibrationEffect The desired effect.

*/

// Extension method to vibrate a phone programmatically
fun Context.vibrate(){
    val vibrator = this.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

    // Check whether device/hardware has a vibrator
    val canVibrate:Boolean = vibrator.hasVibrator()

    if(canVibrate){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            // void vibrate (VibrationEffect vibe)

            val pattern = longArrayOf(0, 100, 1000, 300, 200, 100, 500, 200, 100)


            vibrator.vibrate(

                    pattern, 5


            )


        }

    }

}

fun Context.vibrateCancel(){
    val vibrator = this.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibrator.cancel()
}


// Extension property to check whether device has Vibrator
val Context.hasVibrator:Boolean
    get() {
        val vibrator= this.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        return vibrator.hasVibrator()
//        val pattern = longArrayOf(0, 100, 1000)

    }