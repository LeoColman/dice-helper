/*
 * Copyright 2021 Leonardo Colman Lopes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
@file:Suppress("MaxLineLength")
package br.com.colman.dicehelper

import br.com.colman.dicehelper.Dice.DiceRollResult
import br.com.colman.dicehelper.DiceNotationParser.DiceContext
import kotlin.random.Random

public fun List<Dice>.rollAll(random: Random = Random): List<DiceRollResult> = map { DiceRollResult(it, it.roll(random)) }

public interface Dice {
    public fun roll(random: Random = Random): List<Int>

    public companion object {
        internal operator fun invoke(dice: DiceContext) =
            if (dice.fixed != null) FixedDice(dice) else RandomDice(dice)
    }

    public data class DiceRollResult(val dice: Dice, val result: List<Int>)
}

public data class FixedDice(val value: Int) : Dice {
    internal constructor(context: DiceContext) : this(context.fixed.text.toInt())

    override fun roll(random: Random): List<Int> = listOf(value)
}

public data class RandomDice(val amount: Int, val maxFaceValue: Int) : Dice {
    internal constructor(context: DiceContext) : this(
        context.amount?.text?.toInt() ?: 1,
        context.face.text.toInt()
    )

    override fun roll(random: Random): List<Int> = List(amount) { random.nextInt(1, maxFaceValue + 1) }
}
