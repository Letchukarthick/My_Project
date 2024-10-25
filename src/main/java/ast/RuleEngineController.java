package ast;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rules")
public class RuleEngineController {

    private final RuleEngine ruleEngine = new RuleEngine();

    @PostMapping("/create_rule")
    public Node createRule(@RequestBody Map<String, String> request) {
        String ruleString = request.get("rule_string");
        return ruleEngine.parseRuleString(ruleString);
    }

    @PostMapping("/combine_rules")
    public Node combineRules(@RequestBody Map<String, List<String>> request) {
        List<String> rules = request.get("rules");
        return ruleEngine.combineRuleStrings(rules);
    }

    @PostMapping("/evaluate_rule")
    public boolean evaluateRule(@RequestBody Map<String, Object> request) {
        Node ast = ruleEngine.parseRuleString((String) request.get("ast")); // This should be AST in real usage
        Map<String, Object> data = (Map<String, Object>) request.get("data");
        return ruleEngine.evaluateRule(ast, data);
    }
}