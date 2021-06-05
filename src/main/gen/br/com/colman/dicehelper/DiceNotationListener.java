// Generated from br/com/colman/dicehelper/DiceNotation.g4 by ANTLR 4.9.2

package br.com.colman.dicehelper;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link DiceNotationParser}.
 */
public interface DiceNotationListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link DiceNotationParser#notation}.
	 * @param ctx the parse tree
	 */
	void enterNotation(DiceNotationParser.NotationContext ctx);
	/**
	 * Exit a parse tree produced by {@link DiceNotationParser#notation}.
	 * @param ctx the parse tree
	 */
	void exitNotation(DiceNotationParser.NotationContext ctx);
	/**
	 * Enter a parse tree produced by {@link DiceNotationParser#dice}.
	 * @param ctx the parse tree
	 */
	void enterDice(DiceNotationParser.DiceContext ctx);
	/**
	 * Exit a parse tree produced by {@link DiceNotationParser#dice}.
	 * @param ctx the parse tree
	 */
	void exitDice(DiceNotationParser.DiceContext ctx);
	/**
	 * Enter a parse tree produced by {@link DiceNotationParser#operator}.
	 * @param ctx the parse tree
	 */
	void enterOperator(DiceNotationParser.OperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link DiceNotationParser#operator}.
	 * @param ctx the parse tree
	 */
	void exitOperator(DiceNotationParser.OperatorContext ctx);
}