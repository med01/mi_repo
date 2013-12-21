package template;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import modelo.Item;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

public class ArticuloTemplate {
	private Configuration configuration = new Configuration();
	private Template template;
	
	public ArticuloTemplate() {
		try {
			configuration.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));
			configuration.setObjectWrapper(new DefaultObjectWrapper());
			configuration.setDefaultEncoding("UTF-8");
			configuration.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
			configuration.setIncompatibleImprovements(new Version(2, 3, 20));
			template = configuration.getTemplate("TotalInvestTemplate.ftl");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String aplyTemplate(Collection<Item> items, Float total) {
		List<Item> lista = new ArrayList<Item>(items);
		try {
			Map dataModel = new Hashtable();
			dataModel.put("root", lista);
			dataModel.put("total", total);
			StringWriter sw = new StringWriter();
			template.process(dataModel, sw);
			return sw.toString();
		} catch (IOException | TemplateException e) {
			e.printStackTrace();
		}
		return "";
	}
}
