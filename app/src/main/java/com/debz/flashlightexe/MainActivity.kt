package com.debz.flashlightexe

import android.content.Context
import android.hardware.camera2.CameraManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import com.debz.flashlightexe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var camM: CameraManager
    private lateinit var powerBtn: ImageButton
    var isFlash = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        powerBtn = binding.ibPower
        camM = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        powerBtn.setOnClickListener(){
            fLight(it)
        }
    }

    private fun fLight(it: View?) {
        if(!isFlash){
            val cameraListID=camM.cameraIdList[0]
            camM.setTorchMode(cameraListID,true)
            isFlash=true
            powerBtn.setImageResource(R.drawable.ic_power_on)
            textMessage("Flashlight On", this)
        }
        else {
            val cameraListID=camM.cameraIdList[0]
            camM.setTorchMode(cameraListID,false)
            isFlash=false
            powerBtn.setImageResource(R.drawable.ic_power_off)
            textMessage("Flashlight Off", this)

        }

    }

    private fun textMessage(s: String, c: Context) {
        Toast.makeText(c,s,Toast.LENGTH_SHORT).show()

    }
}