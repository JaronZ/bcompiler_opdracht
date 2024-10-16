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
			}
		}
	}

	private void generateStylerule(Stylerule stylerule, StringBuilder sb) {
		sb.append(stylerule.selectors.get(0));
		sb.append(" {\n");
		for (ASTNode child : stylerule.body) {
			sb.append("\t");
			generateDeclaration((Declaration) child, sb);
		}
		sb.append("\n}");
	}

	private void generateDeclaration(Declaration declaration, StringBuilder sb) {
		sb.append("Declaration");
	}
}
