package com.jacob.material.android.r.inset

import android.animation.ValueAnimator
import android.graphics.Insets
import android.os.Bundle
import android.os.CancellationSignal
import android.util.Log
import android.view.*
import android.view.WindowInsetsAnimationControlListener
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.jacob.material.android.r.R
import com.jacob.material.android.r.databinding.InsetAniAccountActivityBinding


class InsetAniAccountActivity : AppCompatActivity() {

    private var _binding: InsetAniAccountActivityBinding? = null
    private val binding: InsetAniAccountActivityBinding get() = _binding!!
    private var insetsAnimationController: WindowInsetsAnimationController? = null
    private var contentBottom:Int = -1

    private val animationControlListener: WindowInsetsAnimationControlListener by lazy {
        object : WindowInsetsAnimationControlListener {
            override fun onReady(controller: WindowInsetsAnimationController, types: Int){
                insetsAnimationController = controller
                showImeInsets()
            }
            override fun onCancelled(controller: WindowInsetsAnimationController?) {
                insetsAnimationController = controller
            }

            override fun onFinished(controller: WindowInsetsAnimationController) {
                insetsAnimationController = controller
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = InsetAniAccountActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //step 1
        window.setDecorFitsSystemWindows(false)
        binding.rootConstraintLayout.setOnApplyWindowInsetsListener(ApplyWindowInsetsListener())

        //step 2
        val viewTreeObserver: ViewTreeObserver = binding.contentLinearLayout.viewTreeObserver
        if (viewTreeObserver.isAlive) {
            viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    binding.contentLinearLayout.viewTreeObserver.removeOnGlobalLayoutListener(this)
                    val point = IntArray(2)
                    val windowHeight = windowManager.defaultDisplay.height
                    binding.contentLinearLayout.post {
                        binding.contentLinearLayout.getLocationOnScreen(point) // or getLocationInWindow(point)
                        contentBottom = windowHeight - (point[1] + binding.contentLinearLayout.height)
                    }
                }
            })
        }

        binding.rootConstraintLayout.setWindowInsetsAnimationCallback(WindowInsetsAnimationCallback())
    }





    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    private fun showImeInsets(){
        val controller = insetsAnimationController ?: throw IllegalStateException("")
        var showInset = controller.shownStateInsets

        val from: Int = 0
        val to: Int = showInset.bottom
        val animator = ValueAnimator.ofInt(from, to)
        animator.duration = 1000
        animator.addUpdateListener { animation ->
            val currentValue = animation.animatedValue as Int
            val alpha = currentValue.toFloat()/to.toFloat()

            controller.setInsetsAndAlpha(
                    Insets.of(0, 0, 0, currentValue),
                    alpha,
                    1f
            )

            if(currentValue >= to){
                controller.finish(true)
            }
        }
        animator.start()

    }

    inner class ApplyWindowInsetsListener : View.OnApplyWindowInsetsListener{
        override fun onApplyWindowInsets(view: View, windowInsets: WindowInsets): WindowInsets {
            val systemBarTypes = WindowInsets.Type.systemBars()
            val systemBarInsets = windowInsets.getInsets(systemBarTypes)
            view.setPadding(systemBarInsets.left, systemBarInsets.top, systemBarInsets.right, systemBarInsets.bottom)

            //step 3
            val imeIsVisible = windowInsets.isVisible(WindowInsets.Type.ime())
            if(imeIsVisible){
                if(insetsAnimationController == null){
                    binding.contentLinearLayout.windowInsetsController!!.controlWindowInsetsAnimation(
                            WindowInsets.Type.ime(),
                            -1,
                            LinearInterpolator(),
                            CancellationSignal(),
                            animationControlListener
                    )
                }
            }else{
                insetsAnimationController = null
            }

            return WindowInsets.CONSUMED
        }

    }

    inner class WindowInsetsAnimationCallback : WindowInsetsAnimation.Callback(DISPATCH_MODE_STOP) {
        override fun onProgress(insets: WindowInsets, runningAnimations: MutableList<WindowInsetsAnimation>): WindowInsets {
            val navigationBarsInset = insets.getInsets(WindowInsets.Type.navigationBars())
            val imeInset = insets.getInsets(WindowInsets.Type.ime())

            val diff = Insets.subtract(imeInset, navigationBarsInset).let {
                Insets.max(it, Insets.NONE)
            }

            var insetHeight =  (diff.bottom - diff.top) + resources.getDimensionPixelSize(R.dimen.dp_24)
            if(insetHeight < contentBottom){
                binding.contentLinearLayout.translationY = 0f
            }else{
                binding.contentLinearLayout.translationY = (contentBottom - insetHeight).toFloat()
            }
            return insets
        }
    }





}





