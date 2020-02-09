package com.belatrix.shakeaction.ui.shake

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.belatrix.shakeaction.R
import com.belatrix.shakeaction.util.shake.OnShakeActionListener
import com.belatrix.shakeaction.util.shake.ShakeAction
import kotlinx.android.synthetic.main.activity_main.*

class ShakeActivity : AppCompatActivity(), OnShakeActionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onShakeAction(count: Int) {
        Toast.makeText(this@ShakeActivity, "Shake detected", Toast.LENGTH_LONG).show()
        when (count) {
            1 ->
                appCompatImageView.setImageResource(R.drawable.face_1)
            2 ->
                appCompatImageView.setImageResource(R.drawable.face_2)
            else ->
                appCompatImageView.setImageResource(R.drawable.face_3)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        ShakeAction.unRegisterForShakeEvent(this)
    }

}
