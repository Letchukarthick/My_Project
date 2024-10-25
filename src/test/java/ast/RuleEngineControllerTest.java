package ast;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={ RuleEngineController.class })
@AutoConfigureMockMvc
public class RuleEngineControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateRule() throws Exception {
        String rule = "age > 30 AND department = 'Sales'";
        ResultActions expect = mockMvc.perform(post("/api/rules/create_rule")
                .contentType("application/json")
                .content("{\"rule_string\": \"" + rule + "\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCombineRules() throws Exception {
        String rule1 = "age > 30 AND department = 'Sales'";
        String rule2 = "salary > 50000 OR experience > 5";
        mockMvc.perform(post("/api/rules/combine_rules")
                .contentType("application/json")
                .content("{\"rules\": [\"" + rule1 + "\", \"" + rule2 + "\"]}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testEvaluateRule() throws Exception {
        String ast = "((age > 30 AND department = 'Sales') OR (age < 25 AND department = 'Marketing'))";
        mockMvc.perform(post("/api/rules/evaluate_rule")
                .contentType("application/json")
                .content("{\"ast\": \"" + ast + "\", \"data\": {\"age\": 35, \"department\": \"Sales\", \"salary\": 60000, \"experience\": 3}}"))
                .andExpect(status().isOk());
    }
}