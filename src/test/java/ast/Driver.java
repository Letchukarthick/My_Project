package ast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Driver {
	public static void main(String[] args) {

		// create rule
		String rule = "age > 30 AND department == 'Sales'";
		RuleEngineController rec = new RuleEngineController();
		Map<String, String> request = new HashMap<String, String>();
		request.put("rule_string", rule);
		Node createRule = rec.createRule(request);
		Object value = createRule.getValue();
		System.out.println(value);

		// combine rules
		String rule1 = "age > 30 AND department == 'Sales'";
		String rule2 = "salary > 50000 OR experience > 5";
		Map<String, List<String>> request2 = new HashMap<String, List<String>>();
		List<String> listString = new ArrayList<String>();
		listString.add(rule1);
		listString.add(rule2);
		request2.put("rules", listString);
		Node combineRules = rec.combineRules(request2);
		System.out.println(combineRules.getValue().toString());
		System.out.println(combineRules.getLeft().getLeft().getValue().toString());
		System.out.println(combineRules.getRight().getValue().toString());
		System.out.println(combineRules.getRight().getRight().getValue().toString());

		// evalute rule
		String rule4 = "((age > 30 AND department == 'Sales') OR (age < 25 AND department == 'Marketing'))";

		Map<String, String> request4 = new HashMap<String, String>();
		request4.put("rule_string", rule4);
		Node createRule1 = rec.createRule(request4);
		Map<String, Object> key = new HashMap<String, Object>();
		key.put("age", 35);
		key.put("department", "Sales");
		key.put("salary", 60000);
		key.put("experience", 3);
		Map<String, Object> request3 = new HashMap<String, Object>();
		request3.put("ast", rule4);
		request3.put("data", key);
		boolean evaluateRule = rec.evaluateRule(request3);
		System.out.println(evaluateRule);
	}
}
