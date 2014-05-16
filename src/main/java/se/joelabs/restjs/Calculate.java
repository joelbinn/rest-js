/*
 * User: joel
 * Date: 2014-05-15
 * Time: 23:04
 */
package se.joelabs.restjs;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Collection;

import static com.google.common.collect.Lists.*;

/**
 * A calculator powered by javascript
 */
public class Calculate {
    private static final String jsFunctionBody =
            "var res = {" +
            "  sum: (a+b), " +
            "  somestuff: {z:[1,2,3]}" +
            "};";

    private static final String jsScript =
            "function calculate(a,b){" +
            jsFunctionBody +
            "  var Stuff = Java.type(\"se.joelabs.restjs.Calculate.Stuff\");" +
            "  var stuff = new Stuff();" +
            "  stuff.z = res.somestuff.z;" +
            "  var Result = Java.type(\"se.joelabs.restjs.Calculate.Result\");" +
            "  var result = new Result();" +
            "  result.sum = res.sum;" +
            "  result.stuff = stuff;" +
            "  return result;" +
            "}";

    public static class Stuff {
        public int[] z;
    }

    public static class Result {
        public int sum;
        public Stuff stuff;
    }


    public Calculate() {
    }

    public Integer apply(int a, int b) throws ScriptException, NoSuchMethodException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval(
                jsScript
                   );
        Invocable invocable = (Invocable) engine;
        return (Integer) (invocable.invokeFunction("calculate", a, b));
    }

    public String getJsFunctionBody() {
        return jsFunctionBody;
    }

    public Collection<String> getArguments() {
        return newArrayList("a", "b");
    }
}
