import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import android.view.View

fun blurBackground(view: View, radius: Float = 20f) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        view.setRenderEffect(
            RenderEffect.createBlurEffect(radius, radius, Shader.TileMode.CLAMP)
        )
    }
}
