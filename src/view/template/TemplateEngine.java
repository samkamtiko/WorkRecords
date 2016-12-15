package view.template;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateEngine {
    private static final String VAR_BEGIN = "<%";
    private static final String VAR_END = "%>";

    private static final String FOR_BEGIN = "<%!";
    private static final String FOR_END = "!%>";

    private String mTemplate;
    private HashMap<String, String> mVariables;
    private HashMap<String, Iterable> mCollections;

    public TemplateEngine() {
        mVariables = new HashMap<>();
        mCollections = new HashMap<>();
    }

    public void setTemplate(String template) {
        mTemplate = template;
    }

    public void addVariable(String name, String value) {
        mVariables.put(name, value);
    }

    public void addCollection(String name, Iterable value) {
        mCollections.put(name, value);
    }

    public String transform() throws MissingParameterException {
        String result = mTemplate;
        Pattern pattern = Pattern.compile("<%\\h*([\\w]+)\\h*%>");

        Matcher matcher = pattern.matcher(result);
        while(matcher.find()) {
            String matched = matcher.group(1);

            if (!mVariables.containsKey(matched)) {
                throw new MissingParameterException(matched);
            }
            String replacement = mVariables.get(matched);
            
            // TODO: how to write more efficent?
            result = new StringBuilder(result)
                    .replace(matcher.start(), matcher.end(), replacement)
                    .toString();

            matcher = pattern.matcher(result);
        }

        return result;
    }
}
