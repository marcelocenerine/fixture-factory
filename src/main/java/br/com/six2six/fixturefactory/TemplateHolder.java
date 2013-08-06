package br.com.six2six.fixturefactory;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

public class TemplateHolder {
	
	private static Logger logger = Logger.getLogger(TemplateHolder.class.getName());

	private Class<?> clazz;
	
	private Map<String, Rule> rules = new LinkedHashMap<String, Rule>();
	
	public TemplateHolder(Class<?> clazz) {
		this.clazz = clazz;
	}

	public TemplateHolder addTemplate(String label, Rule rule) {
		Rule old = rules.put(label, rule);
		if (old != null) logger.warning(String.format("Overriding existing template ['%s' -> %s] with a new value.", label, clazz.getName()));
		return this;
	}
	
	public ExtendedTemplateHolder addTemplate(String label) {
		return new ExtendedTemplateHolder(this, label);
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public Map<String, Rule> getRules() {
		return rules;
	}
}
