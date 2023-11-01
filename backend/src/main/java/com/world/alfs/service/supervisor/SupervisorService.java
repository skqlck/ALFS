package com.world.alfs.service.supervisor;

import com.world.alfs.controller.supervisor.response.SupervisorLoginResponse;
import com.world.alfs.domain.supervisor.Supervisor;
import com.world.alfs.domain.supervisor.repository.SupervisorRepository;
import com.world.alfs.service.supervisor.dto.OcrUrlDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.*;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class SupervisorService {

    private final SupervisorRepository supervisorRepository;

    @Value("${X_OCR_SECRET}")
    private String secretKey;
    @Value("${OCR_INVOKE_URL}")
    private String apiURL;


    public SupervisorLoginResponse loginSupervisor(String supervisorIdentifier, String supervisorPassword) {

        Optional<Supervisor> supervisor = Optional.ofNullable(supervisorRepository.findByIdentifierAndPassword(supervisorIdentifier, supervisorPassword));
        SupervisorLoginResponse supervisorLoginResponse = null;

        if(supervisor.isPresent()) {
            supervisorLoginResponse = SupervisorLoginResponse.builder()
                    .identifier(supervisor.get().getIdentifier())
                    .build();
            return supervisorLoginResponse;
        }else{
            // 예외 처리 : 유저를 찾을 수 없는 경우
            throw new NoSuchElementException("아이디와 비밀번호를 확인하세요");
        }
    }

    public List<String> getUrlIngredient(OcrUrlDto dto) {
        log.debug("시크릿키 "+secretKey);
        log.debug("url "+apiURL);

        List<String> parseData = null;
        try {
            URL url = new URL(apiURL);
            HttpURLConnection connection = createRequestHeader(url);
            createRequestBody(connection, dto);

            StringBuilder response = getResponseData(connection);
            log.debug("getUrlIngredient 응답 = "+response);
            parseData = jsonparse(response);


        } catch (Exception e) {
            log.debug(e.getMessage());
            e.printStackTrace();
        }

        log.debug("getUrlIngredient 끝");

        return parseData;
    }

    public HttpURLConnection createRequestHeader(URL url) throws IOException {
        log.debug("createRequestHeader 시작");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setUseCaches(false);
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setReadTimeout(5000);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json;");
        connection.setRequestProperty("X-OCR-SECRET", secretKey);

        log.debug("createRequestHeader 끝");
        return connection;
    }

    public void createRequestBody(HttpURLConnection connection, OcrUrlDto dto) throws IOException {
        log.debug("createRequestBody 시작");
        JSONObject json = new JSONObject();
        json.put("version", "v1");
        json.put("requestId", UUID.randomUUID().toString());
        json.put("timestamp", System.currentTimeMillis());
        json.put("lang", "ko");
        json.put("enableTableDetection", true);

        JSONObject image = new JSONObject();
        image.put("format", dto.getFormat());
        image.put("name", dto.getName());
        image.put("url", dto.getUrl());

        JSONArray images = new JSONArray();
        images.put(image);
        json.put("images", images);

        connection.connect();
        DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
        outputStream.write(json.toString().getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();

        log.debug("createRequestBody 끝");
    }


    public StringBuilder getResponseData(HttpURLConnection connection) throws IOException {
        log.debug("getResponseData 시작");
        BufferedReader reader = checkResponse(connection);
        String line;
        StringBuilder response = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        log.debug("getResponseData 끝");
        return response;
    }

    public BufferedReader checkResponse(HttpURLConnection connection) throws IOException {

        log.debug("checkResponse 시작");
        int responseCode = connection.getResponseCode();
        BufferedReader reader;

        if (responseCode == 200) {
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        }
        else {
            reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        }
        log.debug("checkResponse 끝");
        return reader;
    }

    public List<String> jsonparse(StringBuilder response) {
        log.debug("jsonparse 시작");
        List<String> result = new ArrayList<>();

        try{
            JSONObject jobj = new JSONObject(response.toString());
            JSONArray imagesArray = jobj.getJSONArray("images");
            if(imagesArray.length() > 0){
                JSONObject imageObject = imagesArray.getJSONObject(0);
                JSONArray fieldsArray = imageObject.getJSONArray("fields");

                for(int i=0;i<fieldsArray.length(); i++){
                    JSONObject fieldObject = fieldsArray.getJSONObject(i);
                    String inferText = fieldObject.optString("inferText");
                    log.debug(i+"번째 "+inferText);

                    String[] words = inferText.split("[\\s,.\n]+"); // 공백, 줄바꿈문자, . 이랑 ,
                    for (String word : words) {
                        if (!word.isEmpty()) {
                            log.debug(i + "번째 " + word);
                            result.add(word);
                        }
                    }
                }
            }
        } catch (Exception e){
            log.debug(e.getMessage());
            e.printStackTrace();
        }

        log.debug("jsonparse 끝");

        return result;
    }

}