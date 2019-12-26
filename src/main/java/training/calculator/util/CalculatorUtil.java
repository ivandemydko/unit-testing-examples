package training.calculator.util;


import java.util.HashMap;
import java.util.Map;

import training.calculator.operation.BiOperation;
import training.calculator.operation.DivideOperation;
import training.calculator.operation.MinusOperation;
import training.calculator.operation.MultiplyOperation;
import training.calculator.operation.PlusOperation;

public interface CalculatorUtil {

    Map<Character, BiOperation> BASIC_OPERATIONS = new HashMap<Character, BiOperation>() {{
        put('+', new PlusOperation());
        put('-', new MinusOperation());
        put('*', new MultiplyOperation());
        put('/', new DivideOperation());
    }};
}
