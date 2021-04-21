package com.example.popitup_anaugmentedrealitygame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.ar.sceneform.Camera
import com.google.ar.sceneform.Scene
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.collision.Ray;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.MaterialFactory;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.ShapeFactory;
import com.google.ar.sceneform.rendering.Texture;
import android.graphics.Point;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.net.Uri;

class BaseAR : AppCompatActivity() {

    private lateinit var scene: Scene
    private lateinit var camera: Camera

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_a_r)

        val arFragment: BaseARFrag = supportFragmentManager.findFragmentById(R.id.arFragment) as BaseARFrag

        scene = arFragment.arSceneView.scene
        camera = scene.camera
    }

    private fun addBalloonsToScene() {
        ModelRenderable
                .builder()
                .setSource(this, Uri.parse("balloons.sfb"))
                .build()
                .thenAccept(renderable -> {

        })
    }
}