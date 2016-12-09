package http;

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
        mRespCodes.put(404, "Not found");
    }
    private final String mProto = "HTTP/1.1";
    private static HashMap<String, String> mMandatoryHeaders;

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

        // Empty line before data
        resp += "\n";

        resp += mData;
        System.out.println(resp);
        return resp.getBytes();
    }

}