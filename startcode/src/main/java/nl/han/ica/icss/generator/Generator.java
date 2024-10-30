package nl.han.ica.icss.generator;


import nl.han.ica.icss.ast.*;

import java.util.stream.Collectors;

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
		sb.append(
				stylerule.selectors.stream()
						.map(Object::toString)
						.collect(Collectors.joining(", "))
		);
		sb.append(" {\n");
		for (ASTNode child : stylerule.body) {
			if (child instanceof Declaration) {
				sb.append("  ");
				generateDeclaration((Declaration) child, sb);
				sb.append("\n");
			}
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
