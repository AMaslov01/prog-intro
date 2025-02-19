package expression.exceptions;

import expression.Variable;

public class CheckedVariable extends Variable {

    public CheckedVariable(String variable) {
        super(variable);
        char last = variable.charAt(variable.length() - 1);
        if(last != 'x' && last != 'y' && last != 'z'){
            throw new ParsingException("Illegal varible name: " + variable);
        }
    }
}
