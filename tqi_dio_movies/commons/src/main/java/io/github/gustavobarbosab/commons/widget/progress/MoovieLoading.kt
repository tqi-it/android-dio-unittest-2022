package io.github.gustavobarbosab.commons.widget.progress

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

class MoovieLoading {

    var lastDialogShowed: DialogFragment? = null

    fun showLoading(fragmentManager: FragmentManager) {
        if (isShowingProgress())
            return

        lastDialogShowed = MoovieProgressDialogFragment()
        lastDialogShowed?.show(fragmentManager, TAG)
    }

    fun hideLoading() {
        lastDialogShowed?.let {
            it.dialog?.cancel()
            it.dismissAllowingStateLoss()
        }
    }

    private fun isShowingProgress() = lastDialogShowed?.dialog?.isShowing == true

    companion object {
        private const val TAG = "TAG_LOADING"
    }
}