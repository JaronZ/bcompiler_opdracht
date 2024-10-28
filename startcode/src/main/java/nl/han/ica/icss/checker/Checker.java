package nl.han.ica.icss.checker;

import nl.han.ica.datastructures.HANLinkedList;
import nl.han.ica.datastructures.IHANLinkedList;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.*;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.MultiplyOperation;
import nl.han.ica.icss.ast.operations.SubtractOperation;
import nl.han.ica.icss.ast.types.ExpressionType;

import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;


public class Checker {
    private IHANLinkedList<HashMap<String, ExpressionType>> variableTypes;
    private final HashMap<String, Consumer<Expression>> declarationExpressionCheckers;

    public Checker() {
        declarationExpressionCheckers = new HashMap<>();
        declarationExpressionCheckers.put("width", this::checkSizeExpression);
        declarationExpressionCheckers.put("height", this::checkSizeExpression);
        declarationExpressionCheckers.put("color", this::checkColorExpression);
        declarationExpressionCheckers.put("background-color", this::checkColorExpression);
    }

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
        if (expression instanceof AddOperation || expression instanceof SubtractOperation) {
            return getExpressionType(((Operation) expression).lhs);
        }
        if (expression instanceof MultiplyOperation) {
            return getExpressionType(leftUnlessScalar((Operation) expression));
        }
        if (expression instanceof VariableReference) {
            HashMap<String, ExpressionType> scope = getNearestVariableReferenceScope((VariableReference) expression);
            if (scope != null) {
                return scope.get(((VariableReference) expression).name);
            }
        }
        return ExpressionType.UNDEFINED;
    }

    private Expression leftUnlessScalar(Operation operation) {
        if (operation.lhs instanceof ScalarLiteral) {
            return operation.rhs;
        }
        return operation.lhs;
    }

    private void checkStylerule(Stylerule stylerule) {
        variableTypes.addFirst(new HashMap<>());
        checkStyleruleBody(stylerule.body);
        variableTypes.removeFirst();
    }

    private void checkStyleruleBody(List<ASTNode> body) {
        for (ASTNode child : body) {
            if (child instanceof Declaration) {
                checkDeclaration((Declaration) child);
            } else if (child instanceof IfClause) {
                checkIfClause((IfClause) child);
            }
        }
    }

    private void checkIfClause(IfClause ifClause) {
        variableTypes.addFirst(new HashMap<>());
        checkConditionalExpression(ifClause.conditionalExpression);
        checkStyleruleBody(ifClause.body);
        if (ifClause.elseClause != null) {
            checkStyleruleBody(ifClause.elseClause.body);
        }
        variableTypes.removeFirst();
    }

    private void checkConditionalExpression(Expression conditionalExpression) {
        if (conditionalExpression instanceof Literal && !(conditionalExpression instanceof BoolLiteral)) {
            conditionalExpression.setError("Invalid type for conditional. Expected boolean, got " + conditionalExpression.getClass().getSimpleName());
        } else if (conditionalExpression instanceof VariableReference) {
            checkVariableReference((VariableReference) conditionalExpression, ExpressionType.BOOL);
        } else if (conditionalExpression instanceof Operation) {
            conditionalExpression.setError("Invalid type for conditional. Expected boolean, got operation");
        }
    }

    private void checkDeclaration(Declaration declaration) {
        checkPropertyName(declaration.property);
        checkExpression(declaration.expression);
        if (declarationExpressionCheckers.containsKey(declaration.property.name)) {
            Consumer<Expression> checker = declarationExpressionCheckers.get(declaration.property.name);
            checker.accept(declaration.expression);
        }
    }

    private void checkColorExpression(Expression expression) {
        if (expression instanceof VariableReference) {
            checkVariableReference((VariableReference) expression, ExpressionType.COLOR);
        } else if (!(expression instanceof ColorLiteral)) {
            expression.setError("Invalid type for color property. Expected color, got " + expression.getClass().getSimpleName());
        }
    }

    private void checkSizeExpression(Expression expression) {
        if (expression instanceof VariableReference) {
            checkVariableReference((VariableReference) expression, List.of(ExpressionType.PIXEL, ExpressionType.PERCENTAGE));
        } else if (expression instanceof Operation) {
            checkOperation((Operation) expression);
        } else if (!(expression instanceof PixelLiteral || expression instanceof PercentageLiteral)) {
            expression.setError("Invalid type for size property. Expected pixel or percentage, got " + expression.getClass().getSimpleName());
        }
    }

    private void checkVariableReference(VariableReference reference, ExpressionType expectedType) {
        HashMap<String, ExpressionType> scope = getNearestVariableReferenceScope(reference);
        if (scope == null) {
            reference.setError("Variable '" + reference.name + "' used before assignment");
            return;
        }
        checkVariableReferenceType(reference, scope, expectedType);
    }

    private void checkVariableReference(VariableReference reference, List<ExpressionType> expectedTypes) {
        HashMap<String, ExpressionType> scope = getNearestVariableReferenceScope(reference);
        if (scope == null) {
            reference.setError("Variable '" + reference.name + "' used before assignment");
            return;
        }
        checkVariableReferenceType(reference, scope, expectedTypes);
    }

    private HashMap<String, ExpressionType> getNearestVariableReferenceScope(VariableReference reference) {
        for (int i = 0; i < variableTypes.getSize(); i++) {
            HashMap<String, ExpressionType> scope = variableTypes.get(i);
            if (scope.containsKey(reference.name)) {
                return scope;
            }
        }
        return null;
    }

    private void checkVariableReferenceType(VariableReference reference, HashMap<String, ExpressionType> scope, ExpressionType expectedType) {
        if (expectedType != null) {
            ExpressionType type = scope.get(reference.name);
            if (type != expectedType) {
                reference.setError("Invalid type for variable '" + reference.name + "'. Expected " + expectedType + ", got " + type);
            }
        }
    }

    private void checkVariableReferenceType(VariableReference reference, HashMap<String, ExpressionType> scope, List<ExpressionType> expectedTypes) {
        ExpressionType type = scope.get(reference.name);
        if (!expectedTypes.contains(type)) {
            reference.setError("Invalid type for variable '" + reference.name + "'. Expected one of " + expectedTypes + ", got " + type);
        }
    }

    private void checkPropertyName(PropertyName property) {
        if (!declarationExpressionCheckers.containsKey(property.name)) {
            property.setError("Invalid property '" + property.name + "'");
        }
    }

    private void checkExpression(Expression expression) {
        if (expression instanceof VariableReference) {
            checkVariableReference((VariableReference) expression, (ExpressionType) null);
        } else if (expression instanceof Operation) {
            checkOperation((Operation) expression);
        }
    }

    private void checkOperation(Operation operation) {
        checkOperationHandSide(operation.lhs);
        checkOperationHandSide(operation.rhs);
        if (operation instanceof AddOperation || operation instanceof SubtractOperation) {
            checkAdditiveOperation(operation);
        } else if (operation instanceof MultiplyOperation) {
            checkMultiplicativeOperation(operation);
        }
    }

    private void checkMultiplicativeOperation(Operation operation) {
        ExpressionType left = getExpressionType(operation.lhs);
        ExpressionType right = getExpressionType(operation.rhs);
        if (left != ExpressionType.SCALAR && right != ExpressionType.SCALAR) {
            operation.setError("Cannot multiply non-scalars");
        }
    }

    private void checkAdditiveOperation(Operation operation) {
        ExpressionType left = getExpressionType(operation.lhs);
        ExpressionType right = getExpressionType(operation.rhs);
        if (left != right) {
            operation.setError("Cannot add or subtract different types");
        }
    }

    private void checkOperationHandSide(Expression expression) {
        if (expression instanceof ColorLiteral) {
            expression.setError("Cannot use color literals in operations");
        } else if (expression instanceof Operation) {
            checkOperation((Operation) expression);
        }
    }
}
