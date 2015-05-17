package es.jab.utils.json;

public interface JsonTransformer {
	
    String toJson(Object data);
    
    Object fromJson(String json, Class clazz);
    
}
