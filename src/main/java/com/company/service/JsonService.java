package com.company.service;

import com.company.dto.JsonDTO;
import com.company.repository.JsonReopsitory;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class JsonService {

    private final JsonReopsitory jsonReopsitory;

    @Autowired
    public JsonService(JsonReopsitory jsonReopsitory) {
        this.jsonReopsitory = jsonReopsitory;
    }

    //TODO bu method berilgan API dagi malumotlarni read qilib keyin write qiladi DB ga
    public Boolean readAndWrite() {
        Request request = new Request.Builder().url("https://gorest.co.in/public/v1/posts").header("Accept", "application/json").header("Content-Type", "application/json").build();
        OkHttpClient okHttp = new OkHttpClient();
        try {
            Response response = okHttp.newCall(request).execute();
            String json = response.body().string();
            String[] strings = json.split(":", 14);

            JSONArray jsonArray = new JSONArray(strings[13]);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                JsonDTO jsonDTO = new JsonDTO();

                jsonDTO.setId(jsonObject.getInt("id"));
                jsonDTO.setUser_id(jsonObject.getInt("user_id"));
                jsonDTO.setTitle(jsonObject.getString("title"));
                jsonDTO.setBody(jsonObject.getString("body"));
                jsonReopsitory.save(jsonDTO);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    //TODO true
    public JsonDTO update(Integer id, JsonDTO dto) {
        JsonDTO entity = getById(id);
        if (entity == null) {
            throw new IllegalStateException("not found");
        }

        entity.setUser_id(dto.getUser_id());
        entity.setTitle(dto.getTitle());
        entity.setBody(dto.getBody());

        jsonReopsitory.save(entity);
        dto.setId(entity.getId());
        return entity;
    }

    //TODO true
    public JsonDTO create(JsonDTO dto) {
        Optional<JsonDTO> check = jsonReopsitory.findById(dto.getId());

        JsonDTO entity = new JsonDTO();
        entity.setId(dto.getId());
        entity.setUser_id(dto.getUser_id());
        entity.setTitle(dto.getTitle());
        entity.setBody(dto.getBody());

        jsonReopsitory.save(entity);
        dto.setId(entity.getId());
        return entity;
    }

    //TODO true
    public List<JsonDTO> getAll() {
        return jsonReopsitory.findAll();
    }

    //TODO true
    public String delete(Integer id) {
        JsonDTO optional = getById(id);
        if (optional == null) {
            throw new IllegalStateException("not found");
        }
        jsonReopsitory.delete(optional);
        return "Success";
    }

    //TODO true
    public JsonDTO getById(Integer id) {
        Optional<JsonDTO> optional = jsonReopsitory.findById(id);
        if (optional.isEmpty()) {
            throw new IllegalStateException("id not found");
        }
        return optional.get();
    }
}