package com.world.alfs.service.supervisor;

import com.world.alfs.controller.supervisor.response.SupervisorLoginResponse;
import com.world.alfs.domain.supervisor.Supervisor;
import com.world.alfs.domain.supervisor.repository.SupervisorRepository;
import com.world.alfs.service.aws_s3.AwsS3Service;
import com.world.alfs.service.supervisor.dto.OcrFileDto;
import com.world.alfs.service.supervisor.dto.OcrUrlDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.*;

import java.io.*;
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
    private final AwsS3Service awsS3Service;

    @Value("${X_OCR_SECRET}")
    private String secretKey;
    @Value("${OCR_INVOKE_URL}")
    private String apiURL;
    private String boundary;


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
        List<String> parseData = null;
        try {
            URL url = new URL(apiURL);
            HttpURLConnection connection = createRequestHeader(url, false);
            createRequestBody(connection, dto);

            StringBuilder response = getResponseData(connection);
            parseData = jsonparse(response);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return parseData;
    }


    public List<String> getFileIngredient(OcrFileDto dto) {
        List<String> parseData = null;
        try {
            URL url = new URL(apiURL);
            HttpURLConnection connection = createRequestHeader(url, true);
            createRequestBody(connection, dto);

            StringBuilder response = getResponseData(connection);
            parseData = jsonparse(response);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return parseData;

    }

    public HttpURLConnection createRequestHeader(URL url, Boolean value) throws IOException {

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        if(Boolean.FALSE.equals(value)){
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setReadTimeout(30000);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json;");
            connection.setRequestProperty("X-OCR-SECRET", secretKey);
        }else{
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setReadTimeout(30000);
            connection.setRequestMethod("POST");
            boundary = "----" + UUID.randomUUID().toString().replaceAll("-", "");
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            connection.setRequestProperty("X-OCR-SECRET", secretKey);
        }

        return connection;
    }

    public void createRequestBody(HttpURLConnection connection, OcrUrlDto dto) throws IOException {
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

    }


    public void createRequestBody(HttpURLConnection connection, OcrFileDto dto) throws Exception {
        JSONObject json = new JSONObject();
        json.put("version", "v1");
        json.put("requestId", UUID.randomUUID().toString());
        json.put("timestamp", System.currentTimeMillis());
        json.put("lang", "ko");
        json.put("enableTableDetection", true);

        JSONObject image = new JSONObject();
        image.put("format", dto.getFormat());
        image.put("name", dto.getName());

        JSONArray images = new JSONArray();
        images.put(image);
        json.put("images", images);
        String postParams = json.toString();

        connection.connect();
        DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
        File file = awsS3Service.convert(dto.getFile())
                                .orElseThrow(() -> new Exception("error: MultipartFile -> File convert fail"));
        outputStream.write(json.toString().getBytes(StandardCharsets.UTF_8));

        writeMultiPart(outputStream, postParams, file, boundary);
        outputStream.flush();
        outputStream.close();

    }

    private static void writeMultiPart(OutputStream out, String jsonMessage, File file, String boundary) throws
            IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("--").append(boundary).append("\r\n");
        sb.append("Content-Disposition:form-data; name=\"message\"\r\n\r\n");
        sb.append(jsonMessage);
        sb.append("\r\n");

        out.write(sb.toString().getBytes("UTF-8"));
        out.flush();

        if (file != null && file.isFile()) {
            out.write(("--" + boundary + "\r\n").getBytes("UTF-8"));
            StringBuilder fileString = new StringBuilder();
            fileString.append("Content-Disposition:form-data; name=\"file\"; filename=");
            fileString.append("\"" + file.getName() + "\"\r\n");
            fileString.append("Content-Type: application/octet-stream\r\n\r\n");
            out.write(fileString.toString().getBytes("UTF-8"));
            out.flush();

            try (FileInputStream fis = new FileInputStream(file)) {
                byte[] buffer = new byte[8192];
                int count;
                while ((count = fis.read(buffer)) != -1) {
                    out.write(buffer, 0, count);
                }
                out.write("\r\n".getBytes());
            }

            out.write(("--" + boundary + "--\r\n").getBytes("UTF-8"));
        }
        out.flush();
    }


    public StringBuilder getResponseData(HttpURLConnection connection) throws IOException {
        BufferedReader reader = checkResponse(connection);
        String line;
        StringBuilder response = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        return response;
    }

    public BufferedReader checkResponse(HttpURLConnection connection) throws IOException {

        int responseCode = connection.getResponseCode();
        BufferedReader reader;

        if (responseCode == 200) {
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        }
        else {
            reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        }
        return reader;
    }

    public List<String> jsonparse(StringBuilder response) {
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

                    String[] words = inferText.split("[\\s,.\n]+"); // 공백, 줄바꿈문자, . 이랑 ,
                    for (String word : words) {
                        if (!word.isEmpty()) {
                            result.add(word);
                        }
                    }
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }


}