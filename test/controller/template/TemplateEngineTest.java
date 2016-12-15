package controller.template;

import junit.framework.TestCase;
import view.template.MissingParameterException;
import view.template.TemplateEngine;

public class TemplateEngineTest extends TestCase {

    public void testTemplateWithoutVariables() {
        TemplateEngine eng = new TemplateEngine();
        String templateString = "<h1>Hello world</h1>";
        eng.setTemplate(templateString);
        try {
            assertEquals(templateString, eng.transform());
        } catch (MissingParameterException e) {
            assertFalse(true);
        }
    }

    public void testTemplateWithSingleVariable() {
        TemplateEngine eng = new TemplateEngine();
        eng.setTemplate("<h1>Hello, <% name %></h1>");
        eng.addVariable("name", "username");
        try {
            assertEquals("<h1>Hello, username</h1>", eng.transform());
        } catch (MissingParameterException e) {
            assertFalse(true);
        }
    }

    public void testTemplateVariableNotSet() {
        TemplateEngine eng = new TemplateEngine();
        eng.setTemplate("<h1>Hello, <% name %></h1>");

        try {
            eng.transform();
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
            assertEquals("<h1> hello, username</h1>", eng.transform());
        } catch (MissingParameterException ex) {
            assertFalse(true);
        }
    }
}
