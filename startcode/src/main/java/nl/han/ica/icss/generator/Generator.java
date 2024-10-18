package nl.han.ica.icss.generator;


import nl.han.ica.icss.ast.*;

public class Generator {
	public String generate(AST ast) {
		StringBuilder builder = new StringBuilder();
        generateStylesheet(ast.root, builder);
		return builder.toString();
	}

	private void generateStylesheet(Stylesheet stylesheet, StringBuilder sb) {
		for (ASTNode child : stylesheet.getChildren()) {
			if (child instanceof Stylerule) {
				generateStylerule((Stylerule) child, sb);
				sb.append("\n");
			}
		}
	}

	private void generateStylerule(Stylerule stylerule, StringBuilder sb) {
		for (Selector selector : stylerule.selectors) {
			sb.append(selector);
		}
		sb.append(" {\n");
		for (ASTNode child : stylerule.body) {
			sb.append("  ");
			generateDeclaration((Declaration) child, sb);
			sb.append("\n");
		}
		sb.append("}");
	}

	private void generateDeclaration(Declaration declaration, StringBuilder sb) {
		sb.append(declaration.property.name);
		sb.append(": ");
		generateExpression(declaration.expression, sb);
		sb.append(";");
	}

	private void generateExpression(Expression expression, StringBuilder sb) {
		if (expression instanceof Literal) {
			sb.append(((Literal) expression).toStringRepresentation());
		}
	}
}
