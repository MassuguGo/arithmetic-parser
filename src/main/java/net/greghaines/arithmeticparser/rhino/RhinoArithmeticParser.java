package net.greghaines.arithmeticparser.rhino;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import net.greghaines.arithmeticparser.ArithmeticParser;

/**
 * An implementation that uses the a JavaScript parser to evaluate the expression.
 * 
 * @author Greg Haines
 */
public class RhinoArithmeticParser implements ArithmeticParser {

    /**
     * {@inheritDoc}
     */
    @Override
    public double evaluate(final String expression) {
        double result;
        try {
            final ScriptEngineManager factory = new ScriptEngineManager();
            final ScriptEngine engine = factory.getEngineByName("JavaScript");
            final Object resultObj = engine.eval(expression);
            if (resultObj instanceof Number) {
                result = ((Number) resultObj).doubleValue();
            } else if (resultObj instanceof String) {
                result = Double.parseDouble((String) resultObj);
            } else {
                throw new IllegalStateException("Unknown return type: " + resultObj);
            }
        } catch (ScriptException se) {
            throw new RuntimeException(se);
        }
        return result;
    }
}
