package nl.han.ica.icss.parser;


import nl.han.ica.datastructures.HANStack;
import nl.han.ica.datastructures.IHANStack;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.*;
import nl.han.ica.icss.ast.operations.*;
import nl.han.ica.icss.ast.selectors.ClassSelector;
import nl.han.ica.icss.ast.selectors.IdSelector;
import nl.han.ica.icss.ast.selectors.TagSelector;

/**
 * This class extracts the ICSS Abstract Syntax Tree from the Antlr Parse tree.
 */
public class ASTListener extends ICSSBaseListener {
	
	//Accumulator attributes:
	private final AST ast;

	//Use this to keep track of the parent nodes when recursively traversing the ast
	private final IHANStack<ASTNode> currentContainer;

	public ASTListener() {
		ast = new AST();
		currentContainer = new HANStack<>();
	}

    public AST getAST() {
        return ast;
    }

	@Override
	public void enterStylesheet(ICSSParser.StylesheetContext ctx) {
		Stylesheet stylesheet = new Stylesheet();
		currentContainer.push(stylesheet);
	}

	@Override
	public void exitStylesheet(ICSSParser.StylesheetContext ctx) {
		Stylesheet stylesheet = (Stylesheet) currentContainer.pop();
		ast.setRoot(stylesheet);
	}

	@Override
	public void enterStylerule(ICSSParser.StyleruleContext ctx) {
		Stylerule rule = new Stylerule();
		currentContainer.push(rule);
	}

	@Override
	public void exitStylerule(ICSSParser.StyleruleContext ctx) {
		Stylerule rule = (Stylerule) currentContainer.pop();
		currentContainer.peek().addChild(rule);
	}

	@Override
	public void enterTagSelector(ICSSParser.TagSelectorContext ctx) {
		TagSelector selector = new TagSelector(ctx.getText());
		currentContainer.push(selector);
	}

	@Override
	public void exitTagSelector(ICSSParser.TagSelectorContext ctx) {
		TagSelector selector = (TagSelector) currentContainer.pop();
		currentContainer.peek().addChild(selector);
	}

	@Override
	public void enterIdSelector(ICSSParser.IdSelectorContext ctx) {
		IdSelector selector = new IdSelector(ctx.getText());
		currentContainer.push(selector);
	}

	@Override
	public void exitIdSelector(ICSSParser.IdSelectorContext ctx) {
		IdSelector selector = (IdSelector) currentContainer.pop();
		currentContainer.peek().addChild(selector);
	}

	@Override
	public void enterClassSelector(ICSSParser.ClassSelectorContext ctx) {
		ClassSelector selector = new ClassSelector(ctx.getText());
		currentContainer.push(selector);
	}

	@Override
	public void exitClassSelector(ICSSParser.ClassSelectorContext ctx) {
		ClassSelector selector = (ClassSelector) currentContainer.pop();
		currentContainer.peek().addChild(selector);
	}

	@Override
	public void enterDeclaration(ICSSParser.DeclarationContext ctx) {
		Declaration declaration = new Declaration();
		currentContainer.push(declaration);
	}

	@Override
	public void exitDeclaration(ICSSParser.DeclarationContext ctx) {
		Declaration declaration = (Declaration) currentContainer.pop();
		currentContainer.peek().addChild(declaration);
	}

	@Override
	public void enterPropertyName(ICSSParser.PropertyNameContext ctx) {
		PropertyName propertyName = new PropertyName(ctx.getText());
		currentContainer.push(propertyName);
	}

	@Override
	public void exitPropertyName(ICSSParser.PropertyNameContext ctx) {
		PropertyName propertyName = (PropertyName) currentContainer.pop();
		currentContainer.peek().addChild(propertyName);
	}

	@Override
	public void enterColorLiteral(ICSSParser.ColorLiteralContext ctx) {
		ColorLiteral literal = new ColorLiteral(ctx.getText());
		currentContainer.push(literal);
	}

	@Override
	public void exitColorLiteral(ICSSParser.ColorLiteralContext ctx) {
		ColorLiteral literal = (ColorLiteral) currentContainer.pop();
		currentContainer.peek().addChild(literal);
	}

	@Override
	public void enterPixelLiteral(ICSSParser.PixelLiteralContext ctx) {
		PixelLiteral literal = new PixelLiteral(ctx.getText());
		currentContainer.push(literal);
	}

	@Override
	public void exitPixelLiteral(ICSSParser.PixelLiteralContext ctx) {
		PixelLiteral literal = (PixelLiteral) currentContainer.pop();
		currentContainer.peek().addChild(literal);
	}

	@Override
	public void enterBoolLiteral(ICSSParser.BoolLiteralContext ctx) {
		BoolLiteral literal = new BoolLiteral(ctx.getText());
		currentContainer.push(literal);
	}

