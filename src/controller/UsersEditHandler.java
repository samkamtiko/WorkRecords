package controller;

import http.HttpRequest;
import http.HttpResponse;
import model.User;
import model.UserGroup;
import view.StaticContent;
import view.template.MissingParameterException;
import view.template.TemplateEngine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class UsersEditHandler extends GenericHandler {

    private ArrayList<String> transformUserToText(User user) {
        ArrayList<String> result = new ArrayList<>();

        result.add("<div>\n" +
                "<label>Email</label>\n" +
                "<input type=\"email\" name=\"email\" value=\"" + user.getEmail() + "\">\n" +
                "</div>\n");
        result.add("<div>\n" +
                "<label>Password</label>\n" +
                "<input type=\"password\" name=\"password\" value=\"" + user.getPassword() + "\">\n" +
                "</div>\n");
        result.add("<div>\n" +
                "<label>Name</label>\n" +
                "<input type=\"text\" name=\"name\" value=\"" + user.getName() + "\">\n" +
                "</div>\n");

        String salary = user.getSalary().toString();
        result.add("<div>\n" +
                "<label>Salary</label>\n" +
                "<input type=\"text\" name=\"salary\" value=\"" + salary + "\">\n" +
                "</div>\n");
        // TODO: use a list instead
        String group = user.getGroup().toString();
        result.add("<div>\n" +
                "<label>Group</label>\n" +
                "<input type=\"text\" name=\"group\" value=\"" + group + "\">\n" +
                "</div>");
        return result;
    }

    @Override
    public HttpResponse handleGetRequest(HttpRequest request) {

        try {
            String id = request.getMatchedGroup(0);
            User user = getUserHandler().getById(id);

            ArrayList<String> blocks = transformUserToText(user);

            String template = StaticContent.get("usersedit.html");
            TemplateEngine eng = new TemplateEngine();
            eng.setTemplate(template);

            eng.addVariable("id", id);
            eng.addCollection("blocks", blocks);

            String rendered = eng.generate();
            HttpResponse resp = new HttpResponse(200);
            resp.setData(rendered);
            return resp;
        } catch (IOException e) {
            e.printStackTrace();
            return HttpResponse.notFound();
        } catch (MissingParameterException e) {
            e.printStackTrace();
            return HttpResponse.notFound();
        }
    }

    @Override
    public HttpResponse handlePostRequest(HttpRequest request) {
        HashMap<String, String> params = HttpRequest.parseParams(request.getData());
        String id = request.getMatchedGroup(0);

        User user = new User();
        user.setName(params.get("name"));
        user.setEmail(params.get("email"));
        user.setPassword(params.get("password"));
        // FIXME
        user.setGroup(UserGroup.PERFORMER);
        user.setSalary(Integer.parseInt(params.get("salary")));
        getUserHandler().updateUser(id, user);

        return HttpResponse.redirect("/users/" + id + "", "");
    }
}
