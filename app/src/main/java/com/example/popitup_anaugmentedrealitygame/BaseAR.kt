package com.example.popitup_anaugmentedrealitygame

import android.annotation.SuppressLint
import android.graphics.Color
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
import android.view.Display
import com.google.ar.core.Anchor
import com.google.ar.sceneform.AnchorNode
import kotlinx.android.synthetic.main.activity_base_a_r.*
import java.util.*

class BaseAR : AppCompatActivity() {

    private lateinit var scene: Scene
    private lateinit var camera: Camera
    private lateinit var bulletRenderable: ModelRenderable
    private var shouldStartTimer: Boolean = true
    private var balloonsLeft: Int = 20
    private lateinit var point: Point

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val display: Display = windowManager.defaultDisplay
        point = Point()
        display.getRealSize(point)

        setContentView(R.layout.activity_base_a_r)

        val arFragment: BaseARFrag = supportFragmentManager.findFragmentById(R.id.arFragment) as BaseARFrag

        scene = arFragment.arSceneView.scene
        camera = scene.camera

        addBalloonsToScene()

        buildBulletModel()

        shootButton.setOnClickListener {
            if (shouldStartTimer) {
                startTimer()
                shouldStartTimer = false
            }
            shoot()
        }
    }

    private fun shoot() {
        val ray: Ray = camera.screenPointToRay(point.x / 2f, point.y / 2f)
        val node: Node = Node()
        node.renderable = bulletRenderable
        scene.addChild(node)
        Thread {
            for (i in 0 until 200) {

                runOnUiThread {
                    val vector: Vector3 = ray.getPoint(i * 0.1f)
                    node.worldPosition = vector

                    val nodeInContact: Node? = scene.overlapTest(node)

                    if (nodeInContact != null) {
                        balloonsLeft--
                        balloonsCountText.text = "Balloons left: $balloonsLeft"
                        scene.removeChild(nodeInContact)
                    }
                }
                Thread.sleep(10)
            }
            runOnUiThread{
                scene.removeChild(node)
            }
        }.start()
    }

    @SuppressLint("SetTextI18n")
    private fun startTimer() {
        Thread {
            var seconds: Int = 0

            while (balloonsLeft > 0) {
                Thread.sleep(1000)
                seconds++
                val minutesPassed: Int = seconds / 60
                val secondsPassed: Int = seconds % 60

                runOnUiThread {
                    timerText.text = "$minutesPassed:$secondsPassed"
                }
            }
        }.start()
    }

    private fun buildBulletModel() {
        val vector3: Vector3 = Vector3(0f, 0f, 0f)
        Texture.builder()
            .setSource(this, R.drawable.texture)
            .build()
            .thenAccept{
                MaterialFactory.makeOpaqueWithTexture(this, it)
                    .thenAccept { it1 ->
                        bulletRenderable = ShapeFactory
                            .makeSphere(0.01f, vector3, it1)
                    }
            }
    }

    private fun addBalloonsToScene() {

        val color: com.google.ar.sceneform.rendering.Color = com.google.ar.sceneform.rendering.Color()

        MaterialFactory.makeOpaqueWithColor(this, color).thenAccept {
            for (i in 0 until 20) {

                val random: Random = Random()
                val x: Int = random.nextInt(10)
                var z: Int = random.nextInt(10)
                val y: Int = random.nextInt(20)

                z = -z

                val centre: Vector3 = Vector3(x.toFloat(), y/10f, z.toFloat())

                val renderable: ModelRenderable = ShapeFactory.makeSphere(0.1f, centre, it)

                val node: Node = Node()
                node.renderable = renderable
                scene.addChild(node)

            }
        }
    }
}