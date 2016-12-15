package view.template;

public class MissingParameterException extends Exception {

    private String mName;

    public MissingParameterException(String name) {
        mName = name;
    }

    @Override
    public String getMessage() {
        return "Parameter value for '" + mName + "' is missing";
    }
}
