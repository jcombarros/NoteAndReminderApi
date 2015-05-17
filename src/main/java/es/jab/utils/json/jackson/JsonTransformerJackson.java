package es.jab.utils.json.jackson;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.jab.utils.json.JsonTransformer;

public class JsonTransformerJackson implements JsonTransformer {
	
	private ObjectMapper objectMapper;
	
	public JsonTransformerJackson() {
		this.objectMapper = new ObjectMapper();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		this.objectMapper.setDateFormat(df);
	}

    @Override
    public String toJson(Object data) {
        try {
            return objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Object fromJson(String json, Class clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

}
