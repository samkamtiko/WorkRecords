package controller;

import http.HttpRequest;
import http.HttpResponse;
import model.Task;
import view.StaticContent;
import view.template.MissingParameterException;
import view.template.TemplateEngine;

import java.io.IOException;

public class TasksIdHandler extends GenericHandler {

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
                eng.setTemplate(StaticContent.get("taskid.html"));
                eng.addVariable("id", task.getId());
                eng.addVariable("name", task.getName());
                eng.addVariable("description", task.getDescription());
                resp.setData(eng.generate());
            } catch (IOException e) {
                e.printStackTrace();
                return HttpResponse.notFound();
            } catch (MissingParameterException e) {
                e.printStackTrace();
                return HttpResponse.notFound();
            }
            return resp;
        }
    }
}
