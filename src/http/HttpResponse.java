package http;

import view.StaticContent;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class HttpResponse {

    private static HashMap<Integer, String> mRespCodes;
    private Integer mCode;

    static {
        mRespCodes = new LinkedHashMap<>();
        mRespCodes.put(200, "OK");
        mRespCodes.put(302, "Found");
        mRespCodes.put(404, "Not found");
    }
    private final String mProto = "HTTP/1.1";
    private static HashMap<String, String> mMandatoryHeaders;
    private HashMap<String,String> mOptionalHeaders;

    static {
        mMandatoryHeaders = new LinkedHashMap<>();
        mMandatoryHeaders.put("Date", ""); // Date is updated for each response
        mMandatoryHeaders.put("Content-Type", "text/html; charset=UTF-8");
        mMandatoryHeaders.put("Server", "Server/111");
    }

    private static String getCurrentDate() {
        String format = "EEE, d MMM yyyy hh:mm:ss z";
        SimpleDateFormat formatter = new SimpleDateFormat(format);

        return formatter.format(new Date());
    }

    private String mData = "";

    public HttpResponse(Integer code) {
        assert(mRespCodes.containsKey(code));
        mCode = code;
        mMandatoryHeaders.put("Date", getCurrentDate());
        mOptionalHeaders = new HashMap<>();
    }

    public void setData(String data) {
        if (data == null) {
            data = "";
        }
        mData = data;
    }

    public byte[] asByteArray() {
        // TODO: temporary response
        String resp = mProto + " " + mCode.toString() + " " + mRespCodes.get(mCode) + "\n";

        for(String key: mMandatoryHeaders.keySet()) {
            resp += key + ": " + mMandatoryHeaders.get(key) + "\n";
        }

        for(String key: mOptionalHeaders.keySet()) {
            resp += key + ": " + mOptionalHeaders.get(key) + "\n";
        }

        // Empty line before data
        resp += "\n";

        resp += mData;
        //System.out.println(resp);
        return resp.getBytes();
    }

    public Integer getReturnCode() {
        return mCode;
    }

    public void addOptionalHeader(String name, String value) {
        mOptionalHeaders.put(name, value);
    }

    public static HttpResponse notFound() {
        HttpResponse resp = new HttpResponse(404);
        try {
            resp.setData(StaticContent.get("notfound.html"));
        } catch (IOException e) {}
        return resp;
    }

    public static HttpResponse redirect(String route) {
        HttpResponse resp = new HttpResponse(302);
        resp.addOptionalHeader("Location", route);
        return resp;
    }

    public static HttpResponse responseStatic(String filename) throws IOException {
        HttpResponse resp = new HttpResponse(200);
        resp.setData(StaticContent.get(filename));
        return resp;
    }
}