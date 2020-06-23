package com.lucianoluzzi.pokemons.pokemonlist.ui.fragment.matchers

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

object DrawableMatcher {

    fun withDrawable(expectedDrawableId: Int) = object : TypeSafeMatcher<View>(View::class.java) {

        override fun matchesSafely(target: View): Boolean {
            if (target !is ImageView) {
                return false
            }

            if (expectedDrawableId < 0) {
                return target.drawable == null
            }

            val expectedDrawable =
                ContextCompat.getDrawable(target.getContext(), expectedDrawableId) ?: return false

            val actual = getBitmap(target.drawable)
            val expected = getBitmap(expectedDrawable)
            return expected?.sameAs(actual) ?: false
        }

        override fun describeTo(description: Description) {
            description.appendText("with drawable from resource id: ")
            description.appendValue(expectedDrawableId)
        }
    }

    /**
     * [TypeSafeMatcher] for [ImageView] with tinted vector drawables.
     */
    fun withTintedVectorDrawable(
        @DrawableRes vectorDrawable: Int,
        @ColorRes tint: Int? = null,
        tintMode: PorterDuff.Mode = PorterDuff.Mode.SRC_IN
    ) = object : TypeSafeMatcher<View>() {

        override fun describeTo(description: Description) {
            description.appendText("ImageView with drawable same as drawable with id $vectorDrawable")
            tint?.let { description.appendText(", tint color id: $tint, mode: $tintMode") }
        }

        override fun matchesSafely(view: View): Boolean {
            if (view !is ImageView) {
                return false
            }
            val context = view.context
            val tintColor = tint?.toColor(context)
            val expectedBitmap =
                context.getDrawable(vectorDrawable)?.tinted(tintColor, tintMode)?.toBitmap()
            val actualBitmap = view.drawable?.toBitmap()

            return expectedBitmap == actualBitmap || expectedBitmap?.sameAs(actualBitmap) ?: false
        }

        private fun Int.toColor(context: Context) = ContextCompat.getColor(context, this)

        private fun Drawable.tinted(
            @ColorInt tintColor: Int? = null,
            tintMode: PorterDuff.Mode = PorterDuff.Mode.SRC_IN
        ) =
            apply {
                setTintList(tintColor?.toColorStateList())
                setTintMode(tintMode)
            }

        private fun Int.toColorStateList() = ColorStateList.valueOf(this)
    }

    /**
     * [TypeSafeMatcher] for [TextView] with compound drawables.
     *
     * [gravity] parameter is used to specify the position of the compound drawables, only supported values are
     * [Gravity.LEFT], [Gravity.TOP], [Gravity.RIGHT], [Gravity.BOTTOM]
     */
    fun withCompoundDrawable(
        @DrawableRes expectedId: Int,
        gravity: Int
    ) = object : TypeSafeMatcher<View>() {

        override fun matchesSafely(target: View): Boolean {
            if (target !is TextView) {
                return false
            }

            val actualDrawable: Drawable? = when (gravity) {
                Gravity.LEFT -> target.compoundDrawables[0]
                Gravity.TOP -> target.compoundDrawables[1]
                Gravity.RIGHT -> target.compoundDrawables[2]
                Gravity.BOTTOM -> target.compoundDrawables[3]
                else -> null
            }

            return areDrawablesSame(target.context, actualDrawable, expectedId)
        }

        override fun describeTo(description: Description) {
            description.appendText("with compound drawable from resource id: ")
            description.appendValue(expectedId)
        }
    }

    private fun areDrawablesSame(
        context: Context,
        actualDrawable: Drawable?,
        expectedDrawableId: Int
    ): Boolean {
        val expectedDrawable = context.getDrawable(expectedDrawableId)

        return expectedDrawable == actualDrawable ||
                expectedDrawable?.toBitmap()?.sameAs(actualDrawable?.toBitmap()) ?: false
    }

    private fun getBitmap(drawable: Drawable?): Bitmap? {
        if (drawable == null) {
            return null
        }
        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }
}
