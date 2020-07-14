package com.jacob.material.android.r.inset

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowInsetsAnimation
import com.jacob.material.android.r.databinding.InsetAnimationActivityBinding

class InsetAnimationActivity : AppCompatActivity() {

    private var _binding: InsetAnimationActivityBinding? = null
    private val binding: InsetAnimationActivityBinding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = InsetAnimationActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.adapter = ConversationAdapter()


        // Tell the Window that our app is going to responsible for fitting for any system windows.
        // This is similar to the now deprecated:
        // view.setSystemUiVisibility(LAYOUT_STABLE | LAYOUT_FULLSCREEN | LAYOUT_FULLSCREEN)
        window.setDecorFitsSystemWindows(false)

        // There are three steps to WindowInsetsAnimations:

        /**
         * 1) Since our Activity has declared `window.setDecorFitsSystemWindows(false)`, we need to
         * handle any [WindowInsets] as appropriate.
         *
         * Our [RootViewDeferringInsetsCallback] will update our attached view's padding to match
         * the combination of the [WindowInsets.Type.systemBars], and selectively apply the
         * [WindowInsets.Type.ime] insets, depending on any ongoing WindowInsetAnimations
         * (see that class for more information).
         */

        val deferringInsetsListener = RootViewDeferringInsetsCallback(
                persistentInsetTypes = WindowInsets.Type.systemBars(),
                deferredInsetTypes = WindowInsets.Type.ime()
        )
        // RootViewDeferringInsetsCallback is both an WindowInsetsAnimation.Callback and an
        // OnApplyWindowInsetsListener, so needs to be set as so.
        binding.root.setWindowInsetsAnimationCallback(deferringInsetsListener)
        binding.root.setOnApplyWindowInsetsListener(deferringInsetsListener)

        /**
         * 2) The second step is reacting to any animations which run. This can be system driven,
         * such as the user focusing on an EditText and on-screen keyboard (IME) coming on screen,
         * or app driven (more on that in step 3).
         *
         * To react to animations, we set an [android.view.WindowInsetsAnimation.Callback] on any
         * views which we wish to react to inset animations. In this example, we want our
         * EditText holder view, and the conversation RecyclerView to react.
         *
         * We use our [TranslateDeferringInsetsAnimationCallback] class, bundled in this sample,
         * which will automatically move each view as the IME animates.
         *
         * Note about [TranslateDeferringInsetsAnimationCallback], it relies on the behavior of
         * [RootViewDeferringInsetsCallback] on the layout's root view.
         */

        binding.messageHolder.setWindowInsetsAnimationCallback(
                TranslateDeferringInsetsAnimationCallback(
                        view = binding.messageHolder,
                        persistentInsetTypes = WindowInsets.Type.systemBars(),
                        deferredInsetTypes = WindowInsets.Type.ime(),
                        // We explicitly allow dispatch to continue down to binding.messageHolder's
                        // child views, so that step 2.5 below receives the call
                        dispatchMode = WindowInsetsAnimation.Callback.DISPATCH_MODE_CONTINUE_ON_SUBTREE
                )
        )
        binding.recyclerView.setWindowInsetsAnimationCallback(
                TranslateDeferringInsetsAnimationCallback(
                        view = binding.recyclerView,
                        persistentInsetTypes = WindowInsets.Type.systemBars(),
                        deferredInsetTypes = WindowInsets.Type.ime()
                )
        )

        /**
         * 2.5) We also want to make sure that our EditText is focused once the IME
         * is animated in, to enable it to accept input. Similarly, if the IME is animated
         * off screen and the EditText is focused, we should clear that focus.
         *
         * The bundled [ControlFocusInsetsAnimationCallback] callback will automatically request
         * and clear focus for us.
         *
         * Since `binding.messageEdittext` is a child of `binding.messageHolder`, this
         * [WindowInsetsAnimation.Callback] will only work if the ancestor view's callback uses the
         * [WindowInsetsAnimation.Callback.DISPATCH_MODE_CONTINUE_ON_SUBTREE] dispatch mode, which
         * we have done above.
         */

        binding.messageEdittext.setWindowInsetsAnimationCallback(
                ControlFocusInsetsAnimationCallback(binding.messageEdittext)
        )

        /**
         * 3) The third step is when the app wants to control and drive an inset animation.
         * This is an optional step, but suits many types of input UIs. The example scenario we
         * use in this sample is that the user can drag open the IME, by over-scrolling the
         * conversation RecyclerView. To enable this, we use a [InsetsAnimationLinearLayout] as a
         * root view in our layout which handles this automatically for scrolling views,
         * through nested scrolling.
         *
         * Alternatively, this sample also contains [InsetsAnimationTouchListener],
         * which is a [android.view.View.OnTouchListener] which does similar for non-scrolling
         * views, detecting raw drag events rather than scroll events to open/close the IME.
         *
         * Internally, both [InsetsAnimationLinearLayout] & [InsetsAnimationTouchListener] use a
         * class bundled in this sample called [SimpleImeAnimationController], which simplifies
         * much of the mechanics for controlling a [WindowInsetsAnimation].
         */
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}