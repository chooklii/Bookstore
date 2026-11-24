package com.bzenth.bookstore.db

import android.provider.BaseColumns

object DbConstants {

    object Book: BaseColumns {
        const val TABLE_NAME = "books"

        const val ROW_ID = "row_id"
        const val NAME = "name"
        const val RATING = "rating"
        const val COMMENT = "comment"
        const val LEARNING = "learning"
}
}