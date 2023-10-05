package com.example.slider_app

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.Switch
import android.widget.Toast
import com.example.slider_button_app.R

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var firstButton: Button
    private lateinit var secondButton: Button
    private lateinit var thirdButton: Button

    private lateinit var myText: EditText
    //    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var mySwitch: Switch
    private lateinit var mySeekBar: SeekBar

    private var switchPosition = false

    private val myButtonClickListener = View.OnClickListener {

        val btn = (it as Button )
        val btnText = btn.text
        Log.i(TAG, "$btnText was clicked")
        true
    }

    private val myButtonLongPressListener = View.OnLongClickListener{
        val btn = (it as Button)
        val btnText = btn.text
        Log.i(TAG, "$btnText was long pressed")
        true
    }

//    private fun setupButtonCallbacks(){
//        this.firstButton.setOnClickListener{
//            val view
//            var btn: Button = (view as Button)
//            btn.text = this.getString(R.string.clicked_text)
//            print("Frist button was clicked")
//
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firstButton = this.findViewById(R.id.button1)
        secondButton = this.findViewById(R.id.button2)
        thirdButton = this.findViewById(R.id.button3)
        myText = this.findViewById(R.id.edit_text)
        mySwitch = this.findViewById(R.id.my_switch)
        mySeekBar = this.findViewById(R.id.seekBar)

        switchPosition = mySwitch.isChecked

        firstButton.setOnClickListener {
            val btn = (it as Button)
            if (switchPosition) {
                val toast = Toast.makeText (this, R.string.switch_was_on, Toast.LENGTH_SHORT)
                toast.show()
            } else {
                val toast = Toast.makeText(this, R.string.switch_was_off, Toast.LENGTH_LONG)
                toast.show()
            }
            btn.text = this.getString(R.string.clicked_button)
            Log.i(TAG, "button 1 clicked")
        }
        // connectViewPointer

        secondButton.setOnClickListener(myButtonClickListener)
        thirdButton.setOnClickListener(myButtonClickListener)

        secondButton.setOnLongClickListener(myButtonLongPressListener)
        thirdButton.setOnLongClickListener(myButtonLongPressListener)

        myText.setOnKeyListener{ view: View, k:Int, event: KeyEvent -> Boolean
            Log.i(TAG, "Character key press was detected $k $event")
            false
        }

        mySwitch.setOnClickListener{
            val sw = (it as Switch)
            switchPosition = sw.isChecked
            Log.i(TAG, "Switch is cliked to ${sw.isChecked}")
        }

        mySeekBar.setOnSeekBarChangeListener(
            object: SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(sb: SeekBar?, p1: Int, p2: Boolean) {
                    Log.i(TAG, "the value of the seekbar is ${sb?.progress}")
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {}
                override fun onStopTrackingTouch(p0: SeekBar?) {}
            })  // End of mySeekBar.setOnSeekBarChangeListener
    }

}
