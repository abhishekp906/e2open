package com.e2open.Task;

import com.e2open.common.util.StringUtils;
import com.google.appengine.api.labs.modules.ModulesService;
import com.google.appengine.api.labs.modules.ModulesServiceFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by abhishek on 12/15/16.
 */
public class TaskQueryService extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response){
        processQueue(request, response);
    }

        public void processQueue(HttpServletRequest request, HttpServletResponse response) {
            try {

                if (StringUtils.isNullOrEmpty(request.getParameter("orderId")))
                    return;

                 String backendAddress = null;
                try {
                    ModulesService modulesService = ModulesServiceFactory.getModulesService();
                    backendAddress = String.valueOf(modulesService.getVersions("backend"));
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }

//                if(!StringUtils.isNullOrEmpty(backendAddress)){
//                    Queue queue = QueueFactory.getQueue("order-queue");
//                    queue.add(TaskOptions.Builder.withUrl("/orderId")
//                                    .header("Host", backendAddress)
//                                    .method(TaskOptions.Method.POST)
//                    );
//                }else{
//                    Queue queue = QueueFactory.getQueue("order-queue");
//                    queue.add(TaskOptions.Builder.withUrl("/orderId")
//                                    .method(TaskOptions.Method.POST)
//                    );
//                }

            } catch (Exception exe) {
                System.out.println("Exception while creating task in TaskQueueService");
            }
        }

}
