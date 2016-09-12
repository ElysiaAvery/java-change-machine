import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/printout", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String totalCash = request.queryParams("total-cash");
      Float change = Float.parseFloat(totalCash);
      ChangeMachine changeMaker = new ChangeMachine();
      String newChange = changeMaker.makeChange(change);
      model.put("result", newChange);
      model.put("template", "templates/printout.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
