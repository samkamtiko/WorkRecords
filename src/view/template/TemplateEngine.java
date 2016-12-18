package view.template;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateEngine {
    private String mTemplate;
    private HashMap<String, String> mVariables;
    private HashMap<String, Iterable<String>> mCollections;

    public TemplateEngine() {
        mVariables = new HashMap<>();
        mCollections = new HashMap<>();
    }

    public void setTemplate(String template) {
        mTemplate = template;
    }

    public void addVariable(String name, String value) {
        if (value == null)
            value = "";
        mVariables.put(name, value);
    }

    public void addCollection(String name, Iterable value) {
        mCollections.put(name, value);
    }

    private String replaceVariables(String text) throws MissingParameterException {
        return replaceVariablesWithDict(mVariables, text, false);
    }

    private String replaceVariablesWithDict(HashMap<String, String> dict, String text, boolean skipUnknown)
            throws MissingParameterException {
        String result = text;
        Pattern pattern = Pattern.compile("<%\\h*([\\w]+)\\h*%>");

        Matcher matcher = pattern.matcher(result);
        while(matcher.find()) {
            String matched = matcher.group(1);

            if (!dict.containsKey(matched)) {
                if (!skipUnknown) {
                    throw new MissingParameterException(matched);
                } else {
                    continue;
                }
            }
            String replacement = dict.get(matched);

            // TODO: how to write more efficent?
            result = new StringBuilder(result)
                    .replace(matcher.start(), matcher.end(), replacement)
                    .toString();

            matcher = pattern.matcher(result);
        }

        return result;
    }

    // Loops are represented in a following way:
    // <%! for var: collection !%>
    // <% var %>
    // <%! endfor !%>
    public String replaceLoops(String text) throws MissingParameterException {
        String result = text;
        // Pattern for matching for expression
        Pattern begin = Pattern.compile("<%!\\h*for\\h*([\\w]+)\\h*:\\h*([\\w]+)\\h*!%>");
        // Pattern for matching endfor expression
        Pattern end = Pattern.compile("<%!\\h*endfor\\h*!%>");
        Matcher beginMatcher = begin.matcher(result);
        Matcher endMatcher = end.matcher(result);

        while(beginMatcher.find() && endMatcher.find()) {
            // For loop block is found
            // Get indexes of its start and end
            int blockBegin = beginMatcher.start() + beginMatcher.group().length() + 1;
            int blockEnd = endMatcher.start();

            // Get name of iterable variable and collection
            String iteratorName = beginMatcher.group(1);
            String collectionName = beginMatcher.group(2);

            if (!mCollections.containsKey(collectionName)) {
                throw new MissingParameterException("collectionName");
            }

            // Gets the whole block as a string
            String block = result.substring(blockBegin, blockEnd);

            HashMap<String, String> dict = new HashMap<>();
            String totalBlock = "";

            for(String var: mCollections.get(collectionName)) {
                dict.put(iteratorName, var);
                totalBlock += replaceVariablesWithDict(dict, block, true);
            }

            // Construct an result.
            // Note: use start of "for" and end of "endfor" statements as indexes so they
            // wouldn't appear in final text
            result = new StringBuilder(result)
                    .replace(beginMatcher.start(), endMatcher.end(), totalBlock)
                    .toString();
        }

        return result;
    }

    public String generate() throws MissingParameterException {
        return replaceVariables(replaceLoops(mTemplate));
    }
}
