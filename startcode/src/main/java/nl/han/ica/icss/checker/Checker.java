package nl.han.ica.icss.checker;

import nl.han.ica.datastructures.HANLinkedList;
import nl.han.ica.datastructures.IHANLinkedList;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.*;
import nl.han.ica.icss.ast.types.ExpressionType;

import java.util.HashMap;
import java.util.List;


public class Checker {
    private IHANLinkedList<HashMap<String, ExpressionType>> variableTypes;

    public void check(AST ast) {
        variableTypes = new HANLinkedList<>();
        checkStylesheet(ast.root);
    }

    private void checkStylesheet(Stylesheet stylesheet) {
        variableTypes.addFirst(new HashMap<>());
        for (ASTNode child : stylesheet.getChildren()) {
            if (child instanceof Stylerule) {
                checkStylerule((Stylerule) child);
            } else if (child instanceof VariableAssignment) {
                checkVariableAssignment((VariableAssignment) child);
            }
        }
        variableTypes.removeFirst();
    }

    private void checkVariableAssignment(VariableAssignment assignment) {
        checkExpression(assignment.expression);
        ExpressionType type = getExpressionType(assignment.expression);
        variableTypes.getFirst().put(assignment.name.name, type);
    }

    private ExpressionType getExpressionType(Expression expression) {
        if (expression instanceof BoolLiteral) {
            return ExpressionType.BOOL;
        }
        if (expression instanceof ColorLiteral) {
            return ExpressionType.COLOR;
        }
        if (expression instanceof PercentageLiteral) {
            return ExpressionType.PERCENTAGE;
        }
        if (expression instanceof PixelLiteral) {
            return ExpressionType.PIXEL;
        }
        if (expression instanceof ScalarLiteral) {
            return ExpressionType.SCALAR;
        }
        return null;
    }

    private void checkStylerule(Stylerule stylerule) {
        variableTypes.addFirst(new HashMap<>());
        for (ASTNode child : stylerule.getChildren()) {
            if (child instanceof Declaration) {
                checkDeclaration((Declaration) child);
            }
        }
        variableTypes.removeFirst();
    }

    private void checkDeclaration(Declaration declaration) {
        checkPropertyName(declaration.property);
        checkExpression(declaration.expression);
        if (List.of("width", "height").contains(declaration.property.name)) {
            if (!(declaration.expression instanceof PixelLiteral) && !(declaration.expression instanceof PercentageLiteral) && !(declaration.expression instanceof Operation)) {
                declaration.expression.setError("Invalid type for property '" + declaration.property.name + "'");
            }
        }
        if (List.of("color", "background-color").contains(declaration.property.name)) {
            if (!(declaration.expression instanceof ColorLiteral)) {
                declaration.expression.setError("Invalid type for property '" + declaration.property.name + "'");
            }
        }
    }

    private void checkVariableReference(VariableReference reference) {
        for (int i = 0; i < variableTypes.getSize(); i++) {
            HashMap<String, ExpressionType> scope = variableTypes.get(i);
            if (scope.containsKey(reference.name)) {
                return;
            }
        }
        reference.setError("Variable '" + reference.name + "' used before assignment");
    }

    private void checkPropertyName(PropertyName property) {
        if (!List.of("width", "height", "color", "background-color").contains(property.name)) {
            property.setError("Invalid property '" + property.name + "'");
        }
    }

    private void checkExpression(Expression expression) {
        if (expression instanceof VariableReference) {
            checkVariableReference((VariableReference) expression);
        } else if (expression instanceof Operation) {
            checkOperation((Operation) expression);
        }
    }

    private void checkOperation(Operation expression) {
        checkOperationHandSide(expression.lhs);
        checkOperationHandSide(expression.rhs);
    }

    private void checkOperationHandSide(Expression expression) {
        if (expression instanceof ColorLiteral) {
            expression.setError("Cannot use color literals in operations");
        } else if (expression instanceof Operation) {
            checkOperation((Operation) expression);
        }
    }
}
