package controller;

import http.HttpRequest;
import http.HttpResponse;
import model.Task;
import model.TaskHandler;
import view.StaticContent;
import view.template.MissingParameterException;
import view.template.TemplateEngine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class TasksListHandler extends GenericHandler {

    @Override
    public HttpResponse handleGetRequest(HttpRequest request) {
        TaskHandler handler = getTaskHandler();
        ArrayList<Task> tasks = handler.getAllTasks();

        ArrayList<String> taskNames = tasks.stream()
                .map(Task::getName)
                .collect(Collectors.toCollection(ArrayList::new));

        try {
            String listTemplate = StaticContent.get("listoftasks.html");
            TemplateEngine eng = new TemplateEngine();
            eng.setTemplate(listTemplate);
            eng.addCollection("tasks", taskNames);
            String renderedTemplate = eng.generate();
            HttpResponse resp = new HttpResponse(200);
            resp.setData(renderedTemplate);
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
