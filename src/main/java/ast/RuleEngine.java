package ast;
import java.util.List;
import java.util.Map;

public class RuleEngine {

    // Method to create a rule from a string
    public Node parseRuleString(String ruleString) {
        // Simplified parsing logic for demonstration
        // This should be replaced with a robust parser
        if (ruleString.contains("AND")) {
            String[] parts = ruleString.split(" AND ");
            Node left = parseRuleString(parts[0].trim());
            Node right = parseRuleString(parts[1].trim());
            return new Node("operator", left, right, "AND");
        } else if (ruleString.contains("OR")) {
            String[] parts = ruleString.split(" OR ");
            Node left = parseRuleString(parts[0].trim());
            Node right = parseRuleString(parts[1].trim());
            return new Node("operator", left, right, "OR");
        } else {
            // Assuming the rule is an operand, e.g., "age > 30"
            return new Node("operand", null, null, ruleString);
        }
    }

    // Method to combine multiple rule strings into a single AST
    public Node combineRuleStrings(List<String> rules) {
        Node combinedAst = null;
        for (String rule : rules) {
            Node ruleAst = parseRuleString(rule);
            // Logic to combine ASTs (simplified for this example)
            if (combinedAst == null) {
                combinedAst = ruleAst;
            } else {
                combinedAst = new Node("operator", combinedAst, ruleAst, "OR"); // Combine as OR for simplicity
            }
        }
        return combinedAst;
    }

    // Method to evaluate the rule against user attributes
    public boolean evaluateRule(Node node, Map<String, Object> userAttributes) {
        if (node.getType().equals("operand")) {
            String condition = (String) node.getValue();
            String[] parts = condition.split(" ");
            String attr = parts[0];
            attr = attr.replaceAll("[^A-Za-z0-9]","");
            String operator = parts[1];
            Comparable value = (Comparable) userAttributes.get(attr);
            String condValue = parts[2].replaceAll("[^A-Za-z0-9]","");
            Comparable conditionValue = parseConditionValue(condValue);
            return compare(value, operator, conditionValue);
        } else if (node.getType().equals("operator")) {
            boolean leftResult = evaluateRule(node.getLeft(), userAttributes);
            boolean rightResult = evaluateRule(node.getRight(), userAttributes);
            return node.getValue().equals("AND") ? (leftResult && rightResult) : (leftResult || rightResult);
        }
        return false;
    }

    private Comparable parseConditionValue(String value) {
        // Simplified parsing logic for demonstration
        if (value.matches("\\d+")) {
            return Integer.parseInt(value);
        }
        return value; // For strings, return as is
    }

    private boolean compare(Comparable userValue, String operator, Comparable conditionValue) {
        switch (operator) {
            case ">":
                return userValue.compareTo(conditionValue) > 0;
            case "<":
                return userValue.compareTo(conditionValue) < 0;
            case "==":
                return userValue.equals(conditionValue);
            case "!=":
                return !userValue.equals(conditionValue);
            case ">=":
                return userValue.compareTo(conditionValue) >= 0;
            case "<=":
                return userValue.compareTo(conditionValue) <= 0;
            default:
                return false;
        }
    }
}