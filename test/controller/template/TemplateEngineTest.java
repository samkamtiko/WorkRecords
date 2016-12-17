package controller.template;

import junit.framework.TestCase;
import view.template.MissingParameterException;
import view.template.TemplateEngine;

import java.util.ArrayList;

public class TemplateEngineTest extends TestCase {

    public void testTemplateWithoutVariables() {
        TemplateEngine eng = new TemplateEngine();
        String templateString = "<h1>Hello world</h1>";
        eng.setTemplate(templateString);
        try {
            assertEquals(templateString, eng.generate());
        } catch (MissingParameterException e) {
            assertFalse(true);
        }
    }

    public void testTemplateWithSingleVariable() {
        TemplateEngine eng = new TemplateEngine();
        eng.setTemplate("<h1>Hello, <% name %></h1>");
        eng.addVariable("name", "username");
        try {
            assertEquals("<h1>Hello, username</h1>", eng.generate());
        } catch (MissingParameterException e) {
            assertFalse(true);
        }
    }

    public void testTemplateVariableNotSet() {
        TemplateEngine eng = new TemplateEngine();
        eng.setTemplate("<h1>Hello, <% name %></h1>");

        try {
            eng.generate();
            assertTrue(false);
        } catch(MissingParameterException ex) {
            assertEquals("Parameter value for 'name' is missing", ex.getMessage());
        }
    }

    public void testTemplateManyVariables() {
        TemplateEngine eng = new TemplateEngine();
        eng.setTemplate("<h1> <%word%>, <%name%></h1>");
        eng.addVariable("word", "hello");
        eng.addVariable("name", "username");
        try {
            assertEquals("<h1> hello, username</h1>", eng.generate());
        } catch (MissingParameterException ex) {
            assertFalse(true);
        }
    }

    public void testTemplateSimpleLoop() {
        TemplateEngine eng = new TemplateEngine();
        eng.setTemplate("<%! for p : names !%>\n" +
                            "<h1> <% p %> </h1>\n" +
                        "<%! endfor !%>");
        ArrayList<String> names = new ArrayList<>();
        names.add("username1");
        names.add("username2");
        eng.addCollection("names", names);
        try {
            assertEquals("<h1> username1 </h1>\n" +
                         "<h1> username2 </h1>\n", eng.generate());
        } catch (MissingParameterException e) {
            assertFalse(true);
        }
    }

    public void testTemplateLoopWithVariable() {
        TemplateEngine eng = new TemplateEngine();
        eng.setTemplate("<%! for p : names !%>\n" +
                "<h1> <% p %> <% res %> </h1>\n" +
                "<%! endfor !%>");
        ArrayList<String> names = new ArrayList<>();
        names.add("username1");
        names.add("username2");
        eng.addCollection("names", names);
        eng.addVariable("res", "fine");
        try {
            assertEquals("<h1> username1 fine </h1>\n" +
                    "<h1> username2 fine </h1>\n", eng.generate());
        } catch (MissingParameterException e) {
            assertFalse(true);
        }
    }
}
