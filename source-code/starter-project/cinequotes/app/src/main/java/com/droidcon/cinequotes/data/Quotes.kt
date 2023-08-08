package com.droidcon.cinequotes.data

import com.droidcon.cinequotes.R
import com.droidcon.cinequotes.model.Quote

class Quotes() {
    fun loadQuotes(): List<Quote> {
        return listOf(
            Quote(R.string.star_wars_quote, R.string.star_wars_actor, R.string.star_wars_movie, R.drawable.img_star_wars),
            Quote(R.string.dark_knight_quote, R.string.dark_knight_actor, R.string.dark_knight_movie, R.drawable.img_the_joker),
            Quote(R.string.et_quote, R.string.et_actor, R.string.et_movie, R.drawable.img_et),
            Quote(R.string.terminator_quote, R.string.terminator_actor, R.string.terminator_movie, R.drawable.img_the_terminator_2),
            Quote(R.string.titanic_quote, R.string.titanic_actor, R.string.titanic_movie, R.drawable.img_titanic),
            Quote(R.string.toy_story_quote, R.string.toy_story_actor, R.string.toy_story_movie, R.drawable.img_toy_story),
            Quote(R.string.iron_man_quote, R.string.iron_man_actor, R.string.iron_man_movie, R.drawable.img_iron_man),
            Quote(R.string.finding_nemo_quote, R.string.finding_nemo_actor, R.string.finding_nemo_movie, R.drawable.img_finding_nemo),
            )
    }
}