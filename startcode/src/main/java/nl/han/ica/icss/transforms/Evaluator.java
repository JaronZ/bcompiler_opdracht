package nl.han.ica.icss.transforms;

import nl.han.ica.datastructures.HANLinkedList;
import nl.han.ica.datastructures.IHANLinkedList;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.BoolLiteral;
import nl.han.ica.icss.ast.literals.PercentageLiteral;
import nl.han.ica.icss.ast.literals.PixelLiteral;
import nl.han.ica.icss.ast.literals.ScalarLiteral;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.SubtractOperation;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiFunction;

public class Evaluator implements Transform {
    private IHANLinkedList<HashMap<String, Literal>> variableValues;

    public Evaluator() {
        variableValues = new HANLinkedList<>();
    }

    @Override
    public void apply(AST ast) {
        variableValues = new HANLinkedList<>();
        applyStylesheet(ast.root);
    }

    private void applyStylesheet(Stylesheet stylesheet) {
        variableValues.addFirst(new HashMap<>());
        for (ASTNode child : stylesheet.getChildren()) {
            if (child instanceof Stylerule) {
                applyStylerule((Stylerule) child);
            } else if (child instanceof VariableAssignment) {
                applyVariableAssignment((VariableAssignment) child);
            }
        }
        variableValues.removeFirst();
    }

    private void applyVariableAssignment(VariableAssignment assignment) {
        Literal value = evaluateExpression(assignment.expression);
        variableValues.getFirst().put(assignment.name.name, value);
    }

    private void applyStylerule(Stylerule stylerule) {
        variableValues.addFirst(new HashMap<>());
        applyStyleruleBody(stylerule.body);
        variableValues.removeFirst();
    }

    private List<ASTNode> evaluateIfClause(IfClause ifClause) {
        BoolLiteral result = (BoolLiteral) evaluateExpression(ifClause.conditionalExpression);
        if (result.value) {
            return applyStyleruleBody(ifClause.body);
        }
        if (ifClause.elseClause != null) {
            return applyStyleruleBody(ifClause.elseClause.body);
        }
        return new ArrayList<>();
    }

    private List<ASTNode> applyStyleruleBody(List<ASTNode> body) {
        for (ASTNode child : List.copyOf(body)) {
            if (child instanceof Declaration) {
                applyDeclaration((Declaration) child);
            } else if (child instanceof VariableAssignment) {
                applyVariableAssignment((VariableAssignment) child);
            } else if (child instanceof IfClause) {
                applyIfClause((IfClause) child, body);
            }
        }
        return body;
    }

    private void applyIfClause(IfClause ifClause, List<ASTNode> body) {
        int index = body.indexOf(ifClause);
        body.addAll(index, evaluateIfClause(ifClause));
        body.remove(ifClause);
    }

    private void applyDeclaration(Declaration declaration) {
        declaration.expression = evaluateExpression(declaration.expression);
    }

    private Literal evaluateExpression(Expression expression) {
        if (expression instanceof Literal) {
            return (Literal) expression;
        } else if (expression instanceof VariableReference) {
            return evaluateVariableReference((VariableReference) expression);
        }
        return evaluateOperation((Operation) expression);
    }

    private Literal evaluateVariableReference(VariableReference reference) {
        for (int i = 0; i < variableValues.getSize(); i++) {
            HashMap<String, Literal> scope = variableValues.get(i);
            if (scope.containsKey(reference.name)) {
                return scope.get(reference.name);
            }
        }
        return null;
    }

    private Literal evaluateOperation(Operation operation) {
        Literal left = evaluateExpression(operation.lhs);
        Literal right = evaluateExpression(operation.rhs);
        if (operation instanceof AddOperation) {
            return evaluateOperation(left, right, Integer::sum);
        }
        if (operation instanceof SubtractOperation) {
            return evaluateOperation(left, right, (a, b) -> a - b);
        }
        return evaluateOperation(left, right, (a, b) -> a * b);
    }

    private Literal evaluateOperation(Literal left, Literal right, BiFunction<Integer, Integer, Integer> eval) {
        int leftValue = getNumericLiteralValue(left);
        int rightValue = getNumericLiteralValue(right);
        int result = eval.apply(leftValue, rightValue);
        try {
            return leftUnlessScalar(left, right).getClass().getDeclaredConstructor(int.class).newInstance(result);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private Literal leftUnlessScalar(Literal left, Literal right) {
        if (left instanceof ScalarLiteral) {
            return right;
        }
        return left;
    }

    private int getNumericLiteralValue(Literal literal) {
        if (literal instanceof PercentageLiteral) {
            return ((PercentageLiteral) literal).value;
        }
        if (literal instanceof PixelLiteral) {
            return ((PixelLiteral) literal).value;
        }
        return ((ScalarLiteral) literal).value;
    }
}
