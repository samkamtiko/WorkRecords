package controller;

import http.HttpRequest;
import http.HttpResponse;
import model.Task;
import view.StaticContent;
import view.template.MissingParameterException;
import view.template.TemplateEngine;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class TasksEditHandler extends GenericHandler {

    private ArrayList<String> prepareEditForm(Task task) {
        ArrayList<String> result = new ArrayList<>();

        result.add("<div>\n" +
                "<label>Name</label>\n" +
                "<input type=\"text\" name=\"name\" value=\"" + task.getName() + "\">\n" +
                "</div>\n");
        result.add("<div>\n" +
                "<label>Description</label>\n" +
                "<input type=\"text\" name=\"description\" value=\"" + task.getDescription() + "\">\n" +
                "</div>\n");
        return result;
    }

    @Override
    public HttpResponse handleGetRequest(HttpRequest request) {
        String id = request.getMatchedGroup(0);
        Task task = getTaskHandler().getById(id);
        if (task == null) {
            return HttpResponse.notFound();
        } else {
            HttpResponse resp = new HttpResponse(200);
            TemplateEngine eng = new TemplateEngine();
            try {
                eng.setTemplate(StaticContent.get("tasksedit.html"));
                eng.addVariable("id", task.getId());
                eng.addCollection("tasks", prepareEditForm(task));
                resp.setData(eng.generate());
                return resp;
            } catch (IOException e) {
                e.printStackTrace();
                return HttpResponse.notFound();
            } catch (MissingParameterException e) {
                e.printStackTrace();
                return HttpResponse.notFound();
            }
        }
    }

    @Override
    public HttpResponse handlePostRequest(HttpRequest request) {
        String id = request.getMatchedGroup(0);
        HashMap<String, String> params = HttpRequest.parseParams(request.getData());

        Task task = getTaskHandler().getById(id);
        if (task == null) {
            return HttpResponse.notFound();
        } else {
            task.setName(params.get("name"));
            task.setDescription(params.get("description"));
            getTaskHandler().updateTask(task);
            return HttpResponse.redirect("/tasks/" + id, "");
        }
    }
}
