package nl.han.ica.icss.checker;

import nl.han.ica.datastructures.IHANLinkedList;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.ColorLiteral;
import nl.han.ica.icss.ast.literals.PercentageLiteral;
import nl.han.ica.icss.ast.literals.PixelLiteral;
import nl.han.ica.icss.ast.types.ExpressionType;

import java.util.HashMap;
import java.util.List;


public class Checker {
    private IHANLinkedList<HashMap<String, ExpressionType>> variableTypes;

    public void check(AST ast) {
        // variableTypes = new HANLinkedList<>();
        checkStylesheet(ast.root);
    }

    private void checkStylesheet(Stylesheet stylesheet) {
        for (ASTNode child : stylesheet.getChildren()) {
            if (child instanceof Stylerule) {
                checkStylerule((Stylerule) child);
            }
        }
    }

    private void checkStylerule(Stylerule stylerule) {
        for (ASTNode child : stylerule.getChildren()) {
            if (child instanceof Declaration) {
                checkDeclaration((Declaration) child);
            }
        }
    }

    private void checkDeclaration(Declaration declaration) {
        checkPropertyName(declaration.property);
        if (List.of("width", "height").contains(declaration.property.name)) {
            if (!(declaration.expression instanceof PixelLiteral) && !(declaration.expression instanceof PercentageLiteral)) {
                declaration.expression.setError("Invalid type for property '" + declaration.property.name + "'");
            }
        }
        if (List.of("color", "background-color").contains(declaration.property.name)) {
            if (!(declaration.expression instanceof ColorLiteral)) {
                declaration.expression.setError("Invalid type for property '" + declaration.property.name + "'");
            }
        }
    }

    private void checkPropertyName(PropertyName property) {
        if (!List.of("width", "height", "color", "background-color").contains(property.name)) {
            property.setError("Invalid property '" + property.name + "'");
        }
    }
}
