/*
 * User: joel
 * Date: 2014-05-13
 * Time: 22:37
 */
package se.joelabs.restjs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

/**
 * REST resource for loading JS modules.
 */
@Controller
@EnableAutoConfiguration
public class JsModules {

    public interface Calculate {
        Result apply(Params p);
    }

    public JsModules() {
        try {
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
            engine.eval(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("common.js")));
            engine.eval(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("module1.js")));
            Invocable invocable = (Invocable) engine;

            Params params = new Params(
                    new Params.CostRow[]{new Params.CostRow(true, new int[]{10, 20, 30})},
                    new Params.CoFinancingRow[]{new Params.CoFinancingRow(true, new int[]{3, 11, 19})}
            );
            Calculate c = invocable.getInterface(Calculate.class);
            Result result = c.apply(params);
            System.out.println("result = " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/module/{name}", method = RequestMethod.GET, produces = "application/javascript")
    @ResponseBody
    String getJsModule(@PathVariable String name) throws IOException {
        return loadModule(name);
    }

    @RequestMapping(value = "/calculate/{name}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    Result calculateUsingModule(@PathVariable String name) throws IOException, ScriptException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("common.js")));
        engine.eval(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(name+".js")));
        Invocable invocable = (Invocable) engine;

        Params params = new Params(
                new Params.CostRow[]{new Params.CostRow(true, new int[]{10, 20, 30})},
                new Params.CoFinancingRow[]{new Params.CoFinancingRow(true, new int[]{3, 11, 19})}
        );
        Calculate c = invocable.getInterface(Calculate.class);
        return c.apply(params);
    }


    private String loadModule(String moduleName) throws IOException {
        LineNumberReader lnr = new LineNumberReader(
                new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(moduleName + ".js")));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = lnr.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(JsModules.class, args);
    }
}