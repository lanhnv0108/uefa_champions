package com.lanh.uefachampions.screen.base

import androidx.recyclerview.widget.DiffUtil

open class BaseDiffUtil<T> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }
}

inline fun <reified A, reified B> combineDiffUtil(
    diffUtilA: BaseDiffUtil<A>,
    diffUtilB: BaseDiffUtil<B>
): BaseDiffUtil<Any> {
    return object : BaseDiffUtil<Any>() {
        override fun areItemsTheSame(
            oldItem: Any,
            newItem: Any
        ): Boolean {
            return when {
                oldItem is A && newItem is A -> diffUtilA.areItemsTheSame(oldItem, newItem)
                oldItem is B && newItem is B -> diffUtilB.areItemsTheSame(oldItem, newItem)
                else -> false
            }
        }

        override fun areContentsTheSame(
            oldItem: Any,
            newItem: Any
        ): Boolean {
            return when {
                oldItem is A && newItem is A -> diffUtilA.areContentsTheSame(oldItem, newItem)
                oldItem is B && newItem is B -> diffUtilB.areContentsTheSame(oldItem, newItem)
                else -> false
            }
        }
    }
}

inline fun <reified A, reified B, reified C> combineDiffUtil(
    diffUtilA: BaseDiffUtil<A>,
    diffUtilB: BaseDiffUtil<B>,
    diffUtilC: BaseDiffUtil<C>
): BaseDiffUtil<Any> {
    val aAndB = combineDiffUtil(diffUtilA, diffUtilB)
    return object : BaseDiffUtil<Any>() {
        override fun areItemsTheSame(
            oldItem: Any,
            newItem: Any
        ): Boolean {
            return when {
                oldItem is C && newItem is C -> diffUtilC.areItemsTheSame(oldItem, newItem)
                else -> aAndB.areItemsTheSame(oldItem, newItem)
            }
        }

        override fun areContentsTheSame(
            oldItem: Any,
            newItem: Any
        ): Boolean {
            return when {
                oldItem is C && newItem is C -> diffUtilC.areContentsTheSame(oldItem, newItem)
                else -> aAndB.areContentsTheSame(oldItem, newItem)
            }
        }
    }
}

inline fun <reified A, reified B, reified C, reified D> combineDiffUtil(
    diffUtilA: BaseDiffUtil<A>,
    diffUtilB: BaseDiffUtil<B>,
    diffUtilC: BaseDiffUtil<C>,
    diffUtilD: BaseDiffUtil<D>
): BaseDiffUtil<Any> {
    val aAndBAndC = combineDiffUtil(diffUtilA, diffUtilB, diffUtilC)
    return object : BaseDiffUtil<Any>() {
        override fun areItemsTheSame(
            oldItem: Any,
            newItem: Any
        ): Boolean {
            return when {
                oldItem is D && newItem is D -> diffUtilD.areItemsTheSame(oldItem, newItem)
                else -> aAndBAndC.areItemsTheSame(oldItem, newItem)
            }
        }

        override fun areContentsTheSame(
            oldItem: Any,
            newItem: Any
        ): Boolean {
            return when {
                oldItem is D && newItem is D -> diffUtilD.areContentsTheSame(oldItem, newItem)
                else -> aAndBAndC.areContentsTheSame(oldItem, newItem)
            }
        }
    }
}