	@Override
	public void exitBoolLiteral(ICSSParser.BoolLiteralContext ctx) {
		BoolLiteral literal = (BoolLiteral) currentContainer.pop();
		currentContainer.peek().addChild(literal);
	}

	@Override
	public void enterScalarLiteral(ICSSParser.ScalarLiteralContext ctx) {
		ScalarLiteral literal = new ScalarLiteral(ctx.getText());
		currentContainer.push(literal);
	}

	@Override
	public void exitScalarLiteral(ICSSParser.ScalarLiteralContext ctx) {
		ScalarLiteral literal = (ScalarLiteral) currentContainer.pop();
		currentContainer.peek().addChild(literal);
	}

	@Override
	public void enterPercentageLiteral(ICSSParser.PercentageLiteralContext ctx) {
		PercentageLiteral literal = new PercentageLiteral(ctx.getText());
		currentContainer.push(literal);
	}

	@Override
	public void exitPercentageLiteral(ICSSParser.PercentageLiteralContext ctx) {
		PercentageLiteral literal = (PercentageLiteral) currentContainer.pop();
		currentContainer.peek().addChild(literal);
	}

	@Override
	public void enterVariableAssignment(ICSSParser.VariableAssignmentContext ctx) {
		VariableAssignment assignment = new VariableAssignment();
		currentContainer.push(assignment);
	}

	@Override
	public void exitVariableAssignment(ICSSParser.VariableAssignmentContext ctx) {
		VariableAssignment assignment = (VariableAssignment) currentContainer.pop();
		currentContainer.peek().addChild(assignment);
	}

	@Override
	public void enterVariableReference(ICSSParser.VariableReferenceContext ctx) {
		VariableReference reference = new VariableReference(ctx.getText());
		currentContainer.push(reference);
	}

	@Override
	public void exitVariableReference(ICSSParser.VariableReferenceContext ctx) {
		VariableReference reference = (VariableReference) currentContainer.pop();
		currentContainer.peek().addChild(reference);
	}

	@Override
	public void enterMultiplyOperation(ICSSParser.MultiplyOperationContext ctx) {
		MultiplyOperation operation = new MultiplyOperation();
		currentContainer.push(operation);
	}

	@Override
	public void exitMultiplyOperation(ICSSParser.MultiplyOperationContext ctx) {
		MultiplyOperation operation = (MultiplyOperation) currentContainer.pop();
		currentContainer.peek().addChild(operation);
	}

	@Override
	public void enterAdditiveOperation(ICSSParser.AdditiveOperationContext ctx) {
		Operation operation = null;
		switch (ctx.operator.getType()) {
			case ICSSParser.PLUS:
				operation = new AddOperation();
				break;
			case ICSSParser.MIN:
				operation = new SubtractOperation();
				break;
		}
		currentContainer.push(operation);
	}

	@Override
	public void exitAdditiveOperation(ICSSParser.AdditiveOperationContext ctx) {
		Operation operation = (Operation) currentContainer.pop();
		currentContainer.peek().addChild(operation);
	}

	@Override
	public void enterLogicalAndOperation(ICSSParser.LogicalAndOperationContext ctx) {
		LogicalAndOperation operation = new LogicalAndOperation();
		currentContainer.push(operation);
	}

	@Override
	public void exitLogicalAndOperation(ICSSParser.LogicalAndOperationContext ctx) {
		LogicalAndOperation operation = (LogicalAndOperation) currentContainer.pop();
		currentContainer.peek().addChild(operation);
	}

	@Override
	public void enterLogicalOrOperation(ICSSParser.LogicalOrOperationContext ctx) {
		LogicalOrOperation operation = new LogicalOrOperation();
		currentContainer.push(operation);
	}

	@Override
	public void exitLogicalOrOperation(ICSSParser.LogicalOrOperationContext ctx) {
		LogicalOrOperation operation = (LogicalOrOperation) currentContainer.pop();
		currentContainer.peek().addChild(operation);
	}

	@Override
	public void enterIfClause(ICSSParser.IfClauseContext ctx) {
		IfClause clause = new IfClause();
		currentContainer.push(clause);
	}

	@Override
	public void exitIfClause(ICSSParser.IfClauseContext ctx) {
		IfClause clause = (IfClause) currentContainer.pop();
		currentContainer.peek().addChild(clause);
	}

	@Override
	public void enterElseClause(ICSSParser.ElseClauseContext ctx) {
		ElseClause clause = new ElseClause();
		currentContainer.push(clause);
	}

	@Override
	public void exitElseClause(ICSSParser.ElseClauseContext ctx) {
		ElseClause clause = (ElseClause) currentContainer.pop();
		currentContainer.peek().addChild(clause);
	}
}