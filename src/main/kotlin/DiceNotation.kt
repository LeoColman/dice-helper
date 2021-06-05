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
package br.com.colman.dicehelper

import br.com.colman.dicehelper.Dice.DiceRollResult
import br.com.colman.dicehelper.DiceNotationParser.NotationContext
import net.objecthunter.exp4j.ExpressionBuilder
import java.util.LinkedList
import java.util.Queue
import kotlin.random.Random

public data class DiceNotation(
    val dice: Dice,
    val operator: String? = null,
    val notation: DiceNotation? = null
) {
    internal constructor(notation: NotationContext) : this(
        Dice(notation.dice()),
        notation.operator()?.text,
        notation.notation()?.let { DiceNotation(it) }
    )

    public val allDice: List<Dice> = listOf(dice) + notation?.allDice.orEmpty()

    public fun roll(random: Random = Random): NotationRollResult {
        val roll = allDice.rollAll(random)
        return NotationRollResult(
            ExpressionBuilder(expression(LinkedList(roll.map { it.result }))).build().evaluate().toInt(),
            roll
        )
    }

    private fun expression(rolls: Queue<List<Int>>): String =
        "" + rolls.poll().sum() + (operator ?: "") + (notation?.expression(rolls) ?: "")

    public data class NotationRollResult(
        val total: Int,
        val results: List<DiceRollResult>
    )
}
