package com.droidcon.cinequotes.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Quote(
  @StringRes val movieQuote: Int,
  @StringRes val movieActor: Int,
  @StringRes val movieTitle: Int,
  @DrawableRes val movieImage: Int)