package com.jacob.material.android.r.inset

import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsAnimation

/**
 * A [WindowInsetsAnimation.Callback] which will request and clear focus on the given view,
 * depending on the [WindowInsets.Type.ime] visibility state when an IME [WindowInsetsAnimation]
 * has finished.
 *
 * This is primarily used when animating the [WindowInsets.Type.ime], so that the appropriate view
 * is focused for accepting input from the IME.
 *
 * @param view the view to request/clear focus
 * @param dispatchMode The dispatch mode for this callback.
 * See [WindowInsetsAnimation.Callback.getDispatchMode].
 */


class ControlFocusInsetsAnimationCallback(
        private val view: View,
        dispatchMode: Int = DISPATCH_MODE_STOP
) : WindowInsetsAnimation.Callback(dispatchMode) {

    override fun onProgress(insets: WindowInsets, runningAnimations: List<WindowInsetsAnimation>): WindowInsets {
        // no-op and return the insets
        return insets
    }

    override fun onEnd(animation: WindowInsetsAnimation) {
        if (animation.typeMask and WindowInsets.Type.ime() != 0) {
            // The animation has now finished, so we can check the view's focus state.
            // We post the check because the rootWindowInsets has not yet been updated, but will
            // be in the next message traversal
            view.post {
                checkFocus()
            }
        }
    }

    private fun checkFocus() {
        val imeVisible = view.rootWindowInsets.isVisible(WindowInsets.Type.ime())
        if (imeVisible && view.rootView.findFocus() == null) {
            // If the IME will be visible, and there is not a currently focused view in
            // the hierarchy, request focus on our view
            view.requestFocus()
        } else if (!imeVisible && view.isFocused) {
            // If the IME will not be visible and our view is currently focused, clear the focus
            view.clearFocus()
        }
    }
}
